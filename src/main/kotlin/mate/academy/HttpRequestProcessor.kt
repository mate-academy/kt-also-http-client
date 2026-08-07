package mate.academy

private const val I = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        var response: HttpResponse?
        SimpleHttpClient().apply {
            response = client.sendRequest(url)
        }
            .also {
                println("Response Status: ${response?.statusCode} - ${response?.statusText}")
                println("Response Content: ${response?.content}")
            }
            .also {
                return if (response?.statusCode == I) {
                    val content = response!!.content
                    println("Processing content: $content")
                    ResponseData("Success", content)
                } else {
                    println("Request failed with status: ${response?.statusCode}")
                    ResponseData("Failure", "Request failed with status: ${response?.statusCode}")
                }
            }
    }
}
