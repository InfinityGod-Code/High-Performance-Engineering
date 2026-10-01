plugins {
    id("java")
}

// this is how we provide a inbuilt source set to the build script, 
// so that it can be used in the build script
tasks.register("sourceSetJavaProperties") {
    doLast {
        sourceSets.named("main") {
            println("java.srcDirs = ${java.srcDirs}")
            println("resources.srcDirs = ${resources.srcDirs}")
            println("java.files = ${java.files.map { it.name }}")
            println("allJava.files = ${allJava.files.map { it.name }}")
            println("resources.files = ${resources.files.map { it.name }}")
            println("allSource.files = ${allSource.files.map { it.name }}")
            println("output.classesDirs = ${output.classesDirs}")
            println("output.resourcesDir = ${output.resourcesDir}")
            println("output.files = ${output.files}")
        }
        sourceSets.named("test") {
            println("=== TEST ===")
            println("java.srcDirs = ${java.srcDirs}")
            println("resources.srcDirs = ${resources.srcDirs}")
            println("java.files = ${java.files.map { it.name }}")
            println("allJava.files = ${allJava.files.map { it.name }}")
            println("resources.files = ${resources.files.map { it.name }}")
            println("allSource.files = ${allSource.files.map { it.name }}")
            println("output.classesDirs = ${output.classesDirs}")
            println("output.resourcesDir = ${output.resourcesDir}")
            println("output.files = ${output.files}")
        }
    }
}