String val = "";
boolean load = false;
boolean doos = false;
boolean side = false;
void setup() {
  Serial.begin(9600);
  for (int x = 0; x < 13; x++) {    // port loop //i am lazy
    pinMode(x, OUTPUT);
  }
}

void loop() {
  //Serial.println(analogRead(A1));
  if (val == "E") {           //e shutdonw
    digitalWrite(4, LOW);
    digitalWrite(5, 0);
    Serial.println("Shutdown");
    val = "";
  }

  if (analogRead (A0) <= 350 && side == true) {
    digitalWrite(4, LOW);
    analogWrite(5, 255);
    delay(3000);
    digitalWrite(4, LOW);
    analogWrite(5, 0);
    load = false;
    side = false;
  }
  else if (analogRead (A0) <= 350 && side == false) {
    digitalWrite(4, HIGH);
    analogWrite(5, 255);
    delay(3000);
    digitalWrite(4, HIGH);
    analogWrite(5, 0);
    load = false;
    side = true;
  }
  if (analogRead (A1) >= 400 && load == false) {
    digitalWrite(7, HIGH);
    analogWrite(6, 255);
  }
  else if (analogRead (A1) <= 399 && load == false) {
    digitalWrite(7, HIGH);
    analogWrite(6, 255);
    load = true;
  }
  else if (load == true) {
    delay(800);
    digitalWrite(7, HIGH);
    analogWrite(6, 0);
  }


}

void serialEvent() {            // single time updater
  while (Serial.available()) {
    val = Serial.readString();
    //Serial.println(val);
    if (val == 4) {           //Richt action
      digitalWrite(4, HIGH);
      digitalWrite(5, 255);
      delay(1000);
      Serial.println("R");
      val = "";
      digitalWrite(4, LOW);
      digitalWrite(5, 0);
    }
    else if (val == 3) {      //L     action
      digitalWrite(4, LOW);
      digitalWrite(5, 255);
      delay(1000);
      val = "";
      Serial.write("R");
      digitalWrite(4, LOW);
      digitalWrite(5, 0);
    }
    else {                      // notining
      digitalWrite(4, LOW);
      digitalWrite(5, 0);
    }
  }
}
