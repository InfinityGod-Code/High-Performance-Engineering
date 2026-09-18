## CompletableFuture




#### Question 1 : When should I use CompletableFuture over Future in Java? Can you provide code examples showing how both handle async task composition and exception handling?
In a production Spring Boot project, I would generally use CompletableFuture over Future when I need to compose multiple asynchronous operations, combine results, handle failures, timeouts, and build a non-blocking workflow.

