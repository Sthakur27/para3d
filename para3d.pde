//http://aleph0.clarku.edu/~djoyce/ma131/gallery.pdf
/*sin(u)*sinv
cosu*sinv
cosu

*/
FloatList xvals=new FloatList();
FloatList yvals=new FloatList();
FloatList zvals=new FloatList();
boolean line=true;
boolean axis=true;
int rchoose=1;
boolean scaleon=true;
int timer=1;
int timer2=1;
float xscale=1;
float yscale=1;
float zscale=1;
float autoscale=1;
int typing=0;
float maxval=0;
float ry=0;
float rx=0;
double startval=-2*PI;
double endval=2*PI;
int numofintervals=80;
float rz=0;
float zmax; float zmin;
float dheight;
boolean paused=false;
//x  y z expressions
/*String xexp="sinu";
String yexp="cosu";
String zexp="v";*/
/*String xexp="v*sinu";
String yexp="v*cosu";
String zexp="v";*/
//d=4  c=3 a=1 b=3
/*String xexp="(4*(3-cosu*cosv)+9*cosu)/(1-3*cosu*cosv)";
String yexp="(3*sinu*(1-4*cosv))/(1-3*cosu*cosv)";
String zexp="(3*sinv*(3*cosu-4))/(1-3*cosu*cosv)";*/
//mobius strip:
String xexp="(1+(v/2)*cos(u/2))*cosu";
String yexp="(1+(v/2)*cos(u/2))*sinu";
String zexp="(v/2)*sin(u/2)";
//mob#2
/*String xexp="cos(u/2)+cos(u/4)*cos(v/2)";
String yexp="sin(u/2)+cos(u/4)*sin(v/2)";
String zexp="sin(u/4+p/2)";*/
//torus!
/*String xexp="(3+cosv)*cosu";
String yexp="(3+cosv)*sinu";
String zexp="5*sinv";*/
//Klein bottle
/*
String xexp="(3+cos(u/2)*sinv-sin(u/2)*sin(v*2))*cosu";
String yexp="(3+cos(u/2)*sinv-sin(u/2)*sin(v*2))*sinu";
String zexp="sin(u/2)*sinv+cos(u/2)*sin(2*v)";*/
/*String xexp="sinv*(1+u^2)^0.5";
String yexp="cosv*(1+u^2)^0.5";
String zexp="u";*/
String tempexp="";
void setup(){
      size(500, 450,P3D);
      //parse.test();
      calculate();
      dheight=height;
      surface.setResizable(true);
}

void draw(){
    background(255,255,255);
    fill(0);
    if(rchoose==2){
      text("Scroll Mode Y axis stretch",10,80,0);
    }
    else if(rchoose==3){
      text("Scroll Mode X axis stretch",10,80,0);
    }
    else if(rchoose==4){
      text("Scroll Mode Z axis stretch",10,80,0);
    }
    else{
      text("X-Y axis Tilt",10,80,0);
    }
    if (typing!=0){fill(#f42121);}
    text("x= "+xexp,10,20,0);
    text("y= "+yexp,10,40,0);
    text("z= "+zexp,10,60,0);
    fill(0);
    textAlign(RIGHT);
    text(zmin+"≤z≤"+zmax,width-10,20,0);
    textAlign(LEFT);
    translate(width/2,height/2,0);
    rotateY(timer2*PI/180);
    rotateY(ry);
    if(!paused){
       timer2++;}
    if(timer2>360){timer2=0;}
    rotate();
    stroke(0,0,0);   
    
    //draw axis    
    if(axis){
        textSize(15); fill(0);
        
        //x axis
        line(-150*xscale,0,0,150*xscale,0,0);
        text("X",105*xscale,0,0);
        
        //z
        line(0,-150*zscale,0,0,150*zscale,0);        
        text("Z",0,-105*zscale,0);
        
        //y
        line(0,0,-150*yscale,0,0,150*yscale);        
        text("Y",0,0,105*yscale);
    }
    
    stroke(#aa03eb);
    
    //draw function
    //xvals.size()-2
    for (int i=0;i<xvals.size()-2;i++){
      //i!=80  i!=161
        if((i+1)%81!=0){
        drawV(i);}
        else{//print(i);
      }
        if(i<xvals.size()-(numofintervals+1)){
          drawU(i);     
      }  
    }
    if(timer<360){timer+=3;}
    dheight=height;
}
void drawV(int i){
  line(xscale*xvals.get(i),zscale*zvals.get(i),yscale*yvals.get(i),xscale*xvals.get(i+1),zscale*zvals.get(i+1),yscale*yvals.get(i+1)); 
}

void drawU(int i){
  line(xscale*xvals.get(i),zscale*zvals.get(i),yscale*yvals.get(i),xscale*xvals.get(i+numofintervals+1),zscale*zvals.get(i+numofintervals+1),yscale*yvals.get(i+numofintervals+1));
}
void calculate(){
  xvals.clear(); yvals.clear(); zvals.clear();
  parse.parainterp(xexp,yexp,zexp,startval,endval,(endval-startval)/numofintervals);
  for (int i=0;i<parse.xreturnlist.size();i++){
       xvals.append(10*parse.xreturnlist.get(i).floatValue());
       yvals.append(10*parse.yreturnlist.get(i).floatValue());
       zvals.append(-10*parse.zreturnlist.get(i).floatValue());
   }
  zmax=0;
  zmin=0;
  for(int i=0;i<zvals.size();i++){
     if(-1*zvals.get(i)>zmax){
        zmax=-1*zvals.get(i);
     }
     if(-1*zvals.get(i)<zmin){
        zmin=-1*zvals.get(i);
     }
  }
  zmax/=10; zmin/=10;  
  rescale(xvals); rescale(yvals); rescale(zvals);
}

void rescale(FloatList list){
  maxval=0;
  autoscale=1;
  for (int i=0;i<list.size();i++){
         if (abs(list.get(i))>maxval){
            maxval=abs(list.get(i));
            if(100/maxval==0){
                maxval=abs(list.get(i-1));
                break;
            }
         }
  }
  if(maxval==0){autoscale=1;}
  else{
    autoscale=100/maxval;}
  if (autoscale==0){autoscale=5; }
  for (int i=0;i<list.size();i++){
     list.mult(i,autoscale);
  }
}

void rotate(){
  rotateX(rx);
  rotateZ(rz);
}


void keyPressed(){
   if(key=='f'||key=='F'){
     rchoose++;
     if(rchoose>4){rchoose=1;}
   }
   
   if((key=='a'||key=='A')&& typing==0){   if(axis){axis=false;} else{axis=true;}   }
   
   if(key=='s'||key=='S'){
       xscale=dheight/450;
       yscale=dheight/450;
       zscale=dheight/450;
   }

   if(key=='r'||key=='R'){
      rx=0; rz=0;  timer2=0; 
       xscale=dheight/450;
       yscale=dheight/450;
       zscale=dheight/450;
   }
   if((key=='p'||key=='P')&&typing==0){
      println(xexp); println(yexp); println(zexp);println("");
   }
   if(keyCode==LEFT){
      ry-=5*PI/180;
   }
   if(keyCode==RIGHT){
      ry+=5*PI/180;
   }
   if(keyCode==ENTER){
       if(typing==0){typing++; xexp=""; 
       } 
       else if(typing==1){
       yexp=""; typing++;}
       else if(typing==2){
       zexp=""; typing++;}
       else if(typing==3){ 
          typing=0; rx=0; rz=0; timer2=0;  calculate();}
   }
   if(typing!=0){
      if(keyCode!=SHIFT && keyCode!=ENTER && keyCode!=BACKSPACE && keyCode!=DELETE){
         if(typing==1){xexp=xexp+Character.toString(key);}
         if(typing==2){yexp=yexp+Character.toString(key);}
         if(typing==3){zexp=zexp+Character.toString(key);}
      //print(key);
      }
   }
   if((keyCode==DELETE||keyCode==BACKSPACE)){
      if(typing==1){
      xexp=xexp.substring(0,xexp.length()-1);}
      if (typing ==2){
        yexp=yexp.substring(0,yexp.length()-1);
      }
      if(typing==3){zexp=zexp.substring(0,zexp.length()-1);
      }
   }
}
void mouseClicked(){
  if(!paused){paused=true;}else{   paused=false;}
}
void mouseWheel(MouseEvent event) {
  int e = event.getCount();
  if(rchoose==1){
    rz-=5*e*PI/180;    
  }
  if(rchoose==2){
    if(e>0){ yscale=yscale/1.1;}
    else{yscale=yscale*1.1;} 
  }
  if(rchoose==3){
    if(e>0){ xscale=xscale/1.1;}
    else{xscale=xscale*1.1;} 
  }
  if(rchoose==4){
    if(e>0){ zscale=zscale/1.1;}
    else{zscale=zscale*1.1;}   
  }
}