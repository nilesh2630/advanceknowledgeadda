package com.example.navdrawerscratch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import android.widget.Toast

import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    private lateinit var drawerLayout:DrawerLayout
    lateinit var toolbar:Toolbar




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout=findViewById<DrawerLayout>(R.id.drawer_layout)
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

      val navigationView=findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val toggle=ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open_nav,R.string.close_nav)
       drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if(savedInstanceState==null){
            replaceFragment(Homefragment());
//            navigationView.setCheckedItem(R.id.nav_home)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
      when(item.itemId){
          R.id.nav_home-> {replaceFragment(Homefragment())
setname("Home")
          }
          R.id.subject_video-> {replaceFragment(cardviewfragment())
              setname("Subject Video")
          }
          R.id.nav_share->{replaceFragment(ShareFragment())
                  setname("share")}

          R.id.nav_aboutus-> {replaceFragment(AboutFragment())
              setname("Aboutus")
          }
          R.id.items->{replaceFragment(LikeFragment())//from here you can navigate
              setname("paid course")}
          R.id.timer->{replaceFragment(dateandtime())//from here you can navigate
              setname("paid course")}
          R.id.nav_logout-> {
              val intent = Intent(this, SignInActivity::class.java)
              intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
              startActivity(intent)
              finish()
          }
      }
       drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setname(a:String) {
        toolbar.title=a
    }


    private fun replaceFragment(fragment: Fragment){
      supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()

   }
    override fun onBackPressed(){
        super.onBackPressed()
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

}