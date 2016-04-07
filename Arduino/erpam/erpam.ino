#include <stdlib.h>
#include "HeadSet.h"
#include <SoftwareSerial.h>
SoftwareSerial BTSerial(0, 1);
String BluetoothData;

using namespace ErPpamBot;
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
