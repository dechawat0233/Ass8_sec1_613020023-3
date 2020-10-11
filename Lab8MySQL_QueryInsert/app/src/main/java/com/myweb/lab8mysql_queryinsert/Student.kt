package com.myweb.lab8mysql_queryinsert

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Student(
    @Expose
    @SerializedName("Name") val emp_name :String,
    @Expose
    @SerializedName("Gender") val emp_gender :String,
    @Expose
    @SerializedName("Email") val emp_email :String,
    @Expose
    @SerializedName("Salary") val emp_salary :Int) {}