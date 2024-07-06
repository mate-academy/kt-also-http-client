package mate.academy

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class HttpRequestProcessorTest {
    private lateinit var mockHttpClient: HttpClient
    private lateinit var processor: HttpRequestProcessor
    private lateinit var originalOut: PrintStream
    private val outputStreamCaptor = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        originalOut = System.out
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @AfterEach
    fun tearDown() {
        System.setOut(originalOut)
    }

    private fun setMockHttpClient(statusCode: Int, statusText: String, content: String) {
        mockHttpClient = object : HttpClient {
            override fun sendRequest(url: String): HttpResponse {
                println("Sending request to: $url")
                return HttpResponse(statusCode, statusText, content)
            }
        }
        processor = HttpRequestProcessor(mockHttpClient)
    }

    @Test
    fun `test success scenario`() {
        val url = "http://example.com"
        setMockHttpClient(200, "Success", "Mock response content")

        val response = processor.processRequest(url)
        val output = outputStreamCaptor.toString()

        assertEquals("Success", response.status)
        assertEquals("Mock response content", response.contentSummary)
        assertTrue(output.contains("Sending request to: $url"))
        assertTrue(output.contains("Response Status: 200 - Success"))
        assertTrue(output.contains("Response Content: Mock response content"))
        assertTrue(output.contains("Processing content: Mock response content"))
    }

    @Test
    fun `test failure scenario (Non-200 Status Code)`() {
        val url = "http://example.com"
        setMockHttpClient(404, "Not Found", "")

        val response = processor.processRequest(url)
        val output = outputStreamCaptor.toString()

        assertEquals("Failure", response.status)
        assertEquals("Request failed with status: 404", response.contentSummary)
        assertTrue(output.contains("Sending request to: $url"))
        assertTrue(output.contains("Response Status: 404 - Not Found"))
        assertFalse(output.contains("Processing content"))
    }

    @Test
    fun `test different content response`() {
        val url = "http://example.com"
        setMockHttpClient(200, "Success", "Different content")

        val response = processor.processRequest(url)
        val output = outputStreamCaptor.toString()

        assertEquals("Success", response.status)
        assertEquals("Different content", response.contentSummary)
        assertTrue(output.contains("Sending request to: $url"))
        assertTrue(output.contains("Response Status: 200 - Success"))
        assertTrue(output.contains("Response Content: Different content"))
        assertTrue(output.contains("Processing content: Different content"))
    }

    @Test
    fun `test empty content`() {
        val url = "http://example.com"
        setMockHttpClient(200, "Success", "")

        val response = processor.processRequest(url)
        val output = outputStreamCaptor.toString()

        assertEquals("Success", response.status)
        assertEquals("", response.contentSummary)
        assertTrue(output.contains("Sending request to: $url"))
        assertTrue(output.contains("Response Status: 200 - Success"))
        assertTrue(output.contains("Response Content: "))
        assertTrue(output.contains("Processing content: "))
    }
}
