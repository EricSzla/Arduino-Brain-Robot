#include <stdlib.h>
#include "HeadSet.h"
#include <SoftwareSerial.h>
SoftwareSerial BTSerial(0, 1);
String BluetoothData;

using namespace ErpamBot;
using namespace std;

HeadSet hs;


void setup() {
  // put your setup code here, to run once:
  BTSerial.begin(9600);
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
   switch (choice)
  {
    case 1:
      //bluetoothcontrol(BluetoothData);
      break;
    case 2:
      //      aifxn();
      break;
    case 3:
      headSetfxn();
      break;
  }
}

void bluetoothcontrol(String BluetoothData)
{
  if (BluetoothData.startsWith("L"))
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

  if (BluetoothData.startsWith("R")) {

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
