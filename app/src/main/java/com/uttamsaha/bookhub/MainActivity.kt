package com.uttamsaha.bookhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import java.lang.NullPointerException

class MainActivity : AppCompatActivity() {

    lateinit var drawerlayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout:FrameLayout
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerlayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frameLayout)
        navigationView = findViewById(R.id.navigationView)

        setUpToolbar()
        openDashboard()


        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity,drawerlayout,R.string.open_drawer,R.string.close_drawer)
        drawerlayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.dashboard ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,Dashboard())
                        .addToBackStack("Dashboard")
                        .commit()

                    supportActionBar?.title = "Dashboard"
                    drawerlayout.closeDrawers()
                }
                R.id.favourites ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,FavouritesFragment())
                        .addToBackStack("Favourites")
                        .commit()
                    supportActionBar?.title = "Favourites"
                    drawerlayout.closeDrawers()
                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,ProfileFragment())
                        .addToBackStack("Profile")
                        .commit()
                    supportActionBar?.title = "Profile"
                    drawerlayout.closeDrawers()
                }
                R.id.about ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,AboutFragment())
                        .addToBackStack("About")
                        .commit()
                    supportActionBar?.title = "About App"
                    drawerlayout.closeDrawers()
                }
            }
            return@setNavigationItemSelectedListener true
        }


    }

    fun setUpToolbar(){
      setSupportActionBar(toolbar)
        supportActionBar?.title = "Toolbar Title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == android.R.id.home){
            drawerlayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    fun openDashboard(){
        val fragment = Dashboard()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,fragment)
        transaction.commit()
        supportActionBar?.title = "Dashboard"
    }

}