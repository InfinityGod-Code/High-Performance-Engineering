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
- Run the task with command gradle <task-name> here : "helloWorld".


#### Gradle Daemon 
Gradle Daemon is a long-running Gradle process that stays in the background and is reused across builds.the Gradle daemon later, but it essentially keeps Gradle running in memory so that we don't get the penalty of starting the JVM each time we run Gradle. This drastically speeds up the execution of tasks.
Without Daemon:
```
./gradlew build
   ↓
Start Gradle
   ↓
Build
   ↓
Stop Gradle
```

With Daemon : 
```
First build
   ↓
Start Gradle Daemon
   ↓
Build
   ↓
Daemon stays running

Next build
   ↓
Reuse existing Daemon
   ↓
Build faster
```

## 2.0 Default Gradle tasks
We can create our simple build script with one task. We can ask Gradle to show us the available tasks for our project. Gradle has several built-in tasks that we can execute. We type gradle -q tasks to see the tasks for our project:

The properties task is very useful to see the properties available for our project. We haven't defined any property ourselves in the build script, but Gradle provides a lot of built-in properties. The following output shows some of the properties:
```
gradle -q properties
```
The dependencies task will show dependencies (if any) for our project. Our first project doesn't have any dependencies, as the output shows when we run the task:
```
gradle -q dependencies
```
The projects tasks will display subprojects (if any) for a root project. Our project doesn't have any subprojects. Therefore, when we run the projects task, the output shows us our project has no subprojects:
```
gradle -q projects
```
The model tasks displays information about the model that Gradle builds internally from our project build file. This feature is incubating, which means that the functionality can change in future versions of Gradle.The model task shows how Gradle internally understands your project after reading your build.gradle.
```
gradle -q model
```

### 2.1 : Task name abbreviation
We can also abbreviate each word in a CamelCase task name. For example, our helloWorld task name can be abbreviated to hW:

```
$ gradle -q hW
Hello world.
```

## 3.0 Gradle Performance GUI Options
#### 3.1 : Profiling 
Think of --profile as Gradle's build performance report.Gradle also provides the --profile command-line option. This option records the time that certain tasks take to complete. The data is saved in an HTML file in the build/reports/profile directory. We can open this file in a web browser and check the time taken for several phases in the build process.
```
./gradlew build --profile
```
Gradle will:

Run your build.
Measure how long different tasks/phases take.
Generate an HTML report.
Save it under:
```
build/reports/profile/
```
Open that HTML file in your browser and you'll see information like:
```
Task              Time
-----------------------
compileJava       2.1s
test              8.4s
processResources  0.5s
jar               1.2s
```

#### 3.2 : Understanding the Gradle graphical user interface
Finally, we take a look at the --gui command-line option. With this option, we start a graphical shell for our Gradle builds. Until now, we used the command line to start a task. With the Gradle GUI, we have a graphical overview of the tasks in a project and we can execute them by simply clicking on the mouse.

To start the GUI, we invoke the following command:
```
gradle --gui
```
