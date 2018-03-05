import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.Random; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class BrushPaint extends PApplet {



Particle[][] particle;
PVector centralPoint;
int _cellSize;
int _numX;
int _numY;
float _sd;
float _num;
float _mean;
Random _generator;


public void setup( ) {
  
  background(255);
  

  centralPoint = new PVector(width/2, height/2);
  _cellSize = 5;
  _numX = width/_cellSize;
  _numY = height/_cellSize;
  _sd = max(width, height)/6;
  _num = abs((float)_generator.nextGaussian());
  _mean = 0;

  particle = new Particle[_numX][_numY];
  for (int i=0; i<_numX; i++){
    for (int j=0; j<_numY; j++){
      Particle pIJ = new Particle (_cellSize*i, _cellSize*j, _cellSize, centralPoint, _sd, _num, _mean);
      particle[i][j] = pIJ;
    }
  }
}

public void draw( ) {
  for (int i=0; i<_numX; i++){
    for (int j=0; j<_numY; j++){
      particle[i][j].update();
    }
  }
}

class Particle{
  boolean state; //\u5857\u308a\u3064\u3076\u3055\u308c\u3066\u3044\u308b\u304b\u5426\u304b
  PVector location; //\u4f4d\u7f6e
  PVector relativeLocation; //\u4e2d\u5fc3\u304b\u3089\u306e\u4f4d\u7f6e
  float distance; //\u4e2d\u5fc3\u304b\u3089\u306e\u8ddd\u96e2
  float probability; //\u6b21\u306b\u5857\u308a\u3064\u3076\u3055\u308c\u308b\u78ba\u7387
  int cellSize;

  Particle(int x, int y, int cS, PVector cP, float s, float n, float m){
    state = false; //\u6700\u521d\u306f\u767d\u304b\u3089\u30b9\u30bf\u30fc\u30c8
    location.x = x; //x\u5ea7\u6a19
    location.y = y; //y\u5ea7\u6a19
    relativeLocation = PVector.sub(location, cP);
    distance = relativeLocation.mag();
    probability = n;
    cellSize = cS;

    noStroke();
  }

  public void calcNextProbability( ) {
    //coming soon...
  }

  public void calcNextState( ) {
    float ran = random(1);
    if (ran < probability){
      state = true;
    }
  }

  public void drawMe( ) {
    if (state == true){
      fill(0);
    }
    if (state == false){
      fill(255);
    }
    rect(location.x, location.y, cellSize, cellSize);
  }

  public void update(){
    calcNextProbability();
    calcNextState();
    drawMe();
  }
}
  public void settings() {  size(300,300);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "BrushPaint" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
