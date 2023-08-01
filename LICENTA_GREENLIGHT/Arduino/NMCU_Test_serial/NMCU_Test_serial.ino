#include <SoftwareSerial.h>

SoftwareSerial SUART(D2, D1);

int a=5;
int i=0;

void setup() {
  Serial.begin(9600);
  SUART.begin(115200);
}

void loop() {
  //Serial.println(a);
//  SUART.write(i);
//  i++;
if(SUART.available()>0){
  Serial.println(SUART.read());
}
  delay(300);
}
