package com.crystallogic.fooqdialer

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity()
{
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 5000 //3 seconds
    lateinit var sharedPreferences: SharedPreferences
    val sharedPrefFile = "sharedpreference"
    private var loggedIn: Boolean = false
    lateinit var context: Context
    var editor: SharedPreferences.Editor? = null

   companion object
   {
       private const val TAG = "SplashScreen"
   }

    private val mRunnable: Runnable = Runnable {

        if (!isFinishing) {
            if (!loggedIn) {
                Log.e(TAG, "in if")
                val intent = Intent(applicationContext, LoginScreen::class.java)
                startActivity(intent)

                finish()
            } else {
                Log.e(Companion.TAG, "in else")
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar!!.hide()
        //Initialize the Handler
        mDelayHandler = Handler()
        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)

        editor = sharedPreferences.edit()
        loggedIn = sharedPreferences.getBoolean("log_flag", false)
    }

}