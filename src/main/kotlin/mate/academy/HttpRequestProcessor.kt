package mate.academy

const val OK_STATUS_CODE = 200
class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val response = client.sendRequest(url).also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            println("Response Content: ${it.content}")
        }
        return if (response.statusCode == OK_STATUS_CODE) {
             ResponseData("Success", response.content)
                .also { println("Processing content: ${response.content}") }
        } else {
            ResponseData("Failure", "Request failed with status: ${response.statusCode}")
        }
    }
}
