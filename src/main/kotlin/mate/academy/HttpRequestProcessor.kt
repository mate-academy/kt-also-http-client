package mate.academy

const val CODE_SUCCESS = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        return client.sendRequest(url).also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            println("Response Content: ${it.content}")
        }.run {
            if (statusCode == CODE_SUCCESS) {
                 return ResponseData(statusText, content).also {
                    println("Processing content: ${it.contentSummary}")
                }
            }
            ResponseData("Failure", "Request failed with status: $statusCode")
        }
    }
}
