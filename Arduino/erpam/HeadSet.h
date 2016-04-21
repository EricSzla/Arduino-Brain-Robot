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
