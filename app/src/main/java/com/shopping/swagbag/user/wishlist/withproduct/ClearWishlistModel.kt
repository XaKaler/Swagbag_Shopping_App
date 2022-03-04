package com.shopping.swagbag.user.wishlist.withproduct


import com.google.gson.annotations.SerializedName

data class ClearWishlistModel(
    val message: String, // Wish cleared successfully
    val result: Result,
    val status: String // success
) {
    data class Result(
        @SerializedName("clusterTime")
        val clusterTime: ClusterTime,
        val deletedCount: Int, // 1
        val electionId: String, // 7fffffff0000000000000007
        val n: Int, // 1
        val ok: Int, // 1
        val opTime: OpTime,
        val operationTime: String // 7070812055338483713
    ) {
        data class ClusterTime(
            val clusterTime: String, // 7070812055338483713
            val signature: Signature
        ) {
            data class Signature(
                val hash: String, // bUJLlUFVJ/vo4uxLL/Pdyzpt22Y=
                val keyId: String // 7047888470554968068
            )
        }

        data class OpTime(
            val t: Int, // 7
            val ts: String // 7070812055338483713
        )
    }
}