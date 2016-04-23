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
    - <a href="#erpamain">erpam</a> (The main class)
    
    
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

<a id = "npsh"> </a>

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

<a id = "erpammain"> </a>

##erpam.ino

- This class is the main class and this is where we are going to declare and initialize global variables, call and manipulate
  the classes named above.

  This is also where we are opening the serial port in order to connect via Bluetooth.

###Including header files
These are the libraries that we need to include:
- <stdlib.h> which is the header of the general purpose library which includes functions involving memory allocation, process control, conversions and others.
- <SoftwareSerial.h> to allow serial communication on other digital pins of the Arduino which in our case is the Bluetooth sensor.
- <NewPing.h> which is the library used in order to use different functions for the Ultrasonic distance sensor.

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
  BTserial.begin(9600);
  Serial.begin(19200);
  //Setup Channel A
  pinMode(12, OUTPUT); //Initiates Motor Channel A pin
  pinMode(9, OUTPUT); //Initiates Brake Channel A pin

  //Setup Channel B
  pinMode(13, OUTPUT); //Initiates Motor Channel B pin
  pinMode(8, OUTPUT);  //Initiates Brake Channel B pin

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


<a id ="hsh"> </a>

## HeadSet.h

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

<a id="javabt"> </a>
## RemoteControl.java

<a id="javahs"> </a>
## HeadSet.java