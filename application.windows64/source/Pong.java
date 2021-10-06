import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Pong extends PApplet {

float teclaY,teclaY2,bolaY,bolaX;
boolean gamec,colisor1,colisor2,cf,hy,vert1,vert2,vert3,vert4,game;
int c,contw,conts,conti,contk;float speed;
int placar1,placar2;
public void setup (){
  
   background(0);
  teclaY=250;conts=0;contw=0;
  vert1=false;
  vert2=false;
  vert3=false;
  vert4=false;
  game=false;
  contk=0;conti=0;
  teclaY2=250;placar1=0;placar2=0;
  bolaY=300;
  bolaX=500;speed=5;
  colisor1=false;
  colisor2=false;
  hy=false;
  c=PApplet.parseInt(random(0,2));
  if (c==1){
    gamec=true;
  }else{
    gamec=false;
  }
  cf=true;
  //System.out.println(c);
}
public void paredes(){
  rect(0,0,1000,6);
  rect(0,594,1000,6);
  if (bolaY==0){
     if(vert1==true){
       speed=5;
      vert2=true;
      vert1=false;
    }
    if(vert3==true){
      speed=5;
      vert4=true;
      vert3=false;
    }
  }
  if (bolaY==600){
    if(vert2==true){
      speed=5;
      vert2=false;
      vert1=true;
    }
    if(vert4==true){
      speed=5;
      vert4=false;
      vert3=true;
    }
  }
}
public void draw(){
  if(game==true){
  background(0);
  textSize(15);
  text("Developed by Leon ",30,590);
  placar1();
  placar2();
  System.out.println("bola "+bolaY);
  System.out.println("y "+teclaY);
  System.out.println("y2 "+teclaY2);
  paredes();
  if(teclaY<0){
    teclaY=0;
  }
  if(teclaY>500){
    teclaY=500;
  }
  if(teclaY2<0){
    teclaY2=0;
  }
  if(teclaY2>500){
    teclaY2=500;
  }
  fill(255,255,255);
  rect(500,5,7,600);
  rect(80,teclaY,20,100);
  rect(900,teclaY2,20,100);
  verificar();
  bola();
  if (bolaY>=teclaY && bolaY<=teclaY+100 && bolaX>=80 && bolaX<=100){
     if (speed<=20){
       speed+=0.5f;
      }
      if(contw>=3){
        vert1=true;
         
      }else if(conts>=3){
        vert2=true;
         
      }else{
        vert1=false;
        vert2=false;
      }
    colisor1=true;
  }else if(bolaY>=teclaY2 && bolaY<=teclaY2+100 && bolaX>=900 && bolaX<=920){
    if (speed<=20){
       speed+=0.5f;
    }
    if(conti>=3){
        vert3=true;
        speed=5;
      }else if(contk>=3){
        vert4=true;
         speed=5;
      }else{
        vert3=false;
        vert4=false;
      }
    colisor2=true;
  }
  reiniciar();
  }else{
    fill(255,255,255);
    textSize(45);
    text("THE PONG GAME",360,55);
    fill(255,255,255);
    rect(450,250,140,60);
    textSize(15);
    fill(0,0,0);
    text("INICIAR",480,290);
  }
 // System.out.println(speed);
}
public void reiniciar(){
  if(bolaX>950){
    bolaX=500;
    bolaY=300;
    speed=4.5f;
    gamec=true;
    placar2++;
    placar2();
    vert1=false;
    vert2=false;
    vert3=false;
    vert4=false;
  }
  if(bolaX<60){
    bolaX=500;
    bolaY=300;
    speed=4.5f;
    gamec=false;
    placar1++;
    placar1();
    vert1=false;
    vert2=false;
    vert3=false;
    vert4=false;
  }
}
public void placar1(){
    textSize(50);
    text(placar1,900,60);
}
public void placar2(){
    textSize(50);
    text(placar2,100,60);
}
public void mouseClicked(){
  if(mouseX>=310 && mouseX<=590 && mouseY>=90 && mouseY<=310){
    game=true;
  }
}
public void keyPressed(){
  if (key=='w'){
    contw++;
    conts=0;
    conti=0;
    contk=0;
  }else if(key=='s'){
     contw=0;
    conts++;
    conti=0;
    contk=0;
  }else if(key=='i'){
     contw=0;
    conts=0;
    conti++;
    contk=0;
  }else if(key=='k'){
     contw=0;
    conts=0;
    conti=0;
    contk++;
  }
  if (key=='w'){
    teclaY-=30;
  }
  if (key=='s'){
    teclaY+=30;
  }
  if (key=='i'){
    teclaY2-=30;
  }
  if(key=='k'){
    teclaY2+=30;
  }
}
public void verificar(){
  if (colisor1==true){
    gamec=false;
    colisor1=false;
  }else if (colisor2==true){
    gamec=true;
    colisor2=false;
  }
}
public void bola(){
  if (gamec==true){
     if(vert3==true){
       noStroke();
      circle(bolaX-=speed,bolaY-=5,20);
    }
    if(vert4==true){
       noStroke();
      circle(bolaX-=speed,bolaY+=5,20);
    }else{
    noStroke();
    circle(bolaX-=speed,bolaY,20);
  }
  }else if (gamec==false){
    if(vert1==true){
       noStroke();
      circle(bolaX+=speed,bolaY-=5,20);
    }
    if(vert2==true){
       noStroke();
      circle(bolaX+=speed,bolaY+=5,20);
    }else{
    noStroke();
    circle(bolaX+=speed,bolaY,20);
  }}
}
  public void settings() {  size(1000,600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Pong" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
