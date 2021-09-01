package com.example.listviewadvanced;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IOnChildItemClick {
    private final List<ContactModel> listContact = new ArrayList<>();
    private ListView lvContact;
    private ContactAdapter mAdapter;
    private ImageView ivUser;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

        mAdapter = new ContactAdapter(this, listContact);
        mAdapter.registerChildItemClick(this);
        lvContact.setAdapter(mAdapter);
        lvContact.setOnItemClickListener((parent, view, i, id) -> {
            ContactModel contactModel = listContact.get(i);
            Toast.makeText(MainActivity.this, contactModel.getName() + ": " + contactModel.getPhone(), Toast.LENGTH_SHORT).show();
        });
    }

    private void initView() {
        lvContact = findViewById(R.id.lvContact);
        ivUser = findViewById(R.id.ivUser);
        tvName = findViewById(R.id.tvName);
    }

    private void initData() {
        listContact.add(new ContactModel("Dean", "0346121199", R.drawable.avatar7));
        listContact.add(new ContactModel("Tom", "0346111111", R.drawable.avatar8));
        listContact.add(new ContactModel("Rose", "0346222222", R.drawable.avatar4));
        listContact.add(new ContactModel("Adam", "0346333333", R.drawable.avatar3));
        listContact.add(new ContactModel("Lisa", "0346444444", R.drawable.avatar5));
        listContact.add(new ContactModel("Mike", "0346555555", R.drawable.avatar10));
        listContact.add(new ContactModel("Jennie", "034666666", R.drawable.avatar6));
        listContact.add(new ContactModel("John", "0346777777", R.drawable.avatar11));
        listContact.add(new ContactModel("Noze", "0346888888", R.drawable.avatar9));
        listContact.add(new ContactModel("Jay", "0346999999", R.drawable.avatar2));
        listContact.add(new ContactModel("Addison", "0346000000", R.drawable.avatar1));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.unRegisterChildItemClick();
    }

    @Override
    public void onItemChildClick(int position) {
        ContactModel contactModel = listContact.get(position);
        ivUser.setImageResource(contactModel.getImage());
        tvName.setText(contactModel.getName());
    }
}