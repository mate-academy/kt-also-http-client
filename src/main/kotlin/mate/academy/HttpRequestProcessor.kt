package mate.academy

private const val OK_STATUS_CODE = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val response = client.sendRequest(url)
            .also { resp ->
                println("Response Status: ${resp.statusCode} - ${resp.statusText}")
                println("Response Content: ${resp.content}")
            }

        return if (response.statusCode == OK_STATUS_CODE) {
            ResponseData("Success", response.content.also { println("Processing content: $it") })
        } else {
            ResponseData("Failure", "Request failed with status: ${response.statusCode}")
        }
    }
}
