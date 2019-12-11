package com.galaxy.sagittarius.xrobot.webcontroller.domain;

public class Device {
    /// <summary>
    /// 设备类型
    /// </summary>
    private String deviceType;

    /// <summary>
    /// 设备型号
    /// </summary>
    private String deviceModel;

    /// <summary>
    /// 设备编号
    /// </summary>string
    private int deviceNumber;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public int getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(int deviceNumber) {
        this.deviceNumber = deviceNumber;
    }
}
