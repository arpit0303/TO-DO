package jaaga.arpit.todo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {

	protected Context mContext;
	protected String[] mTitle;
	protected String[] mNote;
	protected int i = 0;

	public CustomAdapter(Context context, String[] title, String[] note) {
		super(context, R.layout.single_row, title);
		mContext = context;
		mTitle = title;
		mNote = note;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.single_row, null);
			holder = new ViewHolder();

			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.note = (TextView) convertView.findViewById(R.id.todo);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.title.setText(mTitle[i]);
		holder.note.setText(mNote[i]);

		i++;
		return convertView;
	}

	public static class ViewHolder {
		TextView title;
		TextView note;
	}
}
