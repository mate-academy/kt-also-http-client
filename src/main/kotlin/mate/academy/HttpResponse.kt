package mate.academy

data class HttpResponse(
    val statusCode: Int,
    val statusText: String,
    val content: String
)
