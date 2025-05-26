package com.example.pillertest2_2.domain

import javax.inject.Inject


class LoginFormValidator @Inject constructor(){
    fun checkUserName(data: String) : String?{
        var errorMessage : String? = null
        if(data.length <4 || data.isEmpty()){
            errorMessage= "Need least 4 character!"
        }
        return errorMessage
    }

    fun checkPassword(data: String) : String?{
        var errorMessage : String?= null
        if(data.length <4 || data.isEmpty()){
            errorMessage= "Need least 4 character!"
        }
        if(!Regex("[A-Z]").containsMatchIn(data)){
            errorMessage= "Need least 1 capital letter!"
        }
        return errorMessage
    }
}
