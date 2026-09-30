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
