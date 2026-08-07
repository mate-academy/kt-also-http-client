package mate.academy

class HttpRequestProcessor(private val client: HttpClient) {
    companion object {
        private const val HTTP_OK = 200
    }

    fun processRequest(url: String): ResponseData {
        val response = client.sendRequest(url)

        // Log response status and content
        response.also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            println("Response Content: ${it.content}")
        }

        return if (response.statusCode == HTTP_OK) {
            // Log processing content
            response.also {
                println("Processing content: ${it.content}")
            }
            ResponseData("Success", response.content)
        } else {
            ResponseData("Failure", "Request failed with status: ${response.statusCode}")
        }
    }
}
