
class Particle{
  boolean state; //塗りつぶされているか否か
  PVector location; //位置
  PVector relativeLocation; //中心からの位置
  float distance; //中心からの距離
  float probability; //次に塗りつぶされる確率
  int cellSize;

  Particle(int x, int y, int cS, PVector cP, float s, float n, float m){
    state = false; //最初は白からスタート
    location.x = x; //x座標
    location.y = y; //y座標
    relativeLocation = PVector.sub(location, cP);
    distance = relativeLocation.mag();
    probability = n;
    cellSize = cS;

    noStroke();
  }

  void calcNextProbability( ) {
    //coming soon...
  }

  void calcNextState( ) {
    float ran = random(1);
    if (ran < probability){
      state = true;
    }
  }

  void drawMe( ) {
    if (state == true){
      fill(0);
    }
    if (state == false){
      fill(255);
    }
    rect(location.x, location.y, cellSize, cellSize);
  }

  void update(){
    calcNextProbability();
    calcNextState();
    drawMe();
  }
}
