package mate.academy

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val response = client.sendRequest(url).also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            println("Response Content: ${it.content}")
        }

        return if (response.statusCode == 200) {
            response.also {
                println("Processing content: ${it.content}")
            }.let {
                ResponseData("Success", it.content)
            }
        } else {
            ResponseData("Failure", "Request failed with status: " +
                    "${response.statusCode}")
        }
    }
}
