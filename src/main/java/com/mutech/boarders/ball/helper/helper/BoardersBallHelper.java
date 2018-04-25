package com.mutech.boarders.ball.helper.helper;

import com.mutech.boarders.ball.helper.pojo.RGB;
import com.mutech.boarders.ball.helper.pojo.Point;
import com.mutech.boarders.ball.helper.util.AdbUtils;
import com.mutech.boarders.ball.helper.windows.WindowsApp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * @author H
 */
public class BoardersBallHelper {
    public static int status = 0;

    private static int marginWidth = 0;

    private static int marginHeight = 0;

    public static void init() {

    }

    public static void test() {

    }

    public static void start() {
        WindowsApp.addTipsText(">>>>>>>>>>>>>截屏并上传本地...");
        AdbUtils.getScreenshot();
        WindowsApp.addTipsText(">>>>>>>>>>>>>读取本地图片...");
        BufferedImage bufferedImage = getImageFromLocal();
        WindowsApp.addTipsText(">>>>>>>>>>>>>找到打击目标最高点坐标...");
        Point point = getPoint(bufferedImage);
        AdbUtils.touch( point.getX(),point.getY(),point.getX(),point.getY());
        WindowsApp.addTipsText(">>>>>>>>>>>>>静候十秒...");
        sleep(10 * 1000);
    }

    public static void end() {

    }

    private static Point getPoint(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        // 去掉周边
        removemarginWidthRgb(bufferedImage, width, height);
        // 随便取点
        int tempWidth = width / 2;
        RGB marginRgb = getmarginWidthRGB(bufferedImage.getRGB(tempWidth, marginHeight + 5),
                bufferedImage.getRGB(tempWidth, marginHeight + 10), bufferedImage.getRGB(tempWidth, marginHeight + 15));
        for (int i = marginWidth; i < width - marginWidth; i++) {
            for (int j = marginHeight; j < height; j++) {
                if (!isLikePart(toRGB(bufferedImage.getRGB(i, j)), marginRgb)) {
                    return new Point(i, j);
                }
            }
        }

        try {
            ImageIO.write(bufferedImage, "JPEG", new File("test.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void removemarginWidthRgb(BufferedImage bufferedImage, int width, int height) {
        if (marginWidth == 0) {
            int middleHeight = height / 2;
            int middleWidth = width / 2;
            // 获取周边颜色
            RGB marginWidthRgb = getmarginWidthRGB(bufferedImage.getRGB(5, middleHeight),
                    bufferedImage.getRGB(10, middleHeight), bufferedImage.getRGB(15, middleHeight));
            for (int i = 0; i < middleWidth; i++) {
                RGB rgb = toRGB(bufferedImage.getRGB(i, middleHeight));
                boolean isLikePartOne = isLikePart(rgb, marginWidthRgb);
                if (!isLikePartOne) {
                    if (isLikePart(toRGB(bufferedImage.getRGB(i + 1, middleHeight)), marginWidthRgb)) {
                        marginWidth = i + 5;
                        break;
                    }
                }
            }
        }
        if (marginHeight == 0) {
            int indexHeight = height / 4;
            int middleWidth = width / 2;
            RGB marginHeightRgb = new RGB(250, 250, 250);
            for (int i = indexHeight; i > 0; i--) {
                RGB rgb = toRGB(bufferedImage.getRGB(middleWidth, i));
                boolean isLikePart = isLikePart(rgb, marginHeightRgb);
                if (isLikePart) {
                    marginHeight = i + 5;
                    break;
                }
            }
        }
    }

    private static boolean isLikePart(RGB rgb, RGB partRgb) {
        return Math.abs(rgb.getR() - partRgb.getR()) < 10
                && Math.abs(rgb.getG() - partRgb.getG()) < 10 && Math.abs(rgb.getB() - partRgb.getB()) < 10;
    }

    private static RGB getmarginWidthRGB(int rgb1, int rgb2, int rgb3) {
        RGB tempRgb1 = toRGB(rgb1);
        RGB tempRgb2 = toRGB(rgb2);
        RGB tempRgb3 = toRGB(rgb3);
        int r = (tempRgb1.getR() + tempRgb2.getR() + tempRgb3.getR()) / 3;
        int g = (tempRgb1.getG() + tempRgb2.getG() + tempRgb3.getG()) / 3;
        int b = (tempRgb1.getB() + tempRgb2.getB() + tempRgb3.getB()) / 3;
        return new RGB(r, g, b);
    }

    private static RGB toRGB(int rgb) {
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = rgb & 0xff;
        return new RGB(r, g, b);
    }

    private static BufferedImage getImageFromLocal() {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(System.getProperty("user.dir").concat("/screenshot/screenshot.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }

    private static void sleep(int i) {
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
