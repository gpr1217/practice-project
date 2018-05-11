package com.practiceex.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.practiceex.R;
import com.practiceex.adapter.DepartmentBaseAdapter;
import com.practiceex.model.Departments;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DepartmentsFragment extends Fragment {

    int position = 0;
    @InjectView(R.id.department_no)
    TextView dept_no;
    @InjectView(R.id.department_name)
    TextView dept_name;
    @InjectView(R.id.department_description)
    TextView dept_description;

    Bundle bundle = new Bundle();
    Context context;

    public DepartmentsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            if (getArguments() != null) {
                Log.d("DepartmentsFragment","onCreate");
                try {
                    bundle = getArguments();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("DepartmentsFragment","onCreateView");
        View view = inflater.inflate(R.layout.fragment_depts_details, container, false);
        // Inflate the layout for this fragment
        dept_no = (TextView) view.findViewById(R.id.department_no);
        dept_name = (TextView) view.findViewById(R.id.department_name);
        dept_description = (TextView) view.findViewById(R.id.department_description);
        ButterKnife.inject(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        try {
            if (bundle.isEmpty()){
                dept_no.setText("NA");
                dept_name.setText("NA");
                dept_description.setText("NA");
            }else {
                dept_no.setText(bundle.getString("dept_no"));
                dept_name.setText(bundle.getString("dept_name"));
                dept_description.setText(bundle.getString("dept_description"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
