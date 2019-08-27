package com.example.simpledatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ModifyEntry extends Activity implements OnClickListener {

    private EditText modName, modMobile, modAge, modEmail;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify Record");

        setContentView(R.layout.activity_modify);

        dbManager = new DBManager(this);
        dbManager.open();

        modName = findViewById(R.id.modname);
        modMobile = findViewById(R.id.modmobile);
        modAge = findViewById(R.id.modage);
        modEmail = findViewById(R.id.modemail);

        Button updateBtn = findViewById(R.id.btn_update);
        Button deleteBtn = findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String mobile = intent.getStringExtra("mobile");
        String age = intent.getStringExtra("age");
        String email = intent.getStringExtra("email");

        _id = Long.parseLong(id);

        modName.setText(name);
        modMobile.setText(mobile);
        modAge.setText(age);
        modEmail.setText(email);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String name = modName.getText().toString();
                String mobile = modMobile.getText().toString();
                String age = modAge.getText().toString();
                String email = modEmail.getText().toString();

                dbManager.update(_id, name, mobile, age, email);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
