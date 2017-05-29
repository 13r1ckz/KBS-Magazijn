/*
this is the code that we will try to get done for the presentation of 31th of may 2017

*/
boolean eerste = true;
char aantal;
int coorx[127];
int coory[127];
boolean start = false;
int doelx;
int doely;
boolean gehaaltx = false;
boolean gehaalty = false;
int m = 48;
boolean up;
boolean yfirst = true;
int locatiey = 2;
int locatiex = 0;
int snelheida = 190;
int snelheidv = 230;
boolean first = true;
boolean heen = true;


void setup() {
  // put your setup code here, to run once:
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(7, OUTPUT);
  pinMode(13, OUTPUT);
  pinMode(A0, INPUT);//low
  pinMode(A1, INPUT);//high
  digitalWrite(13, HIGH);
  Serial.begin(9600);
  Serial.println("started");
  start = false;
}

void loop() {
  // boot
  if (start == true) {
    Serial.println(aantal);
    //For each object in the list...
    if( m < aantal) {
      //get the coordinates
      doelx = coorx[m];
      doely = coory[m];
      Serial.print("test ");
      Serial.println(m - 84);
      Serial.print("test2 ");
      Serial.println(aantal);
      //Serial.println(locatiey);
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
        executeCommando();
        delay(1000);
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
    else{
    Serial.println("end");
    delay(5000);
    }
  }
  Serial.println("en");
  //end y
}


void serialEvent() {
  while (Serial.available()) {
    if (eerste == true) {
      aantal = Serial.read();
      eerste = false;
      Serial.println(aantal);
    }

    //Generate locations based on length
    if (eerste == false) {
      Serial.println("int the false");
      Serial.println(aantal);
      //wait for input;
      while (Serial.available() == 0) { }

      //make corect orders
      for (int i = 0; i < aantal - 48; i++) {
        coorx[i] = Serial.read() - 48;
        delay(1);
        Serial.print("X: ");
        Serial.println(coorx[i]);
        Serial.print("aantal: ");
        Serial.println(aantal);
        Serial.print("3 ");
        Serial.println(Serial.available());
      }

      for (int i = 0; i < aantal - 48; i++) {
        coory[i] = Serial.read() - 48;
        delay(1);
        Serial.print("Y: ");
        Serial.println(coory[i]);
        Serial.print("aantal: ");
        Serial.println(aantal);
        Serial.print("4 ");
        Serial.println(Serial.available());
      }
      start = true;
    }
  }
}
