package com.studentnate.week13demo

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SecondViewModel : ViewModel() {
    private var list : MutableLiveData<ArrayList<String>> = MutableLiveData()
    private var listItems: ArrayList<String> = ArrayList()
    private lateinit var db: DbHelper

    fun getList(): MutableLiveData<ArrayList<String>> {
        return list
    }

    @SuppressLint("Range")
    fun setList(context: Context){
        db = DbHelper(context)
        val cursor = db.getEvents()
        if(cursor.moveToFirst()){
          do{

              val title = cursor.getString(cursor.getColumnIndex("ETitle"))
              val detail = cursor.getString(cursor.getColumnIndex("EDetail"))
              val date = cursor.getString(cursor.getColumnIndex("EDate"))
              listItems.add("$title\n$detail\n$date")
          }  while (cursor.moveToNext())
        }
        list.setValue(listItems)

    }

}