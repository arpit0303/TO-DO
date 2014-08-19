package jaaga.arpit.todo.ui;

import jaaga.arpit.todo.CustomAdapter;
import jaaga.arpit.todo.DataBaseAdaptor;
import jaaga.arpit.todo.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	private DataBaseAdaptor db;
	private String[] title;
	private String[] note;
	CustomAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		db = new DataBaseAdaptor(this);
		String[] data = db.getTitle();
		String[] data1 = db.getNote();
		title = new String[data.length - 1];
		note = new String[data.length - 1];
		for (int i = 0; i < data.length - 1; i++) {
			title[i] = data[i];
			note[i] = data1[i];
		}

		adapter = new CustomAdapter(this, title, note);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, final View v,
			final int position, long id) {
		super.onListItemClick(l, v, position, id);

		v.setBackgroundResource(R.color.light_purple_background);
		Intent intent = new Intent(MainActivity.this, ModifyActivity.class);
		intent.putExtra("position", position);
		startActivity(intent);

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
		case R.id.action_new:
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), Submit.class);
			startActivity(intent);
			break;

		case R.id.action_settings:
			String data = db.getAllData();
			Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(adapter.tts != null){
			adapter.tts.stop();
			adapter.tts.shutdown();
		}
	}
}
