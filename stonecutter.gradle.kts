plugins {
    id("dev.kikugie.stonecutter")
    id("com.google.devtools.ksp") version "2.3.6" apply false
    id("net.neoforged.moddev") version "2.0.140" apply false
}
stonecutter active "26.1" /* [SC] DO NOT EDIT */

stonecutter parameters  {
    replacements {
//        string(current.parsed > "1.21.1") {
//            replace("pushPose", "pushMatrix")
//            replace("popPose", "popMatrix")
//        }

        string {
            direction = eval(current.version, ">=1.21.11")
            replace("ResourceLocation", "Identifier")
        }
        string {
            direction = eval(current.version, ">=1.21.11")
            replace("import net.minecraft.Util;", "import net.minecraft.util.Util;")
        }
    }
}

tasks.register<Copy>("collectBuildFiles") {
    group = "build"
    description = "Collect built jars into the root output directory."
    into(layout.projectDirectory.dir("output"))
    from(allprojects.filter {
        it != rootProject &&
                it.childProjects.isEmpty() &&
                !it.projectDir.toPath().startsWith(rootProject.layout.projectDirectory.dir("common").asFile.toPath()) &&
                !it.projectDir.toPath().startsWith(rootProject.layout.projectDirectory.dir("versions").asFile.toPath())
    }.map { it.layout.buildDirectory.dir("libs") }) {
        include("**/*.jar")
    }
}

tasks.register("runAllClients") {
    group = "build"
    description = "Run clients for all versions sequentially."
    val runClients = allprojects.filter {
        it != rootProject &&
                it.childProjects.isEmpty() &&
                !it.projectDir.toPath().startsWith(rootProject.layout.projectDirectory.dir("common").asFile.toPath()) &&
                !it.projectDir.toPath().startsWith(rootProject.layout.projectDirectory.dir("versions").asFile.toPath())
    }.mapNotNull { it.tasks.findByName("runClient") }
    runClients.forEachIndexed { index, task ->
        if (index > 0) {
            task.mustRunAfter(runClients[index - 1])
        }
    }
    dependsOn(runClients)
}

tasks.register("runAllClientsParallel") {
    group = "build"
    description = "Run clients for all versions in parallel."
    val runClients = allprojects.filter {
        it != rootProject &&
                it.childProjects.isEmpty() &&
                !it.projectDir.toPath().startsWith(rootProject.layout.projectDirectory.dir("common").asFile.toPath()) &&
                !it.projectDir.toPath().startsWith(rootProject.layout.projectDirectory.dir("versions").asFile.toPath())
    }.mapNotNull { it.tasks.findByName("runClient") }
    dependsOn(runClients)
}
