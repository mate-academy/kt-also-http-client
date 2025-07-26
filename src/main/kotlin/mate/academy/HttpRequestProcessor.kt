package mate.academy

private const val OK_STATUS_CODE = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        return client.sendRequest(url).also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            println("Response Content: ${it.content}")
        }.run {
            if (statusCode == OK_STATUS_CODE) {
                return ResponseData(statusText, content).also {
                    println("Processing content: ${it.contentSummary}")
                }
            }
            ResponseData("Failure", "Request failed with status: $statusCode")
        }
    }
}
