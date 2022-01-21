package com.shopping.swagbag

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shopping.swagbag.category.CategoryFragment
import com.shopping.swagbag.category.CategoryFragmentDirections
import com.shopping.swagbag.category.home.HomeFragmentDirections
import com.shopping.swagbag.common.RecycleItemClick
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.common.adapter.DropDownCategoryAdapter
import com.shopping.swagbag.databinding.SingleNavigationMenuBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.home.HomeDirections


class NavigationMenuAdapter(
    private val context: Context,
    private val data: List<NavigationMenu>,
    private val itemClick: RecycleItemClick
) :
    RecyclerView.Adapter<NavigationMenuAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleNavigationMenuBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        private var isCategoryShow = false

        fun bind(singleData: NavigationMenu, position: Int) {
            with(viewBinding) {
                navIcon.setImageResource(singleData.icon)
                tvNameMenu.text = singleData.menu

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

                        // set master category
                        viewBinding.masterCategory.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = DummyData().getDummyCategory()
                                ?.let { DropDownCategoryAdapter(context, it) }
                        }
                    }
                    else{
                        isCategoryShow = true
                        viewBinding.masterCategory.visibility = View.VISIBLE
                    }
                }
                "Category" -> {
                    activity.closeDrawer()
                    activity.hideToolbar()
                    navController.navigate(R.id.action_global_categoryFragment)
                }
                "Orders" -> {
                    activity.closeDrawer()
                    activity.hideToolbar()
                    navController.navigate(R.id.action_global_orderWithoutItemsFragment)
                }
                "Wishlist" -> {
                    activity.closeDrawer()
                    activity.hideToolbar()
                    navController.navigate(R.id.action_global_wishlistWithoutProductFragment)
                }
                "Address" -> {
                    activity.closeDrawer()
                    activity.hideToolbar()
                    navController.navigate(R.id.action_global_viewUserDetailsFragment)
                }
                "Coupons" -> {
                    activity.closeDrawer()
                    activity.hideToolbar()
                    navController.navigate(R.id.action_global_couponsFragment)
                }
                "Profile" -> {
                    activity.closeDrawer()
                    activity.hideToolbar()
                    navController.navigate(R.id.action_global_signInFragment)
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