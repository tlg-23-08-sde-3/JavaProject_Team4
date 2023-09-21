package org.prison.silicon.app;

import javax.swing.*;

class WinTimer extends Thread {
    private String name;
    private int winTimeout;

    public WinTimer(int time) {
        setName("WinTimer");
        this.winTimeout = time;
    }

    @Override
    public void run() {
        super.run();
        try {
            Thread.sleep(winTimeout);
        } catch (InterruptedException ignored) {
        }
        this.winPopup();
    }

    private void winPopup() {
        int hours = winTimeout / 3_600_000;
        int minutes = (winTimeout % 3_600_000) / 60_000;
        int seconds = (winTimeout % 3_600_000) % 60_000 / 1000;
        String message = "You survived " + minutes + " minutes and " + seconds + " seconds without a riot!\nYou are a winner!!";
        String title = "You have won";

        JOptionPane.showMessageDialog(null,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE);
    }
}