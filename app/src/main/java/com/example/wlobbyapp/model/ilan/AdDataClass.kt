package com.example.wlobbyapp.model.ilan

import com.example.wlobbyapp.model.Profile

data class AdDataClass(
    val user_uuid:String?,
    var date:String?,
    var time:String?,
    var participiantMaxNumber:Int,
    var participiantTotalNumber:Int,
    val movie_id:String,
    var user_note: String?,
    var participiants_id:List<Profile>
)
