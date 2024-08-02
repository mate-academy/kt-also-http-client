package mate.academy

private const val OK_STATUS_CODE = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        client.sendRequest(url).also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            println("Response Content: ${it.content}")
        }.also {
            if (it.statusCode == OK_STATUS_CODE) {
                println("Processing content: ${it.content}")
                return ResponseData("Success", it.content)
            }
        return ResponseData("Failure", "Request failed with status: ${it.statusCode}")
        }
    }
}
