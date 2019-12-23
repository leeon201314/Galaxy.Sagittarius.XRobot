package com.galaxy.sagittarius.xrobot.webcontroller.controllers;

import com.alibaba.fastjson.JSONObject;
import com.galaxy.sagittarius.xrobot.webcontroller.ServialCommandHelper;
import com.galaxy.sagittarius.xrobot.webcontroller.domain.Motor;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("引擎api")
@RestController
@RequestMapping("/carMotor")
public class CarMotorController {
    private static Motor motor;

    static {
        motor = new Motor();
        motor.setDeviceModel("370");
        motor.setDeviceNumber(1);
        motor.setDirection(0);
        motor.setSpeed(0);
    }

    /**
     * 前进
     */
    @ApiOperation(value = "前进", notes = "前进")
    @GetMapping("/go/{speed}")
    public JSONObject go(@PathVariable(name = "speed") int speed) {
        motor.setDirection(1);
        motor.setSpeed(speed);
        JSONObject commandJson = (JSONObject) JSONObject.toJSON(motor);
        ServialCommandHelper.SendData(commandJson.toJSONString());
        return commandJson;
    }

    /**
     * 后退
     */
    @ApiOperation(value = "后退", notes = "后退")
    @GetMapping("/back/{speed}")
    public JSONObject back(@PathVariable(name = "speed") int speed) {
        motor.setDirection(-1);
        motor.setSpeed(speed);
        JSONObject commandJson = (JSONObject) JSONObject.toJSON(motor);
        ServialCommandHelper.SendData(commandJson.toJSONString());
        return commandJson;
    }

    /**
     * 停止
     */
    @ApiOperation(value = "停止", notes = "停止")
    @GetMapping("/stop")
    public JSONObject stop() {
        motor.setDirection(0);
        motor.setSpeed(0);
        JSONObject commandJson = (JSONObject) JSONObject.toJSON(motor);
        ServialCommandHelper.SendData(commandJson.toJSONString());
        return commandJson;
    }
}
