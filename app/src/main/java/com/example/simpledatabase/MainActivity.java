package com.example.simpledatabase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    final String[] from = new String[]{DatabaseHelper._ID,
            DatabaseHelper.NAME, DatabaseHelper.MOBILE, DatabaseHelper.AGE, DatabaseHelper.EMAIL};
    final int[] to = new int[]{R.id.txtid, R.id.txtname, R.id.txtmobile, R.id.txtage, R.id.txtemail};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);

        DBManager dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        ListView listView = findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idText = view.findViewById(R.id.txtid);
                TextView nameText = view.findViewById(R.id.txtname);
                TextView mobileText = view.findViewById(R.id.txtmobile);
                TextView ageText = view.findViewById(R.id.txtage);
                TextView emailText = view.findViewById(R.id.txtemail);

                String id = idText.getText().toString();
                String name = nameText.getText().toString();
                String mobile = mobileText.getText().toString();
                String age = ageText.getText().toString();
                String email = emailText.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyEntry.class);
                modify_intent.putExtra("name", name);
                modify_intent.putExtra("mobile", mobile);
                modify_intent.putExtra("id", id);
                modify_intent.putExtra("age", age);
                modify_intent.putExtra("email", email);

                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(this, AddEntry.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }

}
