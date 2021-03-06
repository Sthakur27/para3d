/*some math sources: 
http://aleph0.clarku.edu/~djoyce/ma131/gallery.pdf
http://paulbourke.net/geometry/klein/
http://arxiv.org/pdf/0909.5354.pdf
https://elepa.files.wordpress.com/2013/11/fifty-famous-curves.pdf*/
//narrow pillow   sinucosv  cosusinv  sinv    
//weird    sinucosu  sinucosv cosucosv
/*donut String ustartval="-p";
String uendval="p";
String vstartval="-p";
String vendval="p";
String xexp="sinu*cosv";
String yexp="cosu*cosv";
String zexp="sin(2*v)";
*/

public class examples{
  
  //returns a string list with specifications to create example function in para3d
  public static String[] getExample(int index){ 
    switch(index){  
    case 0:
         String[] helicoid={"-p","p","-p","p","v*sinu","v*cosu","u"};
         return(helicoid);
         
    case 1:
         String[] cone={"-p","p","-p","p","v*sinu","v*cosu","v"};
         return(cone);
         
    case 2:
         String[] trefoil={"-p","p","-p","p","(sin(u)+2*sin(2*u))","(cos(u)-2*cos(2*u))","(-2*sin(3*u))"};
         //y +0.25*cosu*cosv
         return(trefoil);
         
    case 3:
         String[] horncyclide={"-p","p","-p","p","(5*(5-13*cosu*cosv)+144*cosu)/(13-5*cosu*cosv)",
       "(12*sinu*(13-5*cosv))/(13-5*cosu*cosv)","(12*sinv*(5*cosu-5))/(13-5*cosu*cosv)"};
       return(horncyclide);
       
    case 4:
         String[] ringcyclide={"-p","p","-p","p","(8*(5-13*cosu*cosv)+144*cosu)/(13-5*cosu*cosv)",
       "(12*sinu*(13-8*cosv))/(13-5*cosu*cosv)","(12*sinv*(5*cosu-8))/(13-5*cosu*cosv)"};
       return(ringcyclide);
       
    case 5:
         String[] mobius={"-2*p","2*p","-2*p","2*p","(1+(v/(4*p))*cos((u/2+p)/2))*cos(u/2+p)",
       "(1+(v/(4*p))*cos(u/2+p))*sin(u/2+p)","(v/(4*p))*sin((u/2+p)/2)"};
       return(mobius);
       
    case 6:
         String[] torus={"-p","p","-p","p","(3+cosv)*cosu",
       "(3+cosv)*sinu","sinv"};
       return(torus);
       
    case 7:
         String[] kleinloop={"-2*p","2*p","-2*p","2*p","cos(u/2)*(5+sin(v/2)*cos(u/4)-sin(v)*sin(u/4)/2)",
       "sin(u/2)*(5+sin(v/2)*cos(u/4)-sin(v)*sin(u/4)/2)","sin(u/4)*sin(v/2)+cos(u/4)*sin(v)/2"};
       return(kleinloop);
       
    case 8:
         String[] kleinbottle={"0","2*p","-p","p","20*(1-cosu)",
       "8*sinu*(1-cosu)+3*sinv*(((1/8)*(u*(5.5/(2*p)))*(u*(5.5/(2*p))-5.5)*(u*(5.5/(2*p))-2))+2)","2.5*cosv*(((1/8)*(u*(5.5/(2*p)))*(u*(5.5/(2*p))-5.5)*(u*(5.5/(2*p))-2))+2)"};
       return(kleinbottle);
       
    case 9:
         String[] monkey={"-0.7","0.7","-0.7","0.7","u",
       "v","u^3-3*u*v^2"};
       return(monkey);
       
    case 10:
         String[] pseudosphere={"-2*p","2*p","0","p","cosu*sinv",
       "sinu*sinv","cosv+log(tan(0.5*v))"};
       return(pseudosphere);
       
    case 11:
         String[] enneper={"-p","p","-p","p","u*((1-(u^2)/3)+v^2)",
       "-v*((1-(v^2)/3)+u^2)","(u^2-v^2)*2"};
       return(enneper);
       
    case 12:
         String[] kuen={"-p","p","0","p","(2*(cosu+u*sinu)*sinv)/(1+u^2*(sinv)^2)",
       "1.5*(2*(sinu-u*cosu)*sinv)/(1+u^2*(sinv)^2)","log(tan(0.5*v))+(2*cosv/(1+u^2*(sinv)^2))"};
       return(kuen);
       
    case 13:
         String[] dini={"-2*p","2*p","0","p","cosu*sinv",
      "sinu*sinv","0.25*((log(tan(0.5*v))+cosv)+0.2*u)"};
       return(dini);
       
    case 14:
         String[] steiner={"0","p","0","p","cosu*cosv*sinv",
      "sinu*cosv*sinv","cosu*sinu*(cosv)^2"};
       return(steiner);
       
    case 15:
         String[] RightConoid={"-p","p","-p","p","v*cosu",
     "v*sinu","3*sin(1*u)"};
       return(RightConoid);
       
    case 16:
         String[] SphericalHelicoid ={"-2*p","2*p","-2*p","2*p","4*u*cosv",
     "4*u*sinv","u^2+6*v-20"};
       return(SphericalHelicoid);
       
    case 17:
     //Boy's Surface
         String[] boys={"-p/2","p/2","0","p","(1.41*cosv^2*cos(2*u)+cosu*sin(2*v))/(2-1.41*sin(3*u)*sin(2*v))",
     "(1.41*cosv^2*sin(2*u)-sinu*sin(2*v))/(2-1.41*sin(3*u)*sin(2*v))",
     "(2.5*cosv^2)/(2-1.41*sin(3*u)*sin(2*v))"};
       return(boys);
       
    case 18:
         String[] breather={"-14","14","-37.4","37.4", "(0.84*cosh(0.4*u)*(-0.9165*sinv*cos(0.9165*v)+cosv*sin(0.9165*v)))/(0.4*(0.84*(cosh(0.4*u)^2)+0.16*(sin(0.9165*v)^2)))",
     "(0.84*cosh(0.4*u)*(-0.9165*cosv*cos(0.9165*v)-sinv*sin(0.9165*v)))/(0.4*(0.84*(cosh(0.4*u)^2)+0.16*(sin(0.9165*v)^2)))", 
 "0.25*(-u+(2*(0.84)*cosh(0.4*u)*sinh(0.4*u))/(0.4*(0.84*(cosh(0.4*u)^2))+0.16*(sin(0.9165*v)^2)))"};
       return(breather);
       
    case 19: 
         String[] flower={"0","p","-p","p","cos(4*u)*cos(v)^6*cos(u)*cosv",
         "cos(4*u)*cos(v)^6*sin(v)","cos(4*u)*cos(v)^6*sin(u)*cosv"};
       return(flower);
       
    case 20:
        String[] snailshell={"0","6*p","0","2*p","2*(1-e^(u/(6*p)))*cosu*cos(v/2)^2","2*(-1+e^(u/(6*p)))*sinu*cos(v/2)^2","4.5-e^(u/(3*p))-sinv+e^(u/(6*p))*sinv"};
        return snailshell;
        
    case 21:
        //replacing sinv^3 with sinv  for z  looks pretty interesting too
        String[] astroid= {"-p","p","-p","p","cosu^3*cosv^3","sinu^3*cosv^3","sinv^3"};
        return astroid;
    
    case 22:
        String[] pillow= {"-p","p","-p","p","sinu","cosu*cosv","sinv"};
        return pillow;
    
    //default should never be reached
    default:
    return(new String[]{});
    }
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

1sheet hyperboloid
String ustartval="-1";
String uendval="1";
String vstartval="-p";
String vendval="p";
String xexp="(1+u^2)^0.5*cosv";
String yexp="(1+u^2)^0.5*sinv";
String zexp="u";

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



Parabola with radius increasing as y increases
u+0.25*u^2*sinv*cosu
u^2+0.25*u^2*sinv*sinu
u^2*cosv


Harmono
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

*/