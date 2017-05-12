int locatiex = 0;
int locatiey = 1;
int doelx = 3;
int doely = 3;
boolean gehaaltx = true;
boolean gehaalty = true;

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
  Serial.println(locatiex);
  Serial.println(doelx);
  
//  if (locatiex < doelx) {
//    executeCommandoxf();
//    if(analogRead (A1) <= 699){
//      locatiex ++;
//      }
//  } else if (locatiex > doelx) {
//    executeCommandoxb();
//  }
//
//  if (locatiey > doely) {
//    executeCommandoyu();
//  } else if (locatiey < doely) {
//    executeCommandoyd();
//  }

  if (analogRead (A1) >= 800 && gehaaltx == true && gehaalty == true) {
    executeCommandoz();
  }

}

