package com.example.wlobbyapp.data.firebase.ilan

data class InvitationModel(
    val invitationUuid:String?,
    var date:String?,
    var time:String?,
    var participantMaxNumber:Int,
    var participantTotalNumber:Int,
    val movieId:String,
    var userNote: String?,
   // var participantsId:List<Profile>
)
