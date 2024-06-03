package mate.academy

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val response = client.sendRequest(url)
            .also { println("Response Status: ${it.statusCode} - ${it.statusText}") }
            .also { println("Response Content: ${it.content}") }
            .also { if (it.statusCode == HttpClient.OK) {
                println("Processing content: ${it.content}")
            } else {
                println("Request failed with status: ${it.statusCode}")
            }
            }
        val str = if (response.statusCode == HttpClient.OK) "Success" else "Failure"
        val cont = if (response.statusCode == HttpClient.OK) response.content
        else "Request failed with status: ${response.statusCode}"
        return ResponseData(str, cont)
    }
}
