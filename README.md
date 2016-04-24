![title](https://cloud.githubusercontent.com/assets/15609881/12981660/82bf5158-d0d9-11e5-8ef1-ccd10aa02157.png)

### Members:
**Name:** Pamela Sabio</br>
**Student number:** C14484542 DT282/2</br>
**Contact:** c14484542@mydit.ie

**Name:** Eryk Szlachetka</br>
**Student number:** C14386641 DT282/2</br>
**Contact:** c14386641@mydit.ie

### Table Of Contents
* [Objective](#objective) </br>
* [Resources](#useful-resources)</br>
* [To do](#to-do)</br>
* <a href="#hardware">Section 1: Hardware</a> </br>
* <a href="#assembly">Section 2: Assembly</a></br>
* <a href ="#ac">Section 3: Arduino Code</a></br>
* <a href ="#jc">Section 4: Java Code</a></br>

### Objective:
- The objective is to build and program an <b>AI</b> robot which can avoid obstacles, by making decisions on its own. <br>
  The project will also implement <b>Bluetooth Control</b> using Processing IDE.</br>
  The robot can also be controlled using a head set that reads your <b>brainwaves</b></br>
  
### Useful resources:
**Arduino Library:** https://www.arduino.cc/en/Reference/HomePage</br>
**Getting Started with Arduino:** https://www.arduino.cc/en/Guide/HomePage</br>
**New Ping Library:** https://bitbucket.org/teckel12/arduino-new-ping/wiki/Home (For Distance Sensor)
       
### To do:
- [x] Order Hardware
- [x] Assembly 
- [x] Arduino: Program distance sensor
- [x] Arduino: Program AI 
- [x] Arduino: Program headSet
- [x] Arduino: Program blueTooth control
- [x] Make Arduino communicate with Processing using Bluetooth

<a id = "hardware"></a>
# Section 1: Hardware
**Arduino board (Arduino UNO ATMEGA328P R3 € 26.98**)   
- A small programmable computer which acts as the head of the robot, allowing us to control its behaviours.

![uno-r3-800](https://cloud.githubusercontent.com/assets/15609881/12980044/8daa8e68-d0d2-11e5-8463-61770e85892a.jpg)

**Distance sensor (HC-SR04 Ultrasonic Sensor € 4.49**)
- A sensor which measures the distance from the nearest objects by sending and recieving waves. This sensor allows us to program the robot to take appropriate action if there is an obstacle within the range.

![ultrasonic](https://cloud.githubusercontent.com/assets/15609881/12980095/c76dedf2-d0d2-11e5-9322-692a192e6f21.jpg)

**AWG Jumper Wires (HC-SR04 Ultrasonic Sensor € 4.95**)
- Wires are used to connect different components together.

![wires](http://www.elecfreaks.com/store/images/BBC_jumper_01.jpg)

**Bluetooth to serial slave**
- Small device which can be pluged into the robot and be programmed to controll the robot remotely e.g. using an android app.

![BT](http://www.micro4you.com/store/images/source/Bluetooth_Module_bb.png)

**Battery holder (€ 1.29**)
- Robots, like humans need energy in order to operate, they consume their energy from batteries. Battery box allow as to connect the batteries to the device.

![Battery Holder](http://d310a9hpolx59w.cloudfront.net/product_photos/503038/8-cell_original.JPG)

**Prototyping board (€ 5.95**)
- Prototyping board plays an unique part in planning stage.

![PBoard](https://upload.wikimedia.org/wikipedia/commons/7/73/400_points_breadboard.jpg)

**Motor controller (Arduino Motor Shield R3 € 44.00**)
- Controller which allows us to control the motors of the vehicle kit, neceserry in order to program/controll the speed and turnings of the robot.

![Motor Controller](https://www.arduino.cc/en/uploads/Main/MotorShield_R3_Front_450px.jpg)


**Vehicle kit (Magician Chassis € 44.00**)
- The base for the robot, which includes motors and wheels, supports the rest of hardware, compiled together forms a fully operational robot.

![vehicle](https://cdn.sparkfun.com//assets/parts/5/8/2/3/10825-04.jpg)

<a id="assembly"> </a>
# Section 2: Assembly

**Components**
- These are the components of the vehicle kit : the plastic chassis, wheels, motors, battery box and screws. It also comes with a manual and we followed that step-by-step in order to build this vehicle.

![parts](https://cloud.githubusercontent.com/assets/15609506/13899485/73b21350-ede7-11e5-8ac7-8cf5a039993f.jpg)

**Bottom Chassis**
- First we worked on assembling the bottom part of the vehicle which are the wheels, omni wheel and the motors. To setup the wheels we first connected the speed holder with wheel rotator of the motors and then attached it on the bottom chassis using the little plastice studs. After the motors are attached, we then push the wheels onto the speed holder. Next we mounted the omni wheel at the front (use the end 2 of the 4 holes in a line).

![bottom](https://cloud.githubusercontent.com/assets/15609506/13899503/f6204b4a-ede7-11e5-9e15-89518e6b7220.jpg)

**Top Chassis**
- Then we assembled the top part by connecting the top chassis with spacers and screws to the bottom chassis. Use the two holes at the corner in the straight end of the bottom chassis and then use the very last two T-shaped holes in the curved end of the bottom chassis. 

![top](https://cloud.githubusercontent.com/assets/15609506/13899520/379f2ea6-ede8-11e5-969b-c0c7c9706f19.jpg)

**Mounting hardwares onto the vehicle**
- We then mounted the other hardwares into the vehicles such as the arduino board, motor shield, breadboard, distance sensor and bluetooth sensor. The manufacturer suggests attaching the battery box to the bottom chassis, but to avoid the hassle of unscrewing the top to change the batteries, we have attached the box to the top platform. We just mounted the motor shield on the arduino board and connected all the wires to the motor shield. If you have a different motor shield, the wires should be connected to the arduino board. 

![board](https://cloud.githubusercontent.com/assets/15609506/13899533/8bf99234-ede8-11e5-8be0-846efd5e32b2.jpg)

**Connecting wires**
- The last step is connecting all the wires from the arduino or motor shield to the breadboard and the motors and then putting on the battery. The robot is now set for programming. 

![motor](https://cloud.githubusercontent.com/assets/15609506/13899540/b395d5fa-ede8-11e5-91e5-25da944ef7a0.jpg)

<a id ="ac"> </a>
# Section 3: Arduino Code
- In our Arduino we are going to have five classes stored in separate files with .h extensions ( headers ):
    - <a href ="#dsh">distanceSensor.h</a> (Base class for the sensors
    - <a href ="#npsh">NewPingSensor.h</a> (Class used to control UltraSonic sensors using newPing library)
    - <a href ="#aih">AiClass.h</a>  (Class that stores methods for Artificial Intelligence)
    - <a href="#hsh">HeadSet.h</a>  (Class used for controlling the robot with the HeadSet)
    - <a href="#erpammain">erpam</a> (The main class)
    
    
<a  id = "dsh"> </a>

## distanceSensor.h
- This class is used as a base class for sensor drivers, if you want to use another library for your sensor then all you have to do is write a driver for your sensor and  extend this class. </br>
  
  The class has a protected int variable which means only this class and the subclasses can use this variable
  
> protected:
          unsigned int maxDistance;
          
  The code for the base class:
  
```
  namespace ErpamBot
  {
  class SensorDriver
  {
    protected:
      unsigned int maxDistance;
    public:
      SensorDriver(unsigned int distance) : maxDistance(distance){}
      virtual unsigned int getDistance();
  
  };
  
  };
```
<a id = "npsh"> </a>

## NewPingSensor.h
- This class is used to control the UltraSonic sensors, the class implements two methods getDistance() and getMedian() <br>

### <b>Constructor</b>
The class has a private field of type NewPing called sensor, which is the variable we are going to refer to in order to receive data from the sensor.
The constructor accepts three parameters, the triggerPin, echoPin and the maxDistance, which are going to be passed in the main class.


```
private:
    NewPing sensor;
  public:
    NewPingSensor(int triggerPin, int echoPin, unsigned int maxDistance)
      : SensorDriver(maxDistance),
        sensor(triggerPin, echoPin, maxDistance)
    {
    }
```

The maxDistance is passed to the base class SensorDriver (distanceSensor.h)

> : SensorDriver(maxDistance),

The sensor variable is initialized with the pins and maxDistance

> sensor(triggerPin, echoPin, maxDistance)

### <b>getDistance()</b>
This method returns distance from the obstacle in <b>cm</b><br>

```
    virtual unsigned int getDistance()
    {
      int distance = sensor.ping_cm();

      if (distance <= 0 )
      {
        return maxDistance;
      } else
      {
        return distance;
      }
    }    
```   
    
Distance variable is storing the distance from the obstacle in cm using the method called from new ping library 'ping_cm()'
> int distance = sensor.ping_cm();

Then we are making sure there are no errors by checking if the distance returned is less than 0

```
if (distance <= 0 )
{
   return maxDistance;
}
```


### <b>getMedian()</b>
This method returns median from 5 sensors scans which are <b> NOT </b> in <b> cm </b>.

```
virtual unsigned int getMedian()
    {
      int median = sensor.ping_median(5);
      int cm = sensor.convert_cm(median);


      if (cm <= 0 )
      {
        return maxDistance;
      } else
      {
        return cm;
      }
      
    }
```

This time we are storing the median instead of just the distance and converting it to cm, the reason for that is that sometime the sensors may read a wrong value, consider the example:

```
Sensor Data:
cm: 30
cm: 29
cm: 28
cm: 0       << --- 
cm: 29
cm: 30
cm: 29
```

If the sensor will return a 0 when it shouldn't it can introduce chaos to the AI function, therefor if we get the median of the 5 values, 0 will lower the median, but the result will be still acceptable.</br>

### <b>Full code for the class</b>
```
/* Driver for newPing sensor */
#include "distanceSensor.h"
namespace ErpamBot
{
class NewPingSensor : public SensorDriver
{
  private:
    NewPing sensor;
  public:
    NewPingSensor(int triggerPin, int echoPin, unsigned int maxDistance)
      : SensorDriver(maxDistance),
        sensor(triggerPin, echoPin, maxDistance)
    {
    }

    virtual unsigned int getDistance()
    {

      int distance = sensor.ping_cm();

      if (distance <= 0 )
      {
        return maxDistance;
      } else
      {
        return distance;
      }
    }

    virtual unsigned int getMedian()
    {
      int median = sensor.ping_median(5);
      int cm = sensor.convert_cm(median);


      if (cm <= 0 )
      {
        return maxDistance;
      } else
      {
        return cm;
      }
      
    }
};
};
```

<a id = "aih"> </a>

## AiClass.h
- This class is used to control the motors, the class implements five methods: go_forward(), go_backward(), brake(), go_left() and go_right()._</br>
  Each method will set different speed to the motors in order to achieve its goal.
  The constructor is empty, as there is nothing to pass or initialize in the method.
  
### <b>go_forward()</b>
This method, first releases the brakes by writing ``LOW`` to pins ``9`` and ``8``. 
Then we are settings the direction to <b>forward</b> by writing ``HIGH`` to pins ``12`` and ``13``.
Finally we are writing the speed in this case ``250`` to pin ``11`` which is the right motor and ``200`` to pin ``3`` which is the left motor.
The speed depends from the motors we have, once of our motors runs slower therefore the difference when writing the speed.

```
void go_forward()
    {
      //brake
      digitalWrite(9, LOW); //left
      digitalWrite(8, LOW); //right

      //direction
      digitalWrite(12, HIGH); //left
      digitalWrite(13, HIGH); //right

      //speed
      analogWrite(11, 250); //right
      analogWrite(3, 200); //left

    }
```

### <b>go_backward()</b>
In this method we release the brakes in the same way as in method above, by writing ``LOW`` to pins ``9`` and ``8``. 
We are settings the direction to <b>backward</b> by writing ``LOW`` to pins ``12`` and ``13``.
Finally we are writing the speed in this case ``230`` for right motor and ``200`` for left motor.

```
void go_backward()
    {
      //direction
      digitalWrite(12, LOW); //left
      digitalWrite(13, LOW); //right

      //brake
      digitalWrite(9, LOW); //left
      digitalWrite(8, LOW); //right

      //speed
      analogWrite(3, 200); //left
      analogWrite(11, 230); //right
    }
```

### <b>brake()</b>
In this method we turn on the brakes by writing ``HIGH`` to pins ``9`` and ``8``. 
We are also setting the speed to ``0`` by writing it to pins ``3`` and ``11``.

```
void brake()
    {
      //speed
      analogWrite(3, 0); //left
      analogWrite(11, 0); // right

      //brake
      digitalWrite(9, HIGH); //left
      digitalWrite(8, HIGH); //right
    }
```

### <b>turn_left()</b>
In order to turn left we have to manipulate the direction of the two motors, we can do it by changing it to opposite direction by writing ``LOW`` to pin ``12`` which is the left motor, and  ``HIGH`` to pin ``13`` which is the right motor.
If the <b>right motor goes forward</b> and the <b>left motor goes backward</b>, the robot will turn left in place by 90 degrees. 
**Note that if we call this function the motor will keep turning unless we stop it.**

```
void turn_left()
    {
      //direction
      digitalWrite(12, LOW); //left
      digitalWrite(13, HIGH); //right

      //brake
      digitalWrite(9, LOW); //left
      digitalWrite(8, LOW); //right

      //speed
      analogWrite(3, 200); //left
      analogWrite(11, 220); //right
    }
```

### <b>turn_right()</b>
This method works almost the same way as turn_left() with the difference that we change the <b>left motor to go forward</b> and the <b>right motor to go backward.</b>
**Note that if we call this function the motor will keep turning unless we stop it.**

```
void turn_right()
    {
      //direction
      digitalWrite(12, HIGH); //left
      digitalWrite(13, LOW); //right

      //brake
      digitalWrite(9, LOW); //left
      digitalWrite(8, LOW); //right

      //speed
      analogWrite(3, 200); //left
      analogWrite(11, 220); //right
    }
```


### <b>Full code for the class</b>
So by now our code should look like this:

```
// Class for AI
namespace ErpamBot
{
class AiClass
{
  public:
    AiClass() {} // Constructor

    void go_forward()
    {
      //brake
      digitalWrite(9, LOW); //left
      digitalWrite(8, LOW); //right

      //direction
      digitalWrite(12, HIGH); //left
      digitalWrite(13, HIGH); //right

      //speed
      analogWrite(11, 250); //right
      analogWrite(3, 200); //left

    }

    void go_backward()
    {
      //direction
      digitalWrite(12, LOW); //left
      digitalWrite(13, LOW); //right

      //brake
      digitalWrite(9, LOW); //left
      digitalWrite(8, LOW); //right

      //speed
      analogWrite(3, 200); //left
      analogWrite(11, 230); //right
    }
    
    void brake()
    {
      //speed
      analogWrite(3, 0); //left
      analogWrite(11, 0); // right

      //brake
      digitalWrite(9, HIGH); //left
      digitalWrite(8, HIGH); //right
    }

    void turn_left()
    {
      //direction
      digitalWrite(12, LOW); //left
      digitalWrite(13, HIGH); //right

      //brake
      digitalWrite(9, LOW); //left
      digitalWrite(8, LOW); //right

      //speed
      analogWrite(3, 200); //left
      analogWrite(11, 220); //right
    }

    void turn_right()
    {
      //direction
      digitalWrite(12, HIGH); //left
      digitalWrite(13, LOW); //right

      //brake
      digitalWrite(9, LOW); //left
      digitalWrite(8, LOW); //right

      //speed
      analogWrite(3, 200); //left
      analogWrite(11, 220); //right
    }

};
};
```

<a id = "erpamain"> </a>

##erpam.ino

- This class is the main class and this is where we are going to declare and initialize global variables, call and manipulate
  the classes named above.

  This is also where we are opening the serial port in order to connect via Bluetooth.

###Including header files
These are the libraries that we need to include:
- stdlib.h which is the header of the general purpose library which includes functions involving memory allocation, process control, conversions and others.
- SoftwareSerial.h to allow serial communication on other digital pins of the Arduino which in our case is the Bluetooth sensor.
- NewPing.h which is the library used in order to use different functions for the Ultrasonic distance sensor.

In C++ we need to include the header files in the main when creating different classes thus, the HeadSet.h, AiClass.h and NewPingSensor.h are included.

```
#include <stdlib.h>
#include <SoftwareSerial.h>
#include <NewPing.h>
#include "HeadSet.h"
#include "AiClass.h"
#include "NewPingSensor.h"
```

###Constant variables
These are the constant variables that we are defining:

```
//Variables for AI
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
```
The first three variables are used for the AI function.

The following variables are used for initializing the three ultrasonic sensors that we are using. The values represent the pins that we are using in the arduino for each sensor.

###namespace, classes and library
Here we are using namespace, initializing the instance of the classes and the SoftwareSerial library.
```
using namespace ErpamBot;
using namespace std;

// Initialize the three sensors
NewPingSensor distanceSensor(TRIGGER_PIN, ECHO_PIN, MAX_DISTANCE);
NewPingSensor sensorLeft(LT_PIN, LE_PIN, MAX_DISTANCE);
NewPingSensor sensorRight(RT_PIN, RE_PIN, MAX_DISTANCE);

AiClass ai;
HeadSet hs;

SoftwareSerial BTserial(0, 1);
```
Namespaces are used to organize code into logical groups and to prevent name collisions that can occur especially when your code base includes multiple libraries.

When you make a call to using namespace namespace_name, all symbols in that namespace will become visible without adding the namespace prefix. A symbol may be for instance a function, class or a variable.

Because we are using the namespace ErPamBot in all our classes and the built in C++ library routines are kept in the standard namespace (that includes stuff like cout, cin, string, vector, map, etc.) and these tools are used so commonly, we want to call a using declarations in order to avoid typing ErPamBot:: and std:: prefixes constantly.
> using namespace ErPamBot;

> using namespace std;

When initializing the distance sensor we are passing three parameters: Echo Pin, Trigger Pin and Max Distance. The parameters that we are passing are the constant parameters that we have defined.
> NewPingSensor distanceSensor(TRIGGER_PIN, ECHO_PIN, MAX_DISTANCE);

The Arduino hardware has built-in support for serial communication on pins 0 and 1 hence why we are using these values when initializing the SoftwareSerial library.
> SoftwareSerial BTserial(0, 1);

###Global variables
These are all the global variable that we are using in the program. We are setting these variables as global because we want all the functions to be able to access these variables.
```
String BluetoothData; // Stores data sent via bluetooth
String acc = ""; // Stores data from headset's accelerometer
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
```

###setup()
We put code here in the setup because we want the code to run once at the start.

Here we are configuring the specified pins to behave either as an input or an output. We are using pinMode which takes two parameters:
> pinMode(pin, mode)

- pin: the number of the pin whose mode you wish to set

- mode: INPUT, OUTPUT, or INPUT_PULLUP. (see https://www.arduino.cc/en/Tutorial/DigitalPins for a more complete description of the functionality.)

```
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
```

If you take a look at the photo below of the Arduino Motor Shield R3 which is the motor shield that we are using for this robot, it indicates what pins we are supposed to use for Motor A and Motor B.

![motorshield_r3_front_450px](https://cloud.githubusercontent.com/assets/15609506/14764058/d2b7cc16-09a1-11e6-88df-bd80ea02f816.jpg)

As you can see, <b>DIR</b> stands for direction, <b>PWM</b> stands for Power Motor and there is also <b>BRAKE</b>. That is why we are using the values above in initiating Motor A and Motor B pins.

Therefore if we want to make the motors go forward, we are going to set pin ``12`` and ``13`` to ``HIGH`` and if we want to make it go backward the we are going to set it to ``LOW``. <i><b>Note that the we have to set the brakes (pins ``8`` and ``9``) to ``LOW`` every time we want the motors to move as we don't want to apply brakes.</i></b> We also need to set the speed (pins ``3`` and ``11``) according to how fast we want the motors to run. <b>Maximum speed is 250.</b>

When we run the program first, we want to apply the brakes as we do not want the robot to be running its motors unless we tell it to do so therefore we are going to set both pin ``8`` and pin ``9`` to ``HIGH``.

```
digitalWrite(9, HIGH);
digitalWrite(8, HIGH);
```

###loop()
After creating a setup() function, which initializes and sets the initial values, the loop() function does precisely what its name suggests, and loops consecutively, allowing your program to change and respond. Use it to actively control the Arduino board.
```
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
```
Here we are going to use a switch statement for calling different functions based on the variable <b>choice</b> which is depending on the data received via bluetooth through BTserial which I will be talking about in the next section.


###serialEvent()
This function is called <i>automatically</i> when data is available. This means that this function does not have to be called in main.

```
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
```

First we get the number of bytes (characters) available for reading from the serial port. This is data that's already arrived and stored in the serial receive buffer.
>  if (BTserial.available()){}

After receiving data we then read the incoming byte from the serial buffer into an array and terminates after ``,`` is detected. We are storing this in <b>BluetoothData</b>.
> BluetoothData = BTserial.readStringUntil(',');

We want to check whether the String that we have read starts with 1, 2 or 3 because this will determine the choice for the switch statement in the loop() function. In the example code below x is 1,2 or 3.
```
    if (BluetoothData.startsWith("x"))
    {
      BluetoothData.replace("x", "");
      choice = x;
    }
```
If BluetoothData starts with 3, then it means that we want to control the robot using the muse headset that reads brainwaves therefore we are storing the data of the accelerometer to the variable <b>acc</b>.
```
    else if (BluetoothData.startsWith("3"))
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
```

###bluetoothcontrol()
This function allows the robot to be controlled using W, A, S, D keys from the keyboard.

```
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
```

If BluetoothData is starting with L then it means the we are passing the speed of the left motor from processing so we are using pin ``3`` to set left motor speed. If it is starting with R then we are passing the speed of the right motor from processing so we are using pin ``11`` to set right motor speed. We are storing the speed in float variable called <b>sSpeed</b>
> float sSpeed = BluetoothData.toFloat();

If sSpeed > 0 then that means that we want the motor to go forward hence why we are setting pin ``12`` or ``13`` to ``HIGH``.

If sSpeed < 0 then that means that we want the motor to go backward hence why we are setting pin ``12``or ``13`` to ``LOW`` and we need to multiply sSpeed by -1 as we need a positive number.

As stated in the setup() section, we are going to set pin ``8`` or ``9`` to ``LOW`` if we want the motors to move.

###headSetfxn
This function just calls the go_forward() function from the HeadSet Class.
We are converting the accelerometer data that we stored in <b>acc</b> and we are passing it to go_forward() function in HeadSet Class. We are going to manipulate all the data that we are receiving in the HeadSet class therefore not much codes are needed here.
```
void headSetfxn()
{
  float nacc = acc.toFloat();
  hs.go_forward(nacc);
}
```

###aifxn()
This function is the function for Artificial Intelligence feature. We are going to make the robot move according to the data that the HC-05 distance sensors are receiving.
```
void aifxn()
{
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
```


First thing the robot does is to go forward and gets the medians of the data from all the sensors.
```
  ai.go_forward();
  median = distanceSensor.getMedian();
  medianLeft = sensorLeft.getMedian();
  medianRight = sensorRight.getMedian();
```

We don't want to sides of the robot scratching the wall or other obtacles when it is going forward thus we are going to make it move slightly if the distance to the wall is too near.

If medianLeft is too near then we are turning the robot to the right for 50ms, brake and then left for 5ms so that it can turn back to the direction that it was originally facing.

If medianRight is too near the we are turning the robot to the left for 50ms, brake, and then right for the same reason as above. Notice that we are using 10ms here and 5ms in the previous if statement. This is because the powers in the motors are different therefore the motor speeds are set differently.
```
 if (medianLeft < TOO_CLOSE / 5)
 {
   ai.turn_right();
   delay(50);
   ai.brake();
   ai.turn_left();
   delay(5);
 } else if (medianRight < TOO_CLOSE / 5)
 {
   ai.turn_left();
   delay(50);
   ai.brake();
   ai.turn_right();
   delay(10);
 }
```

Now we are dealing with the front sensor which is also going to involve the left and right sensors. If median is close enough then we brake the motors and set <b>brakeFlag</b> to <b>True</b> (here we are setting it to CLOSE * 2 because the motors are running fast so to ensure that the robot won't bump into the obstacle, were setting the stopping distance further).

if the obstacle is very close, then the robot most certainly will hit the obstacle when turning left or right. To avoid this we are going to make it go backward for 200ms.


```
  if (median < CLOSE * 2) // medianFront
  {
    ai.brake();
    delay(500);
    brakeFlag = true;
    if (median < TOO_CLOSE / 5)
    {
      ai.go_backward();
      delay(200);
      ai.brake();
    }
  }
```
If there are no obstacles on the way then we are setting <b>brakeFlag</b> to <b>False</b>. That means that the robot can keep on going forward as it won't execute the <b>if(brakeFlag)</b> statement.

If brakeFlag is set to true, the we are calling the <b>checkObstacles()</b> function which is a function in charge of making decisions after detecting an obstacle in front. After that we are setting the brakeFlag to false because we only want to call the checkObstacles() once and it is when the robot has detected an obstacle from the front sensor.

We are then calling the function recursively as we want the function to run all the time. The only time we want this to stop is when we turn the program off.
```
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
```

###checkObstacles
This function is used in the aifxn() and is responsible for the necessary actions of the robot based on the scanned data from the HC-05 distance sensors. <b><i>Note that this function is only called when the robot has detected an obstacle in front. We want to check the left and right sensors in order for the robot to decide which direction its taking next.</b></i>
```
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
```

First it gets the median distance from the three distance sensors: Front, Left and Right. The robot will use these variables in order to make decisions.
```
  median = distanceSensor.getMedian();
  medianLeft = sensorLeft.getMedian();
  medianRight = sensorRight.getMedian();
```
If there are obstacles <b>both</b> in the left and the right of the robot, then we want the robot to reverse as there are no other places to go. We then want to call checkObstacles() function recursively in order for the robot to keep on reversing until there's no more obstacle in either left or right.
```
  if (medianLeft < TOO_CLOSE && medianRight < TOO_CLOSE)
  {
    ai.go_backward();
    checkObstacles();
  }
```
Otherwise, if there is an obstacle <b>only</b> in the <b>left</b> then we are going to make the robot turn 90 degrees to the right. This is done by turning it to the right for 270ms.

If there is an obstacle <b>only</b> in the <b> right</b> then we are going to make the robot turn 90 degrees to the left. This is done by turning it to the left for 220ms (We are turning them with different times due to the motor power difference).
```
  else if (medianLeft < TOO_CLOSE && medianRight > TOO_CLOSE)
  {

    ai.turn_right();
    delay(270);
  } else if (medianLeft > TOO_CLOSE && medianRight < TOO_CLOSE)
  {

    ai.turn_left();
    delay(220);
  }
```
If <b>neither</b> of the left sensor and right sensor has detected any obstacles, then we are making the robot turn left or right <b>randomly</b> as we want the robot to make its own decisions.
```
  else if (medianLeft > TOO_CLOSE && medianRight > TOO_CLOSE)
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
```

<a id ="hsh"> </a>

## HeadSet.h
- This class is for manipulating the robot based on the data that we are receiving from the accelerometer of the headset. How the accelerometer works is it is giving values based on how you are tilting your head (if you tilt your head to the left it will return negative values, and if you tilt your head to the right it will return positive values).<b><i> The more you tilt your head, the bigger the value its gonna return for positive values, and the smaller the values for negative values.</b></i>

###go_forward()
This is the only method in this class. And in this class we are only changing the direction of the robot based on the accelerometer values the we are receiving. This value is passed as a parameter and we are calling it <b>acc</b>.
> void go_forward(float acc)

<b><i>Note that pin ``12`` and ``13`` is always set to ``HIGH`` and pin ``8`` and ``9`` is always set to ``LOW`` throughout the whole function because we want the motors to keep on running. We are only changing the direction by manipulating the values in pin ``3`` and pin ``11``.</b></i>

If acc is less than -150, then we want the robot to turn left so we are setting pin ``11`` to ``220`` and pin ``3`` to ``50``. This is because if we want to turn the robot to the left then the right motor should be faster than the left motor.
```
        if (acc < -150)
        {
          digitalWrite(12, HIGH);
          digitalWrite(13, HIGH);

          digitalWrite(9, LOW);
          digitalWrite(8, LOW);

          analogWrite(11, 220); // RIGHT
          analogWrite(3, 50); // LEFT
        }
```
If acc is more than 150, then we want the robot to turn right so we are setting pin ``11`` to ``50`` and pin ``3`` to ``200``. The difference in values in the pins when turning left and right is due to the difference in motor power.
```
        else if (acc > 150)
        {
          digitalWrite(12, HIGH);
          digitalWrite(9, LOW);
          digitalWrite(13, HIGH);
          digitalWrite(8, LOW);

          analogWrite(11, 50); // RIGHT
          analogWrite(3, 200); // LEFT
        }
```
If acc is more the -150 and less than 150 then that means that our head is in neutral position so we want the robot to go straight so we are setting pin ``11``to ``200`` and pin ``3`` to ``170``. The left motor is more powerful than the right motor so we want to set the left motor with lower speed so that they will have the same speed.
```
        else if (acc > -150 && acc < 150 && acc != 0)
        {
          digitalWrite(12, HIGH);
          digitalWrite(9, LOW);
          digitalWrite(13, HIGH);
          digitalWrite(8, LOW);

          analogWrite(11, 200);
          analogWrite(3, 170);
        }
```

###Full code for class
So by now, our code must look like this:
```
// Class for AI
namespace ErpamBot
{
class HeadSet
{

  private:

  public:
    HeadSet() {} // Constructor

    void go_forward(float acc)
    {
        if (acc < -150)
        {
          digitalWrite(12, HIGH);
          digitalWrite(13, HIGH);

          digitalWrite(9, LOW);
          digitalWrite(8, LOW);

          analogWrite(11, 220); // RIGHT
          analogWrite(3, 50); // LEFT
        } else if (acc > 150)
        {
          digitalWrite(12, HIGH);
          digitalWrite(9, LOW);
          digitalWrite(13, HIGH);
          digitalWrite(8, LOW);

          analogWrite(11, 50); // RIGHT
          analogWrite(3, 200); // LEFT
        } else if (acc > -150 && acc < 150 && acc != 0)
        {
          digitalWrite(12, HIGH);
          digitalWrite(9, LOW);
          digitalWrite(13, HIGH);
          digitalWrite(8, LOW);

          analogWrite(11, 200);
          analogWrite(3, 170);
        }
      }
};
};
```

<a id ="jc"> </a>

# Section 4: Java Code
- In order to run the java code we will need to download and import the following libraries into our IDE (The IDE that we use and recommend is <a href="https://www.jetbrains.com/idea/">IntelliJ</a>)
    * <a href="http://www.java2s.com/Code/Jar/p/Downloadprocessingcorejar.htm">Processing core.jar</a>(Standard library that comes with Processing IDE)
    * <a href="https://github.com/processing/processing-video">Processing video</a> (Standard library that comes with Processing IDE)
    * <a href="https://github.com/processing/processing/tree/master/java/libraries/serial">Processing serial</a> (Standard library that comes with Processing IDE)
    * <a href="http://www.sojamo.de/libraries/controlP5/">Processing controlP5</a>
    * <a href="http://www.sojamo.de/libraries/oscP5/">oscP5</a>
- In our Java Code we are going to have three classes stored in separate files with .java extensions:
    - <a href ="#javamain">Main.java </a>(The main class that manipulates other classes)
    - <a href ="#javabt">RemoteControl.java </a>(Class used to visualize the BT control)
    - <a href ="#javahs">HeadSet.java </a> (Class that visualizes the head set)
    
<a id="javamain"> </a>
## Main.java
- Main class is the class where we are going to establish our bluetooth connections with both arduino robot and the headset and initialize and manipulate classes.
- The methods we are going to use in main are as follow: 
  *  <a href="#settings">Settings</a> - Used to set the screen size
  *  <a href="#setup">Setup</a> - Used to initialize all that is to be executed only once at the start of the program
  *  <a href="#draw">Draw</a> - A loop that is executed 60 times a second, used to constantly update our program
  *  <a href="#mouse">MouseClicked</a> - Used to handle the event when mouse is clicked
  *  <a href="#javamenu">Menu</a> - Method to draw a menu for our program
  *  <a href="#keypressed">KeyPressed</a> - Used to handle the event when key is pressed 
  *  <a href="#turning">Turning</a> - Used to turn the robot while in bluetooth control mode
  *  <a href="#acceleration">Acceleration</a> - Used to set acceleration of the robot when in bluetooth control mode
  *  <a href="#oscevent">oscEvent</a> - Event handler for the headset which recieves the data and takes appropriate action
  
  If we have the libraries downloaded and added to our IDEA (<a href="https://www.jetbrains.com/help/idea/2016.1/configuring-module-dependencies-and-libraries.html?origin=old_help">How to add libraries in IntelliJ</a>) then the first thing we are going to do is import those libraries using ``import`` which will allow us to use or the methods or functions located in those libraries.
  
  ```
  package erpam;
  import processing.core.*;
  import processing.core.PImage;
  import processing.video.*;
  import processing.serial.*;
  import oscP5.*;
  ```
  
  ###Global Variables
  Next we are declaring the <b>global variables</b>, which means we can access those variables from every method in the package.
  
  The variable ``myPort`` of a type ``Serial`` which is a method imported from the ``processing.serial.*`` library, allow us to set up the bluetooth connection with the robot.</br>
  The variable ``BT`` is declared here as type ``RemoteControl``, which is one of our classes, so that means a variable BT is <b>an instance of a class RemoteControl</b>.
  
  ```
    Serial myPort;
    RemoteControl BT;
  
  ```
  
  Next, we are declaring variables for the menu, ``PImage`` is a type of variable that allow us to store the image for the menu.
  ``choice`` will be used to record which functionality the user chooses to use i.e. bluetooth control, headset control etc.
  
  ```
    PImage erpam;  // Menu image
    int choice = 0;         // User choice (menu)
 ```
 
 The next, global variables are the variables used for both left and right motors and the current gear.
 The variables for the motors are initialized to ``0`` as at the beging the motor speeds are 0.
 Also the ``currentGear`` is set to `N` which stands for <b>Neutral</b> this will change depending if user is going forward or backward, then the gear will change to either ``D`` which stands for <b>Drive</b> or ``R`` which stands for <b>Reverse</b>
 
 ```
    float rightspeed = 0;   // Right speed of the motor
    float leftspeed = 0;    // Left speed of the motor
    char currentGear = 'N';
 ```
 
 And the last variables we have to declare are the variables used for the headSet.</br>
 <b>**Note** this is an optional step and if you are following along and don't feel like buying the headSet then ignore this code.</b>
 
 In order to establish connection with the headset, we have to turn on the headSet then pair it with your PC/Laptop bluetooth, and then open your terminal and use the command  ``muse-io --device Muse --osc osc.udp://localhost:5000`` to connect to it. </br>
 <b>Please note the ``localhost:5000`` at the end, which is the port number 5000, if you are using different port, make sure to change it in the setup() method where we are initializing the connections</b></br>
 
 The variable ``oscp5`` of type ``OscP5`` is imported from oscP5 library, which allows us for the connection with the headSet.</br>
 The next two variables ``cVar`` and ``aVar`` are <b>float</b> variables which store the data sent from the headSet (concetration and accelerometer data).</br>
 The variable ``checkA`` and ``checkcVar`` are used to compare the differences in recieved data later on.</br>
 <b>Please note that the variable ``checkA`` is initialized to 800 at the start</b>.</br>
 The ``passVar`` of type ``String`` is the variable we are going to use to pass the data to Arduino, the content of that variable will depend on the data we recieve from the headSet.</br>
 The variable ``headSet`` of type ``HeadSet`` is an instance of the HeadSet class, whenever headSet variable is used that means it refers to that class.
 The last two variables ``pdir`` and ``sdir`` are the variables we are going to pass the the HeadSet method, will be explained in more detail when we get to the oscEvent method.
 
 ```
    // Use this code if HeadSet connected
    //  muse-io --device Muse --osc osc.udp://localhost:5000
    OscP5 oscp5;
    float cVar = 0; // Concetration data
    float aVar = 0; // Acceleroometer data
    float checkA = 800;
    float checkcVar = 0;
    String passVar = "";
    HeadSet headSet;
    int pdir = 0;
    int sdir = 0;
  ```
    
<a id="javabt"> </a>
## RemoteControl.java

<a id="javahs"> </a>
## HeadSet.java
