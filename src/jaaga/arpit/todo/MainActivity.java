package jaaga.arpit.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {

	ImageButton new_todo;
	DataBaseAdaptor db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DataBaseAdaptor(this);
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
    	String data=db.getAllData();
    	Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }
    
}
