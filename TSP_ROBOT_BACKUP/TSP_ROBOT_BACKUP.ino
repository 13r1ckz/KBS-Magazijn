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



  int ding[3] = {1, 3, 5,};
  int dingy[3] = {2, 3, 4,};


  while (m < 3) {
    Serial.println(gaterug);
    doelx = ding[m];
    doely = dingy[m];

    if (locatiex <= doelx) {
      heen = true;
    } else if (locatiex >= doelx) {
      heen = false;
    }

    //XAS motion
    if (doely == 1 || gehaalty == true) {
      if (locatiex == doelx && heen == true) {
        if (analogRead(A0) <= 500) {
          gehaaltx = true;
          digitalWrite(4, HIGH);
          analogWrite(5, 0);

        } else {
          digitalWrite(4, LOW);
          analogWrite(5, 180);
        }
      } else if (locatiex == doelx && heen == false) {
        if (analogRead(A0) <= 500) {
          gehaaltx = true;
          digitalWrite(4, HIGH);
          analogWrite(5, 0);


        } else {
          digitalWrite(4, HIGH);
          analogWrite(5, 150);
        }
      } else if (locatiex <= doelx) {
        digitalWrite (13, HIGH);
        if (analogRead(A0) <= 700 && first == true) {
          digitalWrite(4, HIGH);
          analogWrite(5, 255);
          delay(500);
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
      } else if (locatiex > doelx) {
        digitalWrite (13, HIGH);
        if (analogRead(A0) <= 700 && first == true) {

          digitalWrite(4, LOW);
          analogWrite(5, 255);
          delay(200);
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
        Serial.println("DONE");
      }
    }

    //Yas motion
    if (doely != 1 || gehaaltx == true) {
      if (locatiey <= doely) {
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
        if (locatiey == doely) {
          digitalWrite(7, LOW);
          analogWrite(6, 0);
          gehaalty = true;
        }
      }
    }

    //ZAS pick up
    if (gehaalty == true && gehaaltx == true && gaterug == false && locatiex != 0) {
      Serial.println("p");
      digitalWrite(13, LOW);
      digitalWrite(4, LOW);
      analogWrite(5, 200);
      delay(180);
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
      analogWrite(5, 200);
      delay(190);
      digitalWrite(4, LOW);
      analogWrite(5, 0);
      delay(100);

      digitalWrite(7, HIGH);
      analogWrite(6, 255);
      delay(1200);
      digitalWrite(7, HIGH);
      analogWrite(6, 0);
      delay(100);
      Serial.println("p");
      gehaaltx = false;
      gehaalty = false;
      m++;
    }

    // drop
    if (gehaalty == true && gehaaltx == true && locatiex == 0 && gaterug == true) {
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


