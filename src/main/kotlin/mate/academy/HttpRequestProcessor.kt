package mate.academy

const val STATUS_OK = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val res = client.sendRequest(url).also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            println("Response Content: ${it.content}")
        }

        return if (res.statusCode == STATUS_OK ) {
            ResponseData("Success", res.content).also {
                println("Processing content: ${res.content}")
            }
        } else ResponseData("Failure",
            "Request failed with status: ${res.statusCode}")
    }
}
