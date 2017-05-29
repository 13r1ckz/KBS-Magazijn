void executeCommando() {
  digitalWrite(13, LOW);
  digitalWrite(4, LOW);
  analogWrite(5, 100);
  delay(350);
  digitalWrite(4, LOW);
  analogWrite(5, 0);
  delay(100);

  digitalWrite(7, LOW);
  analogWrite(6, 255);
  delay(1500);
  digitalWrite(7, LOW);
  analogWrite(6, 0);
  delay(100);

  digitalWrite(4, HIGH);
  analogWrite(5, 100);
  delay(335);
  digitalWrite(4, LOW);
  analogWrite(5, 0);
  delay(100);

  digitalWrite(7, HIGH);
  analogWrite(6, 255);
  delay(1250);
  digitalWrite(7, HIGH);
  analogWrite(6, 0);
  delay(100);
  digitalWrite(13, HIGH);
  Serial.println("p");
}
