package com.example.equisolver;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Singleanddouble extends Activity implements OnClickListener{

Button one,two;
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_singleanddouble);
		one=(Button)findViewById(R.id.singlee);
		two=(Button)findViewById(R.id.doublee);
		one.setOnClickListener(this);
		two.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.singleanddouble, menu);
		return true;
	}

	@Override
	public void onClick(View ae) 
	{
	Button suma=(Button)ae;
	if(suma.getText().toString().equals("Single Integration"))
	{
		Intent graph=new Intent(this,Integration.class);
		startActivity(graph);
	}
	
	if(suma.getText().toString().equals("Double Integration"))
	{
		Intent graph=new Intent(this,DoubeIntegration.class);
		startActivity(graph);
	}
		
		
	}

}
