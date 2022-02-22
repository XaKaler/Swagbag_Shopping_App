package com.shopping.swagbag

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.category.*
import com.shopping.swagbag.common.HomeCategoryRecycleItemClickListener
import com.shopping.swagbag.common.adapter.CategorySliderAdapter
import com.shopping.swagbag.databinding.ActivityMainBinding
import com.shopping.swagbag.databinding.MainToolbarBinding
import com.shopping.swagbag.databinding.NavigationDrawerBinding
import com.shopping.swagbag.databinding.NavigationHeaderBinding
import com.shopping.swagbag.service.RemoteDataSource
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.utils.AppUtils


class MainActivity : AppCompatActivity(),
    HomeCategoryRecycleItemClickListener {

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var toolbarBinding: MainToolbarBinding
    private lateinit var navigationBinding: NavigationDrawerBinding
    private lateinit var navigationHeaderBinding: NavigationHeaderBinding
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var masterCategories: List<MasterCategoryModel.Result>

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

            val repository =
                CategoryRepository(RemoteDataSource().getBaseUrl().create(CategoryApi::class.java))

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

                    R.id.btmOffers -> {
                        hideToolbar()
                        val navHostFragment =
                            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                        val navController = navHostFragment.navController
                        navController.navigate(R.id.action_global_brandFragment)
                    }

                    R.id.btmProfile -> {
                        hideToolbar()
                        val navHostFragment =
                            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                        val navController = navHostFragment.navController

                        // check whether user is already login or not
                        val isUserLogIn = AppUtils(this@MainActivity).isUserLoggedIn()

                        Log.e("TAG", "initViews: $isUserLogIn")

                        if (AppUtils(this@MainActivity).isUserLoggedIn())
                            navController.navigate(R.id.action_global_profileFragment)
                        else
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
            categoryViewModel.masterCategory().observe(this, Observer {
                when (it) {
                    is Resource.Success -> {
                        masterCategories = it.value.result

                        viewBinding.rvCategorySlider.apply {
                            layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                            adapter = CategorySliderAdapter(
                                this@MainActivity,
                                it.value.result,
                                this@MainActivity
                            )
                        }
                    }

                    is Resource.Failure -> Toast.makeText(
                        this,
                        it.errorCode.toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                    else -> {}
                }
            })
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
                categoryViewModel.category.observe(this@MainActivity, Observer {
                    when (it) {
                        is Resource.Success -> {
                            Log.e("TAG", "setUpNavigation: $it", )
                            adapter = NavigationMenuAdapter(this@MainActivity, navigationMenu, it.value.result)
                        }

                        is Resource.Failure -> Log.e("TAG", "setUpNavigation: $it", )
                        else -> {}
                    }
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
        when(masterCategories[position].name){
            "Men" -> {
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_global_menFragment)
            }

            "Women"->{
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_global_womenFragment)
            }

            "Kids"->{
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_global_kidsFragment)
            }

            "Pets"->{
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_global_petsFragment)
            }
            "Home"->{
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_global_homeFragment)
            }

            "Travel accessories"->{
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_global_travelFragment)
            }
        }
    }
}