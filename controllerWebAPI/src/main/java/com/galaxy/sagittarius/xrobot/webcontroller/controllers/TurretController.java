package com.galaxy.sagittarius.xrobot.webcontroller.controllers;

import com.alibaba.fastjson.JSONObject;
import com.galaxy.sagittarius.xrobot.webcontroller.ServialCommandHelper;
import com.galaxy.sagittarius.xrobot.webcontroller.domain.Motor;
import com.galaxy.sagittarius.xrobot.webcontroller.domain.Servo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "炮塔控制api")
@RestController
@RequestMapping("/turret")
public class TurretController {
    private static Motor motor;

    static {
        motor = new Motor();
        motor.setDeviceModel("180");
        motor.setDeviceNumber(2);
        motor.setDirection(0);
        motor.setSpeed(0);
    }

    /**
     * 左右转向
     */
    @ApiOperation(value = "设置左右转动角度", notes = "设置转向角度，默认1号舵机是左右转动舵机，-90到90度之间")
    @GetMapping("/leftRight/{angle}")
    public JSONObject LeftRight(@PathVariable(name = "angle") int angle) {
        return SendServoCommand(1, angle);
    }

    /**
     * 上下转向
     */
    @ApiOperation(value = "设置上下转动角度", notes = "设置转向角度，默认2号舵机是左右转动舵机，-90到90度之间")
    @GetMapping("/upDown/{angle}")
    public JSONObject UpDown(@PathVariable(name = "angle") int angle) {
        return SendServoCommand(2, angle);
    }

    private JSONObject SendServoCommand(int deviceNumber, int angle) {
        Servo servo = new Servo();
        servo.setDeviceNumber(deviceNumber);
        int tempAngle = angle < -90 ? -90 : angle;
        tempAngle = angle > 90 ? 90 : angle;
        servo.setAngle(tempAngle);
        JSONObject commandJson = (JSONObject) JSONObject.toJSON(servo);
        ServialCommandHelper.SendData(commandJson.toJSONString());
        return commandJson;
    }

    /**
     * 开火
     */
    @ApiOperation(value = "开火射击", notes = "开火射击")
    @GetMapping("/fire/{range}")
    public JSONObject Fire(@PathVariable(name = "range") int range) {
        motor.setDirection(1);
        motor.setSpeed(range);
        JSONObject commandJson = (JSONObject) JSONObject.toJSON(motor);
        ServialCommandHelper.SendData(commandJson.toJSONString());
        return commandJson;
    }
}
