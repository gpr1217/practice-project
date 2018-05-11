package com.practiceex.service;

import com.practiceex.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Pratyu on 12/2/2017.
 */

public interface EmployeeAPIService {

    @GET("Welcome.php?apiCall=getEmployees")
    Call<ArrayList<Employee>> getEmployeeDetails();

    @GET("Welcome.php?apiCall=getEmployee")
    Call<List<Employee>> getObjEmployeeDetails(@Query("username") String username, @Query("password") String password);

    @POST("Welcome.php?apiCall=addNewEmployee")
    @FormUrlEncoded
    Call<Employee> addEmployee(@Field("emp_id") int emp_id,
                               @Field("first_name") String first_name,
                               @Field("last_name") String last_name,
                               @Field("email") String email,
                               @Field("phone_number") String phone_number,
                               @Field("dep_id") String dep_id,
                               @Field("username") String username,
                               @Field("password") String password);
}
