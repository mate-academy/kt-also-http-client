package mate.academy

private const val SUCCESS_STATUS_CODE = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val response = client.sendRequest(url).also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            println("Response Content: ${it.content}")
        }
        val isSuccess = response.statusCode == SUCCESS_STATUS_CODE
        return ResponseData(
            if (isSuccess) "Success" else "Failure",
            if (isSuccess) response.content else "Request failed with status: ${response.statusCode}"
        ).also {
            if (isSuccess) {
                println("Processing content: ${response.content}")
            }
        }
    }
}
