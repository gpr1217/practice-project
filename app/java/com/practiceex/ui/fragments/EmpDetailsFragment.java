package com.practiceex.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.practiceex.R;
import com.practiceex.adapter.EmployeeBaseAdapter;
import com.practiceex.ui.SignUpActivity;

/**
 * Created by Pratyu on 12/10/2017.
 */

public class EmpDetailsFragment extends DialogFragment implements EmployeeBaseAdapter.OnImageClickListener{

    TextView emp_name,emp_id,emp_email,emp_phone,emp_dept;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empdetails_dialog,container,false);

        getDialog().setTitle("Employee Details");


        emp_id = (TextView) view.findViewById(R.id.emp_id);

        emp_name = (TextView) view.findViewById(R.id.emp_name);

        emp_email = (TextView) view.findViewById(R.id.emp_email);

        emp_phone = (TextView) view.findViewById(R.id.emp_phone);

        emp_dept = (TextView) view.findViewById(R.id.emp_dept);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String Emp_ID = getArguments().getString("emp_id");
        String Emp_Name = getArguments().getString("emp_name");
        String Emp_Email = getArguments().getString("emp_email");
        String Emp_Phone = getArguments().getString("emp_phone");
        String Emp_Dept = getArguments().getString("emp_dept");

        emp_id.setText(Emp_ID);
        emp_name.setText(Emp_Name);
        emp_email.setText(Emp_Email);
        emp_phone.setText(Emp_Phone);
        emp_dept.setText(Emp_Dept);
    }

    @Override
    public void onEditClickListener(int position) {
        Intent intent = new Intent(getActivity(), SignUpActivity.class);
        intent.putExtra("Edit",true);
        startActivity(intent);
    }

    /*@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_launcher_round)
                .setTitle("Employee Details")
                .setMessage("Hello world")
                .setNeutralButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // TODO - Navigate to edit page
                    }
                }).create();
    }*/

}
