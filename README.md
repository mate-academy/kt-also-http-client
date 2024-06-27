# HTTP request processing

In this task, you need to implement the `processRequest()` method within the `HttpRequestProcessor` class. You
will use a very simplified version of HttpClient with mocked logic.

If you are not familiar with the client-server architecture, feel free to check the details for a brief explanation of how
HttpClient works:

<details>
In very simple terms, an `HttpClient` is a tool used in programming to communicate over the internet. 
It sends requests to and receives responses from a server using the HTTP protocol, 
which is the standard way of exchanging information on the web.

Here’s a breakdown of how it typically works:

1. **Sending a Request**: When you want to access information from a server (like a webpage, data from a database, or
   other resources), the `HttpClient` sends a request to the server. This request includes the URL (which specifies the
   server's address and the specific page or data you want) and sometimes additional headers or data if needed.

2. **Receiving a Response**: After the server receives and processes the request, it responds to
   the `HttpClient`. This response includes a status code (which tells if the request was successful or not), any data
   requested (like a webpage, image, or other data), and possibly some headers with extra information about the
   response.

3. **Handling the Response**: The `HttpClient` receives this response and processes it according to the needs of the
   application. For example, it might display data on a webpage, store it in a database, or use it in another way.

In essence, an `HttpClient` acts as a messenger, sending requests to and receiving responses from a server, enabling
your applications to interact with different web services seamlessly.
</details>

## Use `also` function during the task solution

Your goal is to manage HTTP requests efficiently, ensure accurate logging of details, and handle responses using
Kotlin's `also` scope function. It's crucial to adhere to the specified logging format, as it will be checked by unit
tests.

## Goal

Implement the `processRequest()` method to accurately utilize the `also()` function for logging side effects while
processing HTTP responses effectively. Strict adherence to the specified logging formats is mandatory, as these are part
of the assessment criteria in the accompanying unit tests. This ensures functional correctness and compliance with
expected output formats for automated validation.

## Task description (implementation details)

1. **Method to Implement:**
    - `fun processRequest(url: String): ResponseData`
        - This method accepts a URL string as its parameter and should return a `ResponseData` object.

2. **Using the `HttpClient`:**
    - Send an HTTP request to the provided URL using the `HttpClient`'s `sendRequest(URL: String)` method.
    - Capture the response in a variable.

3. **Logging with `also`:**
    - Immediately after receiving the response, use the `also` block to log:
        - “Response Status: `<STATUS_CODE> - <STATUS_TEXT>`”
        - “Response Content: `<CONTENT>`”
    - These logs are crucial for passing unit tests that verify the correctness of the implementation based on the
      expected output format.

4. **Response Handling:**
    - If the `statusCode` in the response is 200:
        - Use the `also` block again to log:
            - “Processing content: `<CONTENT>`”
        - Return a `ResponseData` object with “Success” as the status and the actual content of the response.
    - If the `statusCode` is not 200:
        - Return a `ResponseData` object with “Failure” as the status and a message indicating the failure along with
          the status code:
            - “Request failed with status: `<STATUS_CODE>`”

## Provided classes

The provided classes in the task serve specific purposes related to making and handling HTTP requests and responses.
Check the details below if you need more information about them.

<details>
Here's a brief overview of each:

1. **`HttpClient` Interface:**
    - **Purpose**: This interface defines the structure for an HTTP client class. It has one
      method, `sendRequest(url: String): HttpResponse`, which is intended to send an HTTP request to the specified URL
      and return an HTTP response.
    - **Functionality**: It acts as a contract for any class that implements it, ensuring they provide a specific
      implementation of the `sendRequest()` method.

2. **`SimpleHttpClient` Class:**
    - **Purpose**: This class implements the `HttpClient` interface. It simulates the behavior of an HTTP
      client by providing a mock implementation of the `sendRequest()` method.
    - **Functionality**: When the `sendRequest()` method is called, it prints a message indicating the URL to which a
      request is being sent and returns a simulated `HttpResponse` object with hardcoded values (status code 200, status
      text “Success”, and a generic response content). This is useful for testing without needing to connect to an
      actual external server.

3. **`HttpResponse` Data Class:**
    - **Purpose**: This data class represents the structure of an HTTP response.
    - **Functionality**: It holds three properties: `statusCode` (the HTTP status code of the response), `statusText` (a
      textual description of the status code), and `content` (the body of the response, which could be the data
      retrieved from a server). This class allows easy access and manipulation of these properties in the client code.

4. **`ResponseData` Data Class:**
    - **Purpose**: This class is used to format the final output of processing an HTTP request.
    - **Functionality**: It contains two properties: `status`, which indicates the outcome of the request (like “
      Success” or “Failure”), and `contentSummary`, which provides a summary or the actual content of the response. This
      class is useful for encapsulating the result of an HTTP request in a structured form that is easy to log or
      display.

</details>
