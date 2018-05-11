package com.practiceex.ui.fragments;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.practiceex.R;
import com.practiceex.adapter.EmployeeBaseAdapter;
import com.practiceex.model.Employee;
import com.practiceex.service.EmployeeAPIService;
import com.practiceex.utils.ApiUtilities;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class EmployeesFragment extends ListFragment {

    ArrayList<Employee> employeesData = new ArrayList<>();
    EmployeeAPIService apiService;
    EmployeeBaseAdapter employeeBaseAdapter;

    private OnItemSelectedListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener){
            this.listener = (OnItemSelectedListener) context;
        }else {
            throw new ClassCastException(context.toString()+ "must implement OnItemSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_employees,container,false);
        apiService = ApiUtilities.getAPIService();

        return  super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        employeeBaseAdapter = new EmployeeBaseAdapter(getContext(),employeesData);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Call<ArrayList<Employee>> call = apiService.getEmployeeDetails();
        call.enqueue(new Callback<ArrayList<Employee>>() {
            @Override
            public void onResponse(Response<ArrayList<Employee>> response, Retrofit retrofit) {
                List<Employee> employeeList = response.body();
                Employee employee;
                for (int i = 0; i <employeeList.size();i++) {
                    employee = new Employee();

                    employee.setEmp_id(employeeList.get(i).getEmp_id());
                    employee.setFirst_name(employeeList.get(i).getFirst_name());
                    employee.setLast_name(employeeList.get(i).getLast_name());
                    employee.setUsername(employeeList.get(i).getUsername());
                    employee.setPassword(employeeList.get(i).getPassword());
                    employee.setPhone_number(employeeList.get(i).getPhone_number());
                    employee.setDep_id(employeeList.get(i).getDep_id());
                    employeesData.add(employee);
                }

                setUpList(employeesData);

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailure",t.getMessage());
            }
        });
    }

    public void setUpList(ArrayList<Employee> data){
        employeeBaseAdapter.updateData(data);
        setListAdapter(employeeBaseAdapter);
        try{
            getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Employee data = (Employee) getListView().getItemAtPosition(i);
                    listener.onEmpSelectedListener(data);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public interface OnItemSelectedListener{
        public void onEmpSelectedListener(Employee data);
    }
}
