package jaaga.arpit.todo.ui;

import jaaga.arpit.todo.DataBaseAdaptor;
import jaaga.arpit.todo.GridAdapter;
import jaaga.arpit.todo.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainGridActivity extends Activity {

	private DataBaseAdaptor db;
	private String[] title;
	private String[] note;
	GridAdapter adapter;
	Boolean columnView = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_grid);

		db = new DataBaseAdaptor(this);
		if (db.gridView == null) {
			columnView = false;
		}

		String[] data = db.getTitle();
		String[] data1 = db.getNote();
		title = new String[data.length - 1];
		note = new String[data.length - 1];
		int size = data.length - 1;
		for (int i = 0; i < size; i++) {
			title[i] = data[i];
			note[i] = data1[i];
			// j--;
		}

		GridView grid = (GridView) findViewById(R.id.grid);
		adapter = new GridAdapter(this, title, note);
		grid.setAdapter(adapter);
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> g, View v, int position,
					long id) {
				v.setBackgroundResource(R.color.light_purple_background);
				Intent intent = new Intent(MainGridActivity.this, ModifyActivity.class);
				intent.putExtra("position", position);
				startActivity(intent);
				
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_grid, menu);
		
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

		case R.id.action_listView:
			Intent Listintent = new Intent(MainGridActivity.this, MainActivity.class);
			Listintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Listintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(Listintent);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (adapter.tts != null) {
			adapter.tts.stop();
			adapter.tts.shutdown();
		}
	}
}
