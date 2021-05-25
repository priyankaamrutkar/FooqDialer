package com.crystallogic.fooqdialer

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.crystallogic.deliveryboyapp.api.dialerClient
import com.crystallogic.fooqdialer.utils.ReUsableFunctions

class LoginScreen : AppCompatActivity()
{
    var btn_login: Button? = null
    var ed_mobile: EditText? = null
    var ed_password: EditText? = null
    var progressBar: ProgressBar? = null
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    private lateinit var apiClient: dialerClient
    val reUsableFunctions = ReUsableFunctions()

    companion object
    {
        private const val TAG = "LoginScreen"
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar!!.hide()
        ed_mobile = findViewById(R.id.ed_mobile)
        ed_password = findViewById(R.id.ed_password)
        btn_login = findViewById(R.id.btn_login)
        progressBar = findViewById(R.id.progressBar)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        editor = sharedPreferences.edit()
        apiClient = dialerClient()

        btn_login!!.setOnClickListener {
            if (validate()) {
                val mobile_number: String = ed_mobile!!.getText().toString()
                val password: String = ed_password!!.getText().toString()
                progressBar!!.visibility = View.VISIBLE
                if(reUsableFunctions.chkStatus(this))
                {
                    editor.putBoolean("log_flag", true)
                    editor.apply()
                    editor.commit()

                    var intent = Intent(this@LoginScreen, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                }
                else
                {
                    Toast.makeText(this,"Check your network connectivity",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

       fun validate(): Boolean
    {
        var valid = false

        //Get values from EditText fields
        val mobile_number: String = ed_mobile!!.getText().toString()
        val Password: String = ed_password!!.getText().toString()

        if (mobile_number < "10") {
            valid = false
            ed_mobile!!.setError("Please enter valid mobile")
        } else if (mobile_number.isEmpty()) {
            valid = false
            ed_mobile!!.setError("Please enter your mobile number")
        } else {
            valid = true
            ed_mobile!!.setError(null)
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false
            ed_password!!.setError("Please enter your password")
        } else {
            if (Password.length > 6) {
                valid = true
                ed_password!!.setError(null)
            } else {
                valid = false
                ed_password!!.setError("The password must be 6 characters long or more.")
            }
        }
        return valid
    }
}