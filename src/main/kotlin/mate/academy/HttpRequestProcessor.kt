package mate.academy

const val HTTP_OK = 200
class HttpRequestProcessor(private val client: HttpClient) {

    fun processRequest(url: String): ResponseData {
        val response = client.sendRequest(url).also { res ->
            println("Response Status: ${res.statusCode} - ${res.statusText}")
            println("Response Content: ${res.content}")
        }

        return if (response.statusCode == HTTP_OK) {
            response.also { res ->
                println("Processing content: ${res.content}")
            }
            ResponseData(status = "Success", contentSummary = response.content)
        } else {
            ResponseData(status = "Failure", contentSummary = "Request failed with status: ${response.statusCode}")
        }
    }
}
