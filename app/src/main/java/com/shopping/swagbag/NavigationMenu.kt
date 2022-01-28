package com.shopping.swagbag

data class NavigationMenu(val icon: Int, val menu: String)

class OnNavigationMenu{

    private val navigationMenu = ArrayList<NavigationMenu>()

    fun getNavigationMenu(): List<NavigationMenu>{

        navigationMenu.add(NavigationMenu(R.drawable.ic_person, "Profile"))
        navigationMenu.add(NavigationMenu(R.drawable.ic_hoem_png_to_svg, "Home"))
        navigationMenu.add(NavigationMenu(R.drawable.ic_outline_category_24, "Category"))
        navigationMenu.add(NavigationMenu(R.drawable.ic_outline_category_24, "Master Categories"))
        navigationMenu.add(NavigationMenu(R.drawable.ic_shopping_bag, "Orders"))
        navigationMenu.add(NavigationMenu(R.drawable.ic_heart, "Wishlist"))
        navigationMenu.add(NavigationMenu(R.drawable.ic_location, "Address"))
        navigationMenu.add(NavigationMenu(R.drawable.ic_coupons, "Coupons"))
        navigationMenu.add(NavigationMenu(R.drawable.ic_help_center, "Help Center"))
        navigationMenu.add(NavigationMenu(R.drawable.ic_question_answer, "FAQs"))
        navigationMenu.add(NavigationMenu(R.drawable.ic_info, "About Us"))
        navigationMenu.add(NavigationMenu(R.drawable.ic_contact_phone, "Contact Us"))
        navigationMenu.add(NavigationMenu(R.drawable.ic_terms, "Terms Of Use"))
        navigationMenu.add(NavigationMenu(R.drawable.ic_outline_privacy_tip_24, "Privacy Policy"))
        navigationMenu.add(NavigationMenu(R.drawable.ic_log_out, "Log Out"))

        return navigationMenu
    }
}
