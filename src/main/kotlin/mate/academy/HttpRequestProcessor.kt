package mate.academy

private const val OK_STATUS_CODE = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val response = client.sendRequest(url).also {
            println("Response Status: ${it.statusCode} - ${it.statusText}")
            if (it.statusCode == OK_STATUS_CODE) {
                println("Response Content: ${it.content}")
            }
        }

        lateinit var processedResponseStatus : String
        lateinit var processedResponseContentSummery : String
        if (response.statusCode == OK_STATUS_CODE) {
            processedResponseStatus = "Success"
            processedResponseContentSummery = response.content
        } else {
            processedResponseStatus = "Failure"
            processedResponseContentSummery = "Request failed with status: ${response.statusCode}"
        }

        return ResponseData(processedResponseStatus, processedResponseContentSummery).also {
            if (response.statusCode == OK_STATUS_CODE) {
                println("Processing content: ${response.content}")
            }
        }
    }
}
