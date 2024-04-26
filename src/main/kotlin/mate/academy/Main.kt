package mate.academy

// feel free to test your solution by running this main function
fun main() {
    val client = SimpleHttpClient()
    val processor = HttpRequestProcessor(client)

    val responseData = processor.processRequest("https://api.example.com/data")

    println("Response Data: ${responseData.status} - ${responseData.contentSummary}")
}
