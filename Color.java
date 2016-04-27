import java.util.*;
import java.lang.Math;
public class Color{
  public float r=0;
  public float g=0;
  public float b=0;  
  public static ArrayList<Color>depthcolors=new ArrayList<Color>();
  public static ArrayList<Float> depthvalues=new ArrayList<Float>();
  public Color(float dr, float dg, float db){
    r=dr; g=dg; b=db;
  }
  public Color(double dr, double dg, double db){
    r=(float)dr; g=(float)dg; b=(float)db;
  }
  public static void axiscolorlist(float[] xvals, float[] yvals, float[] zvals,ArrayList<Color> colorList){
      colorList.clear();
      float xmax=findrange(xvals)[1]; float xmin=findrange(xvals)[0]; 
      float ymax=findrange(yvals)[1]; float ymin=findrange(yvals)[0];
      float zmax=findrange(zvals)[1]; float zmin=findrange(zvals)[0];
      for (int j=0;j<xvals.length;j++){
         //colorList.add(new Color(128+(xvals[j]-xmin)*128.0/(xmax-xmin),128+(yvals[j]-ymin)*128.0/(ymax-ymin),128+(zvals[j]-zmin)*128.0/(zmax-zmin)));
         colorList.add(new Color(128+(xvals[j])*128.0/(xmax-xmin),128+(yvals[j])*64.0/(ymax-ymin),128+(zvals[j])*128.0/(zmax-zmin)));
      }
    }
 public static void heightcolorlist(float[] list,ArrayList<Color> colorList){
      float min=findrange(list)[0];
      float max=findrange(list)[1];
      colorList.clear();
      for (int j=0;j<list.length;j++){
         colorList.add(new Color(0,255-(list[j]-min)*255.0/(max-min),(list[j]-min)*255.0/(max-min)));
      }
    }
 public static float getlength(int i,float[] xvals, float[] yvals, float[] zvals){
       return((float)Math.pow(xvals[i]*xvals[i]+yvals[i]*yvals[i]+zvals[i]*zvals[i],0.5));
    }
 public static void generateDepthValues(float[] xvals, float[] yvals, float[] zvals){
      depthvalues.clear();
      float min=getlength(1,xvals,yvals,zvals);
      float max=getlength(1,xvals,yvals,zvals);
      for (int i=1;i<xvals.length;i++){
        if(getlength(i,xvals,yvals,zvals)>max){
            max=getlength(i,xvals,yvals,zvals);
        }
        if(getlength(i,xvals,yvals,zvals)<min){
            min=getlength(i,xvals,yvals,zvals);
        }
      }
      for (int i=0;i<xvals.length;i++){
         depthvalues.add((getlength(i,xvals,yvals,zvals)-min)*(10/(max-min)));
      }
 }
 public static void generateDepthColorList(ArrayList<Color> list,float r1, float g1, float b1, float r2, float g2, float b2){
      depthcolors.clear();
      /*for (int j=0;j<xvals.length;j++){
         depthlist.add(new Color(0,255-(getlength(j,xvals,yvals,zvals)-min)*255.0/(max-min),(getlength(j,xvals,yvals,zvals)-min)*255.0/(max-min)));
      }*/
      for (int j=0;j<depthvalues.size();j++){
         depthcolors.add(new Color(
         r1+((r2-r1)*depthvalues.get(j)/10),
         g1+((g2-g1)*depthvalues.get(j)/10),
         b1+((b2-b1)*depthvalues.get(j)/10)
         ));
      }
      list.clear();
      for(Color c:depthcolors){
        list.add(new Color(c.r,c.g,c.b));
      }
    }
    public static float[] findrange(float[] list){
      float min=list[1];
      float max=list[1];
      for (int i=1;i<list.length;i++){
         if(list[i]>max){max=list[i];}
         if(list[i]<min){min=list[i];}
      }
      float[] temp={min, max};
      return (temp);
    }
    public static void criticalcolor(float[] xvals, float[] yvals, float[] zvals,ArrayList<Color> colorList){
      colorList.clear();
      colorList.add(new Color(0,0,255));
        for(int i=1;i<xvals.length-1;i++){
          //if(  ((xvals[i]-xvals[i-1])*(xvals[i+1]-xvals[i])<0)  ||  ((yvals[i]-yvals[i-1])*(yvals[i+1]-yvals[i])<0) || ((zvals[i]-zvals[i-1])*(zvals[i+1]-zvals[i])<0)){
          if( (yvals[i]-yvals[i-1])*(yvals[i+1]-yvals[i])<0||(xvals[i]-xvals[i-1])*(xvals[i+1]-xvals[i])<0||(zvals[i]-zvals[i-1])*(zvals[i+1]-zvals[i])<0 ){ 
              colorList.add(new Color(255,0,0));
              colorList.get(i-1).r=255; colorList.get(i-1).b=0;
           }    
          else if(i>80&&i<xvals.length-82&&( (yvals[i]-yvals[i-81])*(yvals[i+81]-yvals[i])<0||(xvals[i]-xvals[i-81])*(xvals[i+81]-xvals[i])<0||(zvals[i]-zvals[i-81])*(zvals[i+81]-zvals[i])<0 )){ 
              colorList.add(new Color(255,0,0));
              colorList.get(i-81).r=255; colorList.get(i-1).b=0;
          }
          else{colorList.add(new Color(0,0,255));}
        }
   }
}
/*
void heightcolorlist(FloatList list){
  float min=list.get(1);
  float max=list.get(1);
  for (int i=1;i<list.length;i++){
    if(list[i]>max){
        max=list[i];
    }
    if(list[i]<min){
        min=list[i];
    }
  }
  colorList.clear();
  for (int j=0;j<list.length;j++){
     colorList.add(new Color(0,255-(list[j]-min)*255.0/(max-min),(list[j]-min)*255.0/(max-min)));
  }
}
float getlength(int i){
   return(pow(xvals[i]*xvals[i]+yvals[i]*yvals[i]+zvals[i]*zvals[i],0.5));
}
void depthcolorlist(){
  float min=getlength(1);
  float max=getlength(1);
  for (int i=1;i<xvals.length;i++){
    if(getlength(i)>max){
        max=getlength(i);
    }
    if(getlength(i)<min){
        min=getlength(i);
    }
  }
  colorList.clear();
  for (int j=0;j<xvals.length;j++){
     colorList.add(new Color(0,255-(getlength(j)-min)*255.0/(max-min),(getlength(j)-min)*255.0/(max-min)));
  }
}
*/