package apis.ore

import com.beust.klaxon.Json

data class OreFile(
    @Json(name = "id") val recommendedId: Int,
    val name: String,
    val dependencies: List<OreDependency>,
    val pluginId: String,
    val fileSize: Int,
    val md5: String,
    @Json(name = "staffApproved") val approved: Boolean,
    val href: String

) {
    val version = href.split("/").last().toFloat()
}

data class OreDependency(
        @Json(name = "pluginId") val id: String,
        val version: String
)
