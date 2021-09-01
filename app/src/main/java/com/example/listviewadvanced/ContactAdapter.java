package com.example.listviewadvanced;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<ContactModel> listContact;
    private IOnChildItemClick iOnChildItemClick;

    private static final int REQUEST_CODE = 1;

    public ContactAdapter(Context mContext, List<ContactModel> listContact) {
        this.mContext = mContext;
        this.listContact = listContact;
    }

    public void registerChildItemClick(IOnChildItemClick iOnChildItemClick) {
        this.iOnChildItemClick = iOnChildItemClick;
    }

    public void unRegisterChildItemClick() {
        this.iOnChildItemClick = null;
    }

    //How many items are in the dataset represented by this Adapter.
    @Override
    public int getCount() {
        return listContact.size();
    }

    //Get the data item associated with the specified position in the dataset.
    @Override
    public Object getItem(int i) {
        return null;
    }

    //Get the row id associated with the specified position in the list.
    @Override
    public long getItemId(int i) {
        return 0;
    }

    //Get a view that displays the data at the specified position in the list.
    @SuppressLint("InflateParams")
    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        View rowView = convertView;
        //reuse views
        if (rowView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            rowView = inflater.inflate(R.layout.item_contact, null);
            //configure view holder
            ViewHolder holder = new ViewHolder();
            holder.tvName = rowView.findViewById(R.id.tvName);
            holder.tvPhone = rowView.findViewById(R.id.tvPhone);
            holder.ivAvatar = rowView.findViewById(R.id.ivAvatar);
            holder.btCall = rowView.findViewById(R.id.btCall);
            holder.btEdit = rowView.findViewById(R.id.btEdit);
            rowView.setTag(holder);
        }

        //fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.tvName.setText(listContact.get(i).getName());
        holder.tvPhone.setText(listContact.get(i).getPhone());
        holder.ivAvatar.setImageResource(listContact.get(i).getImage());

        holder.btCall.setOnClickListener(v -> onCall(i));

        holder.btEdit.setOnClickListener(v -> iOnChildItemClick.onItemChildClick(i));

        return rowView;
    }

    private void onCall(int position) {
        ContactModel contactModel = listContact.get(position);
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + contactModel.getPhone()));
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE);
        } else {
            Toast.makeText(mContext, "Calling", Toast.LENGTH_SHORT).show();
        }
        mContext.startActivity(intent);
    }

    private static class ViewHolder {
        TextView tvName;
        TextView tvPhone;
        ImageView ivAvatar;
        Button btCall;
        Button btEdit;
    }
}
