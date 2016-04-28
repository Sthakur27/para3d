/*
x y z expressions
heart
String xexp="(13*cosu-5*cos(2*u)-2*cos(3*u)-cos(4*u))";
String yexp="sinu^3*sinv";
String zexp="(sinu^3)*cosv";

helicoid
String xexp="v*sinu";
String yexp="v*cosu";
String zexp="u";

cone
String xexp="v*sinu";
String yexp="v*cosu";
String zexp="v";

3dify/Bubble expressions  -2p 2p
String xexp = "u+cosv";
String yexp = "sinu+cosv";
String zexp="0+sinv";

trefoilKnot  -p p
String xexp="(sin(u)+2*sin(2*u))";
String yexp="(cos(u)-2*cos(2*u))";
String zexp="(-1*sin(3*u))";

bubbly trefoil
String xexp="(sin(u)+2*sin(2*u))+0.5*sinu*cosv";
String yexp="(cos(u)-2*cos(2*u))+0.5*cosu*cosv";
String zexp="(-1*sin(3*u))+0.5*sinv";

cyclides
d=5  c=5 a=13 b=12    a>b>0  c^2=a^2-b^2 d>0

horn cyclide
String ustartval="-p";
String uendval="p";
String vstartval="-p";
String vendval="p";
String xexp="(5*(5-13*cosu*cosv)+144*cosu)/(13-5*cosu*cosv)";
String yexp="(12*sinu*(13-5*cosv))/(13-5*cosu*cosv)";
String zexp="(12*sinv*(5*cosu-5))/(13-5*cosu*cosv)";

ring cyclide    same as before but d=8
String ustartval="-p";
String uendval="p";
String vstartval="-p";
String vendval="p";
String xexp="(8*(5-13*cosu*cosv)+144*cosu)/(13-5*cosu*cosv)";
String yexp="(12*sinu*(13-8*cosv))/(13-5*cosu*cosv)";
String zexp="(12*sinv*(5*cosu-8))/(13-5*cosu*cosv)";

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

mobius strip:  /2
String xexp="(1+(v/4)*cos(u/4))*cos(u/2)";
String yexp="(1+(v/4)*cos(u/4))*sin(u/2)";
String zexp="(v/4)*sin(u/4)";

mob#2
String xexp="cos(u/2)+cos(u/4)*cos(v/2)";
String yexp="sin(u/2)+cos(u/4)*sin(v/2)";
String zexp="sin(u/4+p/2)";

mob3  -2p 2p
String ustartval="-2*p";
String uendval="2*p";
String vstartval="-2*p";
String vendval="2*p";
String xexp="(1+(v/(4*p))*cos((u/2+p)/2))*cos(u/2+p)";
String yexp="(1+(v/(4*p))*cos(u/2+p))*sin(u/2+p)";
String zexp="(v/(4*p))*sin((u/2+p)/2)";

torus!
String ustartval="-2*p";
String uendval="2*p";
String vstartval="-2*p";
String vendval="2*p";
String xexp="(3+cosv)*cosu";
String yexp="(3+cosv)*sinu";
String zexp="5*sinv";


helicoid
String xexp="sinv*(1+u^2)^0.5";
String yexp="cosv*(1+u^2)^0.5";
String zexp="u";


curly bottle  infinity loops   -2p 2p -2p 2p
String ustartval="-2*p";
String uendval="2*p";
String vstartval="-2*p";
String vendval="2*p";
String xexp = "cos(u/2)*(5+sin(v/2)*cos(u/4)-sin(v)*sin(u/4)/2)";
String yexp = "sin(u/2)*(5+sin(v/2)*cos(u/4)-sin(v)*sin(u/4)/2)";
String zexp = "sin(u/4)*sin(v/2)+cos(u/4)*sin(v)/2";

0 2p -2p 2p
3d mobius???
String xexp = "20*(1-cosu)+5*sinv*cosu*(2+cosu)";
String yexp = "8*sinu*(1-cosu)+5*sinv*sinu*(2+cosu)";
String zexp="cosv*(2+cosu)";



messed up sphere looks cool
String uendval="p";
String vstartval="-p";
String vendval="p";
String xexp="sinu*cosv";
String yexp="sinu*cosu";
String zexp="cosu";

KLEIN BOTTLE!! Wraps into itself!
String ustartval="0";
String uendval="2*p";
String vstartval="-p";
String vendval="p";
String xexp="20*(1-cosu)";
String yexp="8*sinu*(1-cosu)+3*sinv*(((1/8)*(u*(5.5/(2*p)))*(u*(5.5/(2*p))-5.5)*(u*(5.5/(2*p))-2))+2)";
String zexp="cosv*(((1/8)*(u*(5.5/(2*p)))*(u*(5.5/(2*p))-5.5)*(u*(5.5/(2*p))-2))+2)";

Cool thingy

u+0.25*u^2*sinv*cosu
u^2+0.25*u^2*sinv*sinu
u^2*cosv

pseudosphere 
String ustartval="-2*p";
String uendval="2*p";
String vstartval="0";
String vendval="p";
String xexp="cosu*sinv";
String yexp="sinu*sinv";
String zexp="cosv+log(tan(0.5*v))";

trippy
u: (-2*p, 2*p)    v: (-p, p)
String xexp="sin(3*u)/(2+cosv)"
String yexp="(sinu+2*sin(2*u))/(2+cos(v+p*2/3))"
String zexp="(cosu-2*cos(2*u))*(2+cosv)*(2+cos(v+p*2/3))/4"


enneper 
String ustartval="-p";
String uendval="p";
String vstartval="-p";
String vendval="p";
String xexp="u*((1-(u^2)/3)+v^2)";
String yexp="-v*((1-(v^2)/3)+u^2)";
String zexp="(u^2-v^2)/3";

kuen surface
String ustartval="-p";
String uendval="p";
String vstartval="0";
String vendval="p";
String xexp="(2*(cosu+u*sinu)*sinv)/(1+u^2*(sinv)^2)";
String yexp="(2*(sinu-u*cosu)*sinv)/(1+u^2*(sinv)^2)";
String zexp="log(tan(0.5*v))+(2*cosv/(1+u^2*(sinv)^2))";

dini's surface
String ustartval="-2*p";
String uendval="2*p";
String vstartval="0";
String vendval="p";
String xexp="cosu*sinv";
String yexp="sinu*sinv";
String zexp="(log(tan(0.5*v))+cosv)+0.2*u";

steiner surface
String ustartval="0";
String uendval="p";
String vstartval="0";
String vendval="p";
String xexp="cosu*cosv*sinv";
String yexp="sinu*cosv*sinv";
String zexp="cosu*sinu*(cosv)^2";

right conoid  note: z's (1*u) can vary for different kinds of conoids
String ustartval="-p";
String uendval="p";
String vstartval="-p";
String vendval="p";
String xexp="v*cosu";
String yexp="v*sinu";
String zexp="2*sin(1*u)";

conical edge
String ustartval="-p";
String uendval="p";
String vstartval="-p";
String vendval="p";
String xexp="v*cosu";
String yexp="v*sinu";
String zexp="((16-9*(cosu^2))^0.5)";

spherical helicoid  f(x)=x g(x)=x^2
String ustartval="-2*p";
String uendval="2*p";
String vstartval="-2*p";
String vendval="2*p";
String xexp="u*cosv";
String yexp="u*sinv";
String zexp="u^2+6*v-20";

breather
String ustartval="-14";
String uendval="14";
String vstartval="-37.4";
String vendval="37.4";
String xexp="-u+(2*(0.84)*cosh(0.4*u)*sinh(0.4*u))/(0.4*(0.84*(cosh(0.4*u)^2))+0.16*(sin(0.9165*v)^2))";
String yexp="(0.84*cosh(0.4*u)*(-0.9165*cosv*cos(0.9165*v)-sinv*sin(0.9165*v)))/(0.4*(0.84*(cosh(0.4*u)^2)+0.16*(sin(0.9165*v)^2)))";
String zexp="(0.84*cosh(0.4*u)*(-0.9165*sinv*cos(0.9165*v)+cosv*sin(0.9165*v)))/(0.4*(0.84*(cosh(0.4*u)^2)+0.16*(sin(0.9165*v)^2)))";

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