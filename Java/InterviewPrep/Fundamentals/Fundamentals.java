package Java.InterviewPrep.Fundamentals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
No of ways we can create threads and their differences
There are many ways to create threads in Java, but the most common ones are:
1. Extending the Thread class: You can create a new class that extends the Thread class and override its run() method.
   Then, you can create an instance of that class and call its
   start() method to start the thread. This approach is simple and straightforward, 
   but it has some limitations, such as not being able to extend any other class.
2. Implementing the Runnable interface: You can create a new class that implements the Runnable interface and 
   override its run() method. Then, you can create an instance of that class and pass it to a Thread object,
   which you can start by calling its start() method. This approach allows you to extend other classes and is
   more flexible than extending the Thread class.
3. Using the Executor framework: You can use the Executor framework to manage threads and tasks. 
   This approach provides a higher-level abstraction for managing threads and allows you to easily create thread pools,
   schedule tasks, and handle exceptions. It is recommended for most applications that require concurrent programming.
4. Using the Fork/Join framework: You can use the Fork/Join framework to parallelize tasks that can be broken down into smaller sub-tasks. 
   This approach is useful for tasks that can be divided into smaller parts and executed concurrently, such as sorting or searching large datasets.
5. Using the CompletableFuture class: You can use the CompletableFuture class to create asynchronous tasks that can be executed in parallel. 
   This approach allows you to chain multiple tasks together and handle exceptions in a more functional programming style.
 */


class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread is running using Runnable interface.");
    }
}

class ThreadExtendExample extends Thread {
    @Override
    public void run() {
        System.out.println("Thread is running by extending Thread class.");
    }
}


public class Fundamentals {
    public static void main(String[] args) {
        System.out.println("Starting fundamentals example...");
        // Create a thread using the Runnable interface
        Thread thread = new Thread(new MyRunnable());
        thread.start();

        // Create a thread by extending the Thread class
        ThreadExtendExample threadExtend = new ThreadExtendExample();
        threadExtend.start();

        // Executor framework example
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable task1 = () -> {
            System.out.println("Task 1 is running in the executor service.");
        };
        Runnable task2 = () -> {
            System.out.println("Task 2 is running in the executor service.");
        };
        executorService.submit(task1);
        executorService.submit(task2);  
        
        executorService.shutdown(); // Shutdown the executor service after tasks are completed
    }
}
