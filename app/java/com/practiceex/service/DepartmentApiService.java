package com.practiceex.service;

import com.practiceex.model.Departments;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Pratyu on 12/3/2017.
 */

public interface DepartmentApiService {

    @GET("Welcome.php?apiCall=getDepartments")
    Call<List<Departments>> getDepartments();

    @GET("Welcome.php?apiCall=getDepartments")
    Call<Departments> getObjDepartments();
}
