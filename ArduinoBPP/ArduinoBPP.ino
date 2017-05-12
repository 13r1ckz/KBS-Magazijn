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
  //Serial.println(analogRead(A0));
  if (val == "E") {           //e shutdonw
    digitalWrite(4, LOW);
    digitalWrite(5, 0);
    Serial.println("Shutdown");
    val = "";
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
    Serial.println("KEY HAS BEEN PRESSED");
//    else {                      // notining
//      digitalWrite(4, LOW);
//      digitalWrite(5, 0);
//    }
  }
}
