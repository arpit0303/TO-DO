package jaaga.arpit.todo.ui;

import jaaga.arpit.todo.DataBaseAdaptor;
import jaaga.arpit.todo.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class Submit extends Activity {

	DataBaseAdaptor db;
	EditText title,note;
	long DBid;
	public static int uid = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit);
		
		
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
			db=new DataBaseAdaptor(this);
			
			if(!(Title.isEmpty()) || !(Note.isEmpty())){
				DBid = db.insert(Title, Note, uid);
			}
			
			if(DBid<0){
				Toast.makeText(this, "Unsuccessful", Toast.LENGTH_LONG).show();
			}
			else if(DBid >= 0){
				uid++;
			}
			else{
				Toast.makeText(this, "Empty so Not Inserted", Toast.LENGTH_LONG).show();
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
