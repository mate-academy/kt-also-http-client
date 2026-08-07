package mate.academy

private const val OK_STATUS = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val sendRequest = client.sendRequest(url).also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            println("Response Content: ${it.content}")
        }
        return if (sendRequest.statusCode == OK_STATUS) {
            ResponseData("Success", sendRequest.content).also {
                println("Processing content: ${sendRequest.content}")
            }
        } else {
            ResponseData("Failure", "Request failed with status: ${sendRequest.statusCode}").also {
                println("Request failed with status: ${sendRequest.statusCode} ")
            }
        }
    }
}
