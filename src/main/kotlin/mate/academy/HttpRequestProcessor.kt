package mate.academy

const val CODE = 200;

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val response = client.sendRequest(url)
            .also { resp ->
                println("Response Status: ${resp.statusCode} - ${resp.statusText}")
                println("Response Content: ${resp.content}")
            }

        return if (response.statusCode == CODE) {
            response.also {
                println("Processing content: ${it.content}")
            }
            ResponseData(status = "Success", contentSummary = response.content)
        } else {
            ResponseData(
                status = "Failure",
                contentSummary = "Request failed with status: ${response.statusCode}"
            )
        }
    }
}
