package mate.academy

private const val OK_STATUS_CODE = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val response = client.sendRequest(url).also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            println("Response Content: ${it.content}")
        }
        if (response.statusCode == OK_STATUS_CODE) {
            response.also {
                println("Processing content: ${it.content}")
            }
            return ResponseData("Success", response.content)
        } else {
            return ResponseData("Failure", "Request failed with status: ${response.statusCode}")
        }
    }
}
