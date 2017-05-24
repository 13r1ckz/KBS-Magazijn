int locatiex = 0;
int locatiey = 2;
int doelx;
int doely;
boolean gehaaltx = false;
boolean gehaalty = false;
boolean heen = true;
boolean first = true;
int aantal;
int x = 0;
boolean complete = false;
int snelheidv = 210;
int snelheida = 220;
boolean gaterug = false;
int aantalproducten;
int firstloop = 0;
int m = 0;
boolean XorY = false;
boolean eerste = true;
boolean start = false;
boolean yfirst = true;
boolean up;

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
  //get aantal
  int coorx[127];
  int coory[127];
  // First number - Define length
  if (Serial.available() == 1 && eerste == true) {
    aantal = Serial.read() - 48;
    eerste = false;
    Serial.println(aantal);
  }

  //Generate locations based on length
  if (Serial.available() > 0 && eerste == false) {
    for (int i = 0; i < aantal; i++) {
      coorx[i] = Serial.read() - 48;
      delay(350);
      Serial.print("X: ");
      Serial.println(coorx[i]);
    }

    for (int i = 0; i < aantal; i++) {
      coory[i] = Serial.read() - 48;
      delay(350);
      Serial.print("Y: ");
      Serial.println(coory[i]);
    }
    start = true;
  }

  // boot
  if (start == true) {
    //For each object in the list...
    while (m < aantal) {
      //get the coordinates
      doelx = coorx[m];
      doely = coory[m];
      Serial.println(doely);
      Serial.println(locatiey);
      gehaalty = false;

      if (locatiey < doely) {
        up = true;
      } else if (locatiey > doely) {
        up = false;
      }

      if (yfirst == true && up == true) {
        digitalWrite(7, LOW);
        analogWrite(6, 255);
        delay(1000);
        digitalWrite(7, LOW);
        analogWrite(6, 0);
        delay(100);
        yfirst = false;

      } else if (yfirst == true && up == false) {
        digitalWrite(7, HIGH);
        analogWrite(6, 255);
        delay(1000);
        digitalWrite(7, LOW);
        analogWrite(6, 0);
        delay(100);
        yfirst = false;
      } else if (locatiey == doely && yfirst == false) {
        if (up == true) {
          digitalWrite(7, HIGH);
          analogWrite(6, 255);
          delay(500);
          digitalWrite(7, LOW);
          analogWrite(6, 0);
          gehaalty = true;
          yfirst = true;
        } else if (up == false) {
          digitalWrite(7, LOW);
          analogWrite(6, 255);
          delay(500);
          digitalWrite(7, LOW);
          analogWrite(6, 0);
          gehaalty = true;
          yfirst = true;
        }
        delay(5000);
       m++;
      } else if (locatiey <= doely && gehaalty == false && yfirst == false) {
        if (analogRead (A1) <= 600) {
          digitalWrite(7, LOW);
          analogWrite(6, 255);

        } else if (analogRead (A1) >= 601) {
          digitalWrite(7, LOW);
          analogWrite(6, 255);
          delay(500);
          locatiey ++;
        }
      } else if (locatiey >= doely && gehaalty == false && yfirst == false) {

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
  }
}

