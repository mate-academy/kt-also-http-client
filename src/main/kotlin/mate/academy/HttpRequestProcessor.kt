package mate.academy

const val STATUS_CODE_OK = 200
class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val httpResponse = client.sendRequest(url)
        httpResponse.also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            println("Response Content: ${it.content}")
        }
        if (httpResponse.statusCode == STATUS_CODE_OK) {
            httpResponse.also {
                println("Processing content: ${httpResponse.content}")
            }
            return ResponseData("Success", httpResponse.content)
        } else {
            return ResponseData("Failure",
                "Request failed with status: ${httpResponse.statusCode}")
        }
    }
}
