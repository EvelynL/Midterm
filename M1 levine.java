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
int num=1;
int collisions;           


//// OTHER GLOBALS:  strings, pool table, etc ////

String title=  "CST112 MIDTERM EXAM";
String news=   "hit 1 rest Eevee, hit 2 to rests Rose, hit 3 to rests Leaf (r resets all.)";
String author=  "Evelyn Levine";

float left, right, top, bottom;        // Table boundaries
float middle;
boolean wall=true;

boolean mouse=false;            //for mouse
float miceX, miceY, miceDX;
int frame;                      //frame counter

int tableRed=150, tableGreen=250, tableBlue=150;      // green pool table
int score=0,m=0,k=0;

    // ADD YOUR OWN DECLARATIONS HERE. ++++


//// SETUP:  size and table
void setup() {
    size( 640, 480 );          
    left=50;
    right=width-50;
    top=150;
    bottom=height-80;
    middle=left+(right-left)/2;
    miceX=40;
    miceY=450;
    miceDX=1;
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
  
  //table color back to green. 
  tableRed=150; tableGreen=250; tableBlue=150;
  
  //wall back.
    wall=true;
    middle=320;
 
  //mice.
    mouse=false;
    
  //reset collision to 0.
    collisions=0;
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
  if(wall){
    //eevee wall
      eX += eDX;  if ( eX<middle || eX>right ) eDX *= -1;
      eY += eDY;  if ( eY<top || eY>bottom ) eDY *= -1;
    //wall rose
      rX += rDX;  if ( rX<middle || rX>right ) rDX *= -1;
      rY += rDY;  if ( rY<top || rY>bottom ) rDY *= -1;
    //leaf wall
      lX += lDX;  if ( lX<middle || lX>right ) lDX *= -1;
      lY += lDY;  if ( lY<top || lY>bottom ) lDY *= -1;
  }else{ //no walls
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
}
void collisions() {
  float tmp;
   // Swap velocities!
     //collion between eevee and rose
        if ( dist( eX,eY, rX,rY ) < 30 ) {
    tmp=rDX;  rDX=eDX;  eDX=tmp;
    tmp=rDY;  rDY=eDY;  eDY=tmp;
    collisions +=1;
  }
        
        
     //collions between eevee and leaf
        if (dist(eX,eY, lX, lY) < 30) {
    tmp=lDX;  lDX=eDX;  eDX=tmp;
    tmp=lDY;  lDY=eDY;  eDY=tmp;
    collisions +=1;
}
        
     //collions between leaf and rose
       if (dist(rX,rY,lX,lY)< 30){
    tmp=lDX; lDX=rDX; rDX=tmp;
    tmp=lDY; lDY=rDY; rDY=tmp;
    collisions +=1;
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
        
        if (mouse) {
    fill(0);
    ellipse(miceX,miceY, 20,20);
    fill(0,128,255);
    ellipse(miceX+5, miceY, 5,5);
    fill(0);
    triangle(miceX+10,miceY+5,miceX+10,miceY-5,miceX+20,miceY+5);
    line( miceX, miceY, miceX-15, miceY);
    ellipse(miceX,miceY-10,10,10);
   
    //move the mice.
    miceX += miceDX;
    miceX %= width+(width/10); 
    
    //moving legs.
    int k= frameCount/30%2;
    if (k==0) {
     fill(160,150,150);
     line( miceX, miceY, miceX-10, miceY+15);
     line( miceX, miceY, miceX+10, miceY+15);
    }
    
    else {
      fill(160,150,150);
      line( miceX, miceY, miceX-15, miceY+15);
      line( miceX, miceY, miceX+15, miceY+15);
    }
    
  }
 
}

void buttons() {
    
  //click inside rectangles to do the things.
  rectMode(CORNER);
  fill(120,0,0,5);
  rect(25,40, 60,25);
  fill(0);
  text( "RESET" , 35, 60);
 
  fill(120,0,0,5);
  rect(120,40, 100,25);
  fill(0);
  text( "WALL REMOVE" , 130, 60);
  
  fill(120,0,0,5);
  rect(250,40, 100,25);
  fill(0);
  text( "PINK TABLE" , 260, 60);
  
  fill(120,0,0,5);
  rect(380,40, 100,25);
  fill(0);
  text( "ADD MICE" , 390, 60);
  
}



void mousePressed() {
   //reset
   if ( mouseX>25 && mouseX<85 && mouseY>40 && mouseY<65){
    reset();
  }

   //change table to pink
   if ( mouseX>250 && mouseX<350 && mouseY>40 && mouseY<65){
   tableRed=255; tableGreen=204; tableBlue=229; 
  }
   
  // remove the wall
  if ( mouseX>120 && mouseX<220 && mouseY>40 && mouseY<65){
   wall=!wall;
   middle=left-20;
  }

  //add mice and move.
  if ( mouseX>380 && mouseX<480 && mouseY>40 && mouseY<65){
   mouse=true;
  }

}




void messages() {
  fill(0);
  text( title, width/3, 15 );
  text( news, width/5, 30 );
  text( author, 10, height-5 );
  text("or press w to remove wall",100,80);
  text("or hit p for pink table",250,80);
  text("or hit m for mouse",380,80);
  text( "collisions=", 135, height-5);
  text( collisions, 200, height-5);

  // Also, display the number of collisions.

    //++++ ADD YOUR OWN CODE HERE. ++++

}


//// HANDLERS:  keys, click
void keyPressed() {
  if (key == 'q') { exit();  }
  if (key == 'r') {reset(); }
  if (key == 'w') {wall = false;}
  if (key == 'p') {tableRed = 255; tableGreen = 204; tableBlue = 229;}
  if (key == 'm') {mouse = true;}
  if (key == '1' ) {
   eX=  random( 350,right );     eY=  random( top, bottom );
   eDX=  random( 1,3 );     eDY=  random( 1,3 );
  }
  
  if (key == '2' ) {
   rX=  random( 360,right );   rY=  random( top, bottom );
   rDX=  random( 1,3 );   rDY=  random( 1,3 );
  }
  
  if (key == '3' ) {
  lX=  random( 340,right );    lY=  random( top, bottom );
  lDX=  random( 1,3 );    lDY=  random( 1,3 );
  }
}


