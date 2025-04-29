package mate.academy

class HttpRequestProcessor(private val client: HttpClient) {
    companion object{
        private const val OK_STATUS_CODE = 200
    }
    fun processRequest(url: String): ResponseData {
        return client.sendRequest(url).also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            println("Response Content: ${it.content}")
        }.let {
            if (it.statusCode == OK_STATUS_CODE) {
                println("Processing content: ${it.content}")
                ResponseData(
                    status = "Success",
                    contentSummary = it.content
                )
            } else {
                ResponseData(
                    status = "Failure",
                    contentSummary = "Request failed with status: ${it.statusCode}"
                )
            }
        }
    }
}
