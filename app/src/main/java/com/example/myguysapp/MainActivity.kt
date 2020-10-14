package com.example.myguysapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tampilMyGuysFragment()
    }

    private fun gantiFragment(fragmentManager:FragmentManager, fragment: Fragment, frameId: Int) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment)

        transaction.commit()
    }

    fun tampilMyGuysFragment(){
        gantiFragment(supportFragmentManager, MyGuysFragment.newInstance(), R.id.contentFrame)
    }

    fun tampilMyGuysAddFragment(){
        gantiFragment(supportFragmentManager, MyGuysAddFragment.newInstance(), R.id.contentFrame)
    }
}
