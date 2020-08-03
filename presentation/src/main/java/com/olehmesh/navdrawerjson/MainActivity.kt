package com.olehmesh.navdrawerjson

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.olehmesh.navdrawerjson.di.App
import com.olehmesh.navdrawerjson.extensions.loadFragment
import com.olehmesh.navdrawerjson.fragments.image_fragment.ImageFragment
import com.olehmesh.navdrawerjson.fragments.text_fragment.TextFragment
import com.olehmesh.navdrawerjson.fragments.web_fragment.WebFragment
import com.olehmesh.repository.models.MenuModel
import com.olehmesh.repository.network.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var api: ApiService

    private val scope = CoroutineScope(Job())
    private var list: List<MenuModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.component.inject(this)

        setupUI()
        getData()
    }

    private fun setupUI() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val drawerToggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun getData() {
        scope.launch(Dispatchers.IO) {
            try {
                val response = api.getMenuItems()
                when {
                    response.isSuccessful -> {
                        list = response.body()?.listMenu
                        withContext(Dispatchers.Main) {
                            initMenu()
                        }

                    }
                }

            } catch (exception: Exception) {
                Log.e("Error: ", "No data")
            }
        }
    }

    private fun initMenu() {
        val menu: Menu = nav_view.menu
        for (item in list!!)
            menu.apply {
                add(item.name)
                setGroupCheckable(0, true, true)
                onNavigationItemSelected(getItem(0).setChecked(true))
                isChangingConfigurations
            }

    }

    private fun getMenuItem(title: String): MenuModel? {
        for (item in list!!) {
            if (item.name.equals(title)) {
                return item
            }
        }
        return null
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val itemClick: MenuModel = getMenuItem(item.title.toString()) ?: return false

        when (itemClick.function) {
            ("image") -> {
                ImageFragment.newInstance(itemClick.param)?.let { loadFragment(it) }
            }
            ("text") -> {
                TextFragment.newInstance(itemClick.param)?.let { loadFragment(it) }
            }
            ("url") -> {
                WebFragment.newInstance(itemClick.param)?.let { loadFragment(it) }
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}