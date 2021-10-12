package com.example.headsup

import com.google.gson.annotations.SerializedName

class CelebrityDetails {

    var data: ArrayList<CelebrityDetailsItem>? = null

    class CelebrityDetailsItem {


        var name: String? = null

        @SerializedName("pk")
        var pk: Int? = null

        var taboo1: String? = null

        var taboo2: String? = null

        var taboo3: String?  =null

        constructor(name: String?, pk: Int?,taboo1: String?,taboo2: String?,taboo3: String?){

            this.name = name
            this.pk = pk
            this.taboo1 = taboo1
            this.taboo2 = taboo2
            this.taboo3=taboo3
        }
    }
}
