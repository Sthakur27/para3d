public class Color{
  public float r=0;
  public float g=0;
  public float b=0;  
  public Color(float dr, float dg, float db){
    r=dr; g=dg; b=db;
  }
}
/*
void heightcolorlist(FloatList list){
  float min=list.get(1);
  float max=list.get(1);
  for (int i=1;i<list.size();i++){
    if(list.get(i)>max){
        max=list.get(i);
    }
    if(list.get(i)<min){
        min=list.get(i);
    }
  }
  colorList.clear();
  for (int j=0;j<list.size();j++){
     colorList.add(new Color(0,255-(list.get(j)-min)*255.0/(max-min),(list.get(j)-min)*255.0/(max-min)));
  }
}
float getlength(int i){
   return(pow(xvals.get(i)*xvals.get(i)+yvals.get(i)*yvals.get(i)+zvals.get(i)*zvals.get(i),0.5));
}
void depthcolorlist(){
  float min=getlength(1);
  float max=getlength(1);
  for (int i=1;i<xvals.size();i++){
    if(getlength(i)>max){
        max=getlength(i);
    }
    if(getlength(i)<min){
        min=getlength(i);
    }
  }
  colorList.clear();
  for (int j=0;j<xvals.size();j++){
     colorList.add(new Color(0,255-(getlength(j)-min)*255.0/(max-min),(getlength(j)-min)*255.0/(max-min)));
  }
}
*/