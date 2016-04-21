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
  hs.go_forward(nacc);
}

void test()
{
  medianLeft = sensorLeft.getMedian();
  medianRight = sensorRight.getMedian();

  if (medianRight < CLOSE)
  {
    ai.go_forward();
  }else
  {
    ai.brake();
  }


}
void aifxn()
{
  /*if (!go)
  {
    ai.go_forward();
    go = true;
    median = distanceSensor.getMedian();
    medianLeft = sensorLeft.getMedian();
    medianRight = sensorRight.getMedian();

  }*/
  ai.go_forward();
  median = distanceSensor.getMedian();
  medianLeft = sensorLeft.getMedian();
  medianRight = sensorRight.getMedian();

   if(medianLeft < TOO_CLOSE / 5)
    {
      ai.turn_right();
      delay(50);
      ai.brake();
      ai.turn_left();
      delay(5);
    }else if (medianRight < TOO_CLOSE / 5)
    {
      ai.turn_left();
      delay(50);
      ai.brake();
      ai.turn_right();
      delay(10);
    }


  if (median < CLOSE * 2) // medianFront
  {
    ai.brake();
    delay(500);
    brakeFlag = true;
    if(median < TOO_CLOSE/5)
    {
      ai.go_backward();
      delay(200);
      ai.brake();
    }
  }
  else
  {
    brakeFlag = false;
  }

  if (brakeFlag)
  {
    checkObstacles();
    brakeFlag = false;
    go = false;
  }

  aifxn();
}

void checkObstacles()
{
  median = distanceSensor.getMedian();
  medianLeft = sensorLeft.getMedian();
  medianRight = sensorRight.getMedian();

  if (medianLeft < TOO_CLOSE && medianRight < TOO_CLOSE)
  {
    ai.go_backward();
    checkObstacles();
  } else if (medianLeft < TOO_CLOSE && medianRight > TOO_CLOSE)
  {

    ai.turn_right();
    delay(270);
  } else if (medianLeft > TOO_CLOSE && medianRight < TOO_CLOSE)
  {

    ai.turn_left();
    delay(220);
  } else if (medianLeft > TOO_CLOSE && medianRight > TOO_CLOSE)
  {
    int r = random(2, 10);
    if (r % 2 == 0)
    {
      ai.turn_left();
      delay(220);
    } else if (r % 2 != 0)
    {
      ai.turn_right();
      delay(270);
    }
  }
}



// CODE TO WORK WITH ONE ULTRASONIC FRONT SENSOR ONLY
// The method will go forward until hits an obstacle
// When obstacle hit then turn left and see if there is obstacle there
// if there is none continue straight, if there is an obstacle
// turn right in order to come back to the same position
// then turn right again and see if there is obstacle on the right
// if there is none continue straight
// if there is an obstacle then turn left back to the initial position
// and start reversing and use recursive algorithm in order to check
// when the obstacles on the left or right side are gone
/*
  void aifxn()
  {
  if (!go)
  {
    ai.go_forward();
    go = true;
    median = distanceSensor.getMedian();
  }

  cm = distanceSensor.getMedian();

  if (cm < TOO_CLOSE)
  {
    ai.brake();
    delay(500);
    brakeFlag = true;
  }
  else
  {
    brakeFlag = false;
    go = false;
  }

  if (brakeFlag)
  {
    checkObstacles();
    brakeFlag = false;
  }
  }

  void checkObstacles()
  {
  checkObstacleLeft();

  if (obstacleLeft == true)
  {
    checkObstacleRight();
    delay(500);
    if (obstacleRight == true)
    {
      ai.go_backward();
      delay(1000); // Wait a second in order to brake
      ai.brake();
      checkObstacles();
    } else
    {
      ai.go_forward();
    }
  } else
  {
    //brakeFlag = false;
    //go = false;
  }
  }

  void checkObstacleLeft()
  {
  ai.turn_left();
  delay(220);
  ai.brake();
  delay(500);
  cm = distanceSensor.getMedian();

  if (cm < TOO_CLOSE)
  {
    ai.turn_right();
    delay(240);
    ai.brake();
    delay(500);
    obstacleLeft = true;
  }
  else
  {
    obstacleLeft = false;
  }
  }

  void checkObstacleRight()
  {
  ai.turn_right();
  delay(240);
  ai.brake();
  delay(500);

  cm = distanceSensor.getMedian();

  if (cm < TOO_CLOSE)
  {
    ai.turn_left();
    delay(230);
    ai.brake();
    obstacleRight = true;
  }
  else
  {
    obstacleRight = false;
  }
  }*/