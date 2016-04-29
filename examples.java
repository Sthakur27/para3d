public class examples{
  public static String[] getExample(int index){ 
    if(index==0){
      //helicoid
         String[] temp={"-p","p","-p","p","v*sinu","v*cosu","u"};
         return(temp);
    }
    else if(index==1){
      //cone
         String[] temp={"-p","p","-p","p","v*sinu","v*cosu","v"};
         return(temp);
    }
    else if(index==2){
      //trefoil knot
         String[] temp={"-p","p","-p","p","(sin(u)+2*sin(2*u))","(cos(u)-2*cos(2*u))+0.5*cosu*cosv","(-1*sin(3*u))"};
         return(temp);
    }
    else if(index==3){
      //horn cyclide
         String[] temp={"-p","p","-p","p","(5*(5-13*cosu*cosv)+144*cosu)/(13-5*cosu*cosv)",
       "(12*sinu*(13-5*cosv))/(13-5*cosu*cosv)","(12*sinv*(5*cosu-5))/(13-5*cosu*cosv)"};
       return(temp);
    }
       
    else if(index==4){
      //ring cyclide
         String[] temp={"-p","p","-p","p","(8*(5-13*cosu*cosv)+144*cosu)/(13-5*cosu*cosv)",
       "(12*sinu*(13-8*cosv))/(13-5*cosu*cosv)","(12*sinv*(5*cosu-8))/(13-5*cosu*cosv)"};
       return(temp);
    }
   else if(index==5){
     //mobius
         String[] temp={"-2*p","2*p","-2*p","2*p","(1+(v/(4*p))*cos((u/2+p)/2))*cos(u/2+p)",
       "(1+(v/(4*p))*cos(u/2+p))*sin(u/2+p)","(v/(4*p))*sin((u/2+p)/2)"};
       return(temp);
    }
   else if(index==6){
     //torus
         String[] temp={"-2*p","2*p","-2*p","2*p","(3+cosv)*cosu",
       "(3+cosv)*sinu","5*sinv"};
       return(temp);
    }
    else if(index==7){
     //klein loop
         String[] temp={"-2*p","2*p","-2*p","2*p","cos(u/2)*(5+sin(v/2)*cos(u/4)-sin(v)*sin(u/4)/2)",
       "sin(u/2)*(5+sin(v/2)*cos(u/4)-sin(v)*sin(u/4)/2)","sin(u/4)*sin(v/2)+cos(u/4)*sin(v)/2"};
       return(temp);
    }
    else if(index==8){
     //klein bottle
         String[] temp={"0","2*p","-p","p","20*(1-cosu)-20",
       "8*sinu*(1-cosu)+3*sinv*(((1/8)*(u*(5.5/(2*p)))*(u*(5.5/(2*p))-5.5)*(u*(5.5/(2*p))-2))+2)","cosv*(((1/8)*(u*(5.5/(2*p)))*(u*(5.5/(2*p))-5.5)*(u*(5.5/(2*p))-2))+2)"};
       return(temp);
    }
    else if(index==9){
     //monkey sadle
         String[] temp={"-p","p","-p","p","u",
       "v","u^3-3*u*v^2"};
       return(temp);
    }
    else if(index==10){
     //pseudosphere
         String[] temp={"-2*p","2*p","0","p","cosu*sinv",
       "sinu*sinv","cosv+log(tan(0.5*v))"};
       return(temp);
    }
    else if(index==11){
     //enneper surface
         String[] temp={"-p","p","-p","p","u*((1-(u^2)/3)+v^2)",
       "-v*((1-(v^2)/3)+u^2)","(u^2-v^2)/3"};
       return(temp);
    }
    else if(index==12){
     //kuen surface
         String[] temp={"-p","p","0","p","(2*(cosu+u*sinu)*sinv)/(1+u^2*(sinv)^2)",
       "(2*(sinu-u*cosu)*sinv)/(1+u^2*(sinv)^2)","log(tan(0.5*v))+(2*cosv/(1+u^2*(sinv)^2))"};
       return(temp);
    }
    else if(index==13){
     //dini surface 
         String[] temp={"-2*p","2*p","0","p","cosu*sinv",
      "sinu*sinv","0.25*((log(tan(0.5*v))+cosv)+0.2*u)"};
       return(temp);
    }
    else if(index==14){
     //steiner surface 
         String[] temp={"0","p","0","p","cosu*cosv*sinv",
      "sinu*cosv*sinv","cosu*sinu*(cosv)^2"};
       return(temp);
    }
    else if(index==15){
     //Right Conoid  
         String[] temp={"-p","p","-p","p","v*cosu",
     "v*sinu","2*sin(1*u)"};
       return(temp);
    }
    else if(index==16){
     //Spherical Helicoid 
         String[] temp={"-2*p","2*p","-2*p","2*p","u*cosv",
     "u*sinv","u^2+6*v-20"};
       return(temp);
    }
    else if(index==17){
     //Boy's Surface
         String[] temp={"-p/2","p/2","0","p","(1.41*cosv^2*cos(2*u)+cosu*sin(2*v))/(2-1.41*sin(3*u)*sin(2*v))",
     "(1.41*cosv^2*sin(2*u)-sinu*sin(2*v))/(2-1.41*sin(3*u)*sin(2*v))",
     "(3*cosv^2)/(2-1.41*sin(3*u)*sin(2*v))"};
       return(temp);
    }
    else if(index==18){
     //breather  
         String[] temp={"-14","14","-37.4","37.4", "(0.84*cosh(0.4*u)*(-0.9165*sinv*cos(0.9165*v)+cosv*sin(0.9165*v)))/(0.4*(0.84*(cosh(0.4*u)^2)+0.16*(sin(0.9165*v)^2)))",
     "(0.84*cosh(0.4*u)*(-0.9165*cosv*cos(0.9165*v)-sinv*sin(0.9165*v)))/(0.4*(0.84*(cosh(0.4*u)^2)+0.16*(sin(0.9165*v)^2)))", 
 "0.25*(-u+(2*(0.84)*cosh(0.4*u)*sinh(0.4*u))/(0.4*(0.84*(cosh(0.4*u)^2))+0.16*(sin(0.9165*v)^2)))"};
       return(temp);
    }
    else if(index==19){
     //3d flower  
         String[] temp={"0","p","-p","p","cos(4*u)*cos(v)^6*cos(u)*cosv",
         "cos(4*u)*cos(v)^6*sin(u)*cosv",
         "cos(4*u)*cos(v)^6*sin(v)"};
       return(temp);
    }
    return(new String[]{});
  }
}

/*
x y z expressions
heart
String xexp="(13*cosu-5*cos(2*u)-2*cos(3*u)-cos(4*u))";
String yexp="sinu^3*sinv";
String zexp="(sinu^3)*cosv";


3dify/Bubble expressions  -2p 2p
String xexp = "u+cosv";
String yexp = "sinu+cosv";
String zexp="0+sinv";

bubbly trefoil
String xexp="(sin(u)+2*sin(2*u))+0.5*sinu*cosv";
String yexp="(cos(u)-2*cos(2*u))+0.5*cosu*cosv";
String zexp="(-1*sin(3*u))+0.5*sinv";

Symmetrical horn cyclide
String ustartval="-p";
String uendval="p";
String vstartval="-p";
String vendval="p";
String xexp="(0*(5-13*cosu*cosv)+144*cosu)/(13-5*cosu*cosv)";
String yexp="(12*sinu*(13-0*cosv))/(13-5*cosu*cosv)";
String zexp="(12*sinv*(5*cosu-0))/(13-5*cosu*cosv)";

parabolic cyclide
k={0.5,1,1.5}
String ustartval="-p";
String uendval="p";
String vstartval="-p";
String vendval="p";
String xexp="(2*v^2+0.5*(1-u^2-v^2))/(1+u^2+v^2)";
String yexp="u*(v^2+0.5)/(1+u^2+v^2)";
String zexp="v*(1+u^2-0.5)/(1+u^2+v^2)";



curly bottle  infinity loops   -2p 2p -2p 2p
String ustartval="-2*p";
String uendval="2*p";
String vstartval="-2*p";
String vendval="2*p";
String xexp = "cos(u/2)*(5+sin(v/2)*cos(u/4)-sin(v)*sin(u/4)/2)";
String yexp = "sin(u/2)*(5+sin(v/2)*cos(u/4)-sin(v)*sin(u/4)/2)";
String zexp = "sin(u/4)*sin(v/2)+cos(u/4)*sin(v)/2";



Cool thingy

u+0.25*u^2*sinv*cosu
u^2+0.25*u^2*sinv*sinu
u^2*cosv


trippy
u: (-2*p, 2*p)    v: (-p, p)
String xexp="sin(3*u)/(2+cosv)"
String yexp="(sinu+2*sin(2*u))/(2+cos(v+p*2/3))"
String zexp="(cosu-2*cos(2*u))*(2+cosv)*(2+cos(v+p*2/3))/4"




conical edge
String ustartval="-p";
String uendval="p";
String vstartval="-p";
String vendval="p";
String xexp="v*cosu";
String yexp="v*sinu";
String zexp="((16-9*(cosu^2))^0.5)";


rose rainbow projection
String ustartval="0";
String uendval="p";
String vstartval="0";
String vendval="p";
String xexp="4*sin(5*u)*sinu+sinv";
String yexp="4*sin(5*u)*cosu";
String zexp="cosv";

3d flower!
String ustartval="0";
String uendval="p";
String vstartval="-p";
String vendval="p";
String xexp="cos(4*u)*cos(v)^6*cos(u)*cosv";
String yexp="cos(4*u)*cos(v)^6*sin(u)*cosv";
String zexp="cos(4*u)*cos(v)^6*sin(v)";
*/