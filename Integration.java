package com.example.equisolver;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Integration extends Activity implements OnClickListener{

	Button calculate,graph,reset;
	EditText a,b,function;
	TextView ans;
    String functionstr1,functionstr;
	static String []q=new String[10];
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_integration);
		calculate=(Button)findViewById(R.id.calculatee);
		graph=(Button)findViewById(R.id.graphh);
		reset=(Button)findViewById(R.id.resett);
		a=(EditText)findViewById(R.id.a1);
		b=(EditText)findViewById(R.id.b1);
		function=(EditText)findViewById(R.id.function);
		ans=(TextView)findViewById(R.id.anss);
		
		
		calculate.setOnClickListener(this);
		graph.setOnClickListener(this);
		reset.setOnClickListener(this);
	
	}
   
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.integration, menu);
		return true;
	}

	@Override
	public void onClick(View ae) 
	{
	
		Button suma=(Button)ae;
		String str=suma.getText().toString();
		
		if(str.equals("calculate"))
		{
			double a1=Double.parseDouble(a.getText().toString());
            double b1=Double.parseDouble(b.getText().toString());
            double x=a1;double dx=0.0001;
            float area=0;
            functionstr=function.getText().toString();
            functionstr1=changetoqformat(functionstr);//changing to q format
           	conversion bal=new conversion();
           	functionstr1=bal.changing(functionstr1);//changing -2 like to (0-1)*2
           	functionstr1=toPostfix(functionstr1);//channging to postfix
             for(x=a1+dx;x<b1;x=x+dx)//d is nothing but dx
             {
             	area=(float) (area+Math.sqrt(Math.pow(function(functionstr1,x),2))*dx);//for modulus for f(x)
             	
             }
             ans.setText(area+"");
            
			
		}
		else if(str.equals("graph"))
		{
			Intent graph=new Intent(this,Graphactivity.class);
	    	graph.putExtra("f(x)",function.getText().toString());
	    	graph.putExtra("fromwhere","integral");
	    	graph.putExtra("a",a.getText().toString());
	    	graph.putExtra("b",b.getText().toString());
	    	graph.putExtra("area",ans.getText().toString());
	    	startActivity(graph);
		}
		else if(str.equals("reset"))
		{
			
			
			a.setText("");
			b.setText("");
			function.setText("");
			ans.setText("");
			
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

	 
	 public static double function(String str,double x)
	    {
	    	x=evaluatefunction(str,x);
	    	return x;
	    }
	 

public static double evaluatefunction(String s,double value)
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
	         a6.push((float) r9);  
	       }
	       if(ch=='x')
	       {
	    	 
	    	a6.push((float) value);   
	    	
	       }
	       else if(ch=='s'||ch=='c'||ch=='t'||ch=='l'||ch=='S'||ch=='C'||ch=='T')
	       {
	    	
	    	   switch(ch)  
	           {  
	             case 's':r9=Math.sin(a6.pop());  
	                   a6.push((float) r9);
	                break;  
	             case 'c':r9=Math.cos(a6.pop()); 
	                   a6.push((float) r9);
	                break;  
	             case 't':r9=Math.tan(a6.pop());  
	                  a6.push((float) r9); 
	                break;  
	             case 'l':r9=Math.log(a6.pop()); 
	                   a6.push((float) r9); 
	                break;
	             case 'S':
	            	 hk=a6.pop();if(hk>1){hk=Math.sin(hk-(int)hk);}
	            	 r9=Math.asin(hk);  
               a6.push((float) r9);
                   break;  
               case 'C':
             	  hk=a6.pop();if(hk>1){hk=Math.cos(hk-(int)hk);}  
             	  r9=Math.acos(hk); 
               a6.push((float) r9);
                    break;  
               case 'T':r9=Math.atan(a6.pop());  
              a6.push((float) r9); 
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
	    	   a6.push((float) Double.parseDouble(q[some]));
	       }
	       else if(s.charAt(i)=='0'||s.charAt(i)=='1'||s.charAt(i)=='2'||s.charAt(i)=='3'||s.charAt(i)=='4'||s.charAt(i)=='5'||
	    			   s.charAt(i)=='6'||s.charAt(i)=='7'||s.charAt(i)=='8'||s.charAt(i)=='9'||s.charAt(i)=='.')
	       {
	    	   my=""+s.charAt(i);
	    	   a6.push((float) Double.parseDouble(my));
	    		my="";	   
	    	  
	       }
	     
	     }
	     r9=a6.pop();
	     return r9;
	     }


}
