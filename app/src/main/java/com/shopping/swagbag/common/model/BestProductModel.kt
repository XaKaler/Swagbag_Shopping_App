package com.shopping.swagbag.common.model


data class BestProductModel(
    val description: String, // Men's Wear
    val shortDesc: String?,
    val `file`: List<File>, // https://swagbag-space.fra1.digitaloceanspaces.com/1636723978732o59ec.png
    val id: String, // 618e6d0bae7db56d2c449806
    val name: String, // Men's Wear
) {
    data class File(
      /*  val acl: String, // public-read
        val bucket: String, // swagbag-space
        val contentDisposition: Any?, // null
        val contentEncoding: Any?, // null
        val contentType: String, // application/octet-stream
        val encoding: String, // 7bit
        val etag: String, // "f2c83d741aeee55d7685dc63177917d0"
        val fieldname: String, // upload
        val key: String, // 1640589488850j4byg.jpeg
        val metadata: Any?, // null
        val mimetype: String, // image/jpeg
        val originalname: String, // 4.4.jpg
        val serverSideEncryption: Any?, // null
        val size: Int, // 22661
        val storageClass: String // STANDARD,*/
        val location: String, // https://swagbag-space.fra1.digitaloceanspaces.com/1640589488850j4byg.jpeg
    )
}
