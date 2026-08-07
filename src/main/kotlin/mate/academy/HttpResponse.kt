package mate.academy

data class HttpResponse(
    var statusCode: Int,
    val statusText: String,
    val content: String
)
