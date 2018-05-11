package com.practiceex.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.practiceex.R;
import com.practiceex.model.Departments;
import com.practiceex.ui.fragments.DepartmentsFragment;
import com.practiceex.ui.fragments.DeptListFragment;

public class DepartmentsActivity extends AppCompatActivity implements DeptListFragment.OnItemSelectedListener{

    Context context;
    Bundle defaultArgs = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);
        context = this;

        Log.d("DEBUG", getResources().getConfiguration().orientation + "");

        DeptListFragment fragment = new DeptListFragment();
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.list_container, fragment);
            transaction.commit();
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            getSupportFragmentManager().beginTransaction().replace(R.id.list_container,fragment).addToBackStack(null).commit();

            DepartmentsFragment departmentsFragment = new DepartmentsFragment();
            defaultArgs.putString("dept_no",(fragment.dept_num).equals("") ? "NA" : fragment.dept_num);
            defaultArgs.putString("dept_name",(fragment.dept_name).equals("") ? "NA" : fragment.dept_name);
            defaultArgs.putString("dept_description",(fragment.dept_desc).equals("") ? "NA" : fragment.dept_desc);
            departmentsFragment.setArguments(defaultArgs);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.details_container,departmentsFragment);
            ft.commit();
        }

    }

    @Override
    public void onDeptSelectedListener(Departments data) {
        DepartmentsFragment departmentsFragment = new DepartmentsFragment();
        Bundle args = new Bundle();
        args.putString("dept_no", String.valueOf(data.getDept_no()));
        args.putString("dept_name", String.valueOf(data.getDept_name()));
        args.putString("dept_description", String.valueOf(data.getDept_description()));
        departmentsFragment.setArguments(args);

        DeptListFragment deptListFragment = new DeptListFragment();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            getSupportFragmentManager().beginTransaction().replace(R.id.list_container,deptListFragment).addToBackStack(null).commit();
            getSupportFragmentManager().beginTransaction().replace(R.id.details_container,departmentsFragment).addToBackStack(null).commit();
        }
        else{
            getSupportFragmentManager().beginTransaction().replace(R.id.list_container,departmentsFragment).addToBackStack(null).commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
