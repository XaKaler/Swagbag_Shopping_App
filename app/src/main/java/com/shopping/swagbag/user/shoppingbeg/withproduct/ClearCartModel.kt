package com.shopping.swagbag.user.shoppingbeg.withproduct


import com.google.gson.annotations.SerializedName

data class ClearCartModel(
    val message: String, // Cart cleared successfully
    val result: Result,
    val status: String // success
) {
    data class Result(
        @SerializedName("clusterTime")
        val clusterTime: ClusterTime,
        val deletedCount: Int, // 3
        val electionId: String, // 7fffffff0000000000000007
        val n: Int, // 3
        val ok: Int, // 1
        val opTime: OpTime,
        val operationTime: String // 7066395991439572995
    ) {
        data class ClusterTime(
            val clusterTime: String, // 7066395991439572995
            val signature: Signature
        ) {
            data class Signature(
                val hash: String, // iseBm1wHyYIsh4Qndpjz6BJLogw=
                val keyId: String // 7047888470554968068
            )
        }

        data class OpTime(
            val t: Int, // 7
            val ts: String // 7066395991439572995
        )
    }
}