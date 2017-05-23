#define LEFT 0
#define RIGHT 255
#define SWITCH_MOTOR 5
#define INPUT_MOTOR 6

char val;
boolean isLoaded = false;
boolean isStopped = false;

void setup() {
  Serial.begin(9600);
  /* Port loop */
  for (int x = 0; x < 13; x++) {
    pinMode(x, OUTPUT);
  }
}

/**
 * Loop: Wait for an input.
 * Store the input in a value.
 * Move the motor until A0 picked something up.
 * Move the OTHER motor, corresponding to the direction of the input.
 * After two seconds, give a signal to the controller.
 */

//Turns on the motor and sets the direction.
void motorOn(int motor, int dir){
  if(motor == 5){
    digitalWrite(4,dir);
    digitalWrite(5,255);
  }
  if(motor == 6){
    digitalWrite(7,dir);
    digitalWrite(6,255);
  }
}

//Shuts down the motor.
void motorOff(int motor){
  digitalWrite(motor,0);
}

//Loop function.
void loop(){

  //Read a char (if it's available).
  if(Serial.available()){
    val = Serial.read();
  }
  
  //Shift STOP (E)
      if(val == 'E' && !isStopped){
        isStopped = true;
        Serial.println('O');
      }

  //Check if there's an object.
  if(analogRead(A1) < 200 && !isLoaded){
    delay(1000);
    motorOff(INPUT_MOTOR);
    //Send a signal X, that the simulator should return a package.
    Serial.print('X');
    isLoaded = true;
    delay(1000);
  }
  
  
  //Only if loaded
  if(isLoaded){
      //Shift LEFT
      if(val == '1'){
        motorOn(5,LEFT);
        delay(2000);
        isLoaded = false;
        Serial.println('L');
      }
      // Shift RIGHT
      if(val == '2'){
        motorOn(5,RIGHT);
        delay(2000);
        isLoaded = false;
        Serial.println('R');
      }
      motorOff(5);
    val = 0;
  }
  
  if(isStopped){
    motorOff(INPUT_MOTOR);
  }
  else{
    
  motorOn(INPUT_MOTOR,RIGHT);
  }
}
