package com.galaxy.sagittarius.xrobot.webcontroller.controllers;

import com.alibaba.fastjson.JSONObject;
import com.galaxy.sagittarius.xrobot.webcontroller.ServialCommandHelper;
import com.galaxy.sagittarius.xrobot.webcontroller.domain.Servo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("舵机api")
@RestController
@RequestMapping("/servo")
public class ServoController {
    /**
     * 前进
     */
    @ApiOperation(value = "设置角度", notes = "设置角度")
    @GetMapping("/setangle/{deviceNumber}/{angle}")
    public JSONObject setAngle(@PathVariable(name = "deviceNumber") int deviceNumber, @PathVariable(name = "angle") int angle) {
        Servo servo = new Servo();
        servo.setDeviceNumber(deviceNumber);
        int tempAngle = angle < -90 ? -90 : angle;
        tempAngle = angle > 90 ? 90 : angle;
        servo.setAngle(tempAngle);
        JSONObject commandJson = (JSONObject) JSONObject.toJSON(servo);
        ServialCommandHelper.SendData(commandJson.toJSONString());
        return commandJson;
    }
}
