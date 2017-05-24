int locatiex = 0;
int locatiey = 3;
int doelx = 1;
int doely = 4;
boolean gehaaltx = false;
boolean gehaalty = false;
boolean heen = true;
int Speed = 250;
boolean first = true;
int aantal;
int x = 1;
boolean complete = false;
int snelheidv = 220;
int snelheida = 180;


void setup() {
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(7, OUTPUT);
  pinMode(13, OUTPUT);
  pinMode(A0, INPUT);//LOW
  pinMode(A1, INPUT);//HIGH
  Serial.begin(9600);
}

void loop() {
  int Xas[] = {1, 3, 5};
  int Yas[] = {1, 3, 5};
  Serial.println(locatiex);
  Serial.println(analogRead(A0));
  digitalWrite(13, HIGH);

  if (locatiex < doelx) {
    heen = true;
    gehaaltx = false;
    gehaalty = false;
  } else if (locatiex > doelx) {
    heen = false;
    gehaaltx = false;
    gehaalty = false;
  }
  //XAS motion
  if (locatiex == doelx && heen == true) {
    if (analogRead(A0) <= 200) {
      gehaaltx = true;
      digitalWrite(4, HIGH);
      analogWrite(5, 0);
    } else {
      digitalWrite(4, LOW);
      analogWrite(5, 180);
    }
  } else if (locatiex == doelx && heen == false) {
    if (analogRead(A0) <= 200) {
      gehaaltx = true;
      digitalWrite(4, HIGH);
      analogWrite(5, 0);

    } else {
      digitalWrite(4, HIGH);
      analogWrite(5, 150);
    }
  } else if (locatiex <= doelx) {
    digitalWrite (13, HIGH);
    if (analogRead(A0) <= 900 && first == true) {
      digitalWrite(4, HIGH);
      analogWrite(5, 255);
      delay(200);
      digitalWrite(4, HIGH);
      analogWrite(5, 0);
      first = false;
    } else if (analogRead(A0) <= 700 && gehaaltx == false && first == false) {
      digitalWrite(4, HIGH);
      analogWrite(5, 0);
      delay(150);
      digitalWrite(4, HIGH);
      analogWrite(5, snelheidv);
      delay(150);
      locatiex ++;
    } else if (analogRead(A0) >= 701 && gehaaltx == false && first == false) {

      digitalWrite(4, HIGH);
      analogWrite(5, snelheidv);
    }
  } else if (locatiex >= doelx) {
    digitalWrite (13, HIGH);
    if (analogRead(A0) <= 900 && first == true) {
      digitalWrite(4, LOW);
      analogWrite(5, 255);
      delay(200);
      digitalWrite(4, HIGH);
      analogWrite(5, 0);
      first = false;
    } else if (analogRead(A0) <= 200 && gehaaltx == false && first == false) {
      digitalWrite(4, HIGH);
      analogWrite(5, 0);
      delay(150);
      digitalWrite(4, LOW);
      analogWrite(5, snelheida);
      delay(150);
      locatiex --;
    } else if (analogRead(A0) >= 201 && gehaaltx == false && first == false) {
      digitalWrite(4, LOW);
      analogWrite(5, snelheida);
    }
  } else {
    digitalWrite(4, LOW);
    analogWrite(5, 0);


  }
}
