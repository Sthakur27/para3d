//three floatlists used to store x,y,z coordinates of points
FloatList xvals=new FloatList(); 
FloatList yvals=new FloatList();
FloatList zvals=new FloatList();
//list containing color for each point
ArrayList<Color>colorList=new ArrayList();
//says whether each point exists or not
ArrayList<Boolean>validpoints=new ArrayList();
boolean clicktype=false;
int exampleNumber=-1;
//mouse scroll controller
int rchoose=1;
float timer=0;
int autorotatetimer=1;
boolean depthcolor=true;
float xscale=1; float yscale=1; float zscale=1;
int typing=0;  //typing changes meaning based on number   
//0:not typing  1: typing x exp 2: typing y exp 3: typing z exp  4: typing u start 5: typing u end   6: z start 7: z end
float ry=0; float rx=0;float rz=0;
float xtranslate=0; float ytranslate=0; float ztranslate=0;  
int numofintervals=80;
float dheight;
boolean paused=false;
boolean autorotatingForward=true;
boolean displayon=true;
int colored=1; //keep clicking 'c' to cycle thru color modes.
int backgroundcolor=0;  //alternates between black and white  0-255

//x  y z expressions and parameters u and v  (Copy Paste from examples to here)
String ustartval="-1";
String uendval="1";
String vstartval="-1";
String vendval="1";
String xexp="u";
String yexp="v";
String zexp="u*v";


String tempexp="";
void setup(){
      size(500, 450,P3D);
      dheight=height;
      surface.setResizable(true);
      calculate();      
}


void draw(){
    //display screen information
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
        text(numofintervals+" intervals",width-10,60,0);
        textAlign(LEFT);
    }
    
    //translate to origin and autorotate
    translate(width/2+xtranslate,height/2+ytranslate,ztranslate);
    rotateY(autorotatetimer*PI/180);
    rotateY(ry);
    if(!paused){ if(autorotatingForward){
       autorotatetimer++;}else{autorotatetimer-=1;}}
    if(autorotatetimer>360||autorotatetimer<-360){autorotatetimer=0;}
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

    for (int i=0;i<timer+1;i++){
        for(int j=0;j<timer;j++){
            drawV((numofintervals+1)*i+j);
        }
    }
    for(int k=0;k<timer;k++){
       for(int l=0;l<timer+1;l++){
         drawU((numofintervals+1)*k+l);
       }
    }
    if(timer<numofintervals && !paused){timer+=0.5;}
    if(height!=dheight){
       dheight=height;
       xscale=dheight/450;
       yscale=dheight/450;
       zscale=dheight/450;
    }
}

//@param int i: index of point in xvals/yvals/zvals
//draws a line from point x,y,z(u,v) to x,y,z(u,v+1)
void drawV(int i){
  if(validpoints.get(i)&&validpoints.get(i+1)){
  if(colored!=0){stroke(colorList.get(i).r,colorList.get(i).g,colorList.get(i).b);}
  line(xscale*xvals.get(i),zscale*zvals.get(i),yscale*yvals.get(i),xscale*xvals.get(i+1),zscale*zvals.get(i+1),yscale*yvals.get(i+1));
  }
}

//@param int i: index of point in xvals/yvals/zvals
//draws a line from point x,y,z(u,v) to x,y,z(u+1,v)
void drawU(int i){
  if(validpoints.get(i)&&validpoints.get(i+1+numofintervals)){
  if(colored!=0){stroke(colorList.get(i).r,colorList.get(i).g,colorList.get(i).b);}
  line(xscale*xvals.get(i),zscale*zvals.get(i),yscale*yvals.get(i),xscale*xvals.get(i+numofintervals+1),zscale*zvals.get(i+numofintervals+1),yscale*yvals.get(i+numofintervals+1));
  }
}

//generates lists of points based on string expressions, autoscales the function to the window, applies color
void calculate(){
  rx=0; rz=0;  autorotatetimer=0; ztranslate=0; xtranslate=0; ytranslate=0; timer=0;
  xscale=dheight/450;
  yscale=dheight/450;
  zscale=dheight/450;
  rchoose=2;
  xvals.clear(); yvals.clear(); zvals.clear();
  parse.parainterp(xexp,yexp,zexp,parse.interp(ustartval),parse.interp(uendval),parse.interp(vstartval),parse.interp(vendval),numofintervals,validpoints);
  for (int i=0;i<parse.xreturnlist.size();i++){
       xvals.append(10*parse.xreturnlist.get(i).floatValue());
       yvals.append(10*parse.yreturnlist.get(i).floatValue());
       zvals.append(-10*parse.zreturnlist.get(i).floatValue());
  }
  //uses unscaled points to calculate distance from origin for later depth color labeling
  Color.generateDepthValues(xvals.array(),yvals.array(),zvals.array());
  rescale(new FloatList[]{xvals,yvals,zvals});
  applycolor();
  if (parse.invalid){
      xexp="Invalid";
      yexp="Invalid";
      zexp="Invalid";
  }
}

//applies different color modes based on 'colored' variable
void applycolor(){
   Color cyan=new Color(0.0,255.0,197.0); Color purple=new Color(238.0,3.0,240.0);
   if(colored==1){Color.axiscolorlist(xvals.array(),yvals.array(),zvals.array(),colorList);}
   else if(colored==2){Color.showColor(xvals.array(),yvals.array(),zvals.array(),colorList,251.0,253.0,10.0,240.0,3.0,3.0,depthcolor);}
   else if(colored==3){Color.showColor(xvals.array(),yvals.array(),zvals.array(),colorList,0.0,255.0,197.0,238.0,3.0,240.0,depthcolor);}   
   //else if(colored==3){Color.xyzc1c2(xvals.array(),yvals.array(),zvals.array(),colorList,cyan,purple);}
   else if(colored==4){Color.showColor(xvals.array(),yvals.array(),zvals.array(),colorList,5,234.0,9.0,0.0,0.0,255.0,depthcolor);}
   else if(colored==5){Color.showColor(xvals.array(),yvals.array(),zvals.array(),colorList,238,3.0,240.0,0.0,255,0.0,depthcolor);}
}

/*@param coordinate point list(ex xvals) and maximum absolute value of list
scales list's points to window
*/
void rescale(FloatList[] a){
  float maxval=0;
  int sum=0;
  float autoscale=1;
  boolean containsinfinity=false;
  for(FloatList list:a){
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
  }
  for(FloatList list:a){
      if(containsinfinity||maxval>pow(10,15)){   //if values contains infinity scale to the average value
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
  }
  maxval*=autoscale;
}

//rotates the coordinate axis
void rotate(){
  rotateX(rx);
  rotateZ(rz);
}


void keyPressed(){
   // adjust num of intervals with '[' and ']'
   if(key==']' && typing==0){
      numofintervals+=5;
      calculate();
   }
   if(key=='[' && typing==0){
      if(numofintervals>5){numofintervals-=5;
      calculate();}
   }
   //use 'f' to change mousewheel mode
   if(key=='f'||key=='F'){
     rchoose++;
     if(rchoose>2){rchoose=0;}
   }
   // 'h' toggles information and axis
   if((key=='h'||key=='H')&& typing==0){   if(displayon){displayon=false;} else{displayon=true;}   }
   //'w' and 'e' to load different preset examples.
   if((key=='w'||key=='W')&& typing==0){  
       if(exampleNumber<1){exampleNumber=22;} else{exampleNumber--;}
       String[] temp=examples.getExample(exampleNumber); 
       ustartval=temp[0];
       uendval=temp[1];
       vstartval=temp[2];
       vendval=temp[3];
       xexp=temp[4];
       yexp=temp[5];
       zexp=temp[6];
       calculate();  
   }
   if((key=='e'||key=='E')&& typing==0){  
       if(exampleNumber>21){exampleNumber=0;} else{exampleNumber++;}
       String[] temp=examples.getExample(exampleNumber); 
       ustartval=temp[0];
       uendval=temp[1];
       vstartval=temp[2];
       vendval=temp[3];
       xexp=temp[4];
       yexp=temp[5];
       zexp=temp[6];
       calculate();  
   }
   // 'b' to toggle autorotation back and forth
   if((key=='b'||key=='B')){   if(autorotatingForward){autorotatingForward=false;} else{autorotatingForward=true;}   }
   // 'i' to toggle background black and white
   if((key=='i'||key=='I') && typing==0){if(backgroundcolor==0){backgroundcolor=255;} else{backgroundcolor=0;}}
   // 's' autoscales to window
   if(key=='s'||key=='S'){
       xscale=dheight/450;
       yscale=dheight/450;
       zscale=dheight/450;
   }
   // reset rotation and translation
   if(key=='r'||key=='R'){
      rx=0; rz=0;  autorotatetimer=0; ztranslate=0; xtranslate=0; ytranslate=0; timer=0;
   }
   // mousewhell stretches x axis/values
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
      if(depthcolor){depthcolor=false;}else{depthcolor=true;} applycolor();
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
              typing=0; rx=0; rz=0; autorotatetimer=0;  calculate();}
           else if(typing==4){ 
              typing=5; uendval="";}
           else if(typing==5){ 
              typing=0; rx=0; rz=0; autorotatetimer=0;  calculate();}
           else if(typing==6){ 
              typing=7; vendval="";}
           else if(typing==7){ 
              typing=0; rx=0; rz=0; autorotatetimer=0;  calculate();}
       }
   }
   if((key=='g'||key=='G') && typing==0){calculate();}
   //typing into an input string ex. xexp  or ustart
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
   //delete last key when typing into a string
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
   if(key==' '){clicktype=false; if(!paused){paused=true;}else{   paused=false;}}
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