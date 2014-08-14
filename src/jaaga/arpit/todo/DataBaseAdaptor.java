package jaaga.arpit.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBaseAdaptor {

	DataBase db;
	public String[] header = null;

	public DataBaseAdaptor(Context context) {
		db = new DataBase(context);
	}

	public long insert(String title, String note) {
		SQLiteDatabase sqlitedb = db.getWritableDatabase();
		ContentValues contentvalues = new ContentValues();
		contentvalues.put(DataBase.TITLE, title);
		contentvalues.put(DataBase.NOTE, note);
		long id = sqlitedb.insert(DataBase.TABLE_NAME, null, contentvalues);
		db.close();
		return id;
	}
	
	public String getAllData() {
		SQLiteDatabase sqlitedb = db.getWritableDatabase();
		String[] columns = { DataBase.TITLE, DataBase.NOTE,DataBase.UID };
		StringBuffer buffer = new StringBuffer();
		Cursor cursor = sqlitedb.query(DataBase.TABLE_NAME, columns, null,
				null, null, null, null);

		while (cursor.moveToNext()) {
			int index1 = cursor.getColumnIndex(DataBase.TITLE);
			int index2 = cursor.getColumnIndex(DataBase.NOTE);
			int index3 = cursor.getColumnIndex(DataBase.UID);
			String title = cursor.getString(index1);
			String note = cursor.getString(index2);
			String uid = cursor.getString(index3);
			buffer.append(title+"   "+note+"  "+uid+"\n");
		}
		db.close();
		return buffer.toString();
		
	}
	
	public long update(String title, String note,int position) {
		SQLiteDatabase sqlitedb = db.getWritableDatabase();
		ContentValues contentvalues = new ContentValues();
		contentvalues.put(DataBase.TITLE, title);
		contentvalues.put(DataBase.NOTE, note);
		long id = sqlitedb.update(DataBase.TABLE_NAME, contentvalues, DataBase.UID+"="+position, null);
		db.close();
		return id;
	}
	
	public int delete(int position){
		SQLiteDatabase sqlitedb = db.getWritableDatabase();
		int id = sqlitedb.delete(DataBase.TABLE_NAME, DataBase.UID+"="+position, null);
		return id;
	}

	public String[] getTitle() {
		SQLiteDatabase sqlitedb = db.getWritableDatabase();
		String[] columns = { DataBase.TITLE };
		Cursor cursor = sqlitedb.query(DataBase.TABLE_NAME, columns, null,
				null, null, null, null);
		int count = cursor.getCount();

		header = new String[count + 1];

		while (cursor.moveToNext()) {
			int index1 = cursor.getColumnIndex(DataBase.TITLE);

			String title = cursor.getString(index1);
				header[cursor.getPosition()] = title;
		}
		db.close();
		return header;
	}
	
	public String[] getNote() {
		SQLiteDatabase sqlitedb = db.getWritableDatabase();
		String[] columns = {DataBase.NOTE};
		Cursor cursor = sqlitedb.query(DataBase.TABLE_NAME, columns, null,
				null, null, null, null);
		int count = cursor.getCount();

		header = new String[count + 1];

		while (cursor.moveToNext()) {
			int index1 = cursor.getColumnIndex(DataBase.NOTE);

			String title = cursor.getString(index1);
				header[cursor.getPosition()] = title;
		}
		db.close();
		return header;
	}

	public int getCount() {
		SQLiteDatabase sqlitedb = db.getWritableDatabase();
		String[] columns = { DataBase.TITLE, DataBase.NOTE };
		Cursor cursor = sqlitedb.query(DataBase.TABLE_NAME, columns, null,
				null, null, null, null);
		int count = cursor.getCount();

		db.close();
		return count;
	}

	static class DataBase extends SQLiteOpenHelper {
		private static final String DATABASE_NAME = "TODO";
		private static final String TABLE_NAME = "REMEMBER";
		private static final String UID = "_id";
		private static final String TITLE = "Title";
		private static final String NOTE = "Note";
		private static final int DATABASE_VERSION = 1;
		private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
				+ " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT," + TITLE
				+ " VARCHAR(20)," + NOTE + " VARCHAR(255));";
		private static final String DROP_TABLE = "DROP TABLE IF EXISTS"
				+ TABLE_NAME;
		private Context context;

		public DataBase(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			this.context = context;
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL(CREATE_TABLE);
			} catch (SQLException e) {
				Toast.makeText(context, "error" + e, Toast.LENGTH_LONG).show();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			try {
				db.execSQL(DROP_TABLE);
				onCreate(db);
			} catch (SQLException e) {
				Toast.makeText(context, "error" + e, Toast.LENGTH_LONG).show();
			}

		}
	}

}
