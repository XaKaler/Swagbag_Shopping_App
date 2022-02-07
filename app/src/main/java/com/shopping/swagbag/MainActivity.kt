package com.shopping.swagbag

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.category.CategoryRepository
import com.shopping.swagbag.category.CategoryViewModel
import com.shopping.swagbag.category.CategoryViewModelFactory
import com.shopping.swagbag.common.HomeCategoryRecycleItemClickListener
import com.shopping.swagbag.common.adapter.CategorySliderAdapter
import com.shopping.swagbag.databinding.ActivityMainBinding
import com.shopping.swagbag.databinding.MainToolbarBinding
import com.shopping.swagbag.databinding.NavigationDrawerBinding
import com.shopping.swagbag.databinding.NavigationHeaderBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.service.RetrofitSingleton


class MainActivity : AppCompatActivity(),
    HomeCategoryRecycleItemClickListener {

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var toolbarBinding: MainToolbarBinding
    private lateinit var navigationBinding: NavigationDrawerBinding
    private lateinit var navigationHeaderBinding: NavigationHeaderBinding
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initViews()
    }

    private fun initViews() {
        with(viewBinding) {
            // initialize variable
            toolbarBinding = viewBinding.toolbar
            navigationBinding = viewBinding.includeNavigation
            navigationHeaderBinding = navigationBinding.includeHeader


            // initialize view model
            val repository = CategoryRepository(RetrofitSingleton.getRetroApi())

            categoryViewModel = ViewModelProvider(
                this@MainActivity,
                CategoryViewModelFactory(repository)
            )[CategoryViewModel::class.java]

            setCategorySlider()

            // click listeners
            btmNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.btmWishlist -> {
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

                    R.id.btmOffers -> {/*
                        hideToolbar()
                        val navHostFragment =
                            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                        val navController = navHostFragment.navController
                        navController.navigate(R.id.action_global_couponsFragment)*/
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

        setUpNavigationHeader()

        setUPToolbar()
    }

    private fun setUpNavigationHeader() {
        with(navigationHeaderBinding) {
            closeDrawer.setOnClickListener {
                closeDrawer()
            }
        }
    }

    fun setCategorySlider() {
        if (this::viewBinding.isInitialized) {
            viewBinding.rvCategorySlider.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = DummyData().getDummyCategory()
                    ?.let { CategorySliderAdapter(this@MainActivity, it, this@MainActivity) }
            }
        }
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
                categoryViewModel.category.observe(this@MainActivity, Observer{
                    adapter = NavigationMenuAdapter(this@MainActivity, navigationMenu, it.result)
                })
            }
        }
    }


    fun showToolbar() {
        if (this::viewBinding.isInitialized) {
            viewBinding.toolbar.root.visibility = View.VISIBLE
            viewBinding.btmNavigation.visibility = View.VISIBLE
            viewBinding.rvCategorySlider.visibility = View.VISIBLE
        }
    }

    fun hideToolbar() {
        viewBinding.toolbar.root.visibility = View.GONE
        viewBinding.btmNavigation.visibility = View.GONE
        viewBinding.rvCategorySlider.visibility = View.GONE
    }

    fun closeDrawer() {
        viewBinding.drawerLayout.closeDrawer(Gravity.LEFT)
    }


    override fun onHomeCategorySingleItemClickListener(position: Int) {
        when(position){
            0 -> {
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_global_menFragment)
            }

            1->{
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_global_womenFragment)
            }

            2->{
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_global_kidsFragment)
            }

            3->{
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_global_petsFragment)
            }
            4->{
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_global_homeFragment)
            }

            5->{
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_global_travelFragment)
            }
        }
    }
}