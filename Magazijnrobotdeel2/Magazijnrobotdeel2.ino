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

void MOV(int POS) {

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
  if (start) {
    //For each object in the list...
    while (m < aantal) {
      //get the coordinates
      doelx = coorx[m];
      doely = coory[m];
      Serial.println(gehaalty);


      //XAS motion
      if (doely == 1 || gehaalty == true) {
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

      //Yas motion
      if (doely != 1 || gehaaltx == true) {
        if (locatiey == doely && analogRead(A1) >= 601) {
          digitalWrite(7, LOW);
          gehaalty = true;
        } else if (locatiey <= doely) {
          if (analogRead (A1) <= 600 && gehaalty == false) {
            digitalWrite(7, LOW);
            analogWrite(6, 255);
          } else if (analogRead (A1) >= 601 && gehaalty == false) {
            digitalWrite(7, LOW);
            analogWrite(6, 255);
            delay(500);
            locatiey ++;
          }
        } else if (locatiey >= doely) {
          if (analogRead (A1) <= 600 && gehaalty == false) {
            digitalWrite(7, HIGH);
            analogWrite(6, 255);
          } else if (analogRead (A1) >= 601 && gehaalty == false) {
            digitalWrite(7, HIGH);
            analogWrite(6, 255);
            delay(500);
            locatiey --;
          }
        }
      }

      //ZAS pick up
      if (gehaalty == true && gehaaltx == true && gaterug == false && locatiex != 0) {
        
        digitalWrite(13, LOW);
        delay(100);
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
        delay(330);
        digitalWrite(4, LOW);
        analogWrite(5, 0);
        delay(100);

        digitalWrite(7, HIGH);
        analogWrite(6, 255);
        delay(1250);
        digitalWrite(7, HIGH);
        analogWrite(6, 0);
        delay(100);
        Serial.println("p");
        first = true;
        gehaaltx = false;
        gehaalty = false;
        m++;

        if (m == aantal) {
          gaterug == true;
        }
        // drop
        if (gehaalty == true && gehaaltx == true && locatiex == 0 && gaterug == true) {
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
          complete = true;
          gaterug = false;
        }


        if (complete == true) {
          digitalWrite(4, LOW);
          analogWrite(5, 0);
          digitalWrite(7, HIGH);
          analogWrite(6, 0);
        }
      }
    }
  }
}

