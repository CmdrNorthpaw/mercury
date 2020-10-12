package apis.ore

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import io.ktor.client.*
import io.ktor.client.engine.jetty.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

data class OreResource(
        @Json(name = "pluginId") val id: String,
        val name: String,
        val owner: String,
        val description: String,
        @Json(name = "recommended") val file: OreFile
) {
    companion object {
        suspend fun get(pluginId: String): OreResource? {
            val client = HttpClient(Jetty)

            val request: HttpResponse = client.get("https://ore.spongepowered.com/api/v1/$pluginId")
            if (request.status == HttpStatusCode.NotFound) return null

            val resource = Klaxon().parse<OreResource>(request.readText())
            return resource
        }
    }
}