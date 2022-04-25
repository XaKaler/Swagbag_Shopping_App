package com.shopping.swagbag.main_activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.shopping.swagbag.R
import com.shopping.swagbag.category.*
import com.shopping.swagbag.common.ProgressDialogFragment
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.common.adapter.CategorySliderAdapter
import com.shopping.swagbag.databinding.ActivityMainBinding
import com.shopping.swagbag.databinding.MainToolbarBinding
import com.shopping.swagbag.databinding.NavigationDrawerBinding
import com.shopping.swagbag.databinding.NavigationHeaderBinding
import com.shopping.swagbag.home.HomeModel
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductViewModel
import com.shopping.swagbag.products.ProductViewModelFactory
import com.shopping.swagbag.service.RemoteDataSource
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.service.apis.CategoryApi
import com.shopping.swagbag.service.apis.ProductApi
import com.shopping.swagbag.service.apis.SettingApi
import com.shopping.swagbag.service.apis.UserApi
import com.shopping.swagbag.settings.SettingRepository
import com.shopping.swagbag.settings.SettingViewModelFactory
import com.shopping.swagbag.settings.SettingsModel
import com.shopping.swagbag.user.UserViewModelFactory
import com.shopping.swagbag.user.auth.UserRepository
import com.shopping.swagbag.user.auth.UserViewModel
import com.shopping.swagbag.user.wallet.WalletModel
import com.shopping.swagbag.utils.AppUtils
import com.shopping.swagbag.utils.SettingViewModel


class MainActivity : AppCompatActivity(), RecycleViewItemClick{

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var toolbarBinding: MainToolbarBinding
    private lateinit var navigationBinding: NavigationDrawerBinding
    private lateinit var navigationHeaderBinding: NavigationHeaderBinding
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var productViewModel: ProductViewModel
    private lateinit var settingViewModel: SettingViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var settingResult: SettingsModel
    private lateinit var walletResult: WalletModel
    private lateinit var homeResult: HomeModel
    private lateinit var masterCategories: List<MasterCategoryModel.Result>
    private lateinit var allCategories: List<CategoryModel.Result>
    private var appUtils = AppUtils(this@MainActivity)
    private lateinit var progressDialog: ProgressDialogFragment
    private var handler = Handler(Looper.getMainLooper())

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

            //disable the swipe gesture that opens the navigation drawer
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

            // click listeners
            btmNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.btmWishlist -> {
                        hideToolbar()
                        val navHostFragment =
                            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                        val navController = navHostFragment.navController

                        if(appUtils.isUserLoggedIn())
                            navController.navigate(R.id.action_global_wishlistWithProductFragment)
                        else
                            navController.navigate(R.id.action_global_signInFragment)
                    }

                    R.id.btmCart ->{
                        hideToolbar()
                        val navHostFragment =
                            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
                        val navController = navHostFragment.navController

                        if(appUtils.isUserLoggedIn())
                            navController.navigate(R.id.action_global_shoppingBegWithProductFragment)
                        else
                            navController.navigate(R.id.action_global_signInFragment)
                    }

                    R.id.btmCategory ->{
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

                        if (appUtils.isUserLoggedIn())
                            navController.navigate(R.id.action_global_profileFragment)
                        else
                            navController.navigate(R.id.action_global_signInFragment)

                    }
                }
                true
            }
        }

        setUPToolbar()

        initializeRepositories()

        setUpNavigationHeader()

        getDataFromIntent()

        apiCalls()
    }

    private fun getDataFromIntent(): HomeModel {
        val intentData = intent
        homeResult = Gson().fromJson(intentData.getStringExtra("home"), HomeModel::class.java)
        masterCategories = listOf(
            Gson().fromJson(
                intentData.getStringExtra("masterCategories"),
                MasterCategoryModel.Result::class.java
            )
        )
        return homeResult
    }

    private fun initializeRepositories() {
        // category repository
        val repository =
            CategoryRepository(RemoteDataSource().getBaseUrl().create(CategoryApi::class.java))
        categoryViewModel = ViewModelProvider(
            this@MainActivity,
            CategoryViewModelFactory(repository)
        )[CategoryViewModel::class.java]

        // setting repository
        val settingRepository =
            SettingRepository(RemoteDataSource().getBaseUrl().create(SettingApi::class.java))
        settingViewModel = ViewModelProvider(
            this@MainActivity,
            SettingViewModelFactory(settingRepository)
        )[SettingViewModel::class.java]

        // product repository
        val productRepository =
            ProductRepository(RemoteDataSource().getBaseUrl().create(ProductApi::class.java))
        productViewModel = ViewModelProvider(
            this@MainActivity,
            ProductViewModelFactory(productRepository)
        )[ProductViewModel::class.java]

        //user repository
        val userRepository =
            UserRepository(RemoteDataSource().getBaseUrl().create(UserApi::class.java))
        userViewModel = ViewModelProvider(
            this@MainActivity,
            UserViewModelFactory(userRepository)
        )[UserViewModel::class.java]

    }

    private fun apiCalls() {
        setMasterCategories()
        setAllCategories()
    }

    fun getWalletResult(): WalletModel {
        Log.e("wallet", "getWalletResult: $walletResult")
        return if (this::walletResult.isInitialized)
            walletResult
        else {
            getWallet()
            Log.e("wallet", "getWalletResult if not initialized: $walletResult")
            walletResult
        }
    }

    private fun getWallet() {
        if (appUtils.isUserLoggedIn()) {
            userViewModel.wallet(appUtils.getUserId(), "", "").observe(this) {
                when (it) {
                    is Resource.Success -> {
                        walletResult = it.value
                        Log.e("wallet", "getWallet in main activity: $walletResult")
                    }
                    is Resource.Failure -> {
                        Toast.makeText(this, "Failed to get wallet", Toast.LENGTH_SHORT).show()
                        Log.e("wallet", "getWallet error in main activity: $it")
                    }
                }
            }
        }
    }

    fun setUpNavigationHeader() {
        with(navigationHeaderBinding) {
            closeDrawer.setOnClickListener {
                closeDrawer()
            }
            if (AppUtils(this@MainActivity).isUserLoggedIn()) {
                val user = AppUtils(this@MainActivity).getUser()
                navHeaderUserName.text = user?.result?.fname
                navHeaderUserEmail.text = user?.result?.email
            }
        }
    }

    fun setMasterCategories() {
        val intentData = intent
        masterCategories =
            Gson().fromJson(
                intentData.getStringExtra("masterCategories"),
                MasterCategoryModel::class.java
            ).result

        Log.e("master", "$masterCategories\n" +
                "view binding is initialized : ${this::viewBinding.isInitialized}")

        if (this::viewBinding.isInitialized) {
            viewBinding.rvCategorySlider.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter =
                    CategorySliderAdapter(
                        this@MainActivity,
                        masterCategories,
                        this@MainActivity
                    )
            }
        }
    }

     fun getMasterCategories(): List<MasterCategoryModel.Result> {
        /*
        if (this::viewBinding.isInitialized) {
            categoryViewModel.masterCategory().observe(this, Observer {
                when (it) {
                    is Resource.Success -> {
                        masterCategories = it.value.result
                        setMasterCategories()
                    }

                    is Resource.Failure -> Toast.makeText(
                        this,
                        it.errorCode.toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                    else -> {}
                }
            })
        }*/
        return if(this::masterCategories.isInitialized)
            masterCategories
        else {
            val intentData = intent
                Gson().fromJson(
                    intentData.getStringExtra("masterCategories"),
                    MasterCategoryModel::class.java
                ).result
        }
    }

    private fun setAllCategories() {
        if (this::allCategories.isInitialized)
            setUpNavigation()
        else
            getAllCategories()
    }

    private fun getAllCategories() {
        categoryViewModel.getAllCategories().observe(this@MainActivity) {
            when (it) {
                is Resource.Success -> {
                    allCategories = it.value.result
                    setAllCategories()
                    getSettings()
                    getWallet()
                }

                is Resource.Failure ->
                    Log.e("TAG", "setUpNavigation: $it")
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
        }
    }

    private fun setUpNavigation() {
        with(navigationBinding) {
            progressBar.visibility = View.GONE
            // add navigation menu
            val navigationMenu: List<NavigationMenu> = OnNavigationMenu().getNavigationMenu()

            rvnavMenu.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = NavigationMenuAdapter(
                    this@MainActivity,
                    navigationMenu,
                    allCategories
                )
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

    fun getSettingResult(name: String): String {
        /* Log.e("TAG", "getSettingResult: $name\n" +
                 "setting result is : $settingResult", )*/

        var result: String = ""
        for (settingName in settingResult.result) {
            // Log.e("TAG", "setting name is : ${settingName.name}", )
            if (settingName.name == name) {
                result = settingName.value
            }
        }
        return result
    }

    fun getReturnReason() = settingResult.returnReason

    private fun getSettings() {
        settingViewModel.settings().observe(this) {
            when (it) {
                is Resource.Success -> {
                    settingResult = it.value
                }

                is Resource.Failure -> {
                    Log.e("settings", "getSettings: $it")
                }
            }
        }
    }

    fun getHome(): HomeModel {
        /*productViewModel.getHome().observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    stopShowingLoading()
                    homeResult = it.value
                }
                is Resource.Failure -> {
                    stopShowingLoading()
                    Log.e("home", "getHomeScreenData: $it")
                }
            }

        }*/
        return if (this::homeResult.isInitialized)
            homeResult
        else {
            val intentData = intent
            homeResult = Gson().fromJson(intentData.getStringExtra("home"), HomeModel::class.java)
            return homeResult
        }
    }

    fun showLoading() {
        val manager = this.supportFragmentManager
        progressDialog = ProgressDialogFragment.newInstance()
        progressDialog.isCancelable = false
        progressDialog.show(manager, "progress")
    }

    fun stopShowingLoading() {
        if (this::progressDialog.isInitialized) {
            progressDialog.dismiss()
        }
    }

    override fun onItemClickWithName(name: String, position: Int) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
        val navController = navHostFragment.navController
        val action = ParticularCategoryFragmentDirections.actionGlobalParticularCategoryFragment(
            masterCategories[position].id
        )
        navController.navigate(action)

    }

    override fun onItemClickWithView(name: String, view: View, position: Int) {
        super.onItemClickWithView(name, view, position)
        val rv = view as RecyclerView

    }
}