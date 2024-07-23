package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class myTimerLoggings {
    private static final Logger debugLog = LogManager.getLogger("DebugLog");
    private static final Logger errorLog = LogManager.getLogger("ErrorLog");
    private static final Logger infoLog = LogManager.getLogger("InfoLog");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final int MAX_COUNT = 10;

    public static void main(String[] args) throws InterruptedException {

        Thread debugThread = new Thread(new DebugLogTask(),"DEBUG THREAD");
        Thread infoThread = new Thread(new InfoLogTask(), "INFO THREAD");
        Thread errorThread = new Thread(new ErrorLogTask(), "ERROR THREAD");

        debugThread.start();
        infoThread.start();
        errorThread.start();

        debugThread.join();
        infoThread.join();
        errorThread.join();


        System.out.println("FINISHED...");
    }

    static class DebugLogTask implements Runnable {
        private int count = 0;

        @Override
        public void run() {
            LocalDateTime time = LocalDateTime.now();
            while (count < MAX_COUNT) {
                String formattedTime = time.format(formatter);
                debugLog.debug(formattedTime);
                time = time.plusSeconds(1);
                count++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    debugLog.error("Debug thread interrupted", e);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    static class InfoLogTask implements Runnable {
        private int count = 0;

        @Override
        public void run() {
            LocalDateTime time = LocalDateTime.now();
            while (count < MAX_COUNT) {
                String formattedTime = time.format(formatter);
                infoLog.info(formattedTime);
                time = time.plusMinutes(1);
                count++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    infoLog.error("Info thread interrupted", e);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    static class ErrorLogTask implements Runnable {
        private int count = 0;

        @Override
        public void run() {
            LocalDateTime time = LocalDateTime.now();
            while (count < MAX_COUNT) {
                String formattedTime = time.format(formatter);
                errorLog.error(formattedTime);
                time = time.plusHours(1);
                count++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    errorLog.error("Error thread interrupted", e);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}