package com.mutech.boarders.ball.helper.util;

/**
 * @author H
 * @date 2018-01-04
 */
public class AdbUtils {
    private static String adbPath = System.getProperty("user.dir").concat("/adb/adb.exe");

    private static String screenshotLocation = System.getProperty("user.dir").concat("/screenshot");

    private static Process process;

    /**
     * 是否成功截图
     */
    public static boolean isSeccessGetScreenshot = true;

    /**
     * 获取截屏
     */
    public static void getScreenshot() {
        try {
            Process process1 = Runtime.getRuntime().exec(adbPath + " shell screencap -p /sdcard/screenshot.png");
            process1.waitFor();
            Process process2 = Runtime.getRuntime().exec(adbPath + " pull /sdcard/screenshot.png " + screenshotLocation);
            process2.waitFor();
        } catch (Exception e) {
            isSeccessGetScreenshot = false;
            System.out.println("获取截屏失败,可能是文件目录错误!");
            e.printStackTrace();
        }
    }

    /**
     * 模拟触摸
     *
     * @param currentX 当前x
     * @param currentY 当前y
     * @param x        x
     * @param y        y
     */
    public static void touch(int currentX, int currentY, int x, int y) {
        long thisTime = System.currentTimeMillis();
        try {
            process = Runtime.getRuntime().exec(adbPath + " shell input swipe " + currentX + " " + currentY + " " + x + " " + y + " " + 60);
            process.waitFor();
        } catch (Exception e) {
            System.out.println("长按失败");
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - thisTime);

    }

    public static void main(String[] args) {
        getScreenshot();
    }
}
