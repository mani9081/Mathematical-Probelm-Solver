package com.example.equisolver;

import android.os.Bundle;
import android.app.Activity;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class Threeegraph extends Activity {
    
    String functionstr;float xstart,xend,ystart,yend;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Go fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        Bundle extras=getIntent().getExtras();
        functionstr=extras.getString("f(x,y)");
        xstart=extras.getFloat("xstart");
        xend=extras.getFloat("xend");
        ystart=extras.getFloat("ystart");
        yend=extras.getFloat("yend");
        
        
        GLSurfaceView view = new GLSurfaceView(this);
        view.setRenderer(new OpenGLRenderer(functionstr,xstart,xend,ystart,yend));
        setContentView(view);
        
        
       
        
        
    }
}




class OpenGLRenderer implements Renderer {

        private Cube1 xmCube = new Cube1(-2f,-1f,-3f,6,0.05f,0.05f,125,25,25);
        private Cube1 ymCube = new Cube1(-2f,5f,-3f,0.1f,0.05f,6,105,157,25);
        private Cube1 zmCube = new Cube1(-2f,-1f,-3f,0.1f,-6f,0.05f,15,7,125);
        float x,y,fxy,dx=(float) 0.1f;;int i,j;float finall=25,initiall=0,inc=(float)2.5;
        private float mCubeRotation=56f;int drawinitial3axis=0;
       String functionstr,functionstr1;
       static String []q=new String[10];
       float xstart,ystart,xend,yend;
     public OpenGLRenderer(String functionstr,float xstart,float ystart,float xend,float yend)
     {
    	
    	    
    	 this.xstart=xstart;
    	 this.xend=xend;
    	 this.ystart=ystart;
    	 this.yend=yend;
    	 this.functionstr=functionstr;
    	  
    	    functionstr1=changetoqformat(functionstr);//changing to q format
        	conversion bal=new conversion();
        	functionstr1=bal.changing(functionstr1);//changing -2 like to (0-1)*2
        	functionstr1=toPostfix(functionstr1);//channging to postfix
        
     }
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f); 
               
            gl.glClearDepthf(1.0f);
            gl.glEnable(GL10.GL_DEPTH_TEST);
            gl.glDepthFunc(GL10.GL_LEQUAL);

            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                      GL10.GL_NICEST);
            
                
        }

        @Override
        public void onDrawFrame(GL10 gl) {
        	
           
          if(initiall<finall)
          {
        	gl.glClear( GL10.GL_DEPTH_BUFFER_BIT);        
            gl.glLoadIdentity();
            
            gl.glTranslatef(0.0f, 0.0f, -10.0f);
            gl.glRotatef(mCubeRotation, 2.0f, -2.5f, 0.0f);
               
            if(drawinitial3axis==0)
            {
                xmCube.draw(gl);
                ymCube.draw(gl);
                zmCube.draw(gl);
                drawinitial3axis=1;
            }
                  
                
          
             
            for(x=initiall;x<initiall+inc;x=x+dx)
            {
            	for(y=0;y<25;y=y+dx)
            	{
            		fxy=(float) function(functionstr1,x,y);
            		if(x>xstart&&x<xend&&y>ystart&&y<yend)
            		{
            			 new Cube(-2f+x/5,-1f+fxy/5,-3f+y/5,0.1f,0.1f,0.005f,230*((fxy+1)/fxy),18,168).draw(gl);
            		}
            		else
            		{
            	    new Cube(-2f+x/5,-1f+fxy/5,-3f+y/5,0.1f,0.1f,0.005f,230*((fxy+1)/fxy),178,168).draw(gl);
            		}
            	}
            	
            }
            
            
            gl.glLoadIdentity();                                    
                
            mCubeRotation=56f; 
            initiall=initiall+inc;
        
          }
          
            }
        
        

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
        	gl.glClear( GL10.GL_COLOR_BUFFER_BIT);
            gl.glViewport(0, 0, width, height);
            gl.glMatrixMode(GL10.GL_PROJECTION);
            gl.glLoadIdentity();
            GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);
            gl.glViewport(0, 0, width, height);

            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
        }
        
      
        
        public static double function(String str,double x,double y)
	    {
	    	x=evaluatefunction(str,x,y);
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
 
 
 
 public static double evaluatefunction(String s,double value1,double value2)
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
 	    	 
 	    	a6.push((float) value1);   
 	    	
 	       }
 	       if(ch=='y')
 	       {
 	    	 
 	    	a6.push((float) value2);   
 	    	
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






class Cube {
    
    private FloatBuffer mVertexBuffer;
    private FloatBuffer mColorBuffer;
    private ByteBuffer  mIndexBuffer;
        int i=0;
    private float vertices[] = new float[12];
    private byte[] indices = new byte[6];                        
    private float colors[] = new float[16];
           public float d=1f;               
    		
   
    
                
    public Cube(float x,float y,float z,float l,float b,float h,float a,float b1,float c) {
    	
    	
    	a=a/255;b1=b1/255;c=c/255;
    	vertices[0]=x;vertices[1]=y;vertices[2]=z;
    	vertices[3]=x+l;vertices[4]=y;vertices[5]=z;
    	vertices[6]=x+l;vertices[7]=y;vertices[8]=z-b;
    	vertices[9]=x;vertices[10]=y;vertices[11]=z-b;
    	
    	
    	indices[0]=0;indices[1]=1;indices[2]=2;
    	indices[3]=2;indices[4]=3;indices[5]=0;
    	
    	
    	
    	colors[0]=a;colors[1]=b1;colors[2]=c;colors[3]=d;
    	colors[4]=a;colors[5]=b1;colors[6]=c;colors[7]=d;
    	colors[8]=a;colors[9]=b1;colors[10]=c;colors[11]=d;
    	colors[12]=a;colors[13]=b1;colors[14]=c;colors[15]=d;
    	
    	
            ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
            byteBuf.order(ByteOrder.nativeOrder());
            mVertexBuffer = byteBuf.asFloatBuffer();
            mVertexBuffer.put(vertices);
            mVertexBuffer.position(0);
                
            byteBuf = ByteBuffer.allocateDirect(colors.length * 4);
            byteBuf.order(ByteOrder.nativeOrder());
            mColorBuffer = byteBuf.asFloatBuffer();
            mColorBuffer.put(colors);
            mColorBuffer.position(0);
                
            mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
            mIndexBuffer.put(indices);
            mIndexBuffer.position(0);
    }

    public void draw(GL10 gl) {             
            gl.glFrontFace(GL10.GL_CW);
            
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);
            
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
             
            gl.glDrawElements(GL10.GL_TRIANGLES, 6, GL10.GL_UNSIGNED_BYTE, 
                            mIndexBuffer);
                
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}

class Cube1//only for x,y,z axis
{
    
    private FloatBuffer mVertexBuffer;
    private FloatBuffer mColorBuffer;
    private ByteBuffer  mIndexBuffer;
        int i=0;
    private float vertices[] = new float[1000];
    private byte[] indices = new byte[1000];                        
    private float colors[] = new float[1000];
           public float d=1f;               
    		
   
    
                
    public Cube1(float x,float y,float z,float l,float b,float h,float a,float b1,float c) {
    	
    	
    	a=a/255;b1=b1/255;c=c/255;
    	vertices[0]=x;vertices[1]=y;vertices[2]=z;
    	vertices[3]=x;vertices[4]=y;vertices[5]=z-b;
    	vertices[6]=x;vertices[7]=y-h;vertices[8]=z-b;
    	vertices[9]=x;vertices[10]=y-h;vertices[11]=z;
    	vertices[12]=x+l;vertices[13]=y;vertices[14]=z;
    	vertices[15]=x+l;vertices[16]=y;vertices[17]=z-b;
    	vertices[18]=x+l;vertices[19]=y-h;vertices[20]=z-b;
    	vertices[21]=x+l;vertices[22]=y-h;vertices[23]=z;
    	
    	indices[0]=0;indices[1]=1;indices[2]=2;
    	indices[3]=2;indices[4]=3;indices[5]=0;
    	indices[6]=0;indices[7]=1;indices[8]=5;
    	indices[9]=5;indices[10]=4;indices[11]=0;
    	indices[12]=1;indices[13]=2;indices[14]=5;
    	indices[15]=2;indices[16]=5;indices[17]=6;
    	indices[18]=2;indices[19]=6;indices[20]=3;
    	indices[21]=6;indices[22]=7;indices[23]=3;
    	indices[24]=0;indices[25]=4;indices[26]=7;
    	indices[27]=0;indices[28]=3;indices[29]=7;
    	indices[30]=4;indices[31]=5;indices[32]=6;
    	indices[33]=4;indices[34]=6;indices[35]=7;
    	
    	
    	colors[0]=a;colors[1]=b1;colors[2]=c;colors[3]=d;
    	colors[4]=a;colors[5]=b1;colors[6]=c;colors[7]=d;
    	colors[8]=a;colors[9]=b1;colors[10]=c;colors[11]=d;
    	colors[12]=a;colors[13]=b1;colors[14]=c;colors[15]=d;
    	colors[16]=a;colors[17]=b1;colors[18]=c;colors[19]=d;
    	colors[20]=a;colors[21]=b1;colors[22]=c;colors[23]=d;
    	colors[24]=a;colors[25]=b1;colors[26]=c;colors[27]=d;
    	colors[28]=a;colors[29]=b1;colors[302]=c;colors[31]=d;
    	
            ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
            byteBuf.order(ByteOrder.nativeOrder());
            mVertexBuffer = byteBuf.asFloatBuffer();
            mVertexBuffer.put(vertices);
            mVertexBuffer.position(0);
                
            byteBuf = ByteBuffer.allocateDirect(colors.length * 4);
            byteBuf.order(ByteOrder.nativeOrder());
            mColorBuffer = byteBuf.asFloatBuffer();
            mColorBuffer.put(colors);
            mColorBuffer.position(0);
                
            mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
            mIndexBuffer.put(indices);
            mIndexBuffer.position(0);
    }

    public void draw(GL10 gl) {             
            gl.glFrontFace(GL10.GL_CW);
            
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);
            
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
             
            gl.glDrawElements(GL10.GL_TRIANGLES, 36, GL10.GL_UNSIGNED_BYTE, 
                            mIndexBuffer);
                
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}

