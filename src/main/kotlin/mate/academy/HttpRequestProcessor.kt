package mate.academy

const val SUCCESS_CODE = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        return client.sendRequest(url).also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            println("Response Content: ${it.content}")
        }.run {
            if (statusCode == SUCCESS_CODE) {
                println("Processing content: $content")
                ResponseData("Success", content)
            } else {
                ResponseData("Failure", "Request failed with status: $statusCode")
            }
        }
    }
}
