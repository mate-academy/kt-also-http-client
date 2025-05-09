package mate.academy

private const val OK_STATUS_CODE = 200
private const val SUCCESS_STATUS = "Success"
private const val FAILURE_STATUS = "Failure"
private const val FAILURE_MESSAGE_TEMPLATE = "Request failed with status: %d"
private const val RESPONSE_STATUS_LOG_TEMPLATE = "Response Status: %d - %s"
private const val RESPONSE_CONTENT_LOG_TEMPLATE = "Response Content: %s"
private const val PROCESSING_CONTENT_LOG_TEMPLATE = "Processing content: %s"

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val response = client.sendRequest(url)
        response.also {
            println(RESPONSE_STATUS_LOG_TEMPLATE.format(it.statusCode, it.statusText))
            println(RESPONSE_CONTENT_LOG_TEMPLATE.format(it.content))
        }
        return if (response.statusCode == OK_STATUS_CODE) {
            response.also {
                println(PROCESSING_CONTENT_LOG_TEMPLATE.format(it.content))
            }
            ResponseData(
                status = SUCCESS_STATUS,
                contentSummary = response.content
            )
        } else {
            ResponseData(
                status = FAILURE_STATUS,
                contentSummary = FAILURE_MESSAGE_TEMPLATE.format(response.statusCode)
            )
        }
    }
}
