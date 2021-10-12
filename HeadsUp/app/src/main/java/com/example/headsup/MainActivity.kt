package com.example.headsup

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var rvMain:RecyclerView
    lateinit var searchCelebrity: EditText
    var celebrityList = arrayListOf<CelebrityDetails.CelebrityDetailsItem>()
    var celebritySearchName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         rvMain = findViewById(R.id.rvMain)
        var newCelebrity = findViewById<Button>(R.id.newCelebrity)
        searchCelebrity = findViewById(R.id.searchCelebrity)
        var searchBtn = findViewById<Button>(R.id.searchBtn)

        rvMain.adapter = CelebrityAdapter( celebrityList)
        rvMain.layoutManager = LinearLayoutManager(this)


        getCelebrities()

        newCelebrity.setOnClickListener {
            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Please wait")
            progressDialog.show()
            val intent = Intent(this, AddCelebrity::class.java)
            startActivity(intent) }

        searchBtn.setOnClickListener { searchForCelebrity()}
    }

    private fun searchForCelebrity() {
        var searchItem : CelebrityDetails.CelebrityDetailsItem? = null
        celebritySearchName = searchCelebrity.text.toString()

        for ( i in celebrityList){
            if (celebritySearchName == i.name){
                searchItem = i
                break
            }
        }
        if (searchItem != null){
            val intent = Intent(this, UpdateDeleteCelebrity::class.java)
            intent.putStringArrayListExtra("CelebrityData", arrayListOf(searchItem.pk.toString(),searchItem.name,searchItem.taboo1,searchItem.taboo2,searchItem.taboo3))
            startActivity(intent)
        }else {
            Toast.makeText(this, "Celebrity Not Found ", Toast.LENGTH_SHORT).show()

        }
    }

    private fun getCelebrities() {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

        apiInterface?.getCelebrity()?.enqueue(object : Callback<List<CelebrityDetails.CelebrityDetailsItem>> {


            override fun onResponse(
                call: Call<List<CelebrityDetails.CelebrityDetailsItem>>,
                response: Response<List<CelebrityDetails.CelebrityDetailsItem>>,
            ) {
                val resource = response.body()!!
                progressDialog.dismiss()
                for (i in resource) {
                    var pk = i.pk?.toInt()
                    var uName = i.name.toString()
                    var uToboo1 = i.taboo1.toString()
                    var uToboo2 = i.taboo2.toString()
                    var uToboo3 = i.taboo3.toString()
                    celebrityList.add(CelebrityDetails.CelebrityDetailsItem(uName,
                        pk,
                        uToboo1,
                        uToboo2,
                        uToboo3))

                }
                rvMain.adapter?.notifyDataSetChanged()
                rvMain.scrollToPosition(celebrityList.size - 1)
            }

            override fun onFailure(call: Call<List<CelebrityDetails.CelebrityDetailsItem>>, t: Throwable) {

            }
        })

    }
}