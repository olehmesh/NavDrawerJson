package com.olehmesh.navdrawerjson.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.olehmesh.navdrawerjson.R

fun AppCompatActivity.loadFragment(fragment: Fragment) {
    val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    transaction.replace(R.id.nav_host_fragment, fragment)
    transaction.disallowAddToBackStack()
    transaction.commit()
}