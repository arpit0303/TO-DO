package jaaga.arpit.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ImageButton new_todo;
	DataBaseAdaptor db;
	//GridView mGrid;
	ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        db=new DataBaseAdaptor(this);
        //mGrid = (GridView) findViewById(R.id.gridView1);
        mListView = (ListView) findViewById(R.id.listView1); 
    	String[] data = db.getAllData();
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,data);
    	//mGrid.setAdapter(adapter);
    	mListView.setAdapter(adapter);
        newtodo();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void newtodo(){
    	new_todo = (ImageButton) findViewById(R.id.imageButton1);
    	
    	new_todo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
		    	intent.setClass(getApplicationContext(), Submit.class);
		        startActivity(intent);
		        finish();
			}
		});
    	    	
    }
    
    public void viewdetails(View v){
    	//String data=db.getAllData();
    	//String[] data = null;
    	//data =db.getAllData();
    	//ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,data);
    	//mGrid.setAdapter(adapter);
    	//Toast.makeText(this, data[0], Toast.LENGTH_LONG).show();
    }
    
}
