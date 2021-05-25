package com.crystallogic.fooqdialer

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.crystallogic.deliveryboyapp.api.dialerClient
import com.crystallogic.fooqdialer.adapter.MyAdapter
import com.crystallogic.fooqdialer.utils.ReUsableFunctions
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    var toolbar : androidx.appcompat.widget.Toolbar ? = null
    var viewPager : ViewPager?= null
    var tabs: TabLayout? = null
    var recycler_assign_list : RecyclerView? = null
    var arr_assign_list : ArrayList<String> = ArrayList<String>()
    var image_logout : ImageView? = null
    var apiClient = dialerClient()
    var reUsableFunctions = ReUsableFunctions()
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    var token: String? = null
    var progressBar : ProgressBar?= null

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        context = this
        tabs = findViewById(R.id.tabs)
        toolbar  = findViewById(R.id.toolbar)
        viewPager = findViewById(R.id.viewPager)
        tabs!!.addTab(tabs!!.newTab().setText("Pending"), 0, true)
        tabs!!.addTab(tabs!!.newTab().setText("Interested"), 1)
        tabs!!.addTab(tabs!!.newTab().setText("In Complete"), 2)

        progressBar = findViewById(R.id.progressBar)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        editor = sharedPreferences.edit()

//        token = sharedPreferences.getString("token", "").toString()

        Log.e(TAG, "token: " + token)

        val adapter = MyAdapter(this, supportFragmentManager, tabs!!.getTabCount())
        viewPager!!.adapter = adapter




        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs!!))

        tabs!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

//        setSupportActionBar(toolbar!!)
//
//        val adapter = ViewPagerAdapter(supportFragmentManager)
//        adapter.addFragment(AssignListFragment(), "Assign List ")
//        adapter.addFragment(InTransitListFragment(), "In Transit List")
//        viewPager!!.adapter = adapter
//        tabs!!.setupWithViewPager(viewPager)

    }
}