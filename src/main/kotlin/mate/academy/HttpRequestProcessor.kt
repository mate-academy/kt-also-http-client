package mate.academy

const val OK = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String) = client.sendRequest(url)
        .also {
            println(
                """
                Response Status: ${it.statusCode} - ${it.statusText}
                Response Content: ${it.content}
                """.trimIndent()
            )
        }.run {
            if (statusCode == OK) ResponseData("Success", content)
                .also { println("Processing content: $content") }
            else ResponseData("Failure", "Request failed with status: $statusCode")
        }
}
