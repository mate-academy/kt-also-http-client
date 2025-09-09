package mate.academy

private const val SUCCESS = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val response = client.sendRequest(url)
            .also {
            println(
                "Response Status: ${it.statusCode} - ${it.statusText} "
                        + "\nResponse Content: ${it.content}"
            )
        }

        val isResponseSuccess = response.statusCode == SUCCESS

        return ResponseData(
            if (isResponseSuccess) "Success" else "Failure",
            if (isResponseSuccess)
                response.content else "Request failed with status: ${response.statusCode}"
        ).also {
            if (isResponseSuccess) {
                println("Processing content: ${response.content}")
            }
        }
    }
}
