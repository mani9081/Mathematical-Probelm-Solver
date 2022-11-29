
package com.example.equisolver;
import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Realactivity extends Activity implements OnClickListener{

	String infix=new String();
	String infix1=new String();
	String displaytext=new String();
    char postfix[]=new char[200];
	String g[]={".","..","...","...."};
	int A=0,B=0,k=0,value=0;
	String word;
	static String[] q=new String[10];
	double demo=0,a=1,a1=1;
	String let,realxvalue;
	int i,j,count,some;
    double aq[]=new double[4];
	double x=1,x1;
	char t;
	int b1[]=new int[10000];
	double imag,real;
	String str;
	TextView t1;public int zeronotinclude=0; 
	
	Button sin,cos,tan,sin1,cos1,tan1,log,p,m,mu,d,point,pow,eq,L1,R1,one,two,three,four,five,six,seven,eight,nine,zero,ans,e,x2,reset,calcu,bala;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realactivity);
     t1 =(TextView) findViewById(R.id.editText1);
     word="";
     sin=(Button) findViewById(R.id.sin);      one=(Button) findViewById(R.id.button1);
   	 cos=(Button) findViewById(R.id.cos);      two=(Button) findViewById(R.id.button2);
     tan=(Button) findViewById(R.id.tan);      three=(Button) findViewById(R.id.button3);
   	 log=(Button) findViewById(R.id.log);      four=(Button) findViewById(R.id.button4);
   	 sin1=(Button) findViewById(R.id.S);       five=(Button) findViewById(R.id.button5);
   	 cos1=(Button) findViewById(R.id.C);       six=(Button) findViewById(R.id.button6);
   	 tan1=(Button) findViewById(R.id.T);       seven=(Button) findViewById(R.id.button7);
   	 p=(Button) findViewById(R.id.plus);       eight=(Button) findViewById(R.id.button8);
   	 m=(Button) findViewById(R.id.minus);      nine=(Button) findViewById(R.id.button9);
   	 mu=(Button) findViewById(R.id.mul);       zero=(Button) findViewById(R.id.button0);
   	 d=(Button) findViewById(R.id.div);        ans=(Button) findViewById(R.id.ans);
   	 point=(Button) findViewById(R.id.dot);    e=(Button) findViewById(R.id.e);
   	 pow=(Button) findViewById(R.id.power);    x2=(Button) findViewById(R.id.x);
   	 eq=(Button) findViewById(R.id.eq);        reset=(Button) findViewById(R.id.reset);
   	 L1=(Button) findViewById(R.id.left);      calcu=(Button) findViewById(R.id.calcu);
   	 R1=(Button) findViewById(R.id.right);    
         sin.setOnClickListener(this);     one.setOnClickListener(this);
        cos.setOnClickListener(this);     two.setOnClickListener(this);
        tan.setOnClickListener(this);     three.setOnClickListener(this);
        log.setOnClickListener(this);     four.setOnClickListener(this);
        sin1.setOnClickListener(this);    five.setOnClickListener(this);
        cos1.setOnClickListener(this);    six.setOnClickListener(this);
        tan1.setOnClickListener(this);    seven.setOnClickListener(this);
        p.setOnClickListener(this);       eight.setOnClickListener(this);
        m.setOnClickListener(this);       nine.setOnClickListener(this);
        mu.setOnClickListener(this);      zero.setOnClickListener(this);
        d.setOnClickListener(this);       ans.setOnClickListener(this);
        point.setOnClickListener(this);   e.setOnClickListener(this);
        pow.setOnClickListener(this);     x2.setOnClickListener(this);
        eq.setOnClickListener(this);      reset.setOnClickListener(this);
        L1.setOnClickListener(this);      calcu.setOnClickListener(this);
        R1.setOnClickListener(this);     
   
     
       
        
    }
    public void onClick(View ae)
    {
    	
    Button some=(Button) ae;
    String str =some.getText().toString();
    if(str.equals("dot")){str=".";infix=infix+str;displaytext=displaytext+str;t1.setText(displaytext);}
    else if(str.equals("reset"))
    {
    	
    	t1.setText("");
    	infix="";displaytext="";
    	word="";zeronotinclude=0;
    	x=1;
    	k=0;
    	a=1;
    	a1=1;
    }
    else if(str.equals("graph"))
    {
    	Intent graph=new Intent(this,Graphactivity.class);
    	graph.putExtra("f(x)",infix);
    	graph.putExtra("fromwhere","equation");
    	graph.putExtra("xvalue",realxvalue);
    	startActivity(graph);
    	
    }
    
    else if(str.equals("ans"))
    {
    	
    	try
    	{
    		
    		infix1=changetoqformat(infix);
    	    infix1=changing(infix1);
    	    InfixToPostfix bala=new InfixToPostfix();	
    	    infix1= bala.toPostfix(infix1);
    	    evaluation eaa=new evaluation();
    	    realxvalue=eaa.evaluatereal(infix1);
    	    if(realxvalue.equals(""))
    	    {
    	    	t1.setText("No real solutions");
    	    }
    	    else{t1.setText(realxvalue);}
    	}
    	catch(Exception e){ t1.setText("invalid input");}
    }
    else if(str.equals("="))
    {
    	//do nothing;
    	zeronotinclude=1;
    	displaytext=displaytext+str;t1.setText(displaytext);
    }
    else
    {
    	if(zeronotinclude==0)
    	{infix=infix+str;}
    	displaytext=displaytext+str;t1.setText(displaytext);
    }
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
   
 

    
    
    
    public class InfixToPostfix
	{

	public String toPostfix(String infix)
	
	{
		Stack sa = new Stack();
		char symbol;
		String postfix = "";
		
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
		System.out.println("this is"+postfix);
		return postfix;
	}
	
	
	int prior(char x)
	{
		if (x == '+' || x == '-')
			return 1;
		if (x == '*' || x == '/'  )
			return 2;
               if (x == '^')
                      return 3;
		return 0;
	}
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    class evaluation
    {
    	
    	public String evaluatereal(String s)
    	{
    		
    		
    		double x=-500,no1,no2,n=0.5;
    		no1=x;
    		no2=no1+n;
    		String xvalue="";int xcount=0;
    		DecimalFormat df=new DecimalFormat("#.####");
    		double wastecount=0;
    		while(no1<=500)
    		{
    			wastecount=wastecount+0.01;
    			
    			if(check(no1,no2,s)==0)
    			{
    				no1=no2;
    				no2=no2+n;
    			}
    			else if(check(no1,no2,s)==1)
    			{
    				
    				no2=no1+n/2;
    				n=n/2;
    				
    			}
    			else if(check(no1,no2,s)==2)
    			{
    				
    				if(no1>0)//to round of positive no such as 1.99999 to 2 ,6.99999 to  7 etc
					{
						int temp=(int)no1;
						if(temp+1-no1<0.00001){no1=temp+1;}
					}
					else if(no1<0)// to round of -1.00001 to 1,-2.000001  to -2 etc
					{
						int temp=(int)no1;
						if(temp-no1<0.00001){no1=temp;}
					}
    				if(xvalue.equals(""))
    				{
    					xvalue=xvalue+df.format(no1);
    				}
    				else if(!xvalue.equals(""))
    				{
    				xvalue=xvalue+","+df.format(no1);
    				}
    				no1=(float) (no2+0.1);//say dx
    				n=(float) 0.5;
    				no2=no1+n;
    				xcount++;
    				
    			}
    			if(wastecount>50){break;}//to stop stopless ilterative loops
    		}
    		
    		return xvalue;
    	}
    	
    	public int check(double no1,double no2,String s)
    	{
    		evaluation ea=new evaluation();
    		
    		
    		if((no1-no2<0.0001)&&(evaluatefunction(s, no1)<0.000001)&&(evaluatefunction(s, no1)>-0.000001))//since no1 is greater than no2
    		{
    			return 2;
    		}
    		else if((ea.evaluatefunction(s, no2)<0&&ea.evaluatefunction(s, no1)>0)||(ea.evaluatefunction(s, no2)>0&&ea.evaluatefunction(s, no1)<0))
    		{
    			
    			return 1;
    		}
    		return 0;
            }
    	
    	
  public  double evaluatefunction(String s,double value)
{
	  char ch;String my="";
	int n4=s.length(),some;
	double x,y,r9 = 0,hk;
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
	           case'^':r9=Math.pow(y,x);
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
	             case 's':r9=Math.sin(a6.pop());  
	                   a6.push(r9);
	                break;  
	             case 'c':r9=Math.cos(a6.pop()); 
	                   a6.push(r9);
	                break;  
	             case 't':r9=Math.tan(a6.pop());  
	                  a6.push(r9); 
	                break;  
	             case 'l':r9=Math.log(a6.pop()); 
	                   a6.push(r9); 
	                break;
	             case 'S':
	            	 hk=a6.pop();if(hk>1){hk=Math.sin(hk-(int)hk);}
	            	 r9=Math.asin(hk);  
               a6.push(r9);
                   break;  
               case 'C':
             	  hk=a6.pop();if(hk>1){hk=Math.cos(hk-(int)hk);}  
             	  r9=Math.acos(hk); 
               a6.push(r9);
                    break;  
               case 'T':r9=Math.atan(a6.pop());  
              a6.push(r9); 
                    break;  
	             default:r9=0; 
	       }
	     }  
	       else if(ch=='e')
		   {
	    	   
			 a6.push(2.7182818284590452353602875);  
		   }
	       else if(ch=='q')
	       {
	    	   i++;
	    	   ch=s.charAt(i);
	    	   some=Integer.parseInt(""+ch);
	    	   a6.push(Double.parseDouble(q[some]));
	       }
	       else if(s.charAt(i)=='0'||s.charAt(i)=='1'||s.charAt(i)=='2'||s.charAt(i)=='3'||s.charAt(i)=='4'||s.charAt(i)=='5'||
	    			   s.charAt(i)=='6'||s.charAt(i)=='7'||s.charAt(i)=='8'||s.charAt(i)=='9'||s.charAt(i)=='.')
	       {
	    	   my=""+s.charAt(i);
	    	   a6.push(Double.parseDouble(my));
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
	   public double[] a7;  
	   int top1,m5; 
	   
	   public Stack2(int max)  
	   {  
	     m5=max+1;  
	     a7=new double[m5];  
	     top1=0;  
	   }  
	   public void push(double r)  
	   { 
	     a7[++top1]=r;  
	   }
	     
	   public double pop()  
	   {  
	     return a7[top1--];  
	     
	   }  
	  
	}
	
	
		public static String changing(String str3)
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
			System.out.println("i am here");
			int d=0;
			String ne=new String();
			ne="";
			a='\0';
			b=str3.charAt(0);
			for(y=0;y<str3.length();y++)
		    {
				System.out.println(b);
				System.out.println("i for loop am here");
				
				
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
			System.out.println("ne is"+ne);
		return ne;
		}
	
   
    public boolean onCreateOptionsMenu(Menu menu) {
        
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}