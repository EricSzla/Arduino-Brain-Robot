// Class for AI
namespace ErpamBot
{
class AiClass
{

  private:
    int leftSpeed = 0;
    int rightSpeed = 0;

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
      //direction
      //digitalWrite(12, HIGH); //left
      //digitalWrite(13, HIGH); //right

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
