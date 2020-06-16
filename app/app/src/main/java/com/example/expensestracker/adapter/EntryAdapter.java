package com.example.expensestracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.expensestracker.R;
import com.example.expensestracker.data.Database;

import java.util.ArrayList;

public class EntryAdapter extends ArrayAdapter<EntryDetails> {

    Database mDatabase = new Database(getContext());

    private static class ViewHolder {
        TextView txtName;
        TextView txtAmount;
        TextView txtType;
        TextView txtTime;
        Button btnDelete;
    }

    public EntryAdapter(Context context, ArrayList<EntryDetails> details) {
        super(context, 0, details);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EntryDetails details = getItem(position);

        ViewHolder mViewHolder;
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_entry_transaction, parent, false);

            mViewHolder.txtName = convertView.findViewById(R.id.txt_entry_name);
            mViewHolder.txtAmount = convertView.findViewById(R.id.txt_entry_amount);
            mViewHolder.txtType = convertView.findViewById(R.id.txt_entry_type);
            mViewHolder.txtTime = convertView.findViewById(R.id.txt_entry_time);
            mViewHolder.btnDelete = convertView.findViewById(R.id.btn_entry_delete);

            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.txtName.setText(details.name);
        mViewHolder.txtAmount.setText(details.amount);
        mViewHolder.txtType.setText(details.type);
        mViewHolder.txtTime.setText(details.time);

        String[][] entries = mDatabase.getEntries();

        mViewHolder.btnDelete.setTag(entries[position][4]);
        mViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(String.valueOf(v.getTag()));
                mDatabase.deleteEntry(position);
            }
        });

        return convertView;
    }
}
