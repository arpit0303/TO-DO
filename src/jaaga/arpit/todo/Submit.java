package jaaga.arpit.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Submit extends Activity {

	DataBaseAdaptor db;
	EditText title,note;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit);
		db=new DataBaseAdaptor(this);
		
		title=(EditText) findViewById(R.id.editText1);
		note=(EditText) findViewById(R.id.editText2);
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
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

}
