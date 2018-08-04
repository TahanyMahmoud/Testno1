package com.tahany.android.test1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tahany.android.test1.data.UsersDbHelper;

public class LoginActivity extends Activity {
    private UsersDbHelper dphelper;
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dphelper = new UsersDbHelper(this);
        TextView login=(TextView)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email=getEmail();
                String password=getPassword();
                if(email==null){Toast.makeText(LoginActivity.this, "invalid email", Toast.LENGTH_LONG).show();}
                else if(password==null){Toast.makeText(LoginActivity.this, "invalid password", Toast.LENGTH_LONG).show();}
                else{
                    int id=checklogin(email,password);
                    if(id!=-1) {
                      //  Toast.makeText(LoginActivity.this, "welcom user ID" + id, Toast.LENGTH_LONG).show();
                        Intent i = new Intent(LoginActivity.this, WelcomActvity.class);
                        i.putExtra("email", email);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Login Faild", Toast.LENGTH_LONG).show();
                    }
                    }
            }
        });
    }

    private String getEmail(){
        EditText editEmail=(EditText)findViewById(R.id.email2);
        String email=editEmail.getText().toString().trim();
        if(!TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {return email;}
        else return null;
    }

    private String getPassword(){
        EditText editpass=(EditText)findViewById(R.id.pass2);
        String pass=editpass.getText().toString().trim();
        if(pass!=null && !pass.equals("")){return pass;}
        else return null;
    }

    private int checklogin(String email, String pass){
       int id=-1;

        SQLiteDatabase db = dphelper.getReadableDatabase();
        String[] columns = {dphelper._ID};
        String selection = dphelper.COLUMN_Email+"=? AND "+dphelper.COLUMN_password+"=?";
        String[] selectionArgs = {email, pass};
        Cursor cursor = null;
        cursor = db.query(dphelper.TABLE_NAMES, columns, selection, selectionArgs, null, null, null);
        try {
            if(cursor.getCount()>0){
                cursor.moveToFirst();
                 id = cursor.getInt(cursor.getColumnIndex(dphelper._ID));
                Log.wtf(LOG_TAG, ""+id);
            }

        } finally {
            cursor.close();
        }
        return id;
    }

}
