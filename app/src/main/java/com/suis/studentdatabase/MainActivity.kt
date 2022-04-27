package com.suis.studentdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var edName: EditText
    private lateinit var edMail: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnView: Button

    private lateinit var sqLiteHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        sqLiteHelper = SQLiteHelper(context = this)

        btnAdd.setOnClickListener { addStudent() }
        btnView.setOnClickListener { getStudents() }
    }

    private fun getStudents() {
        val stdList = sqLiteHelper.getAllStudents()
        Log.i("Size", "${stdList.size}")
    }

    private fun addStudent() {
        val name = edName.text.toString()
        val email = edMail.text.toString()
        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(
                this, "Please fill out the fields",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val std = StudentModel(name = name, email = email)
            val status = sqLiteHelper.insertStudent(std)
            if (status > -1) {
                Toast.makeText(
                    this, "Student successfully added",
                    Toast.LENGTH_SHORT
                ).show()
                clearEditText()
            } else {
                Toast.makeText(
                    this, "Please try again",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun clearEditText() {
        edName.setText("")
        edMail.setText("")
        edName.requestFocus()
    }

    private fun initView() {
        edName = findViewById(R.id.edName)
        edMail = findViewById(R.id.edEmail)
        btnAdd = findViewById(R.id.btnAdd)
        btnView = findViewById(R.id.btnView)
    }
}