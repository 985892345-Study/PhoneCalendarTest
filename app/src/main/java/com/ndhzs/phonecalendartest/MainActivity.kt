package com.ndhzs.phonecalendartest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    
    var eventId: Long? = null
    
    findViewById<Button>(R.id.main_add).setOnClickListener {
      if (eventId == null) {
        // RRULE 规则：3.8.5.3
        eventId = PhoneCalendar.add(
          PhoneCalendar.CommonEvent(
            "title", "content", -1,
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
    
    findViewById<Button>(R.id.main_delete).setOnClickListener {
      val id = eventId
      if (id != null) {
        if (PhoneCalendar.delete(id)) {
          eventId = null
          Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show()
        } else {
          Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show()
        }
      } else {
        Toast.makeText(this, "不能删除", Toast.LENGTH_SHORT).show()
      }
    }
    
    findViewById<Button>(R.id.main_delete_all).setOnClickListener {
      if (PhoneCalendar.deleteAll()) {
        Toast.makeText(this, "删除全部成功", Toast.LENGTH_SHORT).show()
      } else {
        Toast.makeText(this, "删除全部失败", Toast.LENGTH_SHORT).show()
      }
    }
  }
}