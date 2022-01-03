package com.shopping.swagbag.common

class FreeData : ArrayList<FreeData.FreeDataItem>(){
    data class FreeDataItem(
        val albumId: Int, // 1
        val id: Int, // 1
        val thumbnailUrl: String, // https://via.placeholder.com/150/92c952
        val title: String, // accusamus beatae ad facilis cum similique qui sunt
        val url: String // https://via.placeholder.com/600/92c952
    )
}