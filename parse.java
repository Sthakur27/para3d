import java.lang.Math;
import java.util.*;
public class parse{
    static String exp;
    static String type1="0123456789.ep";
    static String type2="+-*/^";
    static String type3="()";
    static String type4="cossintanlogcoshsinh"; //cos  sin tan log
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
      int ucount=0;  int vcount=0;
      for(double u=ustart;u<=(uend+(ustep/2));u+=ustep){
          //for every v value on interval
          ucount++;
          vcount=0;
          for (double v=vstart;v<=vend+(vstep/2); v+=vstep){
            vcount++;
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
        //System.out.println("u:"+ucount);
        //System.out.println("v:"+vcount);
    }
    public static double interp(String str){
        if(str.equals("")){return(0);}
        exp=str;
        classify();
        /*System.out.println("*****");
        print();System.out.println("*****");*/
        fulleval();
        double temp=pobs.get(0).num;
        pobs.clear();
        return(temp);
        //parse.eval(0,pobs.size()-1);
        //print();
    }
    public static void classify(){
        //char[]temp=exp.toCharArray();
        int numlength=0;
        for(int i=0;i<exp.length();i++){
            if (type1.indexOf(exp.substring(i,i+1))!=-1){
               numlength=i;
               while(numlength+1<exp.length() && type1.indexOf(exp.substring(numlength+1,numlength+2))!=-1){   
                   numlength+=1;
               }
               pobs.add(new pObj(0,exp.substring(i,numlength+1)));
               i=numlength;
            }
            else if(type2.indexOf(exp.substring(i,i+1))!=-1){
               pobs.add(new pObj(1,exp.substring(i,i+1)));
            }
            else if(type3.indexOf(exp.substring(i,i+1))!=-1){
               pobs.add(new pObj(2,exp.substring(i,i+1)));
            }
            //sinh cosh
            else if(type4.indexOf(exp.substring(i,i+4))!=-1){
               pobs.add(new pObj(3,exp.substring(i,i+4)));
               i+=3;
            }
            //sin tan cos log
            else if(type4.indexOf(exp.substring(i,i+3))!=-1){
               pobs.add(new pObj(3,exp.substring(i,i+3)));
               i+=2;
            }
            else if(exp.substring(i,i+1).equals("E")){
               //System.out.println("ok");
               pobs.add(new pObj(2,"*"));pobs.add(new pObj(1,"10")); pobs.add(new pObj(2,"^"));
            }
            else{pobs.add(new pObj(0,exp.substring(i,i+1)));}
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
                if (pobs.get(j).oper.equals("(")){
                    left=j;
                }
                if(pobs.get(j).oper.equals(")")){
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
      for(int prior=4;prior>=1;prior=prior-1){
         for(int i=startpos;i<endpos;i++){
            if(pobs.get(i).priority==prior){
              int numofremovals=operate(pobs,i);
              for (int j=0;j<numofremovals;j++){                
                  pobs.remove(i);
              }
              i-=1; endpos-=numofremovals;
              /*if(pobs.get(i).mode==3){pobs.remove(i); endpos-=1; i-=1;}
              else{pobs.remove(i+1);pobs.remove(i);endpos-=2; i-=1;}*/
            }
              
         }
      }
    }
    public static int operate(ArrayList<pObj> list, int i){
        if(pobs.get(i).mode==1){
           //System.out.println(list.get(i).opernum);
           if(list.get(i).opernum==1){list.get(i-1).num=list.get(i-1).num+list.get(i+1).num;      }
           else if(list.get(i).opernum==2){
                 if(i==0||list.get(i-1).mode!=0){
                     list.get(i+1).num*=-1; return(1);
                 }
                 else{list.get(i-1).num=list.get(i-1).num-list.get(i+1).num; }}
           else if(list.get(i).opernum==3){list.get(i-1).num=list.get(i-1).num*list.get(i+1).num;  }
           else if(list.get(i).opernum==4){list.get(i-1).num=list.get(i-1).num/list.get(i+1).num;  }
           else if(list.get(i).opernum==5){list.get(i-1).num=Math.pow(list.get(i-1).num,list.get(i+1).num);  }
           return(2);
        }
        else if(pobs.get(i).mode==3){
           if(list.get(i).opernum==6){list.get(i+1).num=Math.sin(list.get(i+1).num); }
           else if(list.get(i).opernum==7){list.get(i+1).num=Math.cos(list.get(i+1).num); }
           else if(list.get(i).opernum==8){list.get(i+1).num=Math.tan(list.get(i+1).num); }
           else if(list.get(i).opernum==9){list.get(i+1).num=Math.log(list.get(i+1).num); }
           else if(list.get(i).opernum==10){list.get(i+1).num=Math.sinh(list.get(i+1).num); }
           else if(list.get(i).opernum==11){list.get(i+1).num=Math.cosh(list.get(i+1).num); }
           return(1);
        }
        return(0);
    }
    public static void print(){
       for (pObj a:pobs){
          System.out.println(a);
       }
    }
}