int val = 0 ;
int led = 13;
String test = "8.3,3.9,4.1";
void setup() {
  Serial.begin(9600);
  pinMode(led, OUTPUT);
 
}
 
void loop() {
  delay(100);
 }
 
void serialEvent() {
  while (Serial.available()) {
    val = Serial.parseInt();
    if (val == 1) {
      digitalWrite(led, HIGH);
    }
    else if (val == 0) {
      digitalWrite (led, LOW);
    }
  }
  Serial.println("Succes from arduino.");
  // put your main code here, to run repeatedly:
 
}
