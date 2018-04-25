package com.mutech.boarders.ball.helper.windows.listener;

import com.mutech.boarders.ball.helper.helper.BoardersBallHelper;
import com.mutech.boarders.ball.helper.windows.WindowsApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author H
 */
public class StartButtonListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        WindowsApp.setTipsText("开始游戏");
        BoardersBallHelper.status = 2;
    }
}
