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
  public Color(int dr, int dg, int db){
    r=(float)dr; g=(float)dg; b=(float)db;
  }
  public Color addColor(Color c){
      r=Math.min((int)(this.r+c.r),256); 
      g=Math.min((int)(this.g+c.g),256); 
      b=Math.min((int)(this.b+c.b),256);
      return(this);
  }
  public static void axiscolorlist(float[] xvals, float[] yvals, float[] zvals,ArrayList<Color> colorList){
      colorList.clear();  
      float[] xrange=findrange(xvals); float[] yrange=findrange(yvals); float[] zrange=findrange(zvals);
      for(int i=0;i<xvals.length;i++){
           colorList.add(new Color(255*(xvals[i]-xrange[0])/(xrange[1]-xrange[0]),255*(yvals[i]-yrange[0])/(yrange[1]-yrange[0]),255*(zvals[i]-zrange[0])/(zrange[1]-zrange[0])));
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
   public static void showColor(float[] x, float[] y, float[] z, ArrayList<Color> colorList,float r1, float g1, float b1, float r2, float g2, float b2,boolean mode){
      if(mode){xyzc1c2(x,y,z,colorList,new Color(r1,g1,b1),new Color(r2,g2,b2));}
      else{generateDepthColorList(colorList, r1,  g1,  b1,  r2,  g2,  b2);}
   }
   public static void xyzc1c2(float[] x, float[] y, float[] z,ArrayList<Color> colorList, Color c1, Color c2){
      colorList.clear();  
      float[] xrange=findrange(x); float[] yrange=findrange(y); float[] zrange=findrange(z);
      for(int i=0;i<x.length;i++){
           colorList.add(new Color(
           c1.r+(c2.r-c1.r)*(x[i]-xrange[0])/(xrange[1]-xrange[0]),
           c1.g+(c2.g-c1.g)*(y[i]-yrange[0])/(yrange[1]-yrange[0]),
           c1.b+(c2.b-c1.b)*(z[i]-zrange[0])/(zrange[1]-zrange[0])
           ));
      }
   }
}