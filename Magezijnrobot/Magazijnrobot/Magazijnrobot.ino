int locatie;
int doel = 1;
boolean gehaalt = false;
void setup() {
  pinMode(4, OUTPUT);
pinMode(5, OUTPUT);
 pinMode(6, OUTPUT);
pinMode(7, OUTPUT);
pinMode(A0, INPUT);
Serial.begin(9600);
}

void loop() {
  int x = analogRead (A0);
  Serial.println(x);
  Serial.println(doel);
  Serial.println(locatie);
  Serial.println(gehaalt);


 if(locatie == doel && gehaalt == false){
    digitalWrite(4, LOW);
    analogWrite(5,0);
    gehaalt = true;
    doel = 0;

    digitalWrite(7, HIGH);
    analogWrite(6,255);
    delay(10000);
    digitalWrite(7,LOW);
    analogWrite(6,0);
    delay(1000);
    
  }else if(locatie == doel && gehaalt == true){
    digitalWrite(4, LOW);
    analogWrite(5,0);
    gehaalt = false;
    doel = 1;
    delay(1000);
  }
  else if( x >=300 && gehaalt == false){
  digitalWrite(4, LOW);
  analogWrite(5,255);
    }
    else if ( x <= 100 && gehaalt == false){
      locatie = locatie + 1;
      delay(100);
    }
      else if(gehaalt == true && x>=300){
        digitalWrite(4,HIGH);
        analogWrite(5,255);
      }
      else if(gehaalt == true && x<=100){
        locatie = locatie - 1;
        delay(100);
        
        }
}







