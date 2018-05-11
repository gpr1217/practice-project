package com.practiceex.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.practiceex.R;
import com.practiceex.adapter.DepartmentBaseAdapter;
import com.practiceex.model.Departments;
import com.practiceex.service.DepartmentApiService;
import com.practiceex.ui.DashboardActivity;
import com.practiceex.utils.ApiUtilities;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class DeptListFragment extends Fragment {

    @InjectView(R.id.departments_listview)
    ListView listView;
    private DepartmentApiService apiService;

    private OnItemSelectedListener listener;

    ArrayList<Departments> arrayList = new ArrayList<>();
    private  DepartmentBaseAdapter baseAdapter;

    public String dept_num = "",dept_name = "",dept_desc = "";

    int onLaunch = 0;
    public DeptListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_depts_list, container, false);

        ButterKnife.inject(view);
        listView = (ListView) view.findViewById(R.id.departments_listview);
        baseAdapter = new DepartmentBaseAdapter(getContext(),arrayList);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        apiService = ApiUtilities.getDeptAPIService();
        getDepartmentsLists();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener){
            this.listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()+ "must implement OnItemSelectedListener");
        }
    }

    public void getDepartmentsLists(){
        final Call<List<Departments>> call = apiService.getDepartments();
        call.enqueue(new Callback<List<Departments>>() {
            @Override
            public void onResponse(Response<List<Departments>> response, Retrofit retrofit) {
                try {
                    List<Departments> departmentsList = response.body();
                    Departments departments;
                    for (int i = 0; i <departmentsList.size();i++) {
                        departments = new Departments();
                        dept_num = String.valueOf(departmentsList.get(0).getDept_no());
                        dept_name = departmentsList.get(0).getDept_name();
                        dept_desc = departmentsList.get(0).getDept_description();

                        departments.setDept_no(departmentsList.get(i).getDept_no());
                        departments.setDept_name(departmentsList.get(i).getDept_name());
                        departments.setDept_description(departmentsList.get(i).getDept_description());
                        arrayList.add(departments);
                    }

                    setUpList(arrayList);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void setUpList(ArrayList<Departments> list){
        baseAdapter.updateData(list);
        listView.setAdapter(baseAdapter);
        try{
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Departments selectedItem = (Departments) listView.getItemAtPosition(position);
                    listener.onDeptSelectedListener(selectedItem);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public interface OnItemSelectedListener{
        void onDeptSelectedListener(Departments departments);
    }
}
