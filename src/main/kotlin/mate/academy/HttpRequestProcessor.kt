package mate.academy

private const val SUCCESS_CODE = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val response = client.sendRequest(url).also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            println("Response Content: ${it.content}")
        }

        if (response.statusCode == SUCCESS_CODE) {
            response.also {
                println("Processing content: ${response.content}")
            }

            return ResponseData("Success", response.content)
        } else {
            response.also {
                return ResponseData("Failure",
                    "Request failed with status: ${response.statusCode}")
            }
        }
    }
}
