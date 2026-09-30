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