package com.myweb.lab8mysql_queryinsert


import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface StudentAPI {
    @GET("allstd")
    fun retrieveStudent(): Call<List<Student>>

    @FormUrlEncoded
    @POST("std")
    fun insertStd(
        @Field("Name") emp_name :String,
        @Field("Gender") emp_gender :String,
        @Field("Email") emp_email :String,
        @Field("Salary") emp_salary :Int
    ):Call<Student>
}