package mate.academy

const val ACCEPT_CODE = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val httpResponse = client.sendRequest(url)
        httpResponse.also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            println("Response Content: ${it.content}")
        }
        return if (httpResponse.statusCode == ACCEPT_CODE) {
            httpResponse.content.also {
                println("Processing content: $it")
            }
            ResponseData("Success", httpResponse.content)
        } else {
            println("Request failed with status: ${httpResponse.statusCode}")
            ResponseData("Failure", "Request failed with status: ${httpResponse.statusCode}")
        }
    }
}
