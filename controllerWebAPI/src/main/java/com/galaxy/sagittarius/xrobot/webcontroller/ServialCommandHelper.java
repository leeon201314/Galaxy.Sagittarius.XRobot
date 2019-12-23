package com.galaxy.sagittarius.xrobot.webcontroller;

import com.pi4j.wiringpi.Serial;

public class ServialCommandHelper {
    public static int fd;

    public static void Init() {
        fd = Serial.serialOpen("/dev/ttyUSB0", 9600);
    }

    public static void ReceiveData() {
        System.out.println("begin receiveData!");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Receive();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start(); //不启动线程，立即执行。
    }

    public static void SendData(String data) {
        //String data = "#{\"sensor\":\"gps\",\"time\":1351824120,\"data\":[48.756080,2.302038]}";
        try {
            Serial.serialPuts(fd, "#" + data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Receive() {
        int dataAvail = Serial.serialDataAvail(fd);

        if (dataAvail > 0) {
            byte[] data = Serial.serialGetAvailableBytes(fd);
            String s = new String(data);
            System.out.print("接收数据：");
            System.out.print(s);
        }
    }
}
