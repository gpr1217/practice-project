package com.practiceex.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.practiceex.R;
import com.practiceex.model.Employee;
import com.practiceex.service.EmployeeAPIService;
import com.practiceex.utils.ApiUtilities;
import com.practiceex.utils.Constants;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends CustomMenuActivity {

    @InjectView(R.id.username)
    EditText username;
    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.txt_success)
    TextView txt_success;
    @InjectView(R.id.open_google_link)
    TextView open_google_link;
    @InjectView(R.id.call_phone)
    TextView call_phone;

    private EmployeeAPIService apiService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Log.d("MainActivity", "1 - onCreate");
        try {
            Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
            setSupportActionBar(myToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);

            apiService = ApiUtilities.getAPIService();
            open_google_link.setMovementMethod(LinkMovementMethod.getInstance());
            call_phone.setMovementMethod(LinkMovementMethod.getInstance());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btn_login)
    public void login(Button button){
        try {
            String str_username = username.getText().toString();
            String str_pwd = password.getText().toString();

            if (!str_username.equals("") && !str_pwd.equals("")) {

                Call<List<Employee>> call = apiService.getObjEmployeeDetails(str_username,str_pwd);
                call.enqueue(new Callback<List<Employee>>() {
                    @Override
                    public void onResponse(Response<List<Employee>> response, Retrofit retrofit) {
                        List<Employee> emp = response.body();
                        if (emp.size() == 1){
                            Intent in = new Intent(getApplicationContext(), DashboardActivity.class);
                            startActivity(in);
                            //finish();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.d("onFailure", t.getMessage());
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btn_sign_up)
    public void signUp(Button button){
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity","3 - onResume");
        showMenus(Constants.HIDE_MENU);
        Intent intent = getIntent();
        boolean getExtras = intent.getBooleanExtra("Success",false);
        if (getExtras){
            txt_success.setVisibility(View.VISIBLE);
            txt_success.setText("Congratulations! Your details have added successfully. Login to view info");
        }
    }

    @OnClick(R.id.open_google_link)
    public void openGoogleForInfo(TextView textView){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
            startActivity(intent);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @OnClick(R.id.call_phone)
    public void callPhone(TextView textView){
        try{
            String phone_num = "7042968412";
            Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phone_num));
            startActivity(intent);
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity","2 - onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity","6 - onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity","5 - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity","7 - onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity","4 - onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d("MainActivity","8 - onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("MainActivity","9 - onRestoreInstanceState");
    }
}
