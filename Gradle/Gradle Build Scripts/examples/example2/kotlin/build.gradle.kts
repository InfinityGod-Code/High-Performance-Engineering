val first = tasks.register("first") {
    doLast {
        println("Run $name")
    }
}

tasks.register("second") {
    dependsOn(first)
    doLast {
        println("Run $name")
    }
}