boolean load = false;
boolean doos = false;
boolean side = false;

void setup() {
 
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(7, OUTPUT);
  pinMode(A1, INPUT);
  pinMode(A0, INPUT);
  Serial.begin(9600);
}

void loop() {
    
    int x= analogRead (A0); //down sensor
  
    
    int y = analogRead (A1);// up sensor
   
    
    Serial.println(x);
    //Serial.println(y);
    

    if(x<= 350 && side == true){
       digitalWrite(4, LOW);
       analogWrite(5,255);
       delay(3000);
       digitalWrite(4, LOW);
       analogWrite(5,0);
        load = false; 
        side = false;
      }else if(x<=350 && side == false){
        digitalWrite(4,HIGH);
        analogWrite(5,255);
        delay(3000);
        digitalWrite(4, HIGH);
        analogWrite(5,0);
        load = false; 
        side = true;
        }
      
    if(y>= 400 && load == false){
      digitalWrite(7, HIGH);
      analogWrite(6,255);
      
      }
      else if(y<=399 && load == false){
        
        digitalWrite(7, HIGH);
        analogWrite(6,255);
        
        load = true;
        }
        else if(load == true){
          delay(800);
        digitalWrite(7, HIGH);
        analogWrite(6,0);
        }
}
