package apis.ore

import apis.NotFoundException
import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import io.ktor.client.*
import io.ktor.client.engine.jetty.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import java.io.File


data class OreResource(
        @Json(name = "pluginId") val id: String,
        val name: String,
        val owner: String,
        val description: String,
        @Json(name = "recommended") val file: OreFile
) {

    suspend fun download(target: File, version: Float = 0F) {
        val client = HttpClient(Jetty)
        val requestBytes: ByteArray

        requestBytes = if (version == 0F) {
            client.get("https://ore.spongepowered.org/api/v1/projects/${this.id}/versions/recommended")
        } else {
            client.get(
                    "https://ore.spongepowered.org/api/v1/projects/${this.id}/versions/$version/download"
            )
        }
        target.writeBytes(requestBytes)

        client.close()
    }

    companion object {
        suspend fun get(pluginId: String): OreResource? {
            val client = HttpClient(Jetty)

            val request: HttpResponse = client.get("https://ore.spongepowered.com/api/v1/$pluginId")
            if (request.status == HttpStatusCode.NotFound) throw NotFoundException("Could not find that plugin")

            val resource = Klaxon().parse<OreResource>(request.readText())
            return resource
        }
    }
}