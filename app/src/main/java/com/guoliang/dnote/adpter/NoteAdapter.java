package com.guoliang.dnote.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.guoliang.dnote.R;
import com.guoliang.dnote.model.Note;

import java.util.List;

/**
 * Class NoteAdapter
 *
 * @author XhinLiang
 */

public class NoteAdapter extends BaseAdapter {

    private List<Note> dataList;
    private Context context;

    public NoteAdapter(Context context, List<Note> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Note getItem(int position) {
        return this.dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Note note = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.content = (TextView) convertView.findViewById(R.id.content);
            holder.createdAt = (TextView) convertView.findViewById(R.id.created_at);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.title.setText(note.getTitle());
        holder.content.setText(note.getContent());
        holder.createdAt.setText(note.getCreatedAt());
        return convertView;
    }

    private static class ViewHolder {
        TextView title;
        TextView content;
        TextView createdAt;
    }
}
