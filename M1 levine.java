//// Midterm code for 59CST112

/**************************************************************
REAL NAME.

    MY FIRST NAME IS......Evelyn
    MY MIDDLE NAME IS.....Rosita
    MY LAST NAME IS.......Levine
    
 3 WORDS THAT START WITH MY INITIALS, using lower case letters:

    first word............eevee
    second word...........rose
    third word............reaf
    
***************************************************************/


//// GLOBALS FOR 3 colored balls ////
//// (Assume ball diameter is 30.) ////
float eX,  eY,  eDX,  eDY;          //++++ e means eevee ++++
float rX,  rY,  rDX,  rDY;              //++++ r means rose ++++
float lX,  lY,  lDX,  lDY;      //++++ l means leaf ++++
            


//// OTHER GLOBALS:  strings, pool table, etc ////

String title=  "CST112 MIDTERM EXAM";
String news=   "Click any ball to reset it to right half of table.  (r resets all.)";
String author=  "Evelyn Levine";

float left, right, top, bottom;        // Table boundaries
float middle;
boolean wall=true;

int tableRed=150, tableGreen=250, tableBlue=150;      // green pool table
int score=0,m=0,k=0;

    // ADD YOUR OWN DECLARATIONS HERE. ++++


//// SETUP:  size and table
void setup() {
    size( 600, 400 );          
    left=150;
    right=width-50;
    top=100;
    bottom=height-50;
    middle=left+(right-left)/2;
    reset();
    
 }
void reset(){
   // Random positions.
   eX=  random( middle,right );   eY=  random( top, bottom );
   rX=  random( middle,right );   rY=  random( top, bottom );
   lX=  random( middle,right );   lY=  random( top, bottom );
   // Random speeds
   eDX=  random( 1,3);   eDY=  random( 1,3 );
   rDX=  random( 1,3 );   rDY=  random( 1,3 );
   lDX=  random( 1,3 );   lDY=  random( 1,3 );
}

//// NEXT FRAME:  table, bounce off walls, collisions, show all
void draw() {
  background( 250,250,200 );
  rectMode( CORNERS );
  table( left, top, right, bottom );
  buttons();
  bounce();
  collisions();
  show();
  messages();
}


//// HANDLERS:  keys, click
void keyPressed() {
  if (key == 'q') { exit();  }
  if (key == 'r') {reset(); }
}


//// SCENE:  draw the table with wall down the middle
void table( float left,float top, float right, float bottom ) {
  fill( tableRed, tableGreen, tableBlue );    // pool table
  strokeWeight(20);
  stroke( 127, 0, 0 );      // Brown walls
  rect( left-20, top-20, right+20, bottom+20 );

            //++++ MODIFY THIS CODE, as necessary. ++++

  // Start with a WALL down the middle of the table! 
  if (wall) {
    float middle=  (right+left)/2;    
    stroke( 0, 127, 0 );
    line( middle,top+10, middle,bottom-10 );
  }
  stroke(0);
  strokeWeight(1);
  
   
}

//// ACTION:  bounce off walls, collisions
void bounce() {
  ///eevee bounce
    eX += eDX;  if ( eX<=left || eX>=right ) eDX *= -1;
    eY += eDY;  if ( eY<=top || eY>=bottom ) eDY *=  -1;
    
  /// rose bounce
   rX += rDX;  if ( rX<left || rX>right ) rDX *= -1;
  rY += rDY;  if ( rY<top || rY>bottom ) rDY*=-1;
  
  //leaf bounce
   lX += lDX;  if ( lX<left || lX>right ) lDX *=-1;
   lY += lDY;  if ( lY<top || lY>bottom ) lDY *=  -1;
}
void collisions() {
  float tmp;
   // Swap velocities!
     //collion between eevee and rose
        if ( dist( eX,eY, rX,rY ) < 30 ) {
    tmp=rDX;  rDX=eDX;  eDX=tmp;
    tmp=rDY;  rDY=eDY;  eDY=tmp;
    
  }
        
        
     //collions between eevee and leaf
        if (dist(eX,eY, lX, lY) < 30) {
    tmp=lDX;  lDX=eDX;  eDX=tmp;
    tmp=lDY;  lDY=eDY;  eDY=tmp;
}
        
     //collions between leaf and rose
       if (dist(rX,rY,lX,lY)< 30){
    tmp=lDX; lDX=rDX; rDX=tmp;
    tmp=lDY; lDY=rDY; rDY=tmp;
  }
}


//// SHOW:  balls, messages, etc.
void show() {
    //balls
      //eevee ball
        fill( 255,0,0 );        ellipse( eX,eY, 30,30 );
        fill(0); text("Eevee", eX-10, eY+3);
      //rose ball
        fill( 255,255,0 );      ellipse( rX,rY, 30,30 );
        fill(0); text("Rose", rX-10, rY+3);
      //leaf ball
        fill( 0,0,255 );        ellipse( lX,lY, 30,30 );
        fill(255); text("Leaf", lX-10, lY+3);
}
void buttons() {
    
 //reset button
 // fill(0);
  //rect(10,10,50,50);
 // text("Reset", 60,30);


}
void messages() {
  fill(0);
  text( title, width/3, 15 );
  text( news, width/5, 30 );
  text( author, 10, height-5 );

  // Also, display the number of collisions.

    //++++ ADD YOUR OWN CODE HERE. ++++

}
