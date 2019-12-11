package com.galaxy.sagittarius.xrobot.webcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebcontrollerApplication {

    public static void main(String[] args) {

        ServialCommandHelper.Init();
        ServialCommandHelper.ReceiveData();

        SpringApplication.run(WebcontrollerApplication.class, args);
    }

}
