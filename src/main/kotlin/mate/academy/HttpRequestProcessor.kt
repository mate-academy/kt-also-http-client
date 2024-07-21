package mate.academy

private const val SUCCESS_STATUS = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val (statusCode, _, content) = client.sendRequest(url)
            .also {
                println("Response Status: ${it.statusCode} - ${it.statusText}")
                println("Response Content: ${it.content}")
            }
            .also {
                if (it.statusCode == SUCCESS_STATUS) {
                    println("Processing content: ${it.content}")
                }
            }
        return when (statusCode) {
            SUCCESS_STATUS -> ResponseData("Success", content)
            else -> ResponseData("Failure", "Request failed with status: $statusCode")
        }
    }
}
