package mate.academy

private const val OK_STATUS_CODE = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val response = client.sendRequest(url).also { response ->
            println("Response Status: ${response.statusCode} - ${response.statusText}")
            println("Response Content: ${response.content}")
        }

        if (response.statusCode == OK_STATUS_CODE) {
            val content = response.content.also { content ->
                println("Processing content: $content")
            }
            return ResponseData("Success", content)
        } else {
            return ResponseData(
                "Failure",
                "Request failed with status: ${response.statusCode}"
            )
        }
    }
}
