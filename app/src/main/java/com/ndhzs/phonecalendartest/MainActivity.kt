package com.ndhzs.phonecalendartest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    
    var eventId: Long? = null
  
    AlertDialog.Builder(this).apply {
      setPositiveButton(android.R.string.ok) { _, _ ->
        Log.d("ggg", "(${Exception().stackTrace[0].run { "$fileName:$lineNumber" }}) -> " +
          "setPositiveButton")
      }
      setNegativeButton(android.R.string.cancel) { _, _ ->
        Log.d("ggg", "(${Exception().stackTrace[0].run { "$fileName:$lineNumber" }}) -> " +
          "setNegativeButton")
      }
      setOnCancelListener {
        Log.d("ggg", "(${Exception().stackTrace[0].run { "$fileName:$lineNumber" }}) -> " +
          "setOnCancelListener")
      }
    }.show()
    
    findViewById<Button>(R.id.main_add).setOnClickListener {
      if (eventId == null) {
        // RRULE 规则：3.8.5.3
        eventId = PhoneCalendar.add(
          PhoneCalendar.CommonEvent(
            "title", "content", 20,
            PhoneCalendar.Event.Duration(minute = 30),
            startTime = listOf(
              Calendar.getInstance()
            ),
          )
        )
        if (eventId != null) {
          Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show()
        } else {
          Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show()
        }
      } else {
        Toast.makeText(this, "不能添加", Toast.LENGTH_SHORT).show()
      }
    }
    findViewById<Button>(R.id.main_update).setOnClickListener {
      val id = eventId
      if (id != null) {
        val result = PhoneCalendar.update(
          id,
          PhoneCalendar.CommonEvent(
            "title2", "content2", -1,
            PhoneCalendar.Event.Duration(minute = 20),
            startTime = listOf(
              Calendar.getInstance()
            ),
          )
        )
        Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show()
      }
    }
    
    findViewById<Button>(R.id.main_delete_all).setOnClickListener {
      if (PhoneCalendar.delete(eventId!!)) {
        Toast.makeText(this, "删除全部成功", Toast.LENGTH_SHORT).show()
        eventId = null
      } else {
        Toast.makeText(this, "删除全部失败", Toast.LENGTH_SHORT).show()
      }
    }
  }
}