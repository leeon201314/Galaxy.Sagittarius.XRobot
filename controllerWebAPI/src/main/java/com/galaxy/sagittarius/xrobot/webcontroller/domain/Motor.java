package com.galaxy.sagittarius.xrobot.webcontroller.domain;

public class Motor extends Device {
    /// <summary>
    /// 运行方向
    /// </summary>
    private boolean direction;

    /// <summary>
    /// 速度
    /// </summary>
    private int speed;

    public Motor() {
        this.setDeviceType("Motor");
    }

    public boolean getDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
