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

@Api(tags = "卡车转向舵机api")
@RestController
@RequestMapping("/carServo")
public class CarServoController {
    /**
     * 前进
     */
    @ApiOperation(value = "设置转向角度", notes = "设置转向角度，默认0号舵机是车辆舵机，-20到20度之间（转向角有点小）")
    @GetMapping("/setangle/{angle}")
    public JSONObject setAngle(@PathVariable(name = "angle") int angle) {
        Servo servo = new Servo();
        servo.setDeviceNumber(0); //默认0号舵机是车辆舵机
        int tempAngle = angle < -20 ? -20 : angle;
        tempAngle = angle > 20 ? 20 : angle;
        servo.setAngle(tempAngle);
        JSONObject commandJson = (JSONObject) JSONObject.toJSON(servo);
        ServialCommandHelper.SendData(commandJson.toJSONString());
        return commandJson;
    }
}
