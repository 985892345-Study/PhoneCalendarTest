package com.ndhzs.phonecalendartest

import android.app.Application

/**
 * .
 *
 * @author 985892345
 * @date 2022/10/31 21:58
 */
class App : Application() {
  
  companion object {
    lateinit var application: Application
      private set
  }
  
  override fun onCreate() {
    super.onCreate()
    application = this
  }
}