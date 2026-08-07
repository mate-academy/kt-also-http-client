package mate.academy

const val STATUS_OK = 200
class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val request = client.sendRequest(url)
            .also {
                println("Response Status: ${it.statusCode} - ${it.statusText}")
                println("Response Content: ${it.content}")
            }

        return if (request.statusCode == STATUS_OK) {
            ResponseData("Success", request.content.also { println("Processing content: $it") })
        } else {
            ResponseData("Failure", "Request failed with status: ${request.statusCode}")
        }
    }
}
