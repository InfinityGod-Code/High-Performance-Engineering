## Gradle In Action 
We have seen how to write tasks in a Gradle build and how to execute them, but we haven't seen how to perform real-life tasks, such as compiling source code or testing with Gradle.

Gradle plugins extend your project's capabilities by introducing custom tasks, properties, and conventions, cleanly decoupling this functionality from the core build logic. While you can write custom plugins, Gradle includes a robust set of built-in plugins right out of the box. A prime example is the Java plugin, which automatically equips your project with standardized tasks for compiling, testing, and packaging Java source code.

Currently, core plugins are tied directly to the Gradle release cycle; any update to a built-in plugin requires a new release of Gradle itself (though this architecture may evolve in the future). In contrast, custom and third-party plugins maintain their own independent release lifecycles. Let's explore how this works by diving into the built-in Java plugin.
```
Groovy : 
apply plugin: 'java'
Modern Kotlin:
plugins {
    id("java")
}
```

Note : In order to perform the gradle related tasks we need to create the gradle wrapper and then executes the following gradle related commands like : 
```
plugins { id("java") }  → apply Java plugin

./gradlew tasks          → list tasks
./gradlew tasks --all    → all tasks
./gradlew properties    → project properties
./gradlew build          → run build
```

# Gradle Java Plugin: Tasks and Convention Objects

When applied to a project, the Gradle **Java Plugin** introduces two primary components: visible tasks organized into functional groups, and an underlying **Convention Object** for project configuration.

---

## 1. Task Groups Provided by the Java Plugin

The plugin organizes its tasks into distinct functional categories:

* **Build Tasks**
  * **Purpose:** Handles compiling source code and creating output packages (e.g., JAR files).
* **Documentation Tasks**
  * **Purpose:** Generates project documentation.
  * **Key Task:** `javadoc` (builds API documentation from Javadoc comments).
* **Verification Tasks**
  * **Purpose:** Runs automated tests and evaluates code quality/static analysis.
* **Rule-based & Lifecycle Tasks**
  * **Purpose:** Dynamically created tasks based on defined rules to build, clean, and publish/upload project artifacts.

---

## 2. The Plugin Architecture: Tasks vs. Convention Objects

Applying the Java plugin exposes two distinct layers of functionality:

```
+-------------------------------------------------------+
|                     Java Plugin                       |
+---------------------------+---------------------------+
                            |
            +---------------+---------------+
            |                               |
            v                               v
  [ Visible Interface ]           [ Configuration Layer ]
       Tasks                           Convention Object
  (Build, Verify, Doc)             (Properties & Methods)
```

### The Visible Layer: Tasks
Tasks are the runnable units of work (e.g., `gradle build`, `gradle test`) that developers interact with directly in the terminal or IDE.

### The Configuration Layer: Convention Objects
In addition to tasks, the plugin injects a **Convention Object** into the project.

* **What it is:** A set of default properties and methods added directly to your Gradle project space.
* **How it behaves:** Its properties and methods behave like native project properties.
* **Why it matters:** 
  1. **Inspection:** Allows you to view default plugin settings (e.g., default source directory layouts or target Java versions).
  2. **Customization:** Allows you to override property values to reconfigure task behaviors without rewriting task logic.

---

## Key Takeaway

> Tasks perform the actions (compiling, testing, packaging), while the **Convention Object** holds the configuration parameters that dictate *how* those tasks execute.

## 2. Directory Layout Conventions ("Convention over Configuration")

The Java plugin relies on standard directory structures. Following these conventions eliminates the need to write custom directory-mapping code in `build.gradle`.

| Directory | Purpose |
| :--- | :--- |
| `src/main/java` | Production Java source files |
| `src/main/resources` | Production configuration files (e.g., `.properties`, `.xml`) included in the output JAR |
| `src/test/java` | Test source code (e.g., JUnit, TestNG tests) |
| `src/test/resources` | Resources required strictly for test execution |

> **Best Practice:** While directory conventions can be overridden via source sets, sticking to the standard layout minimizes boilerplate build code and reduces configuration errors.

---

## 3. Sample Project Directory Structure

Below is a typical project structure utilizing localized messages via a resource bundle:

```text
my-project/
├── build.gradle
└── src/
    └── main/
        ├── java/
        │   └── gradle/
        │       └── sample/
        │           └── Sample.java          # Uses ResourceBundle to load messages
        └── resources/
            └── gradle/
                └── sample/
                    └── messages.properties  # welcome = Welcome to Gradle!
```

---

## 4. Lifecycle Tasks and Task Dependencies

In Gradle, tasks can either perform direct execution work or act as **Lifecycle Tasks** that aggregate other tasks.

### The `classes` Task
* **Type:** Lifecycle Task (does not perform compilation directly).
* **Role:** Serves as an umbrella task to assemble all compiled source classes and processed resource files.
* **Dependencies:**
  * `compileJava` – Compiles `.java` files in `src/main/java` to `.class` files.
  * `processResources` – Copies and processes non-code assets from `src/main/resources` to the output destination.

```text
               +-------------------+
               |   classes task    |
               | (Lifecycle Task)  |
               +---------+---------+
                         |
           +-------------+-------------+
           |                           |
           v                           v
+--------------------+      +----------------------+
|    compileJava     |      |   processResources   |
| (Compiles .java)   |      | (Copies resources)   |
+--------------------+      +----------------------+
```

### Inspecting Task Dependencies
To inspect task dependencies and see how lifecycle tasks assemble smaller tasks, run:
```bash
gradle tasks --all
```

**Mental Model** : We use the gradle wrapper to create gradle based setup first for that we must have gradle installed in our machine and once we have that we can now other don't need to install the gradle because gradle wrapper makes it system independant and now others can simply use this.
Before running gradle wrapper there must be build.gradle or build.gradle.kts must be present.

### Java Plugin: Source Sets

#### What is a Source Set?
* A **source set** is a collection of source files (Java files or resources) compiled and executed together.
* Allows grouping files with a specific purpose (e.g., separating API files) without creating a separate project.
* Enables running tasks targeting specific file groups.

#### Default Source Sets
By default, the Java plugin provides two source sets:
* `main`
* `test`

#### Automatically Generated Tasks
For every source set, the plugin creates three tasks:
1. `compile<SourceSet>Java`
2. `process<SourceSet>Resources`
3. `<SourceSet>Classes`

#### Naming Convention
* **`main` Source Set:** The name is omitted from task commands (e.g., `compileJava`).
* **Other Source Sets:** The name is explicitly included (e.g., `compileTestJava` for the `test` source set).