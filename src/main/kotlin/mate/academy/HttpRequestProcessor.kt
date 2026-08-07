package mate.academy

private const val SUCCESS_STATUS = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val response = client.sendRequest(url).also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            println("Response Content: ${it.content}")
        }
        return if (response.statusCode == SUCCESS_STATUS) {
            ResponseData(response.statusText, response.content).also {
                println("Processing content: ${it.contentSummary}")
            }
        } else {
            ResponseData("Failure", "Request failed with status: ${response.statusCode}")
        }
    }
}
