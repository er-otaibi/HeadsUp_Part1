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

class UpdateDeleteCelebrity : AppCompatActivity() {
    lateinit var etname1 : EditText
    lateinit var ettaboo1 : EditText
    lateinit var ettaboo2 : EditText
    lateinit var ettaboo3 : EditText
    lateinit var deleteBtn : Button
    lateinit var updateBtn : Button
    lateinit var vieweBtn : Button
    var newName=""
    var newTaboo1=""
    var newTaboo2=""
    var newTaboo3=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete_celebrity)

        etname1 = findViewById(R.id.etname1)
        ettaboo1 = findViewById(R.id.ettaboo1)
        ettaboo2 = findViewById(R.id.ettaboo2)
        ettaboo3 = findViewById(R.id.ettaboo3)
        deleteBtn = findViewById(R.id.delete_Btn)
        updateBtn = findViewById(R.id.updateBtn)
        vieweBtn = findViewById(R.id.view_Btn)

        var data = intent.extras?.getStringArrayList("CelebrityData")
        if(data!=null){
            etname1.setText(data[1])
            ettaboo1.setText(data[2])
            ettaboo2.setText(data[3])
            ettaboo3.setText(data[4])
        }
        else{
            Toast.makeText(this, "Sorry null", Toast.LENGTH_SHORT).show()
        }


        deleteBtn.setOnClickListener{

            val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)


            apiInterface?.deleteCelebrity(data!![0].toInt())?.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    etname1.setText("")
                    ettaboo1.setText("")
                    ettaboo2.setText("")
                    ettaboo3.setText("")
                    Toast.makeText(applicationContext, "Celebrity deleted" , Toast.LENGTH_SHORT).show()

                }

                override fun onFailure(call: Call<Void>, t: Throwable) {

                    call.cancel()

                }
            })
        }

        updateBtn.setOnClickListener {
            newName = etname1.text.toString()
            newTaboo1 = ettaboo1.text.toString()
            newTaboo2 = ettaboo2.text.toString()
            newTaboo3= ettaboo3.text.toString()
            val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Please wait")
            progressDialog.show()
            apiInterface?.updateCelebrity(data!![0].toInt(),CelebrityDetails.CelebrityDetailsItem(newName, data!![0].toInt(),newTaboo1,newTaboo2,newTaboo3))
                ?.enqueue(object : Callback<List<CelebrityDetails.CelebrityDetailsItem>>{
                    override fun onResponse(
                        call: Call<List<CelebrityDetails.CelebrityDetailsItem>>,
                        response: Response<List<CelebrityDetails.CelebrityDetailsItem>>,
                    ) {

                        Toast.makeText(applicationContext, "User Updated Successfully!", Toast.LENGTH_SHORT).show()
                        response.body()!!
                        progressDialog.dismiss()
                    }

                    override fun onFailure(
                        call: Call<List<CelebrityDetails.CelebrityDetailsItem>>, t: Throwable, ) {
                        progressDialog.dismiss()
                        call.cancel()
                    }

                })

        }
        vieweBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent) }
    }


}