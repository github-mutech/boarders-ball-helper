package com.mutech.boarders.ball.helper.windows;

import com.mutech.boarders.ball.helper.windows.listener.EndButtonListener;
import com.mutech.boarders.ball.helper.windows.listener.StartButtonListener;
import com.mutech.boarders.ball.helper.windows.listener.TestButtonListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Color;

/**
 * @author H
 */
public class WindowsApp {

    private static JTextPane tips;

    public WindowsApp() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        JFrame frame = new JFrame();
        frame.setTitle("《最强跳一跳》辅助");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tips = new JTextPane();
        tips.setForeground(Color.WHITE);
        tips.setText("提示文本框");
        tips.setEditable(false);
        tips.setBackground(Color.BLACK);
        frame.getContentPane().add(tips, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        buttonsPanel.setLayout(new GridLayout(1, 0, 0, 0));

        JButton testButton = new JButton("连接测试");
        testButton.addActionListener(new TestButtonListener());
        buttonsPanel.add(testButton);

        JButton startButton = new JButton("开始游戏");
        startButton.addActionListener(new StartButtonListener());
        buttonsPanel.add(startButton);

        JButton endButton = new JButton("结束游戏");
        endButton.addActionListener(new EndButtonListener());
        buttonsPanel.add(endButton);

        frame.setVisible(true);
    }

    public static void setTipsText(String tipsText) {
        System.out.println(tipsText);
        WindowsApp.tips.setText(tipsText);
    }

    public static void addTipsText(String tipsText) {
        System.out.println(tipsText);
        WindowsApp.tips.setText(WindowsApp.tips.getText() + "\n" + tipsText);
    }
}
