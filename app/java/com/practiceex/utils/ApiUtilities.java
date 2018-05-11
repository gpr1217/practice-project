package com.practiceex.utils;

import com.practiceex.service.DepartmentApiService;
import com.practiceex.service.EmployeeAPIService;
import com.practiceex.RetrofitClient;

/**
 * Created by Pratyu on 12/3/2017.
 */

public class ApiUtilities {
    public ApiUtilities() {
    }

    public static final String BASE_URL = "http://192.168.1.6:8088/RetrofitApi/";

    public static EmployeeAPIService getAPIService(){
        return RetrofitClient.getRetrofitClient(BASE_URL).create(EmployeeAPIService.class);
    }

    public static DepartmentApiService getDeptAPIService(){
        return RetrofitClient.getRetrofitClient(BASE_URL).create(DepartmentApiService.class);
    }
}
