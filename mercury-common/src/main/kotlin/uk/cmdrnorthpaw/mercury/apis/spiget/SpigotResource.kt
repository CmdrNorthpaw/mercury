package uk.cmdrnorthpaw.mercury.apis.spiget

import apis.NotFoundException
import apis.spiget.SpigotFile
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import com.sun.org.apache.xpath.internal.operations.Bool
import io.ktor.client.*
import io.ktor.client.engine.jetty.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


data class SpigotResource(
        val external: Boolean,
        val file: SpigotFile,
        val contributors: List<String>,
        val versions: List<Int>,
        val updates: List<Int>,

        val name: String,
        val tag: String,
        val id: Int,
        val premium: Boolean,
        val languages: List<String>
) {
    companion object {
        private val client = HttpClient(Jetty)

        suspend fun get(id: Int): SpigotResource {
            val response: HttpResponse = client.get("https://api.spiget.org/v2/resources/$id")
            if (response.status == HttpStatusCode.NotFound) throw NotFoundException("Could not find that plugin")

            val responseJson = Klaxon().parse<SpigotResource>(response.readText(Charsets.UTF_8))!!
            return responseJson
        }
    }
}
