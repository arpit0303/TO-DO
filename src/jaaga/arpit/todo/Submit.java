package jaaga.arpit.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Submit extends Activity {

	DataBaseAdaptor db;
	EditText title,note;
	ImageButton back_todo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit);
		db=new DataBaseAdaptor(this);
		
		title=(EditText) findViewById(R.id.editText1);
		note=(EditText) findViewById(R.id.editText2);
		back();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.submit, menu);
		return true;
	}
	
	public void done(View v){
		
		String Title=title.getText().toString();
		String Note=note.getText().toString();
		
		long id = db.insert(Title, Note);
		if(id<0){
			Toast.makeText(this, "Unsuccessfull", Toast.LENGTH_LONG).show();
		}
		else{
			Toast.makeText(this, "successfully Insert", Toast.LENGTH_LONG).show();
		}
		
		Intent intent=new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void back(){
		back_todo = (ImageButton) findViewById(R.id.imageButton1);
		back_todo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(getApplicationContext(), MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
	}

}
