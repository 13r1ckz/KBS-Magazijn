int locatie;
int doel = 3;
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
//  Serial.println(x);
//  Serial.println(doel);
//  Serial.println(locatie);
//  Serial.println(gehaalt);


 if(locatie == doel && gehaalt == false){
    digitalWrite(4, HIGH);
    analogWrite(5,0);
    gehaalt = true;
    doel = 0;
    delay(1000);
  }else if(locatie == doel && gehaalt == true){
    digitalWrite(4, HIGH);
    analogWrite(5,0);
    gehaalt = false;
    doel = 0;
    delay(1000);
  }
  else if( x >=800 && gehaalt == false){
  digitalWrite(4, HIGH);
  analogWrite(5,250);
    }
    else if ( x <= 50 && gehaalt == false){
      locatie = locatie + 1;
      delay(500);
    }
      else if(gehaalt == true && x>=800){
        digitalWrite(4,LOW);
        analogWrite(5,250);
      }
      else if(gehaalt == true && x<=50){
        locatie = locatie - 1;
        Serial.println(String("-1"));
        delay(500);
        
        }
}







