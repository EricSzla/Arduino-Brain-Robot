/*
    Header file for the distanceSensor
    It is the base class for all the sensors, if you wish to use different library for the sensor than New Ping
    Then extend this class in your driver
*/
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
