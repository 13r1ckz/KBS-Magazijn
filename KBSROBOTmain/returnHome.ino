void returnHome() {
  if (locatiey == 2) {
    analogWrite(6, 0);
    digitalWrite(7, LOW);
  }
  else if (locatiey < 2) {
    digitalWrite(7, LOW);
    analogWrite(6, 255);
    if (analogRead(A1) >= 700) {
      analogWrite(6, 0);
      digitalWrite(7, LOW);
    }
  }
  else if (locatiey > 2) {
    if (analogRead (A1) <= 600 ) {
      digitalWrite(7, HIGH);
      analogWrite(6, 255);
    } else if (analogRead (A1) >= 601 ) {
      digitalWrite(7, HIGH);
      analogWrite(6, 255);
      delay(500);
      locatiey --;
    }
  }
}
