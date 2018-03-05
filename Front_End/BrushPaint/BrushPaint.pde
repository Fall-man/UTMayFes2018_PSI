import java.util.Random;

Particle[][] particle;
PVector centralPoint;
int _cellSize;
int _numX;
int _numY;
float _sd;
float _num;
float _mean;
Random _generator;


void setup( ) {
  size(300,300);
  background(255);
  smooth();

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

void draw( ) {
  for (int i=0; i<_numX; i++){
    for (int j=0; j<_numY; j++){
      particle[i][j].update();
    }
  }
}
