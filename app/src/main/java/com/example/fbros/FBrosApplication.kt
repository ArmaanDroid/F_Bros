package com.example.fbros

import android.app.Application
import com.example.fbros.db.FBrosDB

class FBrosApplication  : Application(){
    val db : FBrosDB by lazy { FBrosDB.getDatabase(this) }
}