package mate.academy

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        return client.sendRequest(url).also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            println("Response Content: ${it.content}")
        }.let {
            if (it.statusCode == OK_STATUS_CODE) {
                it.also {
                    println("Processing content: ${it.content}")
                }
                ResponseData("Success", it.content)
            } else {
                ResponseData("Failure", "Request failed with status: ${it.statusCode}")
            }
        }
    }
}
