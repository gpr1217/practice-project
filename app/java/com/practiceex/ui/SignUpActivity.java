package com.practiceex.ui;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.practiceex.R;
import com.practiceex.model.Departments;
import com.practiceex.model.Employee;
import com.practiceex.service.DepartmentApiService;
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

public class SignUpActivity extends CustomMenuActivity {

    public static final String TAG = "SIGN_UP_ACTIVITY";

    boolean editScreen = false;

    @InjectView(R.id.firstname) EditText firstname;
    @InjectView(R.id.lastname) EditText lastname;
    @InjectView(R.id.username) EditText username;
    @InjectView(R.id.password) EditText password;
    @InjectView(R.id.re_enter_password) EditText re_enter_password;
    @InjectView(R.id.email) EditText email;
    @InjectView(R.id.phone_number) EditText phone_number;
    @InjectView(R.id.department_list) Spinner departmentSpinner;

    private DepartmentApiService deptApiService;
    private EmployeeAPIService empApiService;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.inject(this);
        context = this;

        try {
            Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
            setSupportActionBar(myToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            if(savedInstanceState == null){
                if (getIntent().getExtras() != null) {
                    boolean bundle = getIntent().getExtras().getBoolean("Edit");
                    if (bundle) {
                        editScreen = true;
                    }
                }
            }
            deptApiService = ApiUtilities.getDeptAPIService();
            empApiService = ApiUtilities.getAPIService();

            displaySpinnerValues();
            addListenerOnSpinnerItemSelection();
        }catch (Exception e){
            e.printStackTrace();
        }
        //if (editScreen)
    }


    public void displaySpinnerValues(){
        Call<List<Departments>> call = deptApiService.getDepartments();
        call.enqueue(new Callback<List<Departments>>() {
            @Override
            public void onResponse(Response<List<Departments>> response, Retrofit retrofit) {
                List<Departments> deptData = response.body();

                setUpAdapter(deptData);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG + " - Fail",t.getMessage().toString());
            }
        });
    }

    private void setUpAdapter(List<Departments> deptData) {
        try {
            String[] items = new String[deptData.size() + 1];
            for (int i = 0; i < deptData.size(); i++) {
                items[i] = deptData.get(i).getDept_name();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, items);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            departmentSpinner.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addListenerOnSpinnerItemSelection() {
        departmentSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }


    @OnClick(R.id.btn_submit)
    public void submit(Button button){
        try {
            String str_first_name = firstname.getText().toString();
            String str_last_name = lastname.getText().toString();
            String str_user_name = username.getText().toString();
            String str_password = password.getText().toString();
            String str_email = email.getText().toString();
            String str_re_password = re_enter_password.getText().toString();
            String str_phone = phone_number.getText().toString();
            String str_dept = departmentSpinner.getSelectedItem().toString();

            if (!str_password.equals(str_re_password)){
                password.setText(null);
                password.setError("Passwords doesn't match");
                re_enter_password.setText(null);
            }else if (str_first_name.equals("") || str_last_name.equals("") || str_password.equals("")){
                Toast.makeText(getApplicationContext(),"Please fill out all the fields",Toast.LENGTH_LONG).show();
            }else {
                empApiService.addEmployee(0, str_first_name, str_last_name, str_email, str_phone, str_dept, str_user_name, str_password)
                        .enqueue(new Callback<Employee>() {
                            @Override
                            public void onResponse(Response<Employee> response, Retrofit retrofit) {

                                if (response.isSuccess()) {
                                    Log.d(TAG, "Inserted");
                                    Intent in = new Intent(context, MainActivity.class);
                                    in.putExtra("Success", true);
                                    startActivity(in);
                                } else {
                                    firstname.setText(null);
                                    lastname.setText(null);
                                    username.setText(null);
                                    email.setText(null);
                                    password.setText(null);
                                    re_enter_password.setText(null);
                                    phone_number.setText(null);
                                    departmentSpinner.setSelection(1);

                                    Toast.makeText(getApplicationContext(), "Error! Please try again", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                Log.d(TAG, t.getMessage());
                            }
                        });
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("SignUP","onSaveInstanceState");
        outState.putString("firstname",firstname.getText().toString());
        Log.d("SignUP","onSaveInstanceState - " +firstname.getText().toString());
        outState.putString("lastname",lastname.getText().toString());
        outState.putString("username",username.getText().toString());
        outState.putString("password",password.getText().toString());
        outState.putString("re_enter_password",re_enter_password.getText().toString());
        outState.putString("email",email.getText().toString());
        outState.putString("phone_number",phone_number.getText().toString());
        outState.putInt("dept_no",departmentSpinner.getSelectedItemPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("SignUP","onRestoreInstanceState");
        firstname.setText(savedInstanceState.getString("firstname"));
        Log.d("SignUP","onRestoreInstanceState - "+savedInstanceState.getString("firstname"));
        lastname.setText(savedInstanceState.getString("lastname"));
        username.setText(savedInstanceState.getString("username"));
        password.setText(savedInstanceState.getString("password"));
        re_enter_password.setText(savedInstanceState.getString("re_enter_password"));
        email.setText(savedInstanceState.getString("email"));
        phone_number.setText(savedInstanceState.getString("phone_number"));
        departmentSpinner.setSelection(savedInstanceState.getInt("dept_no"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        showMenus(Constants.HIDE_MENU);
    }
}
