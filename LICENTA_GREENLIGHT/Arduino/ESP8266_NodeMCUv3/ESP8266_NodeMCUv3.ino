#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <SoftwareSerial.h>
#include <ArduinoJson.h>
#include <WiFiClient.h> 

const char* ssid = "Iphone";
const char* password = "199220011997";
const char* urlPost = "http://172.20.10.4:8083/api/v1/post/ceva";
String messageToTransmit = "";
WiFiClient wifiClient;

SoftwareSerial SUART(D2, D1);

void setup() 
{
  Serial.begin(9600);
  WiFi.begin(ssid, password);
  SUART.begin(115200);

  while(WiFi.status() != WL_CONNECTED){
    Serial.println("connecting");
    delay(1500);
  }
  if(WiFi.status() != WL_CONNECTED){
    Serial.println("NOT CONNECTED");
  }else{
        Serial.println("CONNECTED");
  }
}

void loop() 
{
  if(SUART.available()>0){
    Serial.println("merge");
  String username = SUART.readString();
  Serial.println(username);


   messageToTransmit = sendPostRequest(username);
   if(messageToTransmit.length() > 0) {
    const char* sendmsg = messageToTransmit.c_str();
    Serial.print(sendmsg);
    SUART.write(sendmsg);
    messageToTransmit = "";
  }
  }

  

  delay(2000);
}


String sendPostRequest(String username){
   String response = "";
   
   if (WiFi.status() == WL_CONNECTED) 
  {
    DynamicJsonDocument user(2048);
    user["username"] = username;
    String jsonFormat;
    serializeJson(user, jsonFormat);
    
    HTTPClient http; //Object of class HTTPClient
    http.begin(wifiClient, urlPost);
    http.addHeader("Content-Type", "application/json");
    
    int httpCode = http.POST(jsonFormat);
    if (httpCode == 200) 
    {
      response = "Successfully Created";
    } else if( httpCode > 0){
      DynamicJsonDocument postResponse(2048);
      deserializeJson(postResponse,http.getString());
      if(postResponse["error"]){
        String convertToString(postResponse["error"]);
        response = convertToString;
      } else { response = "An error has occured";}
      
    } else {
      response = "Endpoint not found";
    }
    Serial.println(httpCode);
    http.end(); //Close connection
  } else{
    response = "NO INTERNET CONNECTION";
  }
  return response;
}
