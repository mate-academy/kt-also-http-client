package mate.academy

const val OK_STATUS_CODE = 200

class SimpleHttpClient : HttpClient {
    override fun sendRequest(url: String): HttpResponse {
        println("Sending request to: $url")
        return HttpResponse(OK_STATUS_CODE, "Success", "Mock response content")
    }
}
