//This project is done by Teach Me Something

#include <Adafruit_GFX.h>    // Core graphics library
#include <MCUFRIEND_kbv.h>
#include "TouchScreen.h"
MCUFRIEND_kbv tft;  
/*______End of Libraries_______*/

/*______Define LCD pins (I have asigned the default values)_______*/
#define YP A3  // must be an analog pin, use "An" notation!
#define XM A2  // must be an analog pin, use "An" notation!
#define YM 9   // can be a digital pin
#define XP 8   // can be a digital pin
#define LCD_CS A3
#define LCD_CD A2
#define LCD_WR A1
#define LCD_RD A0
#define LCD_RESET A4
///*_______End of defanitions______*/

/*______Assign names to colors and pressure_______*/
#define BLACK   0x0000
#define BROWN   0x7980
#define RED     0xF800
#define ORANGE  0xFBE0
#define YELLOW  0xFFE0
#define GREEN   0x07E0
#define BLUE    0x001F
#define VIOLET  0xA81F
#define GREY    0x7BEF
#define WHITE   0xFFFF
#define CYAN    0x07FF
#define MAGENTA 0xF81F
#define LGREEN   0xAFE0
#define BLACK_BLUE 0x319F
#define BACKGROUND_BUTTON_COLOR 0x3186
#define BACKGROUND_BUTTON_TEXT_COLOR 0xE71C
#define BGREEN 0x04A3


#define MINPRESSURE 20
#define MAXPRESSURE 1000
/*_______Assigned______*/

/*____Calibrate TFT LCD_____*/
#define TS_MINX 907  // left 907
#define TS_MINY 942  // RIGHT
#define TS_MAXX 137  // RT
#define TS_MAXY 100  // Bot
/*______End of Calibration______*/

TouchScreen ts = TouchScreen(XP, YP, XM, YM, 100); //300 is the sensitivity

const int resX = 480; // On landscape
const int resY = 320; // On landscape
const int maxPieceOnX = 10;
const int maxPieceOnY = 8;
const int width_element = resX/maxPieceOnX;
const int height_element = resY/maxPieceOnY;

int i=0;
int j=0;
int a=140;
int b=60;
int messageXStartPosition = 40;
int messageYStartPosition = 95;

int c=0;
boolean Caps=false;
String symbol[4][10] = {
  { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"},
  { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P" },
  { "A", "S", "D", "F", "G", "H", "J", "K", "L", "E" },
  { "C", "Z", "X", "C", "V", "B", "N", "M", ".", "M" }
};
 int X,Y;

 String mUsername ="";
 String receivedMessage = "";

void setup() {
  Serial.begin(9600); //Use serial monitor for debugging
  Serial2.begin(115200);  
  tft.reset(); //Always reset at start
  tft.begin(0x9486); // My LCD uses LIL9341 Interface driver IC
  tft.setRotation(1); // I just roated so that the power jack faces up - optional
  tft.invertDisplay(0);
  IntroScreen();
  draw_BoxNButtons(); 
  tft.setCursor(0,0);
}

void loop() {
TSPoint p = waitTouch();
tft.setCursor (a,b);
tft.setTextSize (2);
tft.setTextColor(WHITE);
DetectButtons();
}

char convertToLowerCase(char letter){
  int letter_int = (int)letter ;
  if(letter_int >= 'A' && letter_int <= 'Z'){
    letter_int = letter_int+32;
  }
return ( (char)(letter_int) );
}

TSPoint waitTouch() {
  
  TSPoint p;
  do {
    p = ts.getPoint(); 
    pinMode(XM, OUTPUT);
    pinMode(YP, OUTPUT);
  } while((p.z < MINPRESSURE )|| (p.z > MAXPRESSURE));
  p.x = map(p.x, TS_MINX, TS_MAXX, 0, 320);
  p.y = map(p.y, TS_MINY, TS_MAXY, 0, 480);
  X = p.y; Y = p.x;
 // Serial.print(X); Serial.print(','); Serial.println(Y);// + " " + Y);
  return p;
}

void sendUsername(){
  const char* user = mUsername.c_str();
  Serial2.println(String(mUsername));
  Serial2.println(String(mUsername));
}

String receiveMessage(){
  String message = "";
  
  while(!Serial2.available() > 0){
  }
  if(Serial2.available() > 0){
    String receivedMessage(Serial2.readString());
    message = receivedMessage;
  }

  return message;
}

void clearShowingMessage(){
  tft.fillRect(0,messageYStartPosition,resX,25,BLUE);
}
void showMessage(String message){

      clearShowingMessage();
      tft.fillRoundRect(10,messageYStartPosition+15,resX-2*10,6,8,BLACK);
      tft.setCursor (resX/2-(message.length()*12)/2,messageYStartPosition);
      tft.setTextSize(2);
      tft.setTextColor(YELLOW);
      tft.print(message);
      
}

void addTouchPress(int xStepMin, int xStepMax, int yStepMin, int yStepMax,char lowerLetterToDisplay,char higherLetterToDisplay, int xlT, int ylT, int backgroundPressed,int backgroundUnpressed, int textPressed, int textUnpressed){
  tft.setTextSize (2);
    if ( xStepMin*width_element<X && X<xStepMax*width_element-1 && yStepMin*height_element-1<Y && Y<yStepMax*height_element-1)
        {
            
            if (Caps){
             tft.print(higherLetterToDisplay);
              tft.setTextSize (3); 
              mUsername = mUsername+higherLetterToDisplay;
              tft.fillRoundRect((xStepMin*width_element)+1, yStepMin*height_element, (xStepMax-xStepMin)*width_element-1, (yStepMax-yStepMin)*height_element-1, 3,backgroundPressed);
              tft.setCursor (xStepMin*width_element+xlT,yStepMin*height_element+ylT );
              tft.setTextColor(textPressed);
              tft.print(higherLetterToDisplay);
              delay(300);
              tft.fillRoundRect((xStepMin*width_element)+1, yStepMin*height_element, (xStepMax-xStepMin)*width_element-1, (yStepMax-yStepMin)*height_element-1, 3,backgroundUnpressed);
              tft.setCursor (xStepMin*width_element+xlT,yStepMin*height_element+ylT );
              tft.setTextColor(textUnpressed);
              tft.print(higherLetterToDisplay);
            }
            else{
              tft.print(lowerLetterToDisplay);
              mUsername = mUsername+lowerLetterToDisplay;
              tft.setTextSize (3); 
              tft.fillRoundRect((xStepMin*width_element)+1, yStepMin*height_element, (xStepMax-xStepMin)*width_element-1, (yStepMax-yStepMin)*height_element-1, 3,backgroundPressed);
              tft.setCursor (xStepMin*width_element+xlT,yStepMin*height_element+ylT );
              tft.setTextColor(textPressed);
              tft.print(lowerLetterToDisplay);
              delay(300);
              tft.fillRoundRect((xStepMin*width_element)+1, yStepMin*height_element, (xStepMax-xStepMin)*width_element-1, (yStepMax-yStepMin)*height_element-1, 3,backgroundUnpressed);
              tft.setCursor (xStepMin*width_element+xlT,yStepMin*height_element+ylT );
              tft.setTextColor(textUnpressed);
              tft.print(lowerLetterToDisplay);
            }
            a=a+12;
       }
}

void addTouchToCaps(int xStepMin,int xStepMax, int yStepMin, int yStepMax){

  if( xStepMin*width_element< X && X < xStepMax*width_element && yStepMin*height_element < Y && Y < yStepMax*height_element){
    Caps = !Caps;
    if(Caps){
      // Drawing Pressed CapsLock Button
      tft.fillRoundRect(1,6*height_element, width_element -1, height_element -1, 3,GREEN);
      tft.setTextColor(BLACK);
      tft.setCursor (1,height_element*6 + 12);
      tft.setTextSize(2);
      tft.print("Caps");
      drawUpperKeyboard();
      // Drawing Upressed CapsLock Button
      tft.fillRoundRect(1,6*height_element, width_element -1, height_element -1, 3,RED);
      tft.setTextColor(BACKGROUND_BUTTON_TEXT_COLOR);
      tft.setCursor (1,height_element*6 + 12);
      tft.setTextSize(2);
      tft.print("Caps");
    }else{
      // Drawing Pressed CapsLock Button
      tft.fillRoundRect(1,6*height_element, width_element -1, height_element -1, 3,GREEN);
      tft.setTextColor(BLACK);
      tft.setCursor (1,height_element*6 + 12);
      tft.setTextSize(2);
      tft.print("Caps");
      drawLowerKeyboard();
      // Drawing Upressed CapsLock Button
      tft.fillRoundRect(1,6*height_element, width_element -1, height_element -1, 3,RED);
      tft.setTextColor(BACKGROUND_BUTTON_TEXT_COLOR);
      tft.setCursor (1,height_element*6 + 12);
      tft.setTextSize(2);
      tft.print("Caps");
    }
  } 
}

void addTouchToReset(){
  if (7*width_element<X && X<10*width_element-1 && 7*height_element-1<Y && Y<8*height_element-1){
  // drawing text info
  a = 140;
  // Drawing Pressed Reset Button
      tft.fillRoundRect(7*width_element+1,7*height_element, 3*width_element-1, height_element-1, 3,YELLOW);
      mUsername = "";
      tft.setTextColor(BLACK);
      tft.setTextSize (3);
      tft.setCursor (7*width_element + 30, 7*height_element+10 );
      tft.print("RESET");
      tft.fillRoundRect(a-8,b-5,resX-a-20,25,8,BLACK);
       delay(300);
      // Drawing Unpressed Reset Button
      tft.fillRoundRect(7*width_element+1,7*height_element, 3*width_element-1, height_element-1, 3,WHITE);
      tft.setTextColor(BROWN);
      tft.setTextSize (3);
      tft.setCursor (7*width_element + 30, 7*height_element+10 );
      tft.print("RESET");
  }
}

boolean isAvailableTyping(){
  if(((0 < X && X <480 && 3*height_element <Y && Y< 5 * height_element) 
  || (0 < X && X <9*width_element && 5*height_element <Y && Y< 6 * height_element)
  || (width_element < X && X <9*width_element && 6*height_element <Y && Y< 7 * height_element)
  || (0 < X && X <7*width_element && 7*height_element <Y && Y< 8 * height_element))
  && a<440){
 
    return true;
  }
  return false;
}

void addTouchToDelete(int xStepMin,int xStepMax, int yStepMin, int yStepMax)
{
  if( xStepMin*width_element< X && X < xStepMax*width_element && yStepMin*height_element < Y && Y < yStepMax*height_element && a>140){
    a= a-12;
    tft.fillRect(a,b-5,12,25,BLACK);
      // Drawing Delete Button
      tft.fillRoundRect(9*width_element+1,6*height_element, width_element-1, height_element-1, 3,YELLOW);
      tft.setTextColor(BLACK);
      tft.setTextSize (3);
      tft.setCursor (9*width_element+2,6*height_element+12 );
      tft.print("<-");
      
      if(mUsername.length() > 0){
        mUsername.remove(mUsername.length() - 1);
      }

       delay(300);
      // Drawing Delete Button
      tft.fillRoundRect(9*width_element+1,6*height_element, width_element-1, height_element-1, 3,RED);
      tft.setTextColor(BACKGROUND_BUTTON_TEXT_COLOR);
      tft.setTextSize (3);
      tft.setCursor (9*width_element+2,6*height_element+12 );
      tft.print("<-");
  }
}

void addTouchToEnt(int xStepMin,int xStepMax, int yStepMin, int yStepMax)
{
  if( xStepMin*width_element< X && X < xStepMax*width_element && yStepMin*height_element < Y && Y < yStepMax*height_element && a>140){
      // Drawing Ent Button
      tft.fillRoundRect(xStepMin*width_element+1,yStepMin*height_element, (xStepMax-xStepMin)*width_element-1, (yStepMax-yStepMin)*height_element-1, 3,YELLOW);
      tft.setTextColor(BLACK);
      tft.setTextSize (2);
      tft.setCursor (xStepMin*width_element+6,yStepMin*height_element+14 );
      tft.print("Ent");
       sendUsername();
       showMessage("Loading.. Please wait!");
       Serial.println("S-a transmis user");
       delay(300);
      // Drawing Ent Button
      tft.fillRoundRect(xStepMin*width_element+1,yStepMin*height_element, (xStepMax-xStepMin)*width_element-1, (yStepMax-yStepMin)*height_element-1, 3,BGREEN);
      tft.setTextColor(BACKGROUND_BUTTON_TEXT_COLOR);
      tft.setTextSize (2);
      tft.setCursor (xStepMin*width_element+6,yStepMin*height_element+14 );
      tft.print("Ent");
      receivedMessage = receiveMessage();
      Serial.println("S-a primit message: " + receivedMessage);
      showMessage(receivedMessage);
      Serial.println("username: "+mUsername);
  }
}

void DetectButtons()
{
  if(isAvailableTyping()){
    // add touch for numbers
 for(i=0 ; i<10;i++){
   char lowerLetter = symbol[0][i].charAt(0);
   char higherLetter = symbol[0][i].charAt(0);
   addTouchPress(i,i+1,3,4,lowerLetter,higherLetter,18,10,YELLOW,BACKGROUND_BUTTON_COLOR,BLACK,BACKGROUND_BUTTON_TEXT_COLOR);
 }

 // add touch for second row quertyoup
 for(i=0 ; i<10;i++){
   char lowerLetter = convertToLowerCase(symbol[1][i].charAt(0));
   char higherLetter = symbol[1][i].charAt(0);
   addTouchPress(i,i+1,4,5,lowerLetter,higherLetter,18,10,YELLOW,BACKGROUND_BUTTON_COLOR,BLACK,BACKGROUND_BUTTON_TEXT_COLOR);
 }

 // add touch for third row, asdfghjkl
 for(i=0 ; i<9;i++){
   char lowerLetter = convertToLowerCase(symbol[2][i].charAt(0));
   char higherLetter = symbol[2][i].charAt(0);
   addTouchPress(i,i+1,5,6,lowerLetter,higherLetter,18,10,YELLOW,BACKGROUND_BUTTON_COLOR,BLACK,BACKGROUND_BUTTON_TEXT_COLOR);
 }

 // add touch for fourth row, zxcvbnm.
 for(i=1 ; i<9;i++){
   char lowerLetter = convertToLowerCase(symbol[3][i].charAt(0));
   char higherLetter = symbol[3][i].charAt(0);
   addTouchPress(i,i+1,6,7,lowerLetter,higherLetter,18,10,YELLOW,BACKGROUND_BUTTON_COLOR,BLACK,BACKGROUND_BUTTON_TEXT_COLOR);
 }

  // add touch for -
  addTouchPress(0,0+1,7,8,'-','-',18,10,YELLOW,BACKGROUND_BUTTON_COLOR,BLACK,BACKGROUND_BUTTON_TEXT_COLOR);
  // add touch for _
  addTouchPress(1,1+1,7,8,'_','_',18,10,YELLOW,BACKGROUND_BUTTON_COLOR,BLACK,BACKGROUND_BUTTON_TEXT_COLOR);
}
  // add touch for Caps
  addTouchToCaps(0,0+1,6,7);

  // add touch for reset
  addTouchToReset();

  // add touch for delete
  addTouchToDelete(9,9+1,6,7);

  //add touch to ent
  addTouchToEnt(9,9+1,5,6);
  
}

void IntroScreen()
{tft.fillScreen(BLACK);
  tft.setCursor (width_element/1.5, height_element*2);
  tft.setTextSize (7);
  tft.setTextColor(RED);
  tft.println("GreenLight");
  
  tft.setTextColor(YELLOW);
  tft.setCursor (width_element/1.5, height_element*4 + height_element/2);
  tft.setTextSize (2);
  tft.println("Help the nation to save the planet!");
   
  tft.setTextSize (2);
  tft.setCursor (width_element, height_element*5 +height_element/2);
  tft.setTextColor(BLUE);
  tft.println("The change comes from deep within ");
  tft.setCursor (width_element*3+width_element/2, height_element*6);
  tft.println("your heart..");
  
  delay(8000);
}


void drawUpperKeyboard(){
  
      tft.setTextSize (3);
      tft.setTextColor(BACKGROUND_BUTTON_TEXT_COLOR);
      
      // Drawing Buttons from matrix
      for( j=1; j<4;j++)
      {
         for ( i=0; i<10; i++)
         {
          if( (j >= 2 && i==9) || (j==3 && i == 0)){
            continue;
           }
           tft.fillRoundRect((i*width_element)+1, j*height_element+3*height_element, width_element-1, height_element-1, 3,BACKGROUND_BUTTON_COLOR);
           tft.setCursor (i*width_element+18,j*height_element+3*height_element+10 );
           tft.print(symbol[j][i]);
          }
      }
}

void drawLowerKeyboard(){
      tft.setTextSize (3);
      tft.setTextColor(BACKGROUND_BUTTON_TEXT_COLOR);      
      // Drawing Buttons from matrix
      for( j=1; j<4;j++)
      {
         for ( i=0; i<10; i++)
         {
          if( (j >= 2 && i==9) || (j==3 && i == 0)){
            continue;
           }
           tft.fillRoundRect((i*width_element)+1, j*height_element+3*height_element, width_element-1, height_element-1, 3,BACKGROUND_BUTTON_COLOR);
           tft.setCursor (i*width_element+18,j*height_element+3*height_element+10 );
           char symbolToPrint = symbol[j][i].charAt(0);
           symbolToPrint = convertToLowerCase(symbolToPrint);
           tft.print(symbolToPrint);
          }
      }

     
}
void draw_BoxNButtons()
{     tft.fillScreen(BLUE);
      tft.fillRect(0,3*height_element,resX,(resY-3*height_element),BLACK);
      tft.setTextSize(3);
      tft.setTextColor(BACKGROUND_BUTTON_TEXT_COLOR);
    // Drawing Numbers
      for ( i=0; i<10; i++)
         {
          j=0;
           tft.fillRoundRect((i*width_element)+1, j*height_element+3*height_element, width_element-1, height_element-1, 3,BACKGROUND_BUTTON_COLOR);
           tft.setCursor (i*width_element+18,j*height_element+3*height_element+10 );
           tft.print(symbol[j][i]);
          }

      
      if(Caps){
        drawUpperKeyboard();
      }else{
        drawLowerKeyboard();
      }

       // Drawing CapsLock Button
      tft.fillRoundRect(1,6*height_element, width_element -1, height_element -1, 3,RED);
      tft.setTextColor(BACKGROUND_BUTTON_TEXT_COLOR);
      tft.setCursor (1,height_element*6 + 12);
      tft.setTextSize(2);
      tft.print("Caps");


      // Drawing Delete Button
      tft.fillRoundRect(9*width_element+1,6*height_element, width_element-1, height_element-1, 3,RED);
      tft.setTextColor(BACKGROUND_BUTTON_TEXT_COLOR);
      tft.setTextSize (3);
      tft.setCursor (9*width_element+2,6*height_element+12 );
      tft.print("<-");

    // Drawing Enter Button
      tft.fillRoundRect(9*width_element+1,5*height_element, width_element-1, height_element-1, 3,BGREEN);
      tft.setTextColor(WHITE);
      tft.setTextSize (2);
      tft.setCursor (9*width_element+6,5*height_element+14 );
      tft.print("Ent");

      // Drawing - Button
      tft.fillRoundRect(0*width_element+1,7*height_element, width_element-1, height_element-1, 3,BACKGROUND_BUTTON_COLOR);
      tft.setTextColor(BACKGROUND_BUTTON_TEXT_COLOR);
      tft.setTextSize (3);
      tft.setCursor (0*width_element+18,7*height_element+10 );
      tft.print("-");

      // Drawing _ Button
      tft.fillRoundRect(1*width_element+1,7*height_element, width_element-1, height_element-1, 3,BACKGROUND_BUTTON_COLOR);
      tft.setTextColor(BACKGROUND_BUTTON_TEXT_COLOR);
      tft.setTextSize (3);
      tft.setCursor (1*width_element+18,7*height_element+10 );
      tft.print("_");
      
    // Drawing Space Button
      tft.fillRoundRect(1+2*width_element,7*height_element, 7*width_element -1, height_element , 3,BROWN);
      tft.setTextColor(WHITE);
      tft.setTextSize (3);
      tft.setCursor (140,7*height_element+10 );
      tft.print("Space Bar");

      // Drawing Reset Button
      tft.fillRoundRect(7*width_element+1,7*height_element, 3*width_element-1, height_element-1, 3,WHITE);
      tft.setTextColor(BROWN);
      tft.setTextSize (3);
      tft.setCursor (7*width_element + 30, 7*height_element+10 );
      tft.print("RESET");
      
      //Drawing Info
      tft.setTextColor(YELLOW);
      tft.setTextSize (2);
      tft.setCursor (1, 10 );
      tft.print("Please type your Username and press Ent");

      tft.setCursor (20, 60 );
      tft.setTextSize (2);
      tft.print("username:");

      // drawing text info
      tft.fillRoundRect(a-8,b-5,resX-a-20,25,8,BLACK);

      
      
}
