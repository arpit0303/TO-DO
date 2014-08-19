package jaaga.arpit.todo.ui;

import jaaga.arpit.todo.DataBaseAdaptor;
import jaaga.arpit.todo.R;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class Submit extends Activity {

	DataBaseAdaptor db;
	EditText title, note;
	long DBid;
	public static int uid = 0;
	public static final int REQ_CODE_SPEECH_INPUT = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit);

		title = (EditText) findViewById(R.id.editText1);
		note = (EditText) findViewById(R.id.editText2);

		DataBaseAdaptor db = new DataBaseAdaptor(this);
		uid = db.getCount();
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
			String Title = title.getText().toString();
			String Note = note.getText().toString();
			db = new DataBaseAdaptor(this);

			if (!(Title.isEmpty()) || !(Note.isEmpty())) {

				if (uid < 0)
					uid = 0;
				DBid = db.insert(Title, Note, uid);
			}
			else{
				Toast.makeText(this, "Empty Note Discard", Toast.LENGTH_LONG)
				.show();
			}

			if (DBid < 0) {
				Toast.makeText(this, "Unsuccessful", Toast.LENGTH_LONG).show();
			}

			Intent intent = new Intent();
			intent.setClass(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);

			break;

		case R.id.action_speak:
			Intent speakIntent = new Intent(
					RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			speakIntent
					.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
			startActivityForResult(speakIntent, REQ_CODE_SPEECH_INPUT);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (requestCode) {
		case REQ_CODE_SPEECH_INPUT:
			if(resultCode == RESULT_OK && data != null){
				 ArrayList<String> result = data
	                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
	                note.setText(result.get(0));
			}
			break;

		default:
			break;
		}
	}
}
