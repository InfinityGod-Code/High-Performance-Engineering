## Gradle Build Scripts
In Gradle, projects and tasks are two important concepts. A Gradle build always consists of one or more projects. A project defines some sort of component that we want to build. There are no defining rules about what the component is. It can be a JAR file with utility classes to be used in other projects, or a web application to be deployed to the corporate intranet. A project doesn't have to be about building and packaging code, it can also be about doing things such as copying files on a remote server or deployment of applications to servers.

A project has one or more tasks. A task is a small piece of work that is executed when we run a build, for example, compiling source code, packaging code in an archive file, generating documentation, and so on.

#### 1.0 Defining Tasks
A project has one or more tasks to execute some actions, so a task is made up of actions. These actions are executed when the task is executed. Gradle supports several ways to add actions to our tasks. In this section, we discuss about the different ways to add actions to a task.

```
task first { 
    doFirst { 
        println 'Running first' 
    } 
} 
 
task second { 
    doLast { Task task -> 
        println "Running ${task.name}" 
    } 
} 
```

Points to Ponder : 
- We must keep in mind that Gradle scripts use Groovy. This means that we can use all the Groovy's good stuff in our scripts. 
- Groovy constructs can also be used in Gradle scripts.

#### 2.0 Dependancies in tasks
In Gradle, we can add task dependencies with the dependsOn method for a task. We can specify a task name as the String value or task object as the argument. We can even specify more than one task name or object to specify multiple task dependencies.

```
tasks.register("first") {
    doLast {
        println("Run ${name}")
    }
}

tasks.register("second") {
    dependsOn("first")

    doLast {
        println("Run ${name}")
    }
}
```
When we run the script, we see that the first task is executed before the second task:
```
gradle second - Groovy
./gradlew second - Kotlin
```

Note : In order to run the gradle kotlin we need to have the gradle wrapper, so if gradle is installed in machine we first have to run : 
```
gradle wrapper
./gradlew <task-name>
```
Reference : In the example2 folder we have two folders : Groovy and Kotlin.Check for examples.

#### 3.0 Dependancies in tasks