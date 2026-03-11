package mate.academy

const val SUCCESS_STATUS_CODE = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val httpResponse = client.sendRequest(url).also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            println("Response Content: ${it.content}")
        }
        if (httpResponse.statusCode == SUCCESS_STATUS_CODE) {
            httpResponse.also {
                println("Processing content: ${it.content}")
                return ResponseData("Success", httpResponse.content)
            }
        } else {
            return ResponseData(
                "Failure",
                "Request failed with status: ${httpResponse.statusCode}"
            )
        }
    }
}
