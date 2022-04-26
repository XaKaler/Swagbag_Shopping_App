 package com.shopping.swagbag.main_activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shopping.swagbag.R
import com.shopping.swagbag.category.CategoryModel
import com.shopping.swagbag.common.adapter.DropDownCategoryAdapter
import com.shopping.swagbag.databinding.SingleNavigationMenuBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.utils.AppUtils


class NavigationMenuAdapter (
    private val context: Context,
    private val data: List<NavigationMenu>,
    private val category: List<CategoryModel.Result>
) :
    RecyclerView.Adapter<NavigationMenuAdapter.MyViewHolder>() {

    private var appUtils = AppUtils(context)
    private var currentDrawerItem = ""

    inner class MyViewHolder(private val viewBinding: SingleNavigationMenuBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        private var isCategoryShow = false

        fun bind(singleData: NavigationMenu, position: Int) {
            with(viewBinding) {
                navIcon.setImageResource(singleData.icon)
                tvNameMenu.text = singleData.menu

                // set master category
                viewBinding.masterCategory.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter =  DropDownCategoryAdapter(context, category)
                }

                itemView.setOnClickListener {
                   // itemClick.onItemClickWithView(position, viewBinding.masterCategory)
                    handleNavigationMenuClick(singleData.menu)
                }
            }
        }

        private fun handleNavigationMenuClick(menu: String) {

            val activity = context as MainActivity

            val navHostFragment =
                activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
            val navController = navHostFragment.navController

            when(menu){
                "Home" -> {
                    activity.closeDrawer()
                    navController.navigate(R.id.action_global_home2)
                }
                "Master Categories" -> {
                    if(isCategoryShow){
                        isCategoryShow = false
                        viewBinding.masterCategory.visibility = View.GONE
                    }
                    else{
                        isCategoryShow = true
                        viewBinding.masterCategory.visibility = View.VISIBLE
                    }
                }
                "Orders" -> {
                    activity.closeDrawer()
                    activity.hideToolbar()
                    if (appUtils.isUserLoggedIn())
                        navController.navigate(R.id.action_global_orderWithItemsFragment)
                    else
                        navController.navigate(R.id.action_global_signInFragment)
                }
                "Address" -> {
                    activity.closeDrawer()
                    activity.hideToolbar()
                    if (appUtils.isUserLoggedIn())
                        navController.navigate(R.id.action_global_viewUserDetailsFragment)
                     else
                        navController.navigate(R.id.action_global_signInFragment)
                }
                "Coupons" -> {
                    activity.closeDrawer()
                    activity.hideToolbar()
                    navController.navigate(R.id.action_global_couponsFragment)
                }
                "Help Center" -> {
                    activity.closeDrawer()
                    activity.hideToolbar()
                    navController.navigate(R.id.action_global_helpCenterWithoutOrder)
                }
                "FAQs" -> {
                    activity.closeDrawer()
                    activity.hideToolbar()
                    navController.navigate(R.id.action_global_FAQsFragment)
                }
                "About Us" -> {
                    activity.closeDrawer()
                    activity.hideToolbar()
                    navController.navigate(R.id.action_global_aboutUs)
                }
                "Contact Us" -> {
                    activity.closeDrawer()
                    activity.hideToolbar()
                    navController.navigate(R.id.action_global_contactUsFragment)
                }
                "Terms Of Use" -> {
                    activity.closeDrawer()
                    activity.hideToolbar()
                    navController.navigate(R.id.action_global_termsOfUsesFragment)
                }
                "Privacy Policy" -> {
                    activity.closeDrawer()
                    activity.hideToolbar()
                    navController.navigate(R.id.action_global_privacyPolicyFragment)
                }
                "Log Out" -> {
                    activity.closeDrawer()
                    activity.hideToolbar()
                    AppUtils(context).logOut()
                    navController.navigate(R.id.action_global_home2)
                }
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SingleNavigationMenuBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount() = data.size
}