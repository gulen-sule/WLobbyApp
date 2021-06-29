package com.example.wlobbyapp.data.api.models.ilan

data class AdDataClass(
    val user_uuid:String?,
    var date:String?,
    var time:String?,
    var participiantMaxNumber:Int,
    var participiantTotalNumber:Int,
    val movie_id:String,
    var user_note: String?,
   // var participiants_id:List<Profile>
)
