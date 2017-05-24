int locatiex = -1;
int locatiey = 1;
int doelx = 3;
int doely = 3;
boolean gehaaltx = true;
boolean gehaalty = true;
boolean heen = false;
int Speed = 250;
boolean first = true;
int aantal;
int x = 0;
boolean complete = false;

void setup() {
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(7, OUTPUT);
  pinMode(13, OUTPUT);
  pinMode(A0, INPUT);//low
  pinMode(A1, INPUT);//high
  Serial.begin(9600);
}
void loop() {
  Serial.println("l");
  digitalWrite(13 , LOW);
  digitalWrite(4, LOW);
  analogWrite(5, 200);
  delay(150);
  digitalWrite(4, LOW);
  analogWrite(5, 0);

  digitalWrite(7, HIGH);
  analogWrite(6, 255);
  delay(3100);
  digitalWrite(7, LOW);
  analogWrite(6, 0);
  for (int i = 0; i <= 3; i++) {
    digitalWrite(4, HIGH);
    analogWrite(5, 200);
    delay(100);
    digitalWrite(4, LOW);
    analogWrite(5, 0);
    delay(1000);
  }

  digitalWrite(4, LOW);
  analogWrite(5, 200);
  delay(250);
  digitalWrite(4, LOW);
  analogWrite(5, 0);
  delay(500);

  digitalWrite(7, LOW);
  analogWrite(6, 255);
  delay(3500);
  digitalWrite(7, HIGH);
  analogWrite(6, 0);
}
