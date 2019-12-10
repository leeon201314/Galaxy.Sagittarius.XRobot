#include <ArduinoJson.h>

int i = 0;

void setup() {
  Serial.begin(9600);
}

void loop() {
  DynamicJsonDocument doc(200);
  doc = receiveCommand();
  serializeJson(doc, Serial);
  i = i + 1;
  Serial.println(i);
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
