package mate.academy

interface HttpClient {
    fun sendRequest(url: String): HttpResponse
}
