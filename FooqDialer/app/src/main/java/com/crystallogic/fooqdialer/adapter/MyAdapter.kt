package com.crystallogic.fooqdialer.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.crystallogic.fooqdialer.fragment.InCompleteFragment
import com.crystallogic.fooqdialer.fragment.InterestedFragment
import com.crystallogic.fooqdialer.fragment.PendingFragment


class MyAdapter(context: Context, fm: FragmentManager?, totalTabs: Int) : FragmentPagerAdapter(fm!!) {
    lateinit var myContext: Context
    var totalTabs: Int = 0

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 ->
            {
                PendingFragment()
            }
            1 ->
            {
                InterestedFragment()
            }
            2 ->
            {
                InCompleteFragment()
            }
            else -> PendingFragment()
        }
    }

    // this counts total number of tabs
    override  fun getCount(): Int {
        return totalTabs
    }
    init {
      this.myContext = context
      this.totalTabs = totalTabs
    }

}