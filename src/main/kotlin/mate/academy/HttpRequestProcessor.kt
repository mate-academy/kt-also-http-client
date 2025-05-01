package mate.academy

const val HTTP_STATUS_OK = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {
        val httpResponse = client.sendRequest(url)
        httpResponse.also {
            print("Response Status: ${httpResponse.statusCode} - ${httpResponse.statusText}\n")
            print("Response Content: ${httpResponse.content}\n")
            return if (httpResponse.statusCode == HTTP_STATUS_OK) {
                ResponseData("Success", httpResponse.content).also {
                    print("Processing content: ${httpResponse.content}")
                }
            } else {
                ResponseData("Failure", "Request failed with status: ${httpResponse.statusCode}")
            }
        }
    }
}
