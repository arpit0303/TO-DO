package jaaga.arpit.todo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	ImageButton new_todo;
	DataBaseAdaptor db;
	ListView mListView;
	private Button viewAll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		db = new DataBaseAdaptor(this);

		newtodo();
		
		viewAll = (Button) findViewById(R.id.button2);
		viewAll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, 
						android.R.layout.simple_list_item_1, DataBaseAdaptor.header);
				setListAdapter(adapter);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void newtodo() {
		new_todo = (ImageButton) findViewById(R.id.imageButton1);

		new_todo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), Submit.class);
				startActivity(intent);
			}
		});

	}

}
