int locatie;
int doel = 1;
boolean gehaalt = false;
void setup() {
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(7, OUTPUT);
  pinMode(13, OUTPUT);
  pinMode(A0, INPUT);
  pinMode(A1, INPUT);
  Serial.begin(9600);
}

void loop() {
  int x = analogRead (A0);
  int y = analogRead(A1);
  Serial.println(y);
//  Serial.println(doel);
//  Serial.println(locatie);
//  Serial.println(gehaalt);
  digitalWrite(13, HIGH);

 if(locatie == doel && gehaalt == false){
    digitalWrite(4, LOW);
    analogWrite(5,0);
    gehaalt = true;
    doel = 0;
    digitalWrite(7, LOW);
    analogWrite(6,255);    
    delay(1000);
    
    digitalWrite(7,LOW);
    analogWrite(6,0);
    digitalWrite(13,LOW);
    digitalWrite(4, LOW);
    analogWrite(5,200);    
    delay(1000);

    digitalWrite(4, LOW);
    analogWrite(5,0);
    digitalWrite(7, LOW);
    analogWrite(6,255);
    delay(500);
    
    digitalWrite(7,LOW);
    analogWrite(6,0); 
    digitalWrite(4, HIGH);
    digitalWrite(5,255);    
    delay(1000);
    
    digitalWrite(13,HIGH);
    digitalWrite(4, LOW);
    analogWrite(5,0);
    delay(1000);
    
    digitalWrite(7, HIGH);
    analogWrite(6,255);    
    delay(1000);

    digitalWrite(7, HIGH);
    analogWrite(6,0);

    
    
  }else if(locatie == doel && gehaalt == true){
    digitalWrite(4, LOW);
    analogWrite(5,0);
    gehaalt = false;
    doel = 1;
    delay(1000);
  }
  else if( x >=750 && gehaalt == false){
  digitalWrite(4, LOW);
  analogWrite(5,255);
    }
    else if ( x <= 751 && gehaalt == false){
      locatie = locatie + 1;
      delay(100);
    }
      else if(gehaalt == true && x>=750){
        digitalWrite(4,HIGH);
        analogWrite(5,255);
      }
      else if(gehaalt == true && x<=751){
        locatie = locatie - 1;
        delay(100);
        
        }
}







