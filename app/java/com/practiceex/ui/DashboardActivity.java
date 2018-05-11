package com.practiceex.ui;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;

import com.practiceex.R;
import com.practiceex.ui.fragments.EmployeesFragment;
import com.practiceex.utils.Constants;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class DashboardActivity extends CustomMenuActivity {

    Context context;

    @InjectView(R.id.view_departments)
    Button view_departments;
    @InjectView(R.id.view_employees)
    Button view_employees;
    @InjectView(R.id.new_department)
    Button new_department;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.inject(this);
        context = this;
        Log.d("DashboardActivity","onCreate");
        try {
            Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
            setSupportActionBar(myToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnClick(R.id.view_departments)
    public void viewDepartments(Button button){
        Intent in = new Intent(getApplicationContext(),DepartmentsActivity.class);
        startActivity(in);
    }

    @OnClick(R.id.view_employees)
    public void viewEmployees(Button button){
        Intent in = new Intent(getApplicationContext(),EmployeeActivity.class);
        startActivity(in);
    }

    @OnClick(R.id.new_department)
    public void newDepartment(Button button){
        Intent in = new Intent(getApplicationContext(),DepartmentsActivity.class);
        startActivity(in);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("DashboardActivity","onResume");
        showMenus(Constants.HIDE_SEARCH,Constants.SHOW_MENU);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("DashboardActivity","onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("DashboardActivity","onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("DashboardActivity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("DashboardActivity","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("DashboardActivity","onDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d("DashboardActivity","onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("DashboardActivity","onRestoreInstanceState");
    }
}
