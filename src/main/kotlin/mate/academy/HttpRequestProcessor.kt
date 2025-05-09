package mate.academy

const val STATUS_CODE = 200
class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val response = client.sendRequest(url)

        ResponseData(response.statusText, response.content).also {
            println("Response Status: ${response.statusCode} - ${response.statusText}")
            println("Response Content: ${response.content}")
        }
        return if (response.statusCode == STATUS_CODE) {
            println("Processing content: ${response.content}")
            ResponseData("Success", response.content)
        } else {
            ResponseData("Failure", "Request failed with status: ${response.statusCode}")
        }
    }
}
