int locatiex;
int locatiey;
int doelx = 3;
int doely = 3;
boolean gehaaltx = false;
boolean gehaalty = false;

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
  Serial.println(x);
//  Serial.println(doel);
//  Serial.println(locatie);
//  Serial.println(gehaalt);

digitalWrite(13, HIGH);
 if(locatiex == doelx && gehaaltx == false){
    digitalWrite(4, LOW);
    analogWrite(5,0);
    gehaaltx = true;
        
    
  }else if(locatiex == doelx && gehaaltx == true){
    digitalWrite(4, LOW);
    analogWrite(5,0);
    gehaaltx = false;
    doelx = 0;
    delay(1000);
  }
  else if( x >=750 && gehaaltx == false){
  digitalWrite(4, LOW);
  analogWrite(5,255);
    }
    else if ( x <= 751 && gehaaltx == false){
      locatiex = locatiex + 1;
      delay(500);
    }
      else if(gehaaltx == true && x>=750){
        digitalWrite(4,HIGH);
        analogWrite(5,255);
      }
      else if(gehaaltx== true && x<=751){
        locatiex = locatiex - 1;
        delay(500);
        
        }

    if(locatiey < doely && y >= 100){
      digitalWrite(7, LOW);
      analogWrite(6,255);
      locatiey ++;
      }else if(locatiey < doely && y <= 100){
        digitalWrite(7, LOW);
        analogWrite(6,255);
        }else if(locatiey == doely){
          digitalWrite(7, LOW);
          analogWrite(6,0);
          gehaalty = true;
          }

  
  }


  //LOW is up


 
 







