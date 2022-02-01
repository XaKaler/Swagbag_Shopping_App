package com.shopping.swagbag.dummy

import android.webkit.WebStorage
import com.shopping.swagbag.R

class DummyData {

    var data = ArrayList<DummyModel>()
    private val imageData = ArrayList<SingleProductImageModel>()
    private val userAddress = ArrayList<UserAddress>()
    private val productFilter = ArrayList<ProductFilter>()
    private val categorySideNavigationDataData = ArrayList<CategorySideNavigationData>()
    private val slider = ArrayList<DummySlider>()
    private val child = ArrayList<DummyChild>()
    private val imageResource = ArrayList<Int>()
    private val topTrending = ArrayList<TopTrending>()

    var categoryData = ArrayList<DummyCategoryModel>()

    fun getSingleImage() = "https://swagbag-space.fra1.digitaloceanspaces.com/1640078486875ltnsd.png"

    fun getDummySlider(): ArrayList<DummySlider>{/*
        slider.add(DummySlider("https://m.media-amazon.com/images/I/51b379TtURL._SX3000_.jpg"))
        slider.add(DummySlider("https://m.media-amazon.com/images/I/91t1svzO1HL._SX3000_.jpg"))
        slider.add(DummySlider("https://m.media-amazon.com/images/I/81e4xWrgrAL._SX3000_.jpg"))
        slider.add(DummySlider("https://m.media-amazon.com/images/I/71MwDPWV9XL._SX3000_.jpg"))
        slider.add(DummySlider("https://m.media-amazon.com/images/I/61ZsnODtl1L._SX3000_.jpg"))*/

        slider.add(DummySlider("https://swagbag-space.fra1.digitaloceanspaces.com/1639983624004mlj8x.png"))
        slider.add(DummySlider("https://swagbag-space.fra1.digitaloceanspaces.com/1639984903137b3e4g.png"))
        slider.add(DummySlider("https://swagbag-space.fra1.digitaloceanspaces.com/1640088785097rpoaa.webp"))
        slider.add(DummySlider("https://swagbag-space.fra1.digitaloceanspaces.com/1640079281564elvys.webp"))
        slider.add(DummySlider("https://swagbag-space.fra1.digitaloceanspaces.com/1640110627442mbi30.png"))

        return slider
    }

    fun getTopTrending(): ArrayList<TopTrending>{
        topTrending.add(TopTrending(R.drawable.baby))
        topTrending.add(TopTrending(R.drawable.carpet))
        topTrending.add(TopTrending(R.drawable.dogi))
        topTrending.add(TopTrending(R.drawable.glass))
        topTrending.add(TopTrending(R.drawable.hair))
        topTrending.add(TopTrending(R.drawable.nightsuit))
        return topTrending
    }

    fun getImageResource(): ArrayList<Int>{
        imageResource.add(R.drawable.baby)
        imageResource.add(R.drawable.carpet)
        imageResource.add(R.drawable.dogi)
        imageResource.add(R.drawable.glass)
        imageResource.add(R.drawable.hair)
        imageResource.add(R.drawable.nightsuit)
        return imageResource
    }



    fun getDummyData(): ArrayList<DummyModel>? {

       //  var data: ArrayList<DummyModel>? = null

        data?.add(DummyModel("https://swagbag-space.fra1.digitaloceanspaces.com/1640078486875ltnsd.png",
            "Body Oil",
            "boAt Bassheads 900 Wired On Ear Headphones with Mic (Carbon Black)"))

        data?.add(DummyModel("https://swagbag-space.fra1.digitaloceanspaces.com/16400808763030826g.png",
            "Eye Care",
            "Material: Cotton Slim fit NehruMandarin collarLong sleeveegular machine washCountry of WebStorage.Origin: India"))

        data?.add(DummyModel("https://swagbag-space.fra1.digitaloceanspaces.com/1640107615560ijcjl.png",
            "Travel Bags",
            "SATYAM WEAVES Women Cotton Silk Un-Stitched Dress Material"))


        data?.add(DummyModel("https://swagbag-space.fra1.digitaloceanspaces.com/1640110627442mbi30.png",
            "Kitchen Accessories",
            "NEW GEN Boy's Synthetic Kurta Pyjama Set"))


        data?.add(DummyModel("https://swagbag-space.fra1.digitaloceanspaces.com/16400871862161n86y.png",
            "Face Cream",
            "Desidiya ® 20 Photo Clip LED String Wedge Light (Warm White)"))

        data?.add(DummyModel("https://swagbag-space.fra1.digitaloceanspaces.com/1640078486875ltnsd.png",
            "Body Oil",
            "boAt Bassheads 900 Wired On Ear Headphones with Mic (Carbon Black)"))


/*
  data?.add(DummyModel("https://m.media-amazon.com/images/I/61xeIT6UHrL._SL1500_.jpg",
            "Headphone",
            "boAt Bassheads 900 Wired On Ear Headphones with Mic (Carbon Black)"))

        data?.add(DummyModel("https://m.media-amazon.com/images/I/51v8UlSQfBL._UL1100_.jpg",
            "Shirt",
            "Material: Cotton Slim fit NehruMandarin collarLong sleeveegular machine washCountry of WebStorage.Origin: India"))

        data?.add(DummyModel("https://m.media-amazon.com/images/I/91wsvvvb1aL._UL1500_.jpg",
            "Stitched Dress",
            "SATYAM WEAVES Women Cotton Silk Un-Stitched Dress Material"))


        data?.add(DummyModel("https://m.media-amazon.com/images/I/713lHoz1KOL._UL1500_.jpg",
            "Kurta Pyjama",
            "NEW GEN Boy's Synthetic Kurta Pyjama Set"))


        data?.add(DummyModel("https://m.media-amazon.com/images/I/51wyuKZvXjL._SL1000_.jpg",
            "LED String Wedge Light ",
            "Desidiya ® 20 Photo Clip LED String Wedge Light (Warm White)"))


*/


     /*   data?.add(DummyModel("",
            "",
            ""))*/


        return data
    }

    fun getTwoDummyData(): ArrayList<DummyModel>? {

       //  var data: ArrayList<DummyModel>? = null

        data.add(DummyModel("https://swagbag-space.fra1.digitaloceanspaces.com/1640088405627s1yvv.png",
            "Handbag",
            "boAt Bassheads 900 Wired On Ear Headphones with Mic (Carbon Black)"))

        data.add(DummyModel("https://swagbag-space.fra1.digitaloceanspaces.com/1640087496709oqdod.png",
            "Yoga Shoes",
            "Material: Cotton Slim fit NehruMandarin collarLong sleeveegular machine washCountry of WebStorage.Origin: India"))
/*

        data?.add(DummyModel("https://m.media-amazon.com/images/I/61xeIT6UHrL._SL1500_.jpg",
            "Headphone",
            "boAt Bassheads 900 Wired On Ear Headphones with Mic (Carbon Black)"))

        data?.add(DummyModel("https://m.media-amazon.com/images/I/51v8UlSQfBL._UL1100_.jpg",
            "Shirt",
            "Material: Cotton Slim fit NehruMandarin collarLong sleeveegular machine washCountry of WebStorage.Origin: India"))
*/

      /*  data?.add(DummyModel("https://m.media-amazon.com/images/I/91wsvvvb1aL._UL1500_.jpg",
            "Stitched Dress",
            "SATYAM WEAVES Women Cotton Silk Un-Stitched Dress Material"))


        data?.add(DummyModel("https://m.media-amazon.com/images/I/713lHoz1KOL._UL1500_.jpg",
            "Kurta Pyjama",
            "NEW GEN Boy's Synthetic Kurta Pyjama Set"))


        data?.add(DummyModel("https://m.media-amazon.com/images/I/51wyuKZvXjL._SL1000_.jpg",
            "LED String Wedge Light ",
            "Desidiya ® 20 Photo Clip LED String Wedge Light (Warm White)"))*/




     /*   data?.add(DummyModel("",
            "",
            ""))*/


        return data
    }

    fun getDummyChild(): ArrayList<DummyChild>{
        child.add(DummyChild(R.drawable.child_cloth1, "Value Packs"))
        child.add(DummyChild(R.drawable.bear_dress, "Cartoon Picks"))
        child.add(DummyChild(R.drawable.pngwing, "Indian"))
        child.add(DummyChild(R.drawable.boy_cloth, "Cartoon Picks"))
        child.add(DummyChild(R.drawable.girl_boy, "Sports Packs"))
        child.add(DummyChild(R.drawable.pngwing, "Top Wear"))
        return child
    }

    fun getDummyTravel(): ArrayList<DummyChild>{
        child.add(DummyChild(R.drawable.hand_bag, "Value Packs"))
        child.add(DummyChild(R.drawable.tripod, "Cartoon Picks"))
        child.add(DummyChild(R.drawable.hand_watch, "Indian"))
        child.add(DummyChild(R.drawable.camera, "Cartoon Picks"))
        child.add(DummyChild(R.drawable.alexa, "Sports Packs"))
        child.add(DummyChild(R.drawable.sunglass, "Top Wear"))
        child.add(DummyChild(R.drawable.tripod, "Top Wear"))
        child.add(DummyChild(R.drawable.carri_bag, "Top Wear"))
        return child
    }

    fun getDummyCategory(): ArrayList<DummyModel>?{

        //var data: ArrayList<DummyModel>? = null

        data?.add(DummyModel("https://images.unsplash.com/photo-1584043720379-b56cd9199c94?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mjh8fG1lbnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60",
            "Men",
            ""))


        data?.add(DummyModel("https://images.unsplash.com/photo-1588516903720-8ceb67f9ef84?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTZ8fHdvbWVufGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60",
            "Women",
            ""))


        //https://images.unsplash.com/photo-1552873816-636e43209957?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1031&q=80
        data?.add(DummyModel("https://images.unsplash.com/photo-1612196182754-dae1ec49efc2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mjh8fGtpZHMlMjBmYXNoaW9ufGVufDB8MXwwfHw%3D&auto=format&fit=crop&w=500&q=60",
            "Kids",
            ""))

        //https://images.unsplash.com/photo-1583337130417-3346a1be7dee?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=464&q=80
        data?.add(DummyModel("https://swagbag-space.fra1.digitaloceanspaces.com/1639942660913aapgl.png",
            "Pets",
            ""))


        //https://images.unsplash.com/photo-1512917774080-9991f1c4c750?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80
        data?.add(DummyModel("https://images.unsplash.com/photo-1532323544230-7191fd51bc1b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjF8fGhvbWV8ZW58MHwxfDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60",
            "Home",
            ""))

        //https://images.unsplash.com/photo-1501785888041-af3ef285b470?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80
        data?.add(DummyModel("https://images.unsplash.com/photo-1523906834658-6e24ef2386f9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8dHJhdmVsfGVufDB8MXwwfHw%3D&auto=format&fit=crop&w=500&q=60",
            "Travel",
            ""))



        return data
    }

    fun getCategory(): ArrayList<DummyCategoryModel>?{

        //var data: ArrayList<DummyModel>? = null

        categoryData?.add(DummyCategoryModel(
            R.drawable.mens,
            "Men",
            "Men's category",
        R.drawable.category_background))


        categoryData?.add(DummyCategoryModel(R.drawable.categroy_woman,
            "Women",
            "Women category",
            R.drawable.rec1))


       categoryData?.add(DummyCategoryModel(R.drawable.kids,
            "Kids",
            "Kids category",
           R.drawable.rec2))


        categoryData?.add(DummyCategoryModel(R.drawable.pets,
            "Pets",
            "Pets category",
            R.drawable.rec3))


        categoryData?.add(DummyCategoryModel(R.drawable.travel,
            "Travel",
            "Travel category",
            R.drawable.rec4))


        categoryData?.add(DummyCategoryModel(R.drawable.home,
            "Home",
            "Home category",
            R.drawable.rec5))

        return categoryData
    }

    fun getSingleProduct() = SingleProductModel("https://m.media-amazon.com/images/I/81kJzDOwjNL._UL1500_.jpg",
    "https://m.media-amazon.com/images/I/81ZXaqC4f1L._UL1500_.jpg",
    "https://m.media-amazon.com/images/I/81m-3UafI2L._UL1500_.jpg",
    "https://m.media-amazon.com/images/I/61+FErGT8lL._UL1500_.jpg",
    "https://m.media-amazon.com/images/I/71h9eVsrr4L._UL1500_.jpg",
    "https://m.media-amazon.com/images/I/61HoSvx1gIL._UL1500_.jpg",
    "Inkast Denim Co. Men's Slim Fit Casual Shirt")

    fun getSingleProductImage() : ArrayList<SingleProductImageModel>{
       imageData.add(SingleProductImageModel("https://m.media-amazon.com/images/I/81kJzDOwjNL._UL1500_.jpg",))
       imageData.add(SingleProductImageModel("https://m.media-amazon.com/images/I/81ZXaqC4f1L._UL1500_.jpg",))
       imageData.add(SingleProductImageModel("https://m.media-amazon.com/images/I/81m-3UafI2L._UL1500_.jpg",))
       imageData.add(SingleProductImageModel("https://m.media-amazon.com/images/I/61+FErGT8lL._UL1500_.jpg",))
       imageData.add(SingleProductImageModel("https://m.media-amazon.com/images/I/71h9eVsrr4L._UL1500_.jpg",))
       imageData.add(SingleProductImageModel("https://m.media-amazon.com/images/I/61HoSvx1gIL._UL1500_.jpg",))

        return imageData
    }

    fun getUser(): User{
        return User("Xa Kaler",
            "House no. 143,\nHousing board, Jhunjhunu\nRajasthan\nPIN: 333001\n\n",
            "9876543210",
            "https://images.unsplash.com/photo-1492681290082-e932832941e6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8cGVyc29ufGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60")
    }

    fun getUserAddress(): List<UserAddress>{
        userAddress.add(
            UserAddress("402 - Swarna Jayanti Sadan Deluxe,\n Dr. B.D. Marg, New Delhi 110001\n\n" +
                    "Mobile 9013181677, 9868181280")
        )
        userAddress.add(
            UserAddress("4, Ferozeshah Road, New Delhi\n\n" +
                    " 23314404, 21410133 Mob.:9013181530")
        )

        return userAddress
    }

    fun getCategorySideNavigationData(): List<CategorySideNavigationData>{
        categorySideNavigationDataData.add(CategorySideNavigationData("Clothing"))
        categorySideNavigationDataData.add(CategorySideNavigationData("Shoes"))
        categorySideNavigationDataData.add(CategorySideNavigationData("Beg"))
        categorySideNavigationDataData.add(CategorySideNavigationData("Accessories"))
        categorySideNavigationDataData.add(CategorySideNavigationData("Jewellery"))

        return categorySideNavigationDataData
    }

    fun getProductFilter(): List<ProductFilter>{
        productFilter.add(ProductFilter("Size"))
        productFilter.add(ProductFilter("Color"))
        productFilter.add(ProductFilter("Brand"))
        productFilter.add(ProductFilter("Category"))
        productFilter.add(ProductFilter("Price Range"))

        return productFilter
    }


    fun getProductSize(): List<ProductFilter>{
        productFilter.add(ProductFilter("S"))
        productFilter.add(ProductFilter("M"))
        productFilter.add(ProductFilter("L"))
        productFilter.add(ProductFilter("XL"))
        productFilter.add(ProductFilter("XXL"))

        return productFilter
    }


    fun getWishListWithItemCategory(): List<UserAddress>{
        userAddress.add(
            UserAddress("All")
        )
        userAddress.add(
            UserAddress("Footwear")
        )
        userAddress.add(
            UserAddress("Jeans")
        )
        userAddress.add(
            UserAddress("Shirt")
        )
        userAddress.add(
            UserAddress("Shoes")
        )
        userAddress.add(
            UserAddress("Sunglasses")
        )



        return userAddress
    }


}