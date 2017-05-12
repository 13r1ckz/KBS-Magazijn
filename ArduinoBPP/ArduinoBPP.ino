String val = "";
void setup() {
  Serial.begin(9600);
  for(int x = 0; x < 13; x++){      // port loop //i am lazy
  pinMode(x, OUTPUT);
  }
}
 
void loop() {
  Serial.println(analogRead(A1));
  if(val == "E"){             //e shutdonw
      digitalWrite(4, LOW);
      digitalWrite(5, 0);
      Serial.println("Shutdown");
      val = "";
    }
   else if(analogRead(A1) > 200 ){   //top lain
    digitalWrite(7, HIGH);
      digitalWrite(6, HIGH);
    }
   else if(analogRead(A1) < 200 ){   //top lain
    digitalWrite(7, LOW);
      digitalWrite(6, 0);
    } 
   else if(analogRead(A0)){   // low lain
    }
 }
  
void serialEvent() {            // single time updater
  while (Serial.available()) {
    val = Serial.readString();
    //Serial.println(val);
    if (val == 4) {           //Richt action
      digitalWrite(4, HIGH);
      digitalWrite(5, 255);
      delay(1000);
      Serial.println("R");
      val = "";
      digitalWrite(4, LOW);
      digitalWrite(5, 0);
    }
    else if (val == 3) {      //L     action
      digitalWrite(4, LOW);
      digitalWrite(5, 255);
      delay(1000);
      val = "";
      Serial.write("R");
      digitalWrite(4, LOW);
      digitalWrite(5, 0);
    }
    else{                       // notining
      digitalWrite(4, LOW);
      digitalWrite(5, 0);
      }
  } 
}
