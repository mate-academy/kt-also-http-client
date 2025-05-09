package mate.academy

private const val SUCCESS_STATUS = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val sendRequest = client.sendRequest(url).also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            println("Response Content: ${it.content}")
        }
        return if (sendRequest.statusCode == SUCCESS_STATUS) {
            ResponseData(status = "Success", contentSummary = sendRequest.content.also {
               println("Processing content: $it")
            })
        } else {
            ResponseData(status = "Failure", contentSummary = "Request failed with status: ${sendRequest.statusCode}")
        }

    }
}
