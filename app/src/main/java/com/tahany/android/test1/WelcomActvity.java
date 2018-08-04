package com.tahany.android.test1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomActvity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom_actvity);

        final Bundle bundle = getIntent().getExtras();
        TextView text2 = (TextView) findViewById(R.id.text2);
        String email = bundle.getString("email");
        text2.setText("Welcome "+email);
    }

}
