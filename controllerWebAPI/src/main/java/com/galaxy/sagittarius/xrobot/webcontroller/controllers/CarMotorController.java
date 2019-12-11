package com.galaxy.sagittarius.xrobot.webcontroller.controllers;

import com.alibaba.fastjson.JSONObject;
import com.galaxy.sagittarius.xrobot.webcontroller.ServialCommandHelper;
import com.galaxy.sagittarius.xrobot.webcontroller.domain.Motor;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
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
        motor.setSpeed(30);
    }

    /**
     * 前进
     */
    @ApiOperation(value = "前进", notes = "前进")
    @ApiResponses({
            @ApiResponse(code = 500, message = "服务器内部错误"),
            @ApiResponse(code = 404, message = "找不到请求路径")
    })
    @GetMapping("/go")
    public JSONObject go() {
        motor.setDirection(1);
        JSONObject commandJson = (JSONObject) JSONObject.toJSON(motor);
        ServialCommandHelper.SendData(commandJson.toJSONString());
        return commandJson;
    }

    /**
     * 后退
     */
    @GetMapping("/back")
    public JSONObject back() {
        motor.setDirection(-1);
        JSONObject commandJson = (JSONObject) JSONObject.toJSON(motor);
        ServialCommandHelper.SendData(commandJson.toJSONString());
        return commandJson;
    }

    /**
     * 停止
     */
    @GetMapping("/stop")
    public JSONObject stop() {
        motor.setDirection(0);
        JSONObject commandJson = (JSONObject) JSONObject.toJSON(motor);
        ServialCommandHelper.SendData(commandJson.toJSONString());
        return commandJson;
    }
}
