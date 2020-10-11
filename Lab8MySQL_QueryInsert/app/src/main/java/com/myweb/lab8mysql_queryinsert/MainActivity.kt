package com.myweb.lab8mysql_queryinsert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_insert.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var studentList = arrayListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.adapter = StudentsAdapter(this.studentList,applicationContext)
        recycler_view.layoutManager = LinearLayoutManager(applicationContext)
    }
    override fun onResume() {
        super.onResume()
        callStudentdata()
    }
    fun callStudentdata(){
        studentList.clear()
        val serv: StudentAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StudentAPI::class.java)

        serv.retrieveStudent()
            .enqueue(object : Callback<List<Student>>{
                override fun onResponse(
                    call: Call<List<Student>>,
                    response: Response<List<Student>>
                ) {
                    response.body()?.forEach{
                        studentList.add(Student(it.emp_name,it.emp_gender,it.emp_email,it.emp_salary))
//                        studentList.add(Student(it.emp_name,it.emp_gender,it.emp_email,it.emp_salary))
                    }
                    recycler_view.adapter=StudentsAdapter(studentList,applicationContext)
                }

                override fun onFailure(call: Call<List<Student>>, t: Throwable) {
                    return t.printStackTrace()
                }
            })
    }
    fun btnAddStudent(view: View){
        var add = Intent(this,InsertActivity::class.java)
        startActivity(add)
    }
//    fun callStudent() {
//        studentList.clear()
//
//        val serv: StudentAPI = Retrofit.Builder().baseUrl("http://10.0.2.2:3000/")
//            .addConverterFactory(GsonConverterFactory.create()).build()
//            .create(StudentAPI::class.java)
//
//        serv.retrieveStudent().enqueue(object : Callback<List<Student>> {
//            override fun onResponse(
//                call: Call<List<Student>>,
//                response: Response<List<Student>>
//            ) {
//                response.body()?.forEach {
//                    studentList.add(Student(it.emp_name, it.emp_gender, it.emp_email, it.emp_salary))
//                }
//
//                recycler_view.adapter = StudentsAdapter(studentList, applicationContext)
//            }
//
//            override fun onFailure(call: Call<List<Student>>, t: Throwable) {
//                return t.printStackTrace()
//            }
//        })
//    }


}
