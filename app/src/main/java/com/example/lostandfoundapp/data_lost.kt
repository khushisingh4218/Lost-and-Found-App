package com.example.lostandfoundapp

data class data_lost(var Item:String ?=null, var Description:String ?=null,
                     var Location:String ?=null, var Date:String ?=null, var Email:String? = "", var Settled:String = "False" )