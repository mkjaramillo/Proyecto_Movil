package com.example.proyecto_movil.utils

import android.app.Application
import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class DataBaseInstance : Application(){
    companion object {

        private lateinit var  auth: FirebaseAuth
        private lateinit var  database: FirebaseFirestore
        fun getDatabaseAuth():FirebaseAuth{
            return auth;
        }
        fun getDataBaseFireStore():FirebaseFirestore{
            return database;
        }
    }
    override fun onCreate() {
        super.onCreate()
        auth= FirebaseAuth.getInstance()
        database= FirebaseFirestore.getInstance()

    }

}