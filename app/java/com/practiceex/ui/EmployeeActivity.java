package com.practiceex.ui;

import android.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.practiceex.R;
import com.practiceex.model.Employee;
import com.practiceex.ui.fragments.EmpDetailsFragment;
import com.practiceex.ui.fragments.EmployeesFragment;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Set;

public class EmployeeActivity extends AppCompatActivity implements EmployeesFragment.OnItemSelectedListener{

    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        EmployeesFragment fragment = new EmployeesFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.employees_container,fragment).commit();
    }

    @Override
    public void onEmpSelectedListener(Employee data) {

        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        android.app.Fragment prev = getFragmentManager().findFragmentByTag("Details Fragment");
        if (prev != null){
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        EmpDetailsFragment detailsFragment = new EmpDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putString("emp_id", String.valueOf(data.getEmp_id()));
        bundle.putString("emp_name", data.getFirst_name() + " " + data.getLast_name());
        bundle.putString("emp_email", data.getEmail());
        bundle.putString("emp_phone", data.getPhone_number());
        bundle.putString("emp_dept", data.getDep_id());

        detailsFragment.setArguments(bundle);
        detailsFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
        detailsFragment.show(getFragmentManager(),"Details Fragment");
    }
}
