package mate.academy

const val OK_STATUS = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val response = client.sendRequest(url)
        response.also {
                println("Response Status: ${it.statusCode} - ${it.statusText}")
                println("Response Content: ${it.content}")
        }
        if (response.statusCode == OK_STATUS) {
            response.also {
                println("Processing content: ${it.content}")
            }
            return ResponseData("Success", response.content)
        } else {
            return ResponseData(
                "Failure", "Request failed with status: ${response.statusCode}")
        }
    }
}
