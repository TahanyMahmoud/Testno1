package com.tahany.android.test1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.tahany.android.test1.data.UsersDbHelper;

public class MainActivity extends AppCompatActivity {
    private UsersDbHelper dphelper;
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dphelper = new UsersDbHelper(this);
        TextView cont=(TextView)findViewById(R.id.cont);
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=getEmail();
                String password=getPassword();
                String mob=getmobNumber();
                if(email==null){Toast.makeText(MainActivity.this, "invalid email", Toast.LENGTH_LONG).show();}
                else if(password==null){Toast.makeText(MainActivity.this, "invalid password", Toast.LENGTH_LONG).show();}
                else if(mob==null){Toast.makeText(MainActivity.this, "invalid mobile number", Toast.LENGTH_LONG).show();}
                else {
                    String s= email+password+mob;
                   // Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(MainActivity.this, savedInfoActivity.class);
                    i.putExtra("email", email);
                    i.putExtra("password", password);
                    i.putExtra("mob", mob);
                    long newRow=saveToDB(email,password,mob);
                    Log.wtf(LOG_TAG,"newRow "+newRow);
                    i.putExtra("newRow",(int)newRow);
                    startActivity(i);
                }


            }
        });
        ////////////////////////////
        TextView login=(TextView)findViewById(R.id.signin);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

private String getEmail(){
    EditText editEmail=(EditText)findViewById(R.id.email);
    String email=editEmail.getText().toString().trim();
    if(!TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
    {return email;}
    else return null;
}

private String getPassword(){
    EditText editpass=(EditText)findViewById(R.id.pass);
    EditText editAgain=(EditText)findViewById(R.id.again);
    String pass=editpass.getText().toString().trim();
    String again=editAgain.getText().toString().trim();
    if(pass!=null && again!=null && !pass.equals("")&& !again.equals("")&& pass.equals(again)){
        return pass;
    }
    else return null;
}

    private String getmobNumber(){
        EditText editmob=(EditText)findViewById(R.id.mob);
        String mob=editmob.getText().toString().trim();
        if( !TextUtils.isEmpty(mob) && android.util.Patterns.PHONE.matcher(mob).matches()){
            return mob;
        }
        else return null;

    }
    private long saveToDB(String email ,String pass, String mob){
        Log.wtf(LOG_TAG,"new entryyyyy");

        long newRowId=-1;
        try {
            SQLiteDatabase db = dphelper.getWritableDatabase();
            ContentValues value = new ContentValues();
            value.put(dphelper.COLUMN_Email, email);
            value.put(dphelper.COLUMN_password, pass);
            value.put(dphelper.COLUMN_mobile, mob);
            newRowId = db.insert(dphelper.TABLE_NAMES, null, value);
            Log.wtf(LOG_TAG,"new Iddddd " + newRowId);
        }catch (android.database.sqlite.SQLiteException e) {
            Log.wtf(LOG_TAG, "SQLiteException:" + e.getMessage());
        }
     return newRowId;


    }
}
