/* Driver for newPing sensor
   This class is using the NewPing library (loaded in erpam.ino file)
   The class implements two methods:
   - getDistance() which returns distance from the obstacle in cm
   - getMedian() which returns the median from 5 sensor scans

*/
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
