package mate.academy

const val SUCCESS_STATUS_CODE = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        return client.sendRequest(url).also { response ->
            println("Response Status: ${response.statusCode} - ${response.statusText}")
            println("Response Content: ${response.content}")
        }.let { response ->
            if (response.statusCode == SUCCESS_STATUS_CODE) {
                response.also {
                    println("Processing content: ${it.content}")
                }
                ResponseData("Success", response.content)
            } else {
                ResponseData("Failure",
                    "Request failed with status: ${response.statusCode}")
            }
        }
    }
}
