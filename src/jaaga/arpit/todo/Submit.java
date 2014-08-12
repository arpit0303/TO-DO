package jaaga.arpit.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Submit extends Activity {

	DataBaseAdaptor db;
	EditText title,note;
	long DBid;
	
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
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.action_done:
			String Title=title.getText().toString();
			String Note=note.getText().toString();
			
			if(!(Title.isEmpty())){
				DBid = db.insert(Title, Note);
			}
			else{
				Toast.makeText(Submit.this, "Please enter the title", Toast.LENGTH_LONG).show();
			}
			if(DBid<0){
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
			
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
		
	}
}
