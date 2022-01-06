package com.shopping.swagbag

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.internal.NavigationMenuView
import com.google.android.material.navigation.NavigationView
import com.shopping.swagbag.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        initViews()
    }

    private fun initViews() {
        // set up navigation drawer
        setUpNavigation()

        // set home fragment
        // supportFragmentManager.beginTransaction().replace(R.id.frameLayout, Home()).commit()
    }

    private fun setUpNavigation() {
        with(viewBinding) {
            setSupportActionBar(toolbar)
            // hide toolbar title
            supportActionBar?.setDisplayShowTitleEnabled(false)

            // stop navigation gesture
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            val toggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, toolbar, 0, 0)
            drawerLayout.addDrawerListener(toggle)

            // set toggle icon and icon color
            toggle.isDrawerIndicatorEnabled = false
            // toggle.drawerArrowDrawable.color = resources.getColor(R.color.white)
            toolbar.setNavigationIcon(R.drawable.ic_hameburger_big_small_big)
            toolbar.setNavigationOnClickListener { drawerLayout.openDrawer(Gravity.LEFT) }

            toggle.syncState()

            // disable scroll view from navigation drawer
            disableNavigationViewScrollbars(navigationView)

            // click listener on navigation menus
            for (i in 0..14) {

                navigationView.menu.getItem(i)
                    .setActionView(R.layout.navigation_right_side_icon)
                navigationView.setNavigationItemSelectedListener(this@MainActivity)
            }
        }
    }

    private fun disableNavigationViewScrollbars(navigationView: NavigationView?) {
        if (navigationView != null) {
            val navigationMenuView = navigationView.getChildAt(0) as NavigationMenuView
            navigationMenuView.isVerticalScrollBarEnabled = false
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navMenuHome -> viewBinding.drawerLayout.closeDrawer(Gravity.LEFT)

            R.id.navMenuOrder -> {
                hideToolbar()
                supportActionBar?.hide()
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_categoryFragment_to_orderWithoutItemsFragment)
                viewBinding.drawerLayout.closeDrawer(Gravity.LEFT)
            }

            R.id.navMenuWishlist->{
                hideToolbar()
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_categoryFragment_to_wishlistWithoutProductFragment)
                viewBinding.drawerLayout.closeDrawer(Gravity.LEFT)
            }

            R.id.navMenuSavedCards->{
                hideToolbar()
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_categoryFragment_to_savedCardsWithCards)
                viewBinding.drawerLayout.closeDrawer(Gravity.LEFT)
            }

            R.id.navMenuAddress->{
                hideToolbar()
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_categoryFragment_to_viewUserDetailsFragment)
                viewBinding.drawerLayout.closeDrawer(Gravity.LEFT)
            }

            R.id.navMenuCoupons->{
                hideToolbar()
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_categoryFragment_to_couponsFragment)
                viewBinding.drawerLayout.closeDrawer(Gravity.LEFT)
            }

            R.id.navMenuProfile->{
                hideToolbar()
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_categoryFragment_to_signInFragment)
                viewBinding.drawerLayout.closeDrawer(Gravity.LEFT)
            }

            R.id.navMenuHelpCenter->{
                hideToolbar()
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_categoryFragment_to_helpCenterWithoutOrder)
                viewBinding.drawerLayout.closeDrawer(Gravity.LEFT)
            }

            R.id.navMenuFaqs->{
                hideToolbar()
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_categoryFragment_to_FAQsFragment)
                viewBinding.drawerLayout.closeDrawer(Gravity.LEFT)
            }

            R.id.navMenuAboutUs->{
                hideToolbar()
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_categoryFragment_to_aboutUs)
                viewBinding.drawerLayout.closeDrawer(Gravity.LEFT)
            }

            R.id.navMenuContactUs->{
                hideToolbar()
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_categoryFragment_to_contactUsFragment)
                viewBinding.drawerLayout.closeDrawer(Gravity.LEFT)
            }

            R.id.navMenuTermsOfUses->{
                hideToolbar()
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_categoryFragment_to_termsOfUsesFragment)
                viewBinding.drawerLayout.closeDrawer(Gravity.LEFT)
            }

            R.id.navMenuPrivacyPolicy->{
                hideToolbar()
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_categoryFragment_to_privacyPolicyFragment)
                viewBinding.drawerLayout.closeDrawer(Gravity.LEFT)
            }

            R.id.navMenuLogout->{
                viewBinding.drawerLayout.closeDrawer(Gravity.LEFT)
            }

        }
        return true
    }

     fun hideToolbar() {
        supportActionBar?.hide()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.tbSearch -> {
                hideToolbar()
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_categoryFragment_to_searchFragment)
            }
            R.id.tbNotification -> {
                hideToolbar()
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_categoryFragment_to_notificationFragment)
            }
            R.id.tbWishlist -> {
                hideToolbar()
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_categoryFragment_to_wishlistWithoutProductFragment)
            }
            R.id.tbCart -> {
                hideToolbar()
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_categoryFragment_to_shoppingBegWithProductFragment)
            }
        }
        return true
    }

    fun showToolbar() {
        Log.e("TAG", "showToolbar: ", )
       // setSupportActionBar(viewBinding.toolbar)
        supportActionBar?.show()
        //viewBinding.toolbar.isVisible = true
    }
}

interface ShowToolbar{
    fun showToolbar()
}