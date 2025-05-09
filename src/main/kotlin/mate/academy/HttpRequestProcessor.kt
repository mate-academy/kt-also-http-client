package mate.academy

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        return client.sendRequest(url)
            .also {
                println("Response Status: ${it.statusCode} - ${it.statusText}")
                println("Response Content: ${it.content}")
            }
            .let { response ->
                if (response.statusCode == OK_STATUS_CODE) {
                    println("Processing content: ${response.content}")
                    ResponseData("Success", response.content)
                } else {
                    ResponseData("Failure", "Request failed with status: ${response.statusCode}")
                }
            }
    }
}
