package com.example.equisolver;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Imaginaryactivity extends Activity implements OnClickListener{

	String infix=new String();
	String displaytext=new String();
    char postfix[]=new char[200];
	String g[]={".","..","...","...."};
	int A=0,B=0,k=0,value=0;
	String word;
	static String[] q=new String[10];
	double demo=0,a=1,a1=1;
	String let;
	int i,j,count,some;
    double aq[]=new double[4];
	double x=1,x1;
	char t;
	int b1[]=new int[10000];
	double imag,real;
	String str;
	TextView t1;public int zeronotinclude=1; 
	
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
       t1.setText("enter the equation of form x=f(x)");
       
        
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
    	word="";
    	x=1;
    	k=0;
    	a=1;
    	a1=1;zeronotinclude=1;
    }
    else if(str.equals("calculator"))
    {
    	t1.setText("enter the expression");
    }
    
    else if(str.equals("ans"))
    {
    	
    	try
    	{
    		infix=changetoqformat(infix);
    	    infix=changing(infix);
    	    InfixToPostfix bala=new InfixToPostfix();	
    	    infix= bala.toPostfix(infix);
    	    evaluation eaa=new evaluation();
    	    String realxvalue=eaa.evaluatecomplex(infix);
    	    t1.setText(realxvalue);
    	}
    	catch(Exception e){ t1.setText("invalid input");}
    }
    else if(str.equals("="))
    {
    	//do nothing;
    	zeronotinclude=0;//to eliminate x=  in x=f(x)s
    	displaytext=displaytext+str;t1.setText(displaytext);
    }
    else
    {
    	displaytext=displaytext+str;t1.setText(displaytext);
    	if(zeronotinclude==0)
    	{infix=infix+str;}
    	
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
    	
     public String evaluatecomplex (String s)  
 	   {  
 		   double hk;
 		   char ch;
 	     int n4,i;
 	     long m;
 	     double x,y;
 	     complex Y=new complex();
 	     complex X=new complex();
 	     complex A1=new complex();
 	     complex A=new complex();
 	     A.RE=1;
 	     A.IM=1;
 	     String my="";
 	     n4=s.length();  
 	     complex r8=new complex();
 	     complex no=new complex();
 	     Stack3 a8=new Stack3(n4);
 	    
 	    
 	     
 	    
 	     for(m=0;m<1000;m++)
 	     {
 	    	 A1.RE=A.RE;
 	    	 A1.IM=A.IM;
 	     for(i=0;i<n4;i++)  
 	     {  
 	    	
 	        ch=s.charAt(i); 
 	        
 	       
 	       if(ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='^') 
 	       {  
 	    	   
 	          X=a8.pop();
 	          Y=a8.pop();  
 	         switch(ch)  
 	         {  
 	           case '+':r8=no.plus(X,Y);  

 	              break;  
 	           case '-':r8=no.minus(Y,X);  
 	              break;  
 	           case '*':r8=no.product(X,Y);  
 	              break;  
 	           case '/':r8=no.divide(Y,X); 
 	            break; 
 	           case'^':r8=no.power(Y,X);
 	             break;
 	           default:r8.RE=1;
 	                   r8.IM=1; 
 	         }  
 	         a8.push(r8);  
 	       }
 	       if(ch=='x')
 	       {
 	    	 
 	    	a8.push(A);   
 	    	
 	       }
 	       else if(ch=='s'||ch=='c'||ch=='t'||ch=='l')
 	       {
 	    	
 	    	   switch(ch)  
 	           {  
 	             case 's':r8=no.sin(a8.pop());  
 	                   a8.push(r8);
 	                break;  
 	             case 'c':r8=no.cos(a8.pop()); 
 	                   a8.push(r8);
 	                break;  
 	             case 't':r8=no.tan(a8.pop());  
 	                  a8.push(r8); 
 	                break;  
 	             case 'l':r8=no.log(a8.pop()); 
 	                   a8.push(r8); 
 	                break;
 	             default:r8.RE=1;
                          r8.IM=1;
 	       }
 	     }  
 	       else if(ch=='e')
 		   {
 	    	   complex e2=new complex();
 	    	   e2.RE=2.7182818284590452353602875;
 	    	   e2.IM=0;
 			 a8.push(e2);  
 		   }
 	       else if(s.charAt(i)=='0'||s.charAt(i)=='1'||s.charAt(i)=='2'||s.charAt(i)=='3'||s.charAt(i)=='4'||s.charAt(i)=='5'||
 	    			   s.charAt(i)=='6'||s.charAt(i)=='7'||s.charAt(i)=='8'||s.charAt(i)=='9'||s.charAt(i)=='.')
 	       {
 	    	   complex nu=new complex();
 	    	    my=""+s.charAt(i);
 	    	    nu.RE=Double.parseDouble(my);
 	    	    nu.IM=0;
 	    	    System.out.println("now for"+Double.parseDouble(my)+"imaginary vcalue is"+nu.RE+" "+nu.IM);
 	    	   a8.push(nu);
 	    		my="";	   
 	    	
 	       }
 	       else if(s.charAt(i)=='q')
 	       {
 	    	   complex nu=new complex();
 	    	   i++;
 	    	   ch=s.charAt(i);
 	    	   some=Integer.parseInt(""+ch);
 	    	   nu.RE=Double.parseDouble(q[some]);
 	    	   nu.IM=0;
 	    	   a8.push(nu);
 	       }
 	     }
 	     A=a8.pop();  
 	     System.out.println(A1.RE+" "+A1.IM+"   "+A.RE+"  "+A.IM);
 	     A.RE=(A.RE+A1.RE)/2;
 	     A.IM=(A.IM+A1.IM)/2;
 	     
 	     }
 	     System.out.println("the postfix"+s);
 	    if(A.RE<0.0001){A.RE=0;}
	     if(A.IM<0.0001){A.IM=0;}
 	     return("value of x="+A.RE+" +-  "+A.IM+"i");
 	     }
 	       
 	   
 	 
    }
 	   
 	   
 	   
	class complex {
		double RE;
		double IM;
		public complex plus(complex c1,complex c2)
		{
		complex c3=new complex();
		c3.RE=c1.RE+c2.RE;
		c3.IM=c1.IM+c2.IM;
		return c3;	
		}
		public complex minus(complex c1,complex c2)
		{
		complex c3=new complex();
		c3.RE=c1.RE-c2.RE;
		c3.IM=c1.IM-c2.IM;
		System.out.println("in minus "+c1.RE+" "+c1.IM+"and"+c2.RE+" "+c2.IM+"is"+c3.RE+c3.IM);
		return c3;	

		}
		public complex product(complex c1,complex c2)
		{
		complex c3=new complex();
		c3.RE=(c1.RE*c2.RE)-(c1.IM*c2.IM);
		c3.IM=(c1.RE*c2.IM)+(c1.IM*c2.RE);	
			return c3;
		}
		public complex divide(complex c1,complex c2)
		{
		complex c3=new complex();
		double const1;
		const1=Math.pow(c2.RE,2)+Math.pow(c2.IM,2);
		const1=1/const1;
		c3.RE=const1*((c1.RE*c2.RE)+(c1.IM*c2.IM));
		c3.IM=const1*((c1.IM*c2.RE)-(c1.RE*c2.IM));


		return c3;
		}
		public complex sin(complex c1)
		{
			complex c3=new complex();
			c3.RE=Math.sin(c1.RE)*Math.cosh(c1.IM);
			c3.IM=Math.cos(c1.RE)*Math.sinh(c1.IM);
			return c3;
		}
		public complex cos(complex c1)
		{
			
			complex c3=new complex();
			c3.RE=Math.cos(c1.RE)*Math.cosh(c1.IM);
			System.out.println(c3.RE);
			c3.IM=-Math.sin(c1.RE)*Math.sinh(c1.IM);
			System.out.println("cos value of"+c1.RE+"  "+c1.IM+" is"+c3.RE+" "+c3.IM);
			return c3;
		}
		public complex tan(complex c1)
		{
			complex c3=new complex();
			double const1;
			const1=Math.pow((Math.cos(c1.RE)*Math.cosh(c1.IM)),2)+Math.pow((Math.sin(c1.RE)*Math.sinh(c1.IM)),2);
			const1=1/const1;
			c3.RE=(Math.sin(c1.RE)*Math.cosh(c1.IM)*Math.cos(c1.RE)*Math.cosh(c1.IM)+
			      Math.cos(c1.RE)*Math.sinh(c1.IM)*Math.sin(c1.RE)*Math.sinh(c1.IM))/const1;
			
			c3.IM=(Math.cos(c1.RE)*Math.sinh(c1.IM)*Math.cos(c1.RE)*Math.cosh(c1.IM)-
			Math.sin(c1.RE)*Math.sinh(c1.IM)*Math.sin(c1.RE)*Math.cosh(c1.IM))/const1;
		return c3;
		}
		public complex log(complex c1)
		{
			complex c3=new complex();
			double const1;
			const1=Math.pow(c1.RE,2)+Math.pow(c1.IM,2);
			const1=Math.pow(const1,0.5);
			c3.RE=Math.log(const1);
			const1=c1.RE/const1;
			c3.IM=Math.acos(const1);
			
			return c3;
		}

		public complex power(complex c1,complex c2)
		{
			complex c3=new complex();
			double const2;
			double const1;
			double w,x,y,z;
			const2=Math.pow((Math.pow(c1.RE,2)+Math.pow(c1.IM,2)),0.5);
			const1=Math.pow((Math.pow(c1.RE,2)+Math.pow(c1.IM,2)),(c2.RE/2))
			*Math.pow(2.7183,-(c2.IM*Math.acos(c1.RE/const2)));
			
			w=Math.cos(c2.RE*Math.acos(c1.RE/const2));
			x=Math.sin(c2.RE*Math.acos(c1.RE/const2));
			y=Math.cos(Math.log(Math.pow(const2,c2.IM)));
			z=Math.sin(Math.log(Math.pow(const2,c2.IM)));
			c3.RE=const1*(w*y-x*z);
			c3.IM=const1*(x*y+w*z);
			return c3;
			
		}
		public complex plus2(double a,complex c1)
		{
			complex c3=new complex();
			c3.RE=a+c1.RE;
			c3.IM=c1.IM;
			return c3;
		}

		public complex plus3(complex c1,double a)
		{
			complex c3=new complex();
			c3.RE=a+c1.RE;
			c3.IM=c1.IM;
			return c3;
		}

		public complex minus2(double a,complex c1)
		{
			complex c3=new complex();
			c3.RE=a-c1.RE;
			c3.IM=-c1.IM;
			return c3;
		}

		public complex minus3(complex c1,double a)
		{
			complex c3=new complex();
			c3.RE=c1.RE-a;
			c3.IM=c1.IM;
			return c3;
		}
		public complex product2(double a,complex c1)
		{
			complex c3=new complex();
			c3.RE=a*c1.RE;
			c3.IM=a*c1.IM;
			return c3;
		}

		public complex product3(complex c1,double a)
		{
			complex c3=new complex();
			c3.RE=a*c1.RE;
			c3.IM=a*c1.IM;
			return c3;
		}
		public complex divide2(double a,complex c1)
		{
			double const1;
			complex c3=new complex();
			const1=Math.pow(c1.RE,2)+Math.pow(c1.IM,2);
			const1=1/const1;
			c3.RE=const1*a*c1.RE;
			c3.IM=-const1*a*c1.IM;
			return c3;
		}

		public complex divide3(complex c1,double a)
		{
			complex c3=new complex();
			c3.RE=c1.RE/a;
			c3.IM=c1.IM/a;
			return c3;
		}
		public complex power2(complex c1,double a)
		{
			complex c2=new complex();
			complex c3=new complex();
			c2.IM=0;c2.RE=a;	
			double const4;
			double const1;
			const4=Math.pow((Math.pow(c2.RE,2)+Math.pow(c2.IM,2)),0.5);
			const1=Math.pow(2.7183,-(c1.IM*Math.acos(c2.RE/const4)));
			c3.RE=const1*Math.pow(const4,(2*c1.RE))*Math.cos(c1.RE*Math.acos(c2.RE/const4));
			c3.IM=const1*Math.pow(const4,(2*c1.RE))*Math.sin(c1.RE*Math.asin(c2.IM/const4));
			return c3;
		}
		public complex power3(double a,complex c2)
		{
			complex c1=new complex();
			complex c3=new complex();
			c1.RE=a;c1.IM=0;
			double const4;
			double const1;
			const4=Math.pow((Math.pow(c2.RE,2)+Math.pow(c2.IM,2)),0.5);
			const1=Math.pow(2.7183,-(c1.IM*Math.acos(c2.RE/const4)));
			c3.RE=const1*Math.pow(const4,(2*c1.RE))*Math.cos(c1.RE*Math.acos(c2.RE/const4));
			c3.IM=const1*Math.pow(const4,(2*c1.RE))*Math.sin(c1.RE*Math.asin(c2.IM/const4));
			return c3;
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
	
	class Stack3
	{  
	   public complex[] a7;  
	   int top1,m5; 
	   
	   public Stack3(int max)  
	   {  
	     m5=max;  
	     a7=new complex[m5];  
	     top1=0;  
	   }  
	   public void push(complex so)  
	   { 
	     a7[++top1]=so;  
	   }
	     
	   public complex pop()  
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