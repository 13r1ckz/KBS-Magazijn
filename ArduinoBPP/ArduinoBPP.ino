String val = "";
boolean load = false;
boolean doos = false;
boolean side = false;
int loada;
void setup() {
  Serial.begin(9600);
  /* Port loop */
  for (int x = 0; x < 13; x++) {
    pinMode(x, OUTPUT);
  }
}

#define LEFT 0
#define RIGHT 255
#define SWITCH_MOTOR 5
#define INPUT_MOTOR 6

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

void loop() {
//  Serial.println(val);
//  Serial.println(load);
Serial.println(val);
  if (val == "E") {           //e shutdonw
    digitalWrite(4, LOW);
    digitalWrite(5, 0);
    digitalWrite(7, HIGH);
    analogWrite(6, 0);
    Serial.println("Shutdown");
    val = "";
  }
  if (val == "C") {           //e shutdonw
    Serial.println("clear");
    val = "";
  }
  if (analogRead (A1) >= 400 && load == false) {
    digitalWrite(7, HIGH);
    analogWrite(6, 255);
    //Serial.println("roll");
  }
  else if (analogRead (A1) <= 399 && load == false) {
    digitalWrite(7, HIGH);
    analogWrite(6, 255);
    //Serial.println("doll");
    load = true;
  }
  else if (load == true && loada == 0 && analogRead(A0) <= 400) {
    delay(800);
    digitalWrite(7, HIGH);
    analogWrite(6, 0);
    loada = 1;
    //Serial.println("pooll");
  }
  else if(val == "1" && load == true && analogRead(A0) <= 400 ){
    //Serial.println("lol");
    delay(2000);
    digitalWrite(4, LOW);
    digitalWrite(5, 0);
    Serial.println("R");
    val = "";
    load = false;
    loada = 0;
  }
  else if(val == "2" && load == true && analogRead(A0) <= 400){
    digitalWrite(4, LOW);
    digitalWrite(5, 255);
    //Serial.println("hihi");
    delay(2000);
    digitalWrite(4, LOW);
    digitalWrite(5, 0);
    Serial.println("L");
    val = "";
    load = false;
    loada = 0;
  }
  else if(val == "1" && load == false && analogRead(A0) <= 400){
    digitalWrite(4, LOW);
    digitalWrite(5, 255);
    //Serial.println("hihi");
    delay(2000);
    digitalWrite(4, LOW);
    digitalWrite(5, 0);
    Serial.println("L");
    val = "";
    load = false;
    loada = 0;
  }
  else if(val == "2" && load == false && analogRead(A0) <= 400){
    digitalWrite(4, LOW);
    digitalWrite(5, 255);
    //Serial.println("hihi");
    delay(2000);
    digitalWrite(4, LOW);
    digitalWrite(5, 0);
    Serial.println("L");
    val = "";
    load = false;
    loada = 0;
  }
  else if (val != 1 || val != 2 || val != "" || val != "E" || val != "C"){
    val = "";
  }
}
void serialEvent() {            // single time updater
  while (Serial.available()) {
    val = Serial.readString();
    //Serial.println("KEY HAS BEEN PRESSED");
//    else {                      // notining
//      digitalWrite(4, LOW);
//      digitalWrite(5, 0);
//    }
  }
}
