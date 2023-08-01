void setup() {
  Serial.begin(9600);
  Serial2.begin(115200);
}
int i=0;
void loop() {
  
//  if(Serial2.available()>0){
//    int com =Serial2.read();
//    
//    Serial.println(com); 
//  }

  Serial2.write(i);
  i++;
  delay(1300);
  
}
