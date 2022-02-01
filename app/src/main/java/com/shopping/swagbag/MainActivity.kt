package com.shopping.swagbag

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.databinding.ActivityMainBinding
import com.shopping.swagbag.databinding.MainToolbarBinding
import com.shopping.swagbag.databinding.NavigationDrawerBinding


class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var toolbarBinding: MainToolbarBinding
    private lateinit var navigationBinding: NavigationDrawerBinding
    private var isToolbarVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initViews()
    }

    private fun initViews() {

     /*   val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(R.id.action_global_home2)*/

        with(viewBinding) {
            // initialize variable
            toolbarBinding = viewBinding.toolbar
            navigationBinding = viewBinding.includeNavigation

            // click listeners
            btmNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.btmWishlist->{
                        hideToolbar()

                        val navHostFragment =
                            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                        val navController = navHostFragment.navController
                        navController.navigate(R.id.action_global_wishlistWithoutProductFragment)
                    }

                    R.id.btmCart->{
                        hideToolbar()
                        val navHostFragment =
                            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                        val navController = navHostFragment.navController
                        navController.navigate(R.id.action_global_shoppingBegWithProductFragment)
                    }

                    R.id.btmCategory->{
                        hideToolbar()
                        val navHostFragment =
                            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                        val navController = navHostFragment.navController
                        navController.navigate(R.id.action_global_categoryFragment)
                    }

                    R.id.btmOffers->{
                        hideToolbar()
                        val navHostFragment =
                            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                        val navController = navHostFragment.navController
                        navController.navigate(R.id.action_global_couponsFragment)
                    }

                    R.id.btmProfile->{
                        hideToolbar()
                        val navHostFragment =
                            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                        val navController = navHostFragment.navController
                        navController.navigate(R.id.action_global_signInFragment)
                    }


                }
                true
            }

        }

        setUpNavigation()

        setUPToolbar()
    }

    private fun setUPToolbar() {
        with(toolbarBinding) {
            imgToggle.setOnClickListener {
                // open drawer when user click on toggle button
                if (!viewBinding.drawerLayout.isDrawerOpen(GravityCompat.START))
                    viewBinding.drawerLayout.openDrawer(GravityCompat.START)
                else
                    viewBinding.drawerLayout.closeDrawer(GravityCompat.END)
            }

            fragmentSearch.setOnClickListener {
                hideToolbar()
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_global_searchFragment)
            }

            fragmentNotificaiton.setOnClickListener {
                hideToolbar()
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_global_notificationFragment)
            }

            fragmentWishtlistWithoutProduct.setOnClickListener {
                hideToolbar()
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_global_wishlistWithoutProductFragment)
            }


            fragmentShoppingBegWithProduct.setOnClickListener {
                hideToolbar()
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_global_shoppingBegWithProductFragment)
            }


        }
    }

    private fun setUpNavigation() {
        with(navigationBinding) {
            // add navigation menu

            val navigationMenu: List<NavigationMenu> = OnNavigationMenu().getNavigationMenu()
            var isCategoryShow = false

            rvnavMenu.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = NavigationMenuAdapter(context, navigationMenu)
            }
        }
    }


    fun showToolbar() {
        if (this::viewBinding.isInitialized) {
            viewBinding.toolbar.root.visibility = View.VISIBLE
            viewBinding.btmNavigation.visibility = View.VISIBLE
        } else {

        }
    }

    fun hideToolbar() {
        //supportActionBar?.hide()
        viewBinding.toolbar.root.visibility = View.GONE
        viewBinding.btmNavigation.visibility = View.GONE
    }

    fun closeDrawer() {
        viewBinding.drawerLayout.closeDrawer(Gravity.LEFT)
    }

    fun changeToolbarTitle(title: String, iconVisible: Boolean) {

        if (this::viewBinding.isInitialized) {
            toolbarBinding.toolbarTitle.text = title

            if (iconVisible) {
                toolbarBinding.toobarIcon.visibility = View.VISIBLE
            } else {
                toolbarBinding.toobarIcon.visibility = View.GONE
            }
        }
    }

}