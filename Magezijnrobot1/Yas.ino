void executeCommandoy(){
  int y = analogRead(A1);
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
