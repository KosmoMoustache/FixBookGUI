import net.neoforged.nfrtgradle.CreateMinecraftArtifacts

plugins {
    id("multiloader-loader")
    id("net.neoforged.moddev")
    id("dev.kikugie.fletching-table.neoforge") version "0.1.0-alpha.23"
}

fletchingTable {
    j52j.register("main") {
        extension("json", "**/*.json5")
    }

//    accessConverter.register(sourceSets.main) {
//        add("accesswideners/${mod.aw_version}.aw")
//    }
}

stonecutter {
}

dependencies {
}

neoForge {
    enable {
        version = deps.neoforge
    }

//    val at = project.file("build/resources/main/META-INF/accesstransformer.cfg")
//    accessTransformers.from(at.absolutePath)
//    validateAccessTransformers = true

    accessTransformers.from(project.file("../../src/main/resources/META-INF/accesstransformer.cfg").absolutePath)


    runs {
        register("client") {
            client()
            ideName = "NeoForge Client (${project().path})"
            programArguments.addAll("--quickPlaySingleplayer", "wd_void", "--width", "1280", "--height", "720")
        }
    }

    mods {
        register(mod.id) {
            sourceSet(sourceSets.main.get())
        }
    }

    deps.parchment?.let {
        parchment {
            mappingsVersion = it
            minecraftVersion = deps.minecraft
        }
    }
}

sourceSets.main {
    resources.srcDir("src/generated/resources")
}

tasks {
    named<ProcessResources>("processResources") {
//        exclude("*.aw")

        // Rename neoforge.mods.toml to mods.toml
        if (stonecutterBuild.eval(stonecutterBuild.current.version, "<1.20.5")) {
            doLast {
                moveAndDeleteFileOrFolder(
                    file("${layout.buildDirectory.get().toString()}/resources/main/META-INF"),
                    "neoforge.mods.toml",
                    "mods.toml",
                )
            }
        }

        // Rename function folder to functions for <1.21
        if (stonecutterBuild.eval(stonecutterBuild.current.version, "<1.21")) {
            doLast {
                moveAndDeleteFileOrFolder(
                    file("${layout.buildDirectory.get().toString()}/resources/main/"),
                    "data/musicnotification/function",
                    "data/musicnotification/functions"
                )
            }
        }
    }
}
tasks {
    named<CreateMinecraftArtifacts>("createMinecraftArtifacts") {
        dependsOn(":neoforge:${deps.minecraft}:processResources")
    }
}
