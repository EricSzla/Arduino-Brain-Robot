#include <stdlib.h>

#include <SoftwareSerial.h>
SoftwareSerial Genotronex(10, 11);
String BluetoothData;

using namespace std;


void setup() {
  // put your setup code here, to run once:
  Genotronex.begin(9600);
  //Setup Channel A
  pinMode(12, OUTPUT); //Initiates Motor Channel A pin
  pinMode(9, OUTPUT); //Initiates Brake Channel A pin

  //Setup Channel B
  pinMode(13, OUTPUT); //Initiates Motor Channel A pin
  pinMode(8, OUTPUT);  //Initiates Brake Channel A pin

  digitalWrite(9, HIGH);
  digitalWrite(8, HIGH);
}

void loop() {
  if (Genotronex.available())
  {
    BluetoothData = Genotronex.readStringUntil(',');

    if (BluetoothData.startsWith("L"))// if number 1 pressed ....
    {
      BluetoothData.replace("L", "");
      float sSpeed = BluetoothData.toFloat();

      if (sSpeed > 0 )
      {
        digitalWrite(12, HIGH);

      } else if (sSpeed < 0)
      {
        digitalWrite(12, LOW);
        sSpeed *= -1;
      }

      digitalWrite(9, LOW);
      analogWrite(3, sSpeed);
    }

    if (BluetoothData.startsWith("R")) { // if number 0 pressed ....

      BluetoothData.replace("R", "");
      float sSpeed = BluetoothData.toFloat();

      if (sSpeed > 0 )
      {
        digitalWrite(13, HIGH);

      } else if (sSpeed < 0)
      {
        digitalWrite(13, LOW);
        sSpeed *= -1;
      }

      digitalWrite(8, LOW);
      analogWrite(11, sSpeed);
    }
  }
}
