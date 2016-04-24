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
String xexp="(5*(5-13*cosu*cosv)+144*cosu)/(13-5*cosu*cosv)";
String yexp="(12*sinu*(13-5*cosv))/(13-5*cosu*cosv)";
String zexp="(12*sinv*(5*cosu-5))/(13-5*cosu*cosv)";

ring cyclide    same as before but d=8
String xexp="(8*(5-13*cosu*cosv)+144*cosu)/(13-5*cosu*cosv)";
String yexp="(12*sinu*(13-8*cosv))/(13-5*cosu*cosv)";
String zexp="(12*sinv*(5*cosu-8))/(13-5*cosu*cosv)";

Symmetrical horn cyclide
String xexp="(0*(5-13*cosu*cosv)+144*cosu)/(13-5*cosu*cosv)";
String yexp="(12*sinu*(13-0*cosv))/(13-5*cosu*cosv)";
String zexp="(12*sinv*(5*cosu-0))/(13-5*cosu*cosv)";

parabolic cyclide
k={0.5,1,1.5}
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
String xexp="(1+(v/(4*p))*cos((u/2+p)/2))*cos(u/2+p)";
String yexp="(1+(v/(4*p))*cos(u/2+p))*sin(u/2+p)";
String zexp="(v/(4*p))*sin((u/2+p)/2)";

torus!
String xexp="(3+cosv)*cosu";
String yexp="(3+cosv)*sinu";
String zexp="5*sinv";

Klein bottle
String xexp="(3+cos(u/2)*sinv-sin(u/2)*sin(v*2))*cosu";
String yexp="(3+cos(u/2)*sinv-sin(u/2)*sin(v*2))*sinu";
String zexp="sin(u/2)*sinv+cos(u/2)*sin(2*v)";

hel something
String xexp="sinv*(1+u^2)^0.5";
String yexp="cosv*(1+u^2)^0.5";
String zexp="u";


bottle shape
String xexp="(-2/15)*cosu*(3*cosv-30*sinu+90*(cosu^4)*sinu-60*(cosu^6)*sinu+5*cosu*cosv*sinu)";
String zexp="(-1/15)*sinu*(3*cosv-3*(cosu^2)*cosv-48*(cosu^4)*cosv+48*(cosu^6)*cosv-60*sinu+5*cosu*cosv*sinu-5*(cosu^3)*cosv*sinu-80*(cosu^5)*cosv*sinu+80*(cosu^7)*cosv*sinu)";
String yexp="(2/15)*(3+5*cosu*sinu)*sinv";
a=5

curly bottle  infinity loops   -2p 2p -2p 2p
String xexp = "cos(u/2)*(5+sin(v/2)*cos(u/4)-sin(v)*sin(u/4)/2)";
String yexp = "sin(u/2)*(5+sin(v/2)*cos(u/4)-sin(v)*sin(u/4)/2)";
String zexp = "sin(u/4)*sin(v/2)+cos(u/4)*sin(v)/2";

0 2p -2p 2p
3d mobius???
String xexp = "20*(1-cosu)+5*sinv*cosu*(2+cosu)";
String yexp = "8*sinu*(1-cosu)+5*sinv*sinu*(2+cosu)";
String zexp="cosv*(2+cosu)";

0 2p -2p 2p
//really close klein bottles
String xexp = "20*(1-cosu)";
String yexp = "8*sinu*(1-cosu)+sinv*(2+cosu)";
String zexp="cosv*(2+cosu)";
String xexp = "1/(u^4+1)";
String yexp = "((u^2+u+1)/(u^4+1))+0.05*sinv*(2+1.9*sin(u/4))";
String zexp="0.05*cosv*(2+1.9*sin(u/4))";

Klein BOTTLE! 0 2.01p -p p
String xexp = "20*(1-cosu)";
String yexp = "8*sinu*(1-cosu)+3*sinv*(2-cos(u/2))";
String zexp="cosv*(2-cos(u/2))";

REAL KLEIN BOTTLE!! Wraps into itself!
String xexp = "20*(1-cosu)";
String yexp = "8*sinu*(1-cosu)+3*sinv*(2*p+((2*p)/(2.4*p-u))-((2*p)/(u+2.1))-(u+2))";
String zexp="cosv*(2*p+((2*p)/(2.4*p-u))-((2.072*p)/(u+2.1))-(u+2))";p))-2)+2)";

2nd KLEIN BOTTLE!! Wraps into itself!
String xexp = "20*(1-cosu)";
String yexp = "8*sinu*(1-cosu)+3*sinv*(((1/8)*(u*(5.5/(2*p)))*(u*(5.5/(2*p))-5.5)*(u*(5.5/(2*p))-2))+2)";
String zexp="cosv*(((1/8)*(u*(5.5/(2*p)))*(u*(5.5/(2*p))-5.5)*(u*(5.5/(2*p))-2))+2)";

Cool thingy

u+0.25*u^2*sinv*cosu
u^2+0.25*u^2*sinv*sinu
u^2*cosv

pseudosphere  -2p 2p  0 p
String xexp = "cosu*sinv";
String yexp = "sinu*sinv";
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

kluen surface
String ustartval="-p";
String uendval="p";
String vstartval="0";
String vendval="p";
String xexp="(2^0.5*cos(2*u)*(cosv)^2+cosu*sin(2*v)";
String yexp="((sinu-u*cosu)*sinv)/(1+u^2*(sinv)^2)";
String zexp="log(tan(0.5*v))+(2*cosv/(1+u^2*(sinv)^2))";
*/