package mate.academy

const val HTTP_OK = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val response = client.sendRequest(url).also {
           println("Response Status: ${it.statusCode} - ${it.statusText}")
           println("Response Content: ${it.content}")
        }
        return when (response.statusCode) {
            HTTP_OK -> {
                response.also { println("Processing content: ${it.content}") }
                ResponseData("Success", response.content)
            }
            else -> ResponseData("Failure", "Request failed with status: ${response.statusCode}")
        }
    }
}

