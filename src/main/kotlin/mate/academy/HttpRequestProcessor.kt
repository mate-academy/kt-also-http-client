package mate.academy

private const val OK_STATUS_CODE = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        // Send the HTTP request
        val response = client.sendRequest(url)

        // Log the response details using also()
        response.also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            println("Response Content: ${it.content}")
        }

        // Handle the response
        return if (response.statusCode == OK_STATUS_CODE) {
            response.also {
                println("Processing content: ${it.content}")
            }
            ResponseData("Success", response.content)
        } else {
            ResponseData("Failure", "Request failed with status: ${response.statusCode}")
        }
    }
}
