#include <stdlib.h>
#include <SoftwareSerial.h>
#include <NewPing.h>
#include "HeadSet.h"
#include "AiClass.h"
#include "NewPingSensor.h"
#define CLOSE 45
#define TOO_CLOSE 40
#define MAX_DISTANCE 200

//Front sensor pins
#define TRIGGER_PIN 6
#define ECHO_PIN 7
//Left sensor pins
#define LT_PIN 4
#define LE_PIN 5
//Right sensor pins
#define RT_PIN 2
#define RE_PIN 10

SoftwareSerial BTserial(0, 1);

using namespace ErpamBot;
using namespace std;

// Initialize the three sensors
NewPingSensor distanceSensor(TRIGGER_PIN, ECHO_PIN, MAX_DISTANCE);
NewPingSensor sensorLeft(LT_PIN, LE_PIN, MAX_DISTANCE);
NewPingSensor sensorRight(RT_PIN, RE_PIN, MAX_DISTANCE);
AiClass ai;
HeadSet hs;

String BluetoothData;
String acc = "";
int choice = 0; // Variable for menu
boolean go = false;
boolean brakeFlag = false;

// Variables used by the sensors
unsigned int cm = 0;
unsigned int median = 0; // Front sensor
unsigned int medianLeft = 0; // Left sensor
unsigned int medianRight = 0; // Right sensor

//Variables to be used only if ONLY ONE SENSOR is connected
int obstacleRight = 0;
int obstacleLeft = 0;

void setup() {
// put your setup code here, to run once:
  BTserial.begin(9600);
  Serial.begin(19200);
  //Setup Channel A
  pinMode(12, OUTPUT); //Initiates Motor Channel A pin
  pinMode(9, OUTPUT); //Initiates Brake Channel A pin

  //Setup Channel B
  pinMode(13, OUTPUT); //Initiates Motor Channel A pin
  pinMode(8, OUTPUT);  //Initiates Brake Channel A pin

  // Set brakes at start
  digitalWrite(9, HIGH);
  digitalWrite(8, HIGH);

  //Setup UltraSonic sensor pins
  pinMode(TRIGGER_PIN, OUTPUT);
  pinMode(ECHO_PIN, INPUT);
  pinMode(LT_PIN, OUTPUT);
  pinMode(LE_PIN, INPUT);
  pinMode(RT_PIN, OUTPUT);
  pinMode(RE_PIN, INPUT);
}

void loop() {
   switch (choice)
  {
    case 1:
      bluetoothcontrol(BluetoothData);
      break;
    case 2:
      aifxn();
      break;
    case 3:
      headSetfxn();
      break;
  }
}

void serialEvent()
{
  if (BTserial.available())
  {
    BluetoothData = BTserial.readStringUntil(',');

    if (BluetoothData.startsWith("1"))
    {
      BluetoothData.replace("1", "");
      choice = 1;
    } else if (BluetoothData.startsWith("2"))
    {
      choice = 2;
    } else if (BluetoothData.startsWith("3"))
    {
      BluetoothData.replace("3", "");
      acc = "";
      if (BluetoothData.startsWith("A"))
      {
        acc = BluetoothData;
        acc.replace("A", "");
      }
      choice = 3;
      BTserial.flush();
    }

    Serial.println(BluetoothData);
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

void headSetfxn()
{
  float nacc = acc.toFloat();

  //Serial.println(ncon);
  hs.go_forward(nacc);
}


