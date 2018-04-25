package com.mutech.boarders.ball.helper;

import com.mutech.boarders.ball.helper.helper.BoardersBallHelper;
import com.mutech.boarders.ball.helper.windows.WindowsApp;

import java.awt.*;

/**
 * @author H
 */
public class BoardersBallHelperApplication {


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new WindowsApp();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        while (BoardersBallHelper.status != -1) {
            switch (BoardersBallHelper.status) {
                case 0:
                    BoardersBallHelper.init();
                    break;
                case 1:
                    BoardersBallHelper.test();
                    break;
                case 2:
                    BoardersBallHelper.start();
                    break;
                case 3:
                    BoardersBallHelper.end();
                    break;
                default:
                    break;
            }
        }
    }


}
