package jaaga.arpit.todo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	ImageButton new_todo;
	DataBaseAdaptor db;
	private String data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		db = new DataBaseAdaptor(this);
		String[] data = db.getNote();
		String[] display = new String[data.length -1];
		for(int i=0;i<data.length -1;i++){
			display[i] = data[i];
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.simple_list_item_1,
				display);
		setListAdapter(adapter);
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.action_alldata:
			data = db.getAllData();
			Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();
			int size = data.length();
			String len = Integer.toString(size);
			Log.i("Main", "all data : " + len);
			break;

		case R.id.action_new:
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), Submit.class);
			startActivity(intent);
			break;

		case R.id.action_settings:
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
