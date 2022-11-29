package com.example.equisolver;
import java.util.Scanner;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Menu;
import android.view.View;

public class Graphactivity extends Activity{

	graph grap;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_realactivity);
		Bundle extras=getIntent().getExtras();
		String function=extras.getString("f(x)");
		String fromwhere=extras.getString("fromwhere");
		if(fromwhere.equals("integral"))//for integration displaying graph
		{
			float a2=Float.parseFloat(extras.getString("a"));
			float b2=Float.parseFloat(extras.getString("b"));
			float area=Float.parseFloat(extras.getString("area"));
			grap=new graph(this,function,fromwhere,a2,b2,area);
			setContentView(grap);
		}
		else if(fromwhere.equals("differentiate"))//for integration displaying graph
		{
			float x0=Float.parseFloat(extras.getString("x0"));
			float y0=Float.parseFloat(extras.getString("y0"));
			float x1=Float.parseFloat(extras.getString("x1"));
			float y1=Float.parseFloat(extras.getString("y1"));
			float slope=Float.parseFloat(extras.getString("slope"));
			grap=new graph(this,function,fromwhere,x0,y0,x1,y1,slope);
			setContentView(grap);
		}
		else if(fromwhere.equals("equation"))
		{
		String xval=extras.getString("xvalue");
		grap=new graph(this,function,fromwhere,xval);
		setContentView(grap);
		}
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
}
class graph extends View
{

	static String []q=new String[10];
	String function,fromwhere,xval;float a,b,area,slope;
	float x0,y0,x1,y1;//for slope line
	float[] xvall;int setforallx;
	public graph(Context context,String function,String fromwhere,String xval)
	{
		
		super(context);
		xvall=new float[20];
		this.function=function;
		this.fromwhere=fromwhere;
		this.xval=xval;
		setforallx=0;
		int i=0;String temp="";
		while(i<xval.length())
		{
			if(xval.charAt(i)==','&&i!=0)
			{
				xvall[setforallx]=Float.parseFloat(temp);
				setforallx++;
				temp="";
			}
			else if(xval.charAt(i)!=',')
			{
				temp=temp+xval.charAt(i)+"";
				
			}
			i++;
			
		}
		xvall[setforallx]=Float.parseFloat(temp);
	}
	public graph(Context context,String function,String fromwhere,float a,float b,float area)
	{
		super(context);
		this.function=function;
		this.fromwhere=fromwhere;
		this.a=a;
		this.b=b;
		this.area=area;
	}
	public graph(Context context,String function,String fromwhere,float x0,float y0,float x1,float y1,float slope)
	{
		super(context);
		this.function=function;
		this.fromwhere=fromwhere;
		this.slope=slope;
		this.x0=x0;
		this.y0=y0;
		this.x1=x1;
		this.y1=y1;
		
	}
	
	public void onDraw(Canvas g)
	{
		int xcenter=180,ycenter=190;
		super.onDraw(g);
		 System.out.println("enter function");
         
         String functionstr=function;
         String displayfunctioningraph=functionstr;
         Paint p=new Paint();
         p.setStyle(Paint.Style.FILL);
         p.setColor(Color.BLACK);
            float x,fx,fxd,d=1,scale=40;//scaling
           
          g.drawLine(xcenter,0,xcenter,1660,p);
          g.drawLine(0,ycenter,1480,ycenter,p);
            
            
           
            x=-500;//x varies from -500 to 500
            
         functionstr=changetoqformat(functionstr);//changing to q format
        	conversion bal=new conversion();
        	functionstr=bal.changing(functionstr);//changing -2 like to (0-1)*2
        	functionstr=toPostfix(functionstr);//channging to postfix
        	
        	for(int j=-10;j<=10;j++)//for writing numbers from-10 to 10 along x axis
        	{
        	g.drawText(j+"",(xcenter+j*scale)-3,ycenter+10,p);	
        	}
        	
        	for(int j=-10;j<=10;j++)//for writing numbers from-10 to 10 along y axis
        	{
        	if(j!=0)	
        	{g.drawText(j+"",xcenter+10,(ycenter-j*scale),p);}
        	
        	}
        	
        g.drawText("f(x)="+displayfunctioningraph,250,80,p);
        	
        	
         for(float i=0;i<1200;i=i+d)//d is nothing but dx
         {
             fx=function(functionstr,x/scale);fx=scale*fx;//functionstr is required function in string
         	fxd=function(functionstr,(x+d)/scale);fxd=scale*fxd;
         
         	g.drawLine((float)(x+xcenter),(float)(ycenter-fx),(float)(x+d+xcenter),(float)(ycenter-fxd),p);//drawing line between consecutive points
             
             x=x+d;//going to next x i.e x=x+dx
         }
         
         if(fromwhere.equals("integral"))
         {
        	 
        	 for(float i=a*scale;i<=b*scale;i=(float) (i+0.5))
        	 {
        		 fx=function(functionstr,i/scale);fx=scale*fx;//functionstr is required function in string
             
        		 g.drawLine((float)(i+xcenter),ycenter,(float)(i+xcenter),(float)(ycenter-fx), p);
        		 
        		 g.drawText("AREA= "+area,xcenter+b*scale-40,100,p);
        	 }
        	 
         }
         if(fromwhere.equals("differentiate"))
         {
        	 
        	
             
        		 g.drawLine((float)(x0*scale+xcenter),(ycenter-y0*scale),(float)(x1*scale+xcenter),(float)(ycenter-y1*scale), p);
        		 
        		 g.drawText("SLOPE= "+slope,xcenter+x1*scale-40,100,p);
        }
         if(fromwhere.equals("equation"))
         {
        	 for(int i=0;i<=setforallx;i++)
        	 {
        	     g.drawLine((float)(xvall[i]*scale+xcenter),(ycenter-1000*scale),(float)(xvall[i]*scale+xcenter),(float)(ycenter-(-1000)*scale), p);
        		 g.drawText("x= "+xval,xcenter+xvall[i]*scale-40,100,p);
        	 }
        }
         
         
         
	}
	
	public static float function(String str,float x)
    {
    	x=evaluatefunction(str,x);
    	return x;
    }
    public static String changetoqformat(String str)
    {
    
    	int n=str.length();String result="",temp="";int k=0;
    	for(int i=0;i<n;i++)
    	{
    		if(str.charAt(i)=='s'||str.charAt(i)=='c'||str.charAt(i)=='t'||str.charAt(i)=='l')
    		{
    			result=result+str.charAt(i);i=i+2;//to eliminate 'in' in sin, 'os' in cos 'og' in log 
    		}
    		else if(str.charAt(i)=='S'||str.charAt(i)=='C'||str.charAt(i)=='T')
    		{
    			result=result+str.charAt(i);i=i+4;
    		}
    		else if(isnumber(str.charAt(i)))
    		{
    	         while(isnumber(str.charAt(i))||str.charAt(i)=='.')
    	         {
    	        	 temp=temp+str.charAt(i);
    	        	 if(i!=n-1){i++;}
    	        	 else if(i==n-1){ break;}//x^2 ,x+78 there will be problem so only
    	         }
    	         if(i!=n-1){i--;}//x^2 ,x+78 there will be problem so only
    	         
    	        q[k]=temp;
    	        result=result+"q"+k;
    	        k++;
    	        temp="";
    		}
    		else
    		{
    			result=result+str.charAt(i);
    		}
    		
    		
    		
    	}
    	return result;
    	
    }
    
   public static boolean isnumber(char x)
   {
	   String com=x+"";
	   for(int i=0;i<10;i++)
	   {
		   if(com.equals(i+"")){return true;}
	   }
	   return false;
   }
public static String toPostfix(String infix)
	
	{
		 
		char symbol;
		String postfix = "";
		Stack sa = new Stack();
              for(int i=0;i<infix.length();++i)
					
		{
			symbol = infix.charAt(i);
			
                      
			 if (symbol=='('||symbol=='[')
			 
			{
				
				sa.push(symbol);
			}
			else if (symbol==')'||symbol==']')
			
			{
				
				while (sa.peek() != '('&&sa.peek()!='[')
				{
					postfix = postfix + sa.pop();
				}
				sa.pop();
               if (sa.peek()=='s'||sa.peek()=='c'||sa.peek()=='t'||sa.peek()=='l'||sa.peek()=='S'
            	   ||sa.peek()=='C'||sa.peek()=='T')
               postfix=postfix+sa.pop();
              	 
		
			}
                      else if(symbol=='s'||symbol=='c'||symbol=='t'||symbol=='l'||symbol=='S'
                    	  ||symbol=='C'||symbol=='T')
                      {
                          sa.push(symbol);
                      }
                       
			else if(symbol=='+'||symbol=='-'||symbol=='*'||symbol=='/'||symbol=='^')
			
			{
                 while ( prior(symbol) <= prior(sa.peek())&&!sa.isEmpty())
					postfix = postfix + sa.pop();
				
				sa.push(symbol);
			}
			else if(symbol=='e')
			{
				postfix=postfix+'e';
			}
			else 
          {
	postfix = postfix + symbol;
          }
                      
		}
              
		while (!sa.isEmpty())
		postfix = postfix + sa.pop();
		return postfix;
	}
	
static int prior(char x)
{
	if (x == '+' || x == '-')
		return 1;
	if (x == '*' || x == '/'  )
		return 2;
           if (x == '^')
                  return 3;
	return 0;
}


public static float evaluatefunction(String s,float value)
{
	  char ch;String my="";
	int n4=s.length(),some;
	float x,y,hk;
	float r9 = 0;
	 Stack2 a6=new Stack2(n4);
	     for(int i=0;i<n4;i++)  
	     {  
	    	
	        ch=s.charAt(i); 
	        
	       
	       if(ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='^') 
	       {  
	    	   
	          x=a6.pop();
	          y=a6.pop();  
	         switch(ch)  
	         {  
	           case '+':r9=x+y;  

	              break;  
	           case '-':r9=y-x;  
	              break;  
	           case '*':r9=x*y;  
	              break;  
	           case '/':r9=y/x; 
	              break; 
	           case'^':r9=(float) Math.pow(y,x);
	             break;
	           default:r9=0;  
	         }  
	         a6.push(r9);  
	       }
	       if(ch=='x')
	       {
	    	 
	    	a6.push(value);   
	    	
	       }
	       else if(ch=='s'||ch=='c'||ch=='t'||ch=='l'||ch=='S'||ch=='C'||ch=='T')
	       {
	    	
	    	   switch(ch)  
	           {  
	             case 's':r9=(float) Math.sin(a6.pop());  
	                   a6.push(r9);
	                break;  
	             case 'c':r9=(float) Math.cos(a6.pop()); 
	                   a6.push(r9);
	                break;  
	             case 't':r9=(float) Math.tan(a6.pop());  
	                  a6.push(r9); 
	                break;  
	             case 'l':r9=(float) Math.log(a6.pop()); 
	                   a6.push(r9); 
	                break;
	             case 'S':
	            	 hk=a6.pop();if(hk>1){hk=(float) Math.sin(hk-(int)hk);}
	            	 r9=(float) Math.asin(hk);  
               a6.push(r9);
                   break;  
               case 'C':
             	  hk=a6.pop();if(hk>1){hk=(float) Math.cos(hk-(int)hk);}  
             	  r9=(float) Math.acos(hk); 
               a6.push(r9);
                    break;  
               case 'T':r9=(float) Math.atan(a6.pop());  
              a6.push(r9); 
                    break;  
	             default:r9=0; 
	       }
	     }  
	       else if(ch=='e')
		   {
	    	   
			 a6.push((float) 2.7182818284590452353602875);  
		   }
	       else if(ch=='q')
	       {
	    	   i++;
	    	   ch=s.charAt(i);
	    	   some=Integer.parseInt(""+ch);
	    	   a6.push(Float.parseFloat(q[some]));
	       }
	       else if(s.charAt(i)=='0'||s.charAt(i)=='1'||s.charAt(i)=='2'||s.charAt(i)=='3'||s.charAt(i)=='4'||s.charAt(i)=='5'||
	    			   s.charAt(i)=='6'||s.charAt(i)=='7'||s.charAt(i)=='8'||s.charAt(i)=='9'||s.charAt(i)=='.')
	       {
	    	   my=""+s.charAt(i);
	    	   a6.push(Float.parseFloat(my));
	    		my="";	   
	    	  
	       }
	     
	     }
	     r9=a6.pop();
	     return r9;
	     }

	
}
class Stack
{
char a[]=new char[100];
int top=0;

void push(char c)
{

a[++top]= c;


}

char pop()
{
return a[top--];
}

boolean isEmpty()
{
return (top==0)?true:false;
}

char peek()
{
return a[top];
}

}
class Stack2
{  
   public float[] a7;  
   int top1,m5; 
   
   public Stack2(int max)  
   {  
     m5=max+1;  
     a7=new float[m5];  
     top1=0;  
   }  
   public void push(float r)  
   { 
     a7[++top1]=r;  
   }
     
   public float pop()  
   {  
     return a7[top1--];  
     
   }  
  
}
class conversion
{
	
	String changing(String str3)
	{
		int visit[]=new int[10];
		int some[]=new int[10];
		int i;
		for(i=0;i<10;i++)
		{
			visit[i]=0;
			some[i]=0;
		}
		int y,x=-1;
		char a;
		char b;
		int d=0;
		String ne=new String();
		ne="";
		a='\0';
		b=str3.charAt(0);
		for(y=0;y<str3.length();y++)
	    {
			
			if((a=='\0'||a=='+'||a=='*'||a=='/'||a=='(')&&b=='-')
		{
			System.out.println("inside if");
			d=1;
			ne=ne+"((0-1)*";
			x=x+1;
			visit[x]=1;
		}
		if(d==0)
		{
		ne=ne+str3.charAt(y);
		}
	
		a=b;
		d=0;
		for(i=0;i<=x;i++)
		{
		if(str3.charAt(y)=='('&&visit[i]==1)
		{
			some[i]=some[i]+1;
		}
		if(str3.charAt(y)==')'&&visit[i]==1)
		{
			some[i]=some[i]-1;
		}
		if(some[i]==0&&visit[i]==1&&str3.charAt(y)!='-'&&str3.charAt(y)!='s'
			&&str3.charAt(y)!='c'&&str3.charAt(y)!='t'&&str3.charAt(y)!='l'&&str3.charAt(y)!='S'
				&&str3.charAt(y)!='C'&&str3.charAt(y)!='T'&&str3.charAt(y)!='q')
		{
			ne=ne+")";
			visit[i]=0;
		}
		}
		if(y<str3.length()-1)
		{b=str3.charAt(y+1);}	
	    }
		
	return ne;
	}
}

