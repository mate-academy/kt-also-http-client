package mate.academy

private const val OK_STATUS_CODE = 200

class HttpRequestProcessor(private val client: HttpClient) {
    fun processRequest(url: String): ResponseData {// 1. Надсилаємо запит та отримуємо HttpResponse
        val response = client.sendRequest(url)
            // 2. Перший блок also для загального логування статусу та вмісту
            .also {
                println("Response Status: ${it.statusCode} - ${it.statusText}")
                println("Response Content: ${it.content}")
            }

        // 3. Перевіряємо код статусу
        return if (response.statusCode == OK_STATUS_CODE) {
            // 4. Якщо 200, використовуємо also для специфічного логування
            response.also {
                println("Processing content: ${it.content}")
            }
            // Повертаємо об'єкт успіху
            ResponseData("Success", response.content)
        } else {
            // 5. Якщо не 200, повертаємо об'єкт збою з повідомленням
            ResponseData("Failure", "Request failed with status: ${response.statusCode}")
        }
    }
}
