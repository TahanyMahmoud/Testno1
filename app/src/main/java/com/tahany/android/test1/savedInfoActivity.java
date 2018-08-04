package com.tahany.android.test1;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

public class savedInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_info);

        final Bundle bundle = getIntent().getExtras();
        TextView text1=(TextView)findViewById(R.id.text1);
        int newRow = bundle.getInt("newRow");
        if(newRow != -1)
            text1.setText("Congratulation \n your Info has saved as \n \n\n\n" +bundle.getString("email") +"\n  "+bundle.getString("password")+"\n  "+  bundle.getString("mob")+"\n your ID "+newRow);
         else
            text1.setText("Database Error when saving this Info \n \n\n\n" +bundle.getString("email") +"\n  "+bundle.getString("password")+"\n  "+  bundle.getString("mob")+"\n your ID "+newRow);
    }

}
