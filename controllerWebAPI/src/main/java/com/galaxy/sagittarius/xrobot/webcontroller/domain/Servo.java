package com.galaxy.sagittarius.xrobot.webcontroller.domain;

public class Servo extends Device {
    /// <summary>
    /// 角度
    /// </summary>
    private int angle;

    public Servo() {
        this.setDeviceType("Servo");
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}
