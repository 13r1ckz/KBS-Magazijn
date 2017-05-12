void executeCommandoz() {
  int x = analogRead (A1);
  if (x >= 800) {
    digitalWrite(4, LOW);
    analogWrite(5, 255);
    delay(150);
    digitalWrite(4, LOW);
    analogWrite(5, 0);
    delay(100);

    digitalWrite(7, LOW);
    analogWrite(6, 255);
    delay(1900);
    digitalWrite(7, LOW);
    analogWrite(6, 0);
    delay(100);
    
    digitalWrite(4, HIGH);
    analogWrite(5, 255);
    delay(150);
    digitalWrite(4, LOW);
    analogWrite(5, 0);
    delay(100);

    digitalWrite(7, HIGH);
    analogWrite(6, 255);
    delay(1600);
    digitalWrite(7, HIGH);
    analogWrite(6, 0);
    delay(100);

  }
}

