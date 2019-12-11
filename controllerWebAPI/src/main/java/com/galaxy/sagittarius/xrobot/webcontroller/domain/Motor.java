package com.galaxy.sagittarius.xrobot.webcontroller.domain;

public class Motor extends Device {
    /// <summary>
    /// 运行方向 -1:反方向 0：停止 1：正向
    /// </summary>
    private int direction;

    /// <summary>
    /// 速度
    /// </summary>
    private int speed;

    public Motor() {
        this.setDeviceType("Motor");
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
