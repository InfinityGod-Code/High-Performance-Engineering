## Foundations
Gradle is a tool for build automation. With Gradle, we can automate compiling, testing, packaging, and deployment of our software or any other types of projects.

Few Points to ponder : 
- Gradle uses a Domain Specific Language (DSL) based on Groovy to declare builds.
- Groovy is a language for the Java Virtual Machine (JVM), such as Java and Scala.

Maven and Ivy repositories are supported to publish or fetch dependencies. So, we can continue to use any repository infrastructure that we already have.
Suppose we have Jfrog artifactory : 
```
JFrog Artifactory
       │
       └── Maven repository
              │
              ├── company-lib-1.0.jar
              └── company-lib-2.0.jar
```
Now we can use that in our project using the following : 
```
repositories {
    maven {
        url = uri("https://jfrog.company.com/artifactory/libs-release")
    }
}

dependencies {
    implementation("com.company:company-lib:1.0")
}
```

Some of the most used and important properties of the gradle is : 

- Incremental builds : With Gradle, we have incremental builds. This means the tasks in a build are only executed if necessary.In Gradle, a Task is a unit of work that Gradle can execute.
- Multi-project builds : Gradle has great support for multi-project builds. A project can simply be dependent on other projects or be a dependency of other projects.
- Gradle Wrapper : The Gradle Wrapper allows us to execute Gradle builds even if Gradle is not installed on a computer. 
```
./gradlew build

```
The Wrapper:

Checks which Gradle version the project requires.
Downloads that version if it's not already available.
Runs the build using that exact version.

Benefit : 
```
Developer A → Gradle 8.10
Developer B → Gradle 8.10
CI/CD       → Gradle 8.10
```
So we all on the same page.

#### Installing Gradle 
Install using the brew for mac and for other need to check this http://www.gradle.org/downloads.
Installing with SKDMAN! : 
Software Development Kit Manager (SDKMAN!) is a tool to manage versions of software development kits such as Gradle.

#### Running our first task with gradle 
Gradle uses the concept of projects to define a related set of tasks. A Gradle build can have one or more projects. A project is a very broad concept in Gradle, but it is mostly a set of components that we want to build for our application.
Tasks are a unit of work that need to be executed by the build. Examples of tasks are compiling source code, packaging class files into a JAR file, running tests, and deploying the application.

```
Old version : 
task helloWorld << { 
    println 'Hello world.' 
} 
```
```
New Version :
task helloWorld {
    doLast {
        println "Hello World"
    }
}
```
With this code, we will define a helloWorld task. The task will print the words Hello world. to the console. The println is a Groovy method to print text to the console and is basically a shorthand version of the System.out.println Java method.

The code between the brackets is a closure. A closure is a code block that can be assigned to a variable or passed to a method. Java doesn't support closures, but Groovy does. As Gradle uses Groovy to define the build scripts, we can use closures in our build scripts.

The << syntax is, technically speaking, an operator shorthand for the leftShift()method, which actually means add to. Therefore, here we are defining that we want to add the closure (with the println 'Hello world' statement) to our task with the helloWorld name.

Note : 
- The << operator means leftShift(), which was removed from modern Gradle.
- doLast {} means "execute this code when the task runs."
- "<<" is replaced with doLast {}.