package com.mutech.boarders.ball.helper.windows.listener;

import com.mutech.boarders.ball.helper.helper.BoardersBallHelper;
import com.mutech.boarders.ball.helper.windows.WindowsApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author H
 */
public class EndButtonListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        WindowsApp.setTipsText("游戏结束！");
        BoardersBallHelper.status = 3;
    }
}
