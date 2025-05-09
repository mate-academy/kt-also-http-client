package mate.academy

interface HttpClient {
    companion object {
        const val OK: Int = 200
    }
    fun sendRequest(url: String): HttpResponse
}
