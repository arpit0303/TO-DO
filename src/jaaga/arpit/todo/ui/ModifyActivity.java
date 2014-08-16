package jaaga.arpit.todo.ui;

import jaaga.arpit.todo.DataBaseAdaptor;
import jaaga.arpit.todo.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class ModifyActivity extends Activity {

	private EditText title;
	private EditText note;
	private DataBaseAdaptor db;
	long DBid;
	private int position;
	private String[] preTitle;
	private String[] preNote;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit);
		// Show the Up button in the action bar.
		setupActionBar();
		
		db=new DataBaseAdaptor(this);
		
		title=(EditText) findViewById(R.id.editText1);
		note=(EditText) findViewById(R.id.editText2);
		
		
		preTitle = db.getTitle();
		preNote = db.getNote();
		
		Intent intent = getIntent();
		position = intent.getIntExtra("position", 0);
		title.setText(preTitle[position]);
		note.setText(preNote[position]);
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modify, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			break;
		case R.id.action_done:
			String Title=title.getText().toString();
			String Note=note.getText().toString();
			
			if(!(Title.isEmpty())){
				DBid = db.update(Title, Note, position);
			}
			else{
				Toast.makeText(ModifyActivity.this, "Please enter the field", Toast.LENGTH_LONG).show();
			}
			if(DBid<0){
				Toast.makeText(this, "Unsuccessfull", Toast.LENGTH_LONG).show();
			}
			else{
				Toast.makeText(this, "successfully Updated", Toast.LENGTH_LONG).show();
			}
			
			Intent intentUpdate=new Intent();
			intentUpdate.setClass(this, MainActivity.class);
			intentUpdate.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			intentUpdate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intentUpdate);
			
			break;
		case R.id.action_delete:
			int DB = db.delete(position);
			db.updateUID(position);
			db.delete(db.getCount());
			if(DB<0){
				Toast.makeText(this, "Unsuccessful", Toast.LENGTH_LONG).show();
			}
			else{
				Toast.makeText(this, "successfully Deleted", Toast.LENGTH_LONG).show();
				Submit.uid--;
			}
			Intent intentDelete=new Intent();
			intentDelete.setClass(this, MainActivity.class);
			intentDelete.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			intentDelete.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intentDelete);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
