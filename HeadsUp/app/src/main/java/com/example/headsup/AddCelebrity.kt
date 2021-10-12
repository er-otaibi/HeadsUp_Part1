package com.example.headsup

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCelebrity : AppCompatActivity() {
    lateinit var et_name : EditText
    lateinit var et_toboo1 : EditText
    lateinit var et_toboo2 : EditText
    lateinit var et_toboo3 : EditText
    lateinit var addBtn : Button
    lateinit var viewBtn : Button
    var name2 =""
    var toboo_1 =""
    var toboo_2 =""
    var toboo_3 =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_celebrity)

        et_name = findViewById(R.id.et_name)
        et_toboo1 = findViewById(R.id.et_toboo1)
        et_toboo2 = findViewById(R.id.et_toboo2)
        et_toboo3 = findViewById(R.id.et_toboo3)
        addBtn = findViewById(R.id.addBtn)
        viewBtn = findViewById(R.id.viewBtn)

        addBtn.setOnClickListener {
            name2 = et_name.text.toString()
            toboo_1 = et_toboo1.text.toString()
            toboo_2 = et_toboo2.text.toString()
            toboo_3 = et_toboo3.text.toString()
            addCelebrity()
            Toast.makeText(applicationContext, "New Celebrity is Added" , Toast.LENGTH_SHORT).show()
            clearText()
        }

        viewBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent) }


    }

    private fun clearText() {
        et_name.setText("")
        et_toboo1.setText("")
        et_toboo2.setText("")
        et_toboo3.setText("")
    }

    private fun addCelebrity() {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

        apiInterface?.addCelebrity(CelebrityDetails.CelebrityDetailsItem(name2, pk = null,toboo_1,toboo_2,toboo_3))
            ?.enqueue(object : Callback<List<CelebrityDetails.CelebrityDetailsItem>>{
                override fun onResponse(
                    call: Call<List<CelebrityDetails.CelebrityDetailsItem>>,
                    response: Response<List<CelebrityDetails.CelebrityDetailsItem>>,
                ) {

                    Toast.makeText(applicationContext, "New Celebrity is Added" , Toast.LENGTH_SHORT).show()
                    progressDialog.dismiss()

                }

                override fun onFailure(
                    call: Call<List<CelebrityDetails.CelebrityDetailsItem>>,
                    t: Throwable,
                ) {
                    progressDialog.dismiss()
                    call.cancel()
                }

            })

    }
}