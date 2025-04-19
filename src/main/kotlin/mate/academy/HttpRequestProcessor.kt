package mate.academy

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        return client.sendRequest(url).also { response ->
            println("Response Status: ${response.statusCode} - ${response.statusText}")
            println("Response Content: ${response.content}")
        }.let { response ->
            if (response.statusCode == 200) {
                response.also {
                    println("Processing content: ${it.content}")
                }
                ResponseData("Success", response.content)
            } else {
                ResponseData("Failure", "Request failed with status: ${response.statusCode}")
            }
        }
    }
}
