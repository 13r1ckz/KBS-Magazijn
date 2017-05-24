int locatiex = 0;
int locatiey = 2;
int doelx;
int doely;
boolean gehaaltx = false;
boolean gehaalty = false;
boolean heen = true;
int Speed = 250;
boolean first = true;
int aantal;
int x = 0;
boolean complete = false;
int snelheidv = 210;
int snelheida = 220;
boolean gaterug = false;
boolean XorY = false;
boolean eerste = true;
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
  int coorx[] = {};
  int coory[] = {};
  if (Serial.available() > 0 && Serial.available() <= 1 && eerste == true) {
    aantal = Serial.read() - 48;
    eerste = false;
    Serial.println(aantal);
  } else if (Serial.available() > 0 && Serial.available() <= aantal && eerste == false && XorY == false) {

    for (int q = 0; q < aantal; q++) {
      coorx[q] = Serial.read() - 48;
      delay(1000);
      Serial.println("X");
      Serial.println(coorx[q]);
    }
    XorY = true;
  } else if (Serial.available() > 0 && Serial.available() <= aantal && eerste == false && XorY == true) {

    for (int q = 0; q < aantal; q++) {
      coory[q] = Serial.read() - 48;
      delay(1000);
      Serial.println("y");
      Serial.println(coory[q]);
    }
    XorY = false;
  }
}



















