//http://aleph0.clarku.edu/~djoyce/ma131/gallery.pdf
//http://paulbourke.net/geometry/klein/
//http://arxiv.org/pdf/0909.5354.pdf
FloatList xvals=new FloatList(); 
FloatList yvals=new FloatList();
FloatList zvals=new FloatList();
ArrayList<Color>colorList=new ArrayList();
boolean line=true;
boolean clicktype=false;
int rchoose=1;
int timer=1;
int timer2=1;
float xscale=1; float yscale=1; float zscale=1;
int typing=0;  //typing changes meaning based on number   0:not typing  1: typing x exp 2: typing y exp 3: typing z exp  4: typing u start 5: typing u end   6: z start 7: zend
float xmaxval=0; float ymaxval=0; float zmaxval=0;
float ry=0; float rx=0;float rz=0;
float xtranslate=0; float ytranslate=0; float ztranslate=0;
int numofintervals=80;
float zmax; float zmin;
float dheight;
boolean paused=false;
Boolean autorotatingForward=true;
Boolean displayon=true;
int colored=1; //keep clicking 'c' to cycle thru color modes.
int backgroundcolor=255;

//x  y z expressions and parameters u and v  (Copy Paste from examples to here)
String ustartval="-14";
String uendval="14";
String vstartval="-37.4";
String vendval="37.4";
String zexp="0.25*(-u+(2*(0.84)*cosh(0.4*u)*sinh(0.4*u))/(0.4*(0.84*(cosh(0.4*u)^2))+0.16*(sin(0.9165*v)^2)))";
String yexp="(0.84*cosh(0.4*u)*(-0.9165*cosv*cos(0.9165*v)-sinv*sin(0.9165*v)))/(0.4*(0.84*(cosh(0.4*u)^2)+0.16*(sin(0.9165*v)^2)))";
String xexp="(0.84*cosh(0.4*u)*(-0.9165*sinv*cos(0.9165*v)+cosv*sin(0.9165*v)))/(0.4*(0.84*(cosh(0.4*u)^2)+0.16*(sin(0.9165*v)^2)))";


String tempexp="";
void setup(){
      size(500, 450,P3D);
      dheight=height;
      surface.setResizable(true);
      calculate();
}


void draw(){
    background(backgroundcolor);
    if(displayon){
        fill(255-backgroundcolor);
        if(rchoose==3){
          text("Scroll Mode Y axis stretch",10,80,0);
        }
        else if(rchoose==4){
          text("Scroll Mode X axis stretch",10,80,0);
        }
        else if(rchoose==5){
          text("Scroll Mode Z axis stretch",10,80,0);
        }
        else if(rchoose==2){
          text("Window Depth",10,80,0);
        }
        else if(rchoose==1){
          text("X-Z axis Tilt",10,80,0);
        }
        else {
          text("Y-Z axis Tilt",10,80,0);
        }
        //highlighting what expresssions you are typing in red
        if (typing==1){fill(#f42121);}
        text("x= "+xexp,10,20,0);    fill(255-backgroundcolor);
        if (typing==2){fill(#f42121);}
        text("y= "+yexp,10,40,0);   fill(255-backgroundcolor);
        if (typing==3){fill(#f42121);}
        text("z= "+zexp,10,60,0);    fill(255-backgroundcolor);
        
        textAlign(RIGHT);
        if(typing==4||typing==5){fill(#f42121);}
        text(ustartval+"≤u≤"+uendval,width-10,20,0); fill(255-backgroundcolor);
        if(typing==6||typing==7){fill(#f42121);}
        text(vstartval+"≤v≤"+vendval,width-10,40,0); fill(255-backgroundcolor);
        textAlign(LEFT);
    }
    
    //translate to origin and autorotate
    translate(width/2+xtranslate,height/2+ytranslate,ztranslate);
    rotateY(timer2*PI/180);
    rotateY(ry);
    if(!paused){ if(autorotatingForward){
       timer2++;}else{timer2-=1;}}
    if(timer2>360||timer2<-360){timer2=0;}
    rotate();
    stroke(255-backgroundcolor);        
    
    //draw axis    
    if(displayon){
        textSize(15); fill(255-backgroundcolor);
        
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
    //default color of function (purplish)
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
  if(colored!=0){stroke(colorList.get(i).r,colorList.get(i).g,colorList.get(i).b);}
  line(xscale*xvals.get(i),zscale*zvals.get(i),yscale*yvals.get(i),xscale*xvals.get(i+1),zscale*zvals.get(i+1),yscale*yvals.get(i+1));
}

void drawU(int i){
  if(colored!=0){stroke(colorList.get(i).r,colorList.get(i).g,colorList.get(i).b);}
  line(xscale*xvals.get(i),zscale*zvals.get(i),yscale*yvals.get(i),xscale*xvals.get(i+numofintervals+1),zscale*zvals.get(i+numofintervals+1),yscale*yvals.get(i+numofintervals+1));
}
void calculate(){
  xvals.clear(); yvals.clear(); zvals.clear();
  parse.parainterp(xexp,yexp,zexp,parse.interp(ustartval),parse.interp(uendval),parse.interp(vstartval),parse.interp(vendval),numofintervals);
  for (int i=0;i<parse.xreturnlist.size();i++){
       xvals.append(10*parse.xreturnlist.get(i).floatValue());
       yvals.append(10*parse.yreturnlist.get(i).floatValue());
       zvals.append(-10*parse.zreturnlist.get(i).floatValue());
  }
  Color.generateDepthValues(xvals.array(),yvals.array(),zvals.array());
  rescale(xvals, xmaxval); rescale(yvals, ymaxval); rescale(zvals,zmaxval);
  applycolor();
}
void applycolor(){
   if(colored==1){Color.axiscolorlist(xvals.array(),yvals.array(),zvals.array(),colorList);}
   else if(colored==2){Color.generateDepthColorList(colorList,251.0,253.0,10.0,240.0,3.0,3.0);}
   else if(colored==3){Color.generateDepthColorList(colorList,0.0,255.0,197.0,238.0,3.0,240.0);}
   else if(colored==4){Color.generateDepthColorList(colorList,5,234.0,9.0,0.0,0.0,255.0);}
   else if(colored==5){Color.generateDepthColorList(colorList,238,3.0,240.0,0.0,255,0.0);}
}

void rescale(FloatList list, float maxval){
  maxval=0;
  int sum=0;
  float autoscale=1;
  boolean containsinfinity=false;
  for (int j=0;j<list.size();j++){
    if((100/abs(list.get(j)))==0){
        containsinfinity=false;
    }    
  }
  //find abs biggest values for rescaling
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
  if(containsinfinity||maxval>pow(10,15)){   //if values contains infinity scale to the average value
    println(maxval);
    for (int i=0;i<list.size();i++){
        sum+=list.get(i);          
    }
    sum/=list.size()-1;
    if(sum==0){autoscale=1;}
    else{
      autoscale=130/sum;}
    if (autoscale==0){autoscale=5; }
    for (int i=0;i<list.size();i++){
       list.mult(i,autoscale);
    }
  }
  else{
    //println(maxval);
    if(maxval==0){autoscale=1;}
    else{
      autoscale=130/maxval;}
    if (autoscale==0){autoscale=5; }
    for (int i=0;i<list.size();i++){
       list.mult(i,autoscale);
    }    
  }
  maxval*=autoscale;
}

void rotate(){
  rotateX(rx);
  rotateZ(rz);
}


void keyPressed(){
   if(key=='f'||key=='F'){
     rchoose++;
     if(rchoose>2){rchoose=0;}
   }
   if((key=='h'||key=='H')&& typing==0){   if(displayon){displayon=false;} else{displayon=true;}   }
   if((key=='b'||key=='B')){   if(autorotatingForward){autorotatingForward=false;} else{autorotatingForward=true;}   }
   if((key=='i'||key=='I') && typing==0){if(backgroundcolor==0){backgroundcolor=255;} else{backgroundcolor=0;}}
   if(key=='s'||key=='S'){
       xscale=dheight/450;
       yscale=dheight/450;
       zscale=dheight/450;
   }

   if(key=='r'||key=='R'){
      rx=0; rz=0;  timer2=0; ztranslate=0;
   }
   if(key=='x'||key=='X'){
      rchoose=4;
   }
   if(key=='y'||key=='Y'){
      rchoose=3;
   }
   if(key=='z'||key=='Z'){
      rchoose=5;
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
      xtranslate-=20;//ry-=5*PI/180;
   }
   if(keyCode==RIGHT){
      xtranslate+=20;//ry+=5*PI/180;
   }
   if(keyCode==DOWN){
      ytranslate+=20;
   }
   if(keyCode==UP){
      ytranslate-=20;
   }
   if(keyCode==ENTER){
       if(clicktype){typing=0; clicktype=false;}
       else{
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
   }
   if((key=='g'||key=='G') && typing==0){calculate();}
   if(typing!=0){
      if(keyCode!=SHIFT && keyCode!=ENTER && keyCode!=BACKSPACE && keyCode!=DELETE){
         if(typing!=0){displayon=true;}
         if(typing==1){xexp=xexp+Character.toString(key);}
         else if(typing==2){yexp=yexp+Character.toString(key);}
         else if(typing==3){zexp=zexp+Character.toString(key);}
         else if(typing==4){ustartval=ustartval+Character.toString(key);}
         else if(typing==5){uendval=uendval+Character.toString(key);}
         else if(typing==6){vstartval=vstartval+Character.toString(key);}
         else if(typing==7){vendval=vendval+Character.toString(key);}
      //print(key);
      }
   }
   if((keyCode==DELETE||keyCode==BACKSPACE)){
      if(typing==1 && xexp.length()>0){
      xexp=xexp.substring(0,xexp.length()-1);}
      if (typing ==2 && yexp.length()>0){
        yexp=yexp.substring(0,yexp.length()-1);
      }
      if(typing==3 && zexp.length()>0){zexp=zexp.substring(0,zexp.length()-1);
      }
      if(typing==4 && ustartval.length()>0){ustartval=ustartval.substring(0,ustartval.length()-1);
      }
      if(typing==5 && uendval.length()>0){uendval=uendval.substring(0,uendval.length()-1);
      }
      if(typing==6 && vstartval.length()>0){vstartval=vstartval.substring(0,vstartval.length()-1);
      }
      if(typing==7 && vendval.length()>0){vendval=vendval.substring(0,vendval.length()-1);
      }
   }
    if((key=='u'||key=='U') && typing==0){
      typing=4; ustartval="";
   }
   if((key=='v'||key=='V') && typing==0){
      typing=6; vstartval="";
   }
   if((key=='c'||key=='C') && typing==0){
      if(colored<5){colored++;}else{colored=0;}
      applycolor();
   }
}
void mouseClicked(){
  clicktype=true;
  if(mouseX>10 && mouseX<200 && mouseY>6 && mouseY<19){typing=1; xexp="";}  //x
  else if(mouseX>10 && mouseX<200 && mouseY>19 && mouseY<41){typing=2; yexp="";} //y
  else if(mouseX>10 && mouseX<200 && mouseY>41 && mouseY<61){typing=3; zexp="";} //z
  else if(width-mouseX<28 && mouseY>6 && mouseY<19){typing=5; uendval="";} //uend
  else if(width-mouseX<90 && mouseY>6 && mouseY<19){typing=4; ustartval="";} //ustart
  else if(width-mouseX<28 && mouseY>19 && mouseY<41){typing=7; vendval="";}//vend
  else if(width-mouseX<90 && mouseY>19 && mouseY<41){typing=6; vstartval="";} //vstart
  else {clicktype=false; if(!paused){paused=true;}else{   paused=false;}}
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
    ztranslate+=5*e;
  }
  if(rchoose==3){
    if(e>0){ yscale=yscale/1.1;}
    else{yscale=yscale*1.1;} 
  }
  if(rchoose==4){
    if(e>0){ xscale=xscale/1.1;}
    else{xscale=xscale*1.1;} 
  }
  if(rchoose==5){
    if(e>0){ zscale=zscale/1.1;}
    else{zscale=zscale*1.1;}   
  }
}