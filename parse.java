import java.lang.Math;
import java.util.*;
public class parse{
    static String exp;
    static String type1="0123456789.ep";
    static String type2="+-*/^";
    static String type3="()";
    static String type4="cstl"; //cos  sin tan log
    static ArrayList<pObj> pobs=new ArrayList<>();
    static ArrayList<Double> xreturnlist=new ArrayList<>();
    static ArrayList<Double> yreturnlist=new ArrayList<>();
    static ArrayList<Double> zreturnlist=new ArrayList<>();
    public static void parainterp(String str1, String str2, String str3, double ustart, double uend,double vstart, double vend, double numofsteps){
        //evaluates every string and fills values in corresponding returnlist.  to access the list use parse.xreturnlist etc
        xreturnlist.clear();
        yreturnlist.clear();
        zreturnlist.clear();
        interponevar(xreturnlist,str1,ustart,uend,vstart,vend,(uend-ustart)/numofsteps,(vend-vstart)/numofsteps);
        interponevar(yreturnlist,str2,ustart,uend,vstart,vend,(uend-ustart)/numofsteps,(vend-vstart)/numofsteps);
        interponevar(zreturnlist,str3,ustart,uend,vstart,vend,(uend-ustart)/numofsteps,(vend-vstart)/numofsteps);
    }
    public static void interponevar(ArrayList<Double> list,String str, double ustart, double uend, double vstart, double vend, double ustep,double vstep){
      //for every u val  
      for(double u=ustart;u<=uend;u+=ustep){
          //for every v value on interval
          for (double v=vstart;v<=vend; v+=vstep){
             if(str.equals("")){
               list.add(0.0);
             }
             else{
                 String temp=str.replaceAll("u","("+Double.toString(u)+")");
                 exp=temp.replaceAll("v","("+Double.toString(v)+")");
                 //System.out.println(exp);
                 classify();
                 fulleval();
                 double answer=pobs.get(0).num;
                 pobs.clear();
                 list.add(answer);            
             }
          }
        }  
    }
    public static double interp(String str){
        if(str.equals("")){return(0);}
        exp=str;
        classify();
        fulleval();
        double temp=pobs.get(0).num;
        pobs.clear();
        return(temp);
        //parse.eval(0,pobs.size()-1);
        //print();
    }
    public static void classify(){
        char[]temp=exp.toCharArray();
        int a=0;
        for(int i=0;i<temp.length;i++){
            if (type1.indexOf(temp[i])!=-1){
               a=i;
               while(a+1<temp.length && type1.indexOf(temp[a+1])!=-1){   
                   a+=1;
               }
               pobs.add(new pObj(0,exp.substring(i,a+1)));
               i=a;
            }
            else if(type2.indexOf(temp[i])!=-1){
               pobs.add(new pObj(1,Character.toString(exp.charAt(i))));
            }
            else if(type3.indexOf(temp[i])!=-1){
               pobs.add(new pObj(2,Character.toString(exp.charAt(i))));
            }
            else if(type4.indexOf(temp[i])!=-1){
               pobs.add(new pObj(3,Character.toString(exp.charAt(i))));
               i+=2;
            }
            else if(temp[i]=='E'){
               //System.out.println("ok");
               pobs.add(new pObj(2,"*"));pobs.add(new pObj(1,"10")); pobs.add(new pObj(2,"^"));
            }
            else{pobs.add(new pObj(0,Character.toString(exp.charAt(i))));}
        }
    }
    //check if parenthesis exists
    public static boolean paren(){
        for (pObj a:pobs){
            if (a.mode==2){return true;}
        }
        return false;
    }
    //parenthesis eval
    public static void fulleval(){
        int left=0;
        while(paren()){
            for (int j=0;j<pobs.size();j++){
                if (pobs.get(j).paren.equals("(")){
                    left=j;
                }
                if(pobs.get(j).paren.equals(")")){
                    eval(left+1,j-1);
                    pobs.remove(left+2);
                    pobs.remove(left);
                    break;
                }
            }
        }   
        //now the parenthesis are all gone
        eval(0,pobs.size()-1);
    }
    //startpos is  after (  and endpos is before )
    public static void eval(int startpos,int endpos){
        //pobs.remove(endpos);
        for(int i=startpos;i<=endpos;i++){
            if(pobs.get(i).mode==3){
                if(pobs.get(i).sciop.equals("sin")){
                    pobs.get(i).num=Math.sin(pobs.get(i+1).num);
                    pobs.get(i).mode=0;
                    pobs.remove(i+1);
                    endpos-=1;
                }
            }
        }
        for(int i=startpos;i<=endpos;i++){
            if(pobs.get(i).mode==3){
                if(pobs.get(i).sciop.equals("cos")){
                    pobs.get(i).num=Math.cos(pobs.get(i+1).num);
                    pobs.get(i).mode=0;
                    pobs.remove(i+1);
                    endpos-=1;
                }
            }
        }
        for(int i=startpos;i<=endpos;i++){
            if(pobs.get(i).mode==3){
                if(pobs.get(i).sciop.equals("tan")){
                    pobs.get(i).num=Math.tan(pobs.get(i+1).num);
                    pobs.get(i).mode=0;
                    pobs.remove(i+1);
                    endpos-=1;
                }
            }
        }
        for(int i=startpos;i<=endpos;i++){
            if(pobs.get(i).mode==3){
                if(pobs.get(i).sciop.equals("log")){
                    pobs.get(i).num=Math.log(pobs.get(i+1).num);
                    pobs.get(i).mode=0;
                    pobs.remove(i+1);
                    endpos-=1;
                }
            }
        }
        for(int i=startpos;i<=endpos;i++){
            if(pobs.get(i).mode==1){
                if(pobs.get(i).oper.equals("^")){
                    pobs.get(i-1).num=Math.pow(pobs.get(i-1).num,pobs.get(i+1).num);
                    pobs.remove(i+1); pobs.remove(i);
                    i=i-1; endpos-=2;
                }
            }
        }
        for(int i=startpos;i<=endpos;i++){
            if(pobs.get(i).mode==1){
                if(pobs.get(i).oper.equals("/")){
                    pobs.get(i-1).num=pobs.get(i-1).num/pobs.get(i+1).num;
                    pobs.remove(i+1); pobs.remove(i);
                    i=i-1; endpos-=2;
                }
            }
        }  
        for(int i=startpos;i<=endpos;i++){
            if(pobs.get(i).mode==1){
                if(pobs.get(i).oper.equals("*")){
                    pobs.get(i-1).num=pobs.get(i-1).num*pobs.get(i+1).num;
                    pobs.remove(i+1); pobs.remove(i);
                    i=i-1; endpos-=2;
                }}}
        for(int i=startpos;i<=endpos;i++){
            if(pobs.get(i).mode==1){        
                if(pobs.get(i).oper.equals("+")){
                    pobs.get(i-1).num=pobs.get(i-1).num+pobs.get(i+1).num;
                    pobs.remove(i+1); pobs.remove(i);
                    i=i-1; endpos-=2;
                } }}
        for(int i=startpos;i<=endpos;i++){
            if(pobs.get(i).mode==1){        
                if(pobs.get(i).oper.equals("-")){
                    if(i==0 || pobs.get(i-1).mode!=0){
                        pobs.get(i+1).num*=-1;
                        pobs.remove(i);
                        i=i-1; endpos=endpos-1;
                    }
                    else{
                    pobs.get(i-1).num=pobs.get(i-1).num-pobs.get(i+1).num;
                    pobs.remove(i+1); pobs.remove(i);
                    i=i-1; endpos-=2;}
                }  
            }
        }
       // pobs.remove(startpos);
    }
    public static void print(){
       for (pObj a:pobs){
          System.out.println(a);
       }
    }
}