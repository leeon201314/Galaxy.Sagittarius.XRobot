#include <ArduinoJson.h>
#include <Wire.h>
#include <Adafruit_PWMServoDriver.h>

const int motor_pwm = 2 ;
const int motor_pin_a = 8 ;
const int motor_pin_b = 9 ;

////以这种方式调用，它使用默认地址0x40。
Adafruit_PWMServoDriver pwm = Adafruit_PWMServoDriver();

/*根据你的伺服制作，脉冲宽度最小和最大可能变化，你想要这些尽可能小大而不碰到
  硬停止，对于最大范围。你必须调整它们以匹配你的伺服系统！*/
#define SERVOMIN  150 // this is the 'minimum' pulse length count (out of 4096)
//这是“最小”脉冲长度计数（在4096）中
#define SERVOMAX  600 // this is the 'maximum' pulse length count (out of 4096)
//这是“最大”脉冲长度计数（在4096中）

void setup() {
  Serial.begin(9600);

  pinMode(motor_pwm, OUTPUT) ;
  pinMode(motor_pin_a, OUTPUT) ;
  pinMode(motor_pin_b, OUTPUT) ;

  digitalWrite(motor_pin_a, LOW) ;
  digitalWrite(motor_pin_b, LOW) ;
  analogWrite(motor_pwm, 0) ;

  pwm.begin();
  pwm.setPWMFreq(50);  // Analog servos run at ~60 Hz updates
  ////模拟伺服在50赫兹更新下运行
}

void loop() {
  DynamicJsonDocument doc(200);
  doc = receiveCommand();
  serializeJson(doc, Serial);

  String deviceType = doc["deviceType"];
  Serial.println(deviceType);

  if (deviceType == "Motor") {
    parseMotorCommand(doc);
  }
  else if (deviceType == "Servo") {
    parseServoCommand(doc);
  }
}

void parseServoCommand(DynamicJsonDocument doc) {

}

void parseMotorCommand(DynamicJsonDocument doc) {
  Serial.println("parseMotorCommand");

  int direction = (int)doc["direction"];
  int speed = (int)doc["speed"];

  if (direction == 1) {
    digitalWrite(motor_pin_a, HIGH) ;
    digitalWrite(motor_pin_b, LOW) ;
    analogWrite(motor_pwm, speed) ;
  }
  else if (direction == -1) {
    digitalWrite(motor_pin_a, LOW) ;
    digitalWrite(motor_pin_b, HIGH) ;
    analogWrite(motor_pwm, speed) ;
  }
  else {
    digitalWrite(motor_pin_a, LOW) ;
    digitalWrite(motor_pin_b, LOW) ;
    analogWrite(motor_pwm, 0) ;
  }
}

DynamicJsonDocument receiveCommand() {
  String command = "";
  DynamicJsonDocument doc(200);

  while (true) {
    if ( Serial.available())
    {
      char commandChar = char(Serial.read());
      delay(2);

      if (commandChar == '#') {
        command = "";
        continue;
      }

      if (command.length() > 200) {
        command = "";
        Serial.println("this command is to long!");
        continue;
      } else {
        command += commandChar;
      }

      DeserializationError error = deserializeJson(doc, command);

      if (!error) {
        return doc;
      }
    }
  }
}
