 void executeCommandox(){
int x = analogRead (A0);
  
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
      delay(100);
    }
      else if(gehaaltx == true && x>=750){
        digitalWrite(4,HIGH);
        analogWrite(5,255);
      }
      else if(gehaaltx== true && x<=751){
        locatiex = locatiex - 1;
        delay(100);
        
        }
 }

