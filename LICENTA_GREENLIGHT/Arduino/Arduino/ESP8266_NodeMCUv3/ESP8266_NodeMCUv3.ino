#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <SoftwareSerial.h>
#include <ArduinoJson.h>
#include <WiFiClient.h> 

const char* ssid = "Iphone";
const char* password = "199220011997";
const char* urlPost = "http://172.20.10.4:8083/api/v1/user/user-recycling";
const char* getTrashCategory = "http://172.20.10.4:8083/api/v1/trash_classifier/get-trash-category";
String messageToTransmit = "";
const unsigned long TIME_OUT = 60000;
WiFiClient wifiClient;

SoftwareSerial SUART(D2, D1);

void setup() 
{

  pinMode(D3, OUTPUT);
  Serial.begin(9600);
  WiFi.begin(ssid, password);
  SUART.begin(9600);

  while(WiFi.status() != WL_CONNECTED){
    Serial.println("connecting");
    delay(1500);
    digitalWrite(D3,HIGH);
  }
  if(WiFi.status() != WL_CONNECTED){
    Serial.println("NOT CONNECTED");
  }else{
        Serial.println("CONNECTED");
        digitalWrite(D3,LOW);
  }
}

void loop() 
{
  Serial.println("Data to read: "+String(SUART.available()));
  delay(1000);

  if(SUART.available()>=3){
  String dataType = SUART.readStringUntil('\n');
  String recivedTime = "";
  String message = "";
  String category = "";
  String undefinedVal = "";
  if(dataType.toInt() == 1){
    recivedTime = SUART.readStringUntil('\n');
    message = SUART.readStringUntil('\n');
    undefinedVal = SUART.readString();

  Serial.println("Data recived via SRX:");
  Serial.println(dataType);
  Serial.println(recivedTime);
  Serial.println(message);

   unsigned long currentTime = millis();
  if( currentTime - recivedTime.toInt() < TIME_OUT){
    receiveCategoryData(message);
  }
  
  }else if (dataType.toInt() == 2){
    recivedTime = SUART.readStringUntil('\n');
    message = SUART.readStringUntil('\n');
    category = SUART.readStringUntil('\n');
    undefinedVal = SUART.readString();

  Serial.println("Data recived via SRX:");
  Serial.println(dataType);
  Serial.println(recivedTime);
  Serial.println(message);
  Serial.println(category);
  
  unsigned long currentTime = millis();
  if( currentTime - recivedTime.toInt() < TIME_OUT){
    receiveUsernameData(message,category);
  }
  }else{
    
  }
  
  Serial.println("Data recived via SRX:");
  Serial.println(dataType);
  Serial.println(recivedTime);
  Serial.println(message);
 }
  delay(2000);
}

void receiveUsernameData(String username,String category){
  username.trim();
  category.trim();
  String userResponse = updateUserReciclying(username,category);
  unsigned long currentTime = millis();
  
  Serial.println("Data sent via STX:");
  Serial.println("2");
  Serial.println(String(currentTime));
  Serial.println(userResponse);
  
  SUART.println("2");
  SUART.println(String(currentTime));
  SUART.println(String(userResponse));
}

void receiveCategoryData(String category){
  category.trim();
  String categoryResponse = handleCategoryRequest(category);
  unsigned long currentTime = millis();
  
  Serial.println("Data sent via STX:");
  Serial.println("1");
  Serial.println(String(currentTime));
  Serial.println(categoryResponse);
  
  SUART.println("1");
  SUART.println(String(currentTime));
  SUART.println(String(categoryResponse));
}

String handleCategoryRequest(String value){
    String response = "no response";
   
   if (WiFi.status() == WL_CONNECTED) 
  {
    Serial.println(value);
    DynamicJsonDocument category(2048);
    category["bar_code"] = value;
    String jsonFormat;
    serializeJson(category, jsonFormat);
    
    HTTPClient http; //Object of class HTTPClient
    http.begin(wifiClient, getTrashCategory);
    http.addHeader("Content-Type", "application/json");
    
    int httpCode = http.POST(jsonFormat);
    Serial.print("Http code: ");
    Serial.println(httpCode);
    if (httpCode == 200) 
    {
      DynamicJsonDocument postResponse(2048);
      deserializeJson(postResponse,http.getString());
      response = String(postResponse["payload"]["category"]);
       Serial.println( response);
    } else if( httpCode > 0){
      DynamicJsonDocument postResponse(2048);
      deserializeJson(postResponse,http.getString());
      if(postResponse["message"]){
        String convertToString(postResponse["message"]);
        response = convertToString;
      } else { response = "An error has occured";}
      
    } else {
      response = "Endpoint not found";
    }
    
    http.end(); //Close connection
  } else{
    response = "NO INTERNET CONNECTION";
  }

  return response;
}

String updateUserReciclying(String username,String category){
   String response = "no response";
   
   if (WiFi.status() == WL_CONNECTED) 
  {
    DynamicJsonDocument user(2048);
    user["username"] = username;
    user["category"] = category;
    String jsonFormat;
    serializeJson(user, jsonFormat);
    
    HTTPClient http; //Object of class HTTPClient
    http.begin(wifiClient, urlPost);
    http.addHeader("Content-Type", "application/json");
    
    int httpCode = http.PUT(jsonFormat);
    Serial.print("Http code: ");
    Serial.println(httpCode);
    if (httpCode == 200) 
    {
      response = "Successfully added";
    } else if( httpCode > 0){
      DynamicJsonDocument postResponse(2048);
      deserializeJson(postResponse,http.getString());
      if(postResponse["message"]){
        String convertToString(postResponse["message"]);
        response = convertToString;
      } else { response = "An error has occured";}
      
    } else {
      response = "Endpoint not found";
    }
    
    http.end(); //Close connection
  } else{
    response = "NO INTERNET CONNECTION";
  }

  return response;
}
