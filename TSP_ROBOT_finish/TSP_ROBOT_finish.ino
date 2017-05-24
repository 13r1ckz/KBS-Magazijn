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
      Serial.println(analogRead(A0));
      Serial.println(locatiex);
      gehaalty = false;

      if (locatiey < doely) {
        up = true;
      } else if (locatiey > doely) {
        up = false;
      }

      if (locatiex < doelx) {
        heen = true;
        gehaaltx = false;
        gehaalty = false;
      } else if (locatiex > doelx) {
        heen = false;
        gehaaltx = false;
        gehaalty = false;
      }


      if (locatiex == doelx ) {
        if (analogRead(A0) <= 200 && heen == true) {
          gehaaltx = true;
          digitalWrite(4, HIGH);
          analogWrite(5, 0);
        } else if (analogRead(A0) >= 200 && heen == true && gehaaltx == false) {
          digitalWrite(4, LOW);
          analogWrite(5, 180);
        } else if (analogRead(A0) <= 200 && heen == false) {
          gehaaltx = true;
          digitalWrite(4, HIGH);
          analogWrite(5, 0);
        } else if (analogRead(A0) >= 200 && heen == false && gehaaltx == false) {
          digitalWrite(4, HIGH);
          analogWrite(5, 150);
        }
        delay(5000);
        m++;
      } else if (locatiex <= doelx && gehaaltx == false) {
        digitalWrite (13, HIGH);
        if (analogRead(A0) <= 900 && first == true) {
          digitalWrite(4, HIGH);
          analogWrite(5, 255);
          delay(400);
          digitalWrite(4, HIGH);
          analogWrite(5, 0);
          delay(100);
          first = false;
        } else if (analogRead(A0) <= 700 && first == false) {
          digitalWrite(4, HIGH);
          analogWrite(5, 0);
          delay(150);
          digitalWrite(4, HIGH);
          analogWrite(5, snelheidv);
          delay(150);
          locatiex ++;
        } else if (analogRead(A0) >= 701 && first == false) {
          digitalWrite(4, HIGH);
          analogWrite(5, snelheidv);
        }
      } else if (locatiex >= doelx && gehaaltx == false) {
        digitalWrite (13, HIGH);
        if (analogRead(A0) <= 900 && first == true) {
          digitalWrite(4, LOW);
          analogWrite(5, 255);
          delay(400);
          digitalWrite(4, HIGH);
          analogWrite(5, 0);
          delay(100);
          first = false;
        } else if (analogRead(A0) <= 200 && first == false) {
          digitalWrite(4, HIGH);
          analogWrite(5, 0);
          delay(150);
          digitalWrite(4, LOW);
          analogWrite(5, snelheida);
          delay(150);
          locatiex --;
        } else if (analogRead(A0) >= 201 && first == false) {
          digitalWrite(4, LOW);
          analogWrite(5, snelheida);
        }
      }

    }
  }
}


