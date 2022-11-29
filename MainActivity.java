package com.example.equisolver;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	Button real,imaginary,integration,differentiation;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		real=(Button)findViewById(R.id.realbutton1);
		imaginary=(Button)findViewById(R.id.imgbutton2);
		integration=(Button)findViewById(R.id.integbutton3);
		differentiation=(Button)findViewById(R.id.differebutton4);
		
		real.setOnClickListener(this);
		imaginary.setOnClickListener(this);
		integration.setOnClickListener(this);
		differentiation.setOnClickListener(this);
		
		
	}

	public void onClick(View ae)
	{
		Button suma=(Button)ae;
		if(suma.getText().toString().equals("Real root"))
		{
			Intent graph=new Intent(this,Realactivity.class);
			startActivity(graph);
		}
		
		else if(suma.getText().toString().equals("Imaginary root"))
		{
			Intent graph=new Intent(this,Imaginaryactivity.class);
			startActivity(graph);
		}
		
		else if(suma.getText().toString().equals("Integration"))
		{
			Intent graph=new Intent(this,Singleanddouble.class);
			startActivity(graph);
		}
		else if(suma.getText().toString().equals("Differentiation"))
		{
			Intent graph=new Intent(this,Differentiation.class);
			startActivity(graph);
		}
		
		
		
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
