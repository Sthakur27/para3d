//http://aleph0.clarku.edu/~djoyce/ma131/gallery.pdf
//http://paulbourke.net/geometry/klein/
//http://arxiv.org/pdf/0909.5354.pdf
/*
Fixedish:  Note: For some reason, do not start or end v parameter on "0"  
*/
FloatList xvals=new FloatList();
FloatList yvals=new FloatList();
FloatList zvals=new FloatList();
IntList asymptotepoints=new IntList();
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
int typing=0;  //typing changes meaning based on number   0:not typing  1: typing x exp 2: typing y exp 3: typing z exp  4: typing u start 5: typing u end   6: z start 7: zend
float xmaxval=0;
float ymaxval=0;
float zmaxval=0;
float ry=0;
float rx=0;
int numofintervals=80;
float rz=0;
float zmax; float zmin;
float dheight;
float depth=0;
boolean paused=false;
Boolean autorotatingForward=true;

//x  y z expressions and parameters u and v  (Copy Paste from examples to here)
String ustartval="-2*p";
String uendval="2*p";
String vstartval="0";
String vendval="p";
String xexp="cosu*sinv";
String yexp="sinu*sinv";
String zexp="(log(tan(0.5*v))+cosv)+0.2*u";

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
    else if(rchoose==1){
      text("X-Z axis Tilt",10,80,0);
    }
    else {
      text("Y-Z axis Tilt",10,80,0);
    }
    //highlighting what expresssions you are typing in red
    if (typing==1){fill(#f42121);}
    text("x= "+xexp,10,20,0);    fill(0);
    if (typing==2){fill(#f42121);}
    text("y= "+yexp,10,40,0);   fill(0);
    if (typing==3){fill(#f42121);}
    text("z= "+zexp,10,60,0);    fill(0);
    
    textAlign(RIGHT);
    if(typing==4||typing==5){fill(#f42121);}
    text(ustartval+"≤u≤"+uendval,width-10,20,0); fill(0);
    if(typing==6||typing==7){fill(#f42121);}
    text(vstartval+"≤v≤"+vendval,width-10,40,0); fill(0);
    textAlign(LEFT);
    
    translate(width/2,height/2,depth);
    rotateY(timer2*PI/180);
    rotateY(ry);
    if(!paused){ if(autorotatingForward){
       timer2++;}else{timer2-=1;}}
    if(timer2>360||timer2<-360){timer2=0;}
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
    //<xvals.size()-1
    for (int i=0;i<xvals.size()-2;i++){
      //i!=80  i!=161
        if((i+1)%(numofintervals+1)!=0){
        drawV(i);
      }

        if(i<xvals.size()-(numofintervals+1)){
          drawU(i);     
      }  
    }
    drawV(xvals.size()-2);
    if(timer<360){timer+=3;}
    dheight=height;
}
void drawV(int i){
  
  line(xscale*xvals.get(i),zscale*zvals.get(i),yscale*yvals.get(i),xscale*xvals.get(i+1),zscale*zvals.get(i+1),yscale*yvals.get(i+1));
}

void drawU(int i){
  //if(!asymptotepoints.hasValue(i)){
  line(xscale*xvals.get(i),zscale*zvals.get(i),yscale*yvals.get(i),xscale*xvals.get(i+numofintervals+1),zscale*zvals.get(i+numofintervals+1),yscale*yvals.get(i+numofintervals+1));
}
void calculate(){
  xvals.clear(); yvals.clear(); zvals.clear();
  //println(parse.interp(ustartval));
  parse.parainterp(xexp,yexp,zexp,parse.interp(ustartval),parse.interp(uendval),parse.interp(vstartval),parse.interp(vendval),numofintervals);
  for (int i=0;i<parse.xreturnlist.size();i++){
       xvals.append(10*parse.xreturnlist.get(i).floatValue());
       yvals.append(10*parse.yreturnlist.get(i).floatValue());
       zvals.append(-10*parse.zreturnlist.get(i).floatValue());
   }
  rescale(xvals, xmaxval); rescale(yvals, ymaxval); rescale(zvals,zmaxval);
  //println(xvals.size()+"  "+ yvals.size()+"  " +zvals.size());
}

void rescale(FloatList list, float maxval){
  maxval=0;
  int sum=0;
  autoscale=1;
  boolean containsinfinity=false;
  for (int j=0;j<list.size();j++){
    if((100/abs(list.get(j)))==0){
        containsinfinity=false;
        //println(list.get(j));
    }    
  }
  for (int i=0;i<list.size();i++){
        if (abs(list.get(i))>maxval){
              maxval=abs(list.get(i));
              if(100/maxval==0){
                  if(i-1>=0){
                  maxval=abs(list.get(i-1));
                  break;
                  }
                  else{
                    maxval=abs(list.get(i+1));
                    break;
                  }
              }
        }
  }
  if(containsinfinity||maxval>1000000){
    for (int i=0;i<list.size();i++){
        sum+=list.get(i);          
    }
    sum/=list.size()-1;
    if(sum==0){autoscale=1;}
    else{
      autoscale=100/sum;}
    if (autoscale==0){autoscale=5; }
    for (int i=0;i<list.size();i++){
       list.mult(i,autoscale);
    }
  }
  else{
    //println(maxval);
    if(maxval==0){autoscale=1;}
    else{
      autoscale=100/maxval;}
    if (autoscale==0){autoscale=5; }
    for (int i=0;i<list.size();i++){
       list.mult(i,autoscale);
    }    
  }
}

void rotate(){
  rotateX(rx);
  rotateZ(rz);
}


void keyPressed(){
   if(key=='f'||key=='F'){
     rchoose++;
     if(rchoose>1){rchoose=0;}
   }
   
   if((key=='a'||key=='A')&& typing==0){   if(axis){axis=false;} else{axis=true;}   }
   if((key=='b'||key=='B')){   if(autorotatingForward){autorotatingForward=false;} else{autorotatingForward=true;}   }
   
   if(key=='s'||key=='S'){
       xscale=dheight/450;
       yscale=dheight/450;
       zscale=dheight/450;
   }

   if(key=='r'||key=='R'){
      rx=0; rz=0;  timer2=0; depth=0;
   }
   if(key=='x'||key=='X'){
      rchoose=3;
   }
   if(key=='y'||key=='Y'){
      rchoose=2;
   }
   if(key=='z'||key=='Z'){
      rchoose=4;
   }
   if(key=='d'||key=='D'){
      println("dank");
   }
   if((key=='p'||key=='P')&&typing==0){
      println("String ustartval=\""+ustartval+"\";");
      println("String uendval=\""+uendval+"\";");
      println("String vstartval=\""+vstartval+"\";");
      println("String vendval=\""+vendval+"\";");
      println("String xexp=\""+xexp+"\";"); println("String yexp=\""+yexp+"\";"); println("String zexp=\""+zexp+"\";");println("");
   }
   if(keyCode==LEFT){
      ry-=5*PI/180;
   }
   if(keyCode==RIGHT){
      ry+=5*PI/180;
   }
   if(keyCode==DOWN){
      depth+=20;
   }
   if(keyCode==UP){
      depth-=20;
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
       else if(typing==4){ 
          typing=5; uendval="";}
       else if(typing==5){ 
          typing=0; rx=0; rz=0; timer2=0;  calculate();}
       else if(typing==6){ 
          typing=7; vendval="";}
       else if(typing==7){ 
          typing=0; rx=0; rz=0; timer2=0;  calculate();}
   }
   if(typing!=0){
      if(keyCode!=SHIFT && keyCode!=ENTER && keyCode!=BACKSPACE && keyCode!=DELETE){
         if(typing==1){xexp=xexp+Character.toString(key);}
         if(typing==2){yexp=yexp+Character.toString(key);}
         if(typing==3){zexp=zexp+Character.toString(key);}
         if(typing==4){ustartval=ustartval+Character.toString(key);}
         if(typing==5){uendval=uendval+Character.toString(key);}
         if(typing==6){vstartval=vstartval+Character.toString(key);}
         if(typing==7){vendval=vendval+Character.toString(key);}
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
      if(typing==4){ustartval=ustartval.substring(0,ustartval.length()-1);
      }
      if(typing==5){uendval=uendval.substring(0,uendval.length()-1);
      }
      if(typing==6){vstartval=vstartval.substring(0,vstartval.length()-1);
      }
      if(typing==7){vendval=vendval.substring(0,vendval.length()-1);
      }
   }
    if((key=='u'||key=='U') && typing==0){
      typing=4; ustartval="";
   }
   if((key=='v'||key=='V') && typing==0){
      typing=6; vstartval="";
   }
}
void mouseClicked(){
  if(!paused){paused=true;}else{   paused=false;}
}
void mouseWheel(MouseEvent event) {
  int e = event.getCount();
  if(rchoose==0){
     rx-=5*e*PI/180;
  }
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