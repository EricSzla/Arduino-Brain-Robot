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
* [Section 1: Hardware](#section-1:-hardware) </br>
* [Section 2: Assembly](#section-2:-assembly)</br>
* [Section 3: Arduino Code](#section-3:-arduino)</br>

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

# Section 2: Arduino Code
- In our Arduino we are going to have five classes stored in separate files with .h extensions ( headers ):
    - [erpam (The main class)](##erpam)
    - AiClass.h  (Class that stores methods for Artificial Intelligence
    - HeadSet.h  (Class used for controlling the robot with the HeadSet)
    - <a href = "#npsh">NewPingSensor.h (Class used to control UltraSonic sensors using newPing library)</a>
    - <a href="#dsh">distanceSensor.h (Base class for the sensors)</a>
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