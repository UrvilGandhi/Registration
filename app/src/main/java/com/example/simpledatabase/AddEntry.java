package com.example.simpledatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddEntry extends Activity implements OnClickListener {

    private EditText edtName, edtMobile, edtAge, edtEmail;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add Record");

        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.name);
        edtMobile = findViewById(R.id.mobile);
        edtAge = findViewById(R.id.age);
        edtEmail = findViewById(R.id.email);

        Button btnRegister = findViewById(R.id.btn_register);

        dbManager = new DBManager(this);
        dbManager.open();
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_register) {
            final String name = edtName.getText().toString();
            final String mobile = edtMobile.getText().toString();
            final String age = edtAge.getText().toString();
            final String email = edtEmail.getText().toString();

            dbManager.insert(name, mobile, age, email);

            Intent main = new Intent(AddEntry.this, MainActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(main);
        }
    }

}
