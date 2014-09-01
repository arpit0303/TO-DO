package jaaga.arpit.todo;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

public class GridAdapter extends ArrayAdapter<String> {

	protected Context mContext;
	protected String[] mTitle;
	protected String[] mNote;
	protected PopupMenu popup;
	public TextToSpeech tts;

	public GridAdapter(Context context, String[] title, String[] note) {
		super(context, R.layout.grid_row, title);
		mContext = context;
		mTitle = title;
		mNote = note;

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.grid_row, null);
				holder = new ViewHolder();

				holder.title = (TextView) convertView.findViewById(R.id.Grdititle);
				holder.note = (TextView) convertView.findViewById(R.id.todo);
				holder.menu = (ImageView) convertView.findViewById(R.id.menu);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.title.setText(mTitle[position]);
			holder.note.setText(mNote[position]);
			holder.menu.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(final View v) {
					popup = new PopupMenu(mContext, v);
					MenuInflater inflater = popup.getMenuInflater();
					inflater.inflate(R.menu.item_menu, popup.getMenu());

					popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {

						@Override
						public boolean onMenuItemClick(MenuItem item) {
							int id = item.getItemId();
							switch (id) {
							case R.id.action_del:
								DataBaseAdaptor db = new DataBaseAdaptor(
										mContext);
								db.delete(position);
								db.updateUID(position);
								db.delete(db.getCount());
								return true;

							case R.id.action_listen:
								tts = new TextToSpeech(mContext,
										new OnInitListener() {

											@Override
											public void onInit(int status) {
												if (status == TextToSpeech.SUCCESS) {
													String mText;

													if (mTitle[position]
															.isEmpty()) {
														mText = "Note is : "
																+ mNote[position];
													} else if (mNote[position]
															.isEmpty()) {
														mText = "Title is : "
																+ mTitle[position];
													} else {
														mText = "Title is : "
																+ mTitle[position]
																+ " And note is : "
																+ mNote[position];
													}
													tts.setSpeechRate((float) 0.8);
													
													tts.speak(
															mText,
															TextToSpeech.QUEUE_FLUSH,
															null);
													
												}
											}
										});

								return true;

							default:
								return false;
							}
						}
					});
					popup.show();
				}
			});

		return convertView;
	}

	public static class ViewHolder {
		TextView title;
		TextView note;
		ImageView menu;
	}

}
