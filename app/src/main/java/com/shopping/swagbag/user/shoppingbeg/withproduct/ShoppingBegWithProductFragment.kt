package com.shopping.swagbag.user.shoppingbeg.withproduct

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentShoppingBegWithProductBinding
import com.shopping.swagbag.databinding.ToolbarWithTwoMenusDeleteAndWishlistBinding
import com.shopping.swagbag.products.ProductApi
import com.shopping.swagbag.products.ProductRepository
import com.shopping.swagbag.products.ProductViewModel
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.utils.AppUtils

class ShoppingBegWithProductFragment : BaseFragment<
        FragmentShoppingBegWithProductBinding,
        ProductViewModel,
        ProductRepository>(FragmentShoppingBegWithProductBinding::inflate),
    View.OnClickListener,
    RecycleViewItemClick {

    private lateinit var toolbarBinding: ToolbarWithTwoMenusDeleteAndWishlistBinding
    private lateinit var product: GetCartModel
    private lateinit var appUtils: AppUtils
    private lateinit var shoppingBegProductAdapter: ShoppingBegProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include

        //when user click on shop now button in shopping beg without product layout
        viewBinding.btnShopNow.setOnClickListener {
            findNavController().navigate(R.id.action_global_home2)
        }

        initViews()
    }

    private fun initViews() {
        toolbar()

        getCart()

        handleOnClick()
    }

    private fun getCart() {
        appUtils = context?.let { AppUtils(it) }!!
        val userId = appUtils.getUserId()

        viewModel.getCart(userId).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    stopShowingLoading()

                    val productCount = it.value.result.size
                    Log.e("TAG", "getCart: $productCount", )

                    if (productCount == 0) {
                        showEmptyCart()
                    } else {
                        product = it.value
                        setItems(it.value.result)
                    }
                }

                is Resource.Failure -> Log.e("TAG", "getCart: $it")
            }
        }

        //viewModel.getCart()
    }

    private fun handleOnClick() {
        with(viewBinding) {
            placeOrder.setOnClickListener(this@ShoppingBegWithProductFragment)
        }
    }

    private fun setItems(product: List<GetCartModel.Result>) {
        with(viewBinding) {
            rvShoppingBegItems.apply {
                layoutManager = LinearLayoutManager(context)
                shoppingBegProductAdapter =
                    ShoppingBegProductAdapter(context, product, this@ShoppingBegWithProductFragment)
                adapter = shoppingBegProductAdapter
            }
        }
    }

    /*[{"user":"61792ee606e68836727c77f1",
        "product":{"combo_products":null,
            "name":"New Clothing Material",
            "master_category":"615ae18463d6a6435a183dcc",
            "category":"61bb805e66538a1c1f366787",
            "sub_category":"61bb810866538a1c1f3667c6",
            "cuisine":null,"brand":"6148c87ee40e5da07a1d9eed",
            "vendor":"618f661be5821a22644f6bd1",
            "desc":"",
            "short_desc":"PARTY WEAR\n\nVOL - 16\n\nDESIGN SERIES : 152\n\nDESIGN NO : 1005\n\nPRODUCT FULL DESCRIPTION ATTACHED IN SEPERATE IMAGE\n\nWEIGHT : 2.5 KG",
            "file":[{"fieldname":"upload","originalname":"product.webp","encoding":"7bit","mimetype":"image/webp","size":3396,"bucket":"swagbag-space","key":"1639933852943fimom.webp","acl":"public-read","contentType":"application/octet-stream","contentDisposition":null,"storageClass":"STANDARD","serverSideEncryption":null,"metadata":null,"location":"https://swagbag-space.fra1.digitaloceanspaces.com/1639933852943fimom.webp","etag":"\"92034dbbef5a465219733d4d5c0f94a1\"","versionId":null}],
            "price":680,
            "selling_price":440,
            "discounted_price":null,
            "batchno":"232323",
            "commission":"0",
            "packaging_charge":"40",
            "tax_status":"",
            "cgst":"",
            "sgst":"","igst":"","sku":"IT-3699","stock_qty":"12","backorders":"","threshold":"","manage_stock":0,"weight":"","length":"","width":"","height":"","attribute":"","tags":"","start_date":"2021-10-18T00:00:00.000Z","end_date":"2021-10-18T00:00:00.000Z","created_date":"2021-10-18T17:50:41.889Z","update_date":"2021-10-18T17:50:41.889Z","active":1,"deal":0,"featured":0,"deleted":0,"express":false,"added_by":"5fe463f5a9e14206002dd63e","point":0,
            "options":[{"name":"Size","value":"L:20:A1423:10, XL:10:B1423:5"},{"name":"Color","value":"#d41344:500:AB22C:20, #191344:1000:C22DF:20, #4dd453:0:X33YZ:10"}],
            "shelving_location":"","slug_history":["new-clothing-material"],"_id":"6177ce5132fc966bab32b48c","point_exp_date":"2021-10-26T00:00:00.000Z","slug":"new-clothing-material2","__v":0},
            "option":[{"key":"Size","value":" XL","price":"10"},{"key":"Color","value":" #4dd453","price":"0"}],
            "quantity":1,
            "price":440,
            "created_date":"2022-01-05T14:54:26.681Z",
            "update_date":"2022-01-05T14:54:26.681Z",
            "_id":"61e13624b610527e7dc29736",
            "name":"New Clothing Material",
            "image":"https://swagbag-space.fra1.digitaloceanspaces.com/1639933852943fimom.webp","__v":0}]*/

    private fun toolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.shopping_beg)

            //click listeners
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }

            imgWishlist.setOnClickListener {
                findNavController().navigate(R.id.action_shoppingBegWithProductFragment_to_wishlistWithProductFragment)
            }

            delete.setOnClickListener {
                clearCart()
            }
        }
    }

    private fun clearCart() {
        val userId = appUtils.getUserId()
        viewModel.clearCart(userId).observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    stopShowingLoading()

                    toast(it.value.message)

                    //send user to empty cart
                    showEmptyCart()
                }

                is Resource.Failure -> Log.e("cart", "clearCart: $it")
            }
        })
    }

    private fun showEmptyCart(){
        viewBinding.lytWithoutProduct.visibility = View.VISIBLE
        viewBinding.lytWithProduct.visibility = View.GONE

        toolbarBinding.delete.visibility = View.GONE
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.placeOrder -> findNavController().navigate(R.id.action_shoppingBegWithProductFragment_to_shoppingBegSelectAddressFragment)
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentShoppingBegWithProductBinding.inflate(inflater, container, false)

    override fun getViewModel() = ProductViewModel::class.java

    override fun getFragmentRepository() =
        ProductRepository(remoteDataSource.getBaseUrl().create(ProductApi::class.java))

    override fun onItemClickWithName(name: String, position: Int) {
        val productId = product.result[position].product.id
        val userId = appUtils.getUserId()

        viewModel.deleteSingleWish(productId, userId).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    stopShowingLoading()

                    toast(it.value.message)

                    //update list
                    val productList: MutableList<GetCartModel.Result> =
                        product.result as MutableList

                    productList.removeAt(position)
                    shoppingBegProductAdapter.updateList(productList)
                    //if size is 0 then show user to empty cart
                    if (position == 0) {
                        showEmptyCart()
                    }
                }

                is Resource.Failure -> Log.e("TAG", "onItemClickWithName: $it",)
            }
        }
    }
}