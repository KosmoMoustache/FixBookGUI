import net.neoforged.nfrtgradle.CreateMinecraftArtifacts

plugins {
    kotlin("jvm")
    id("multiloader-loader")
    id("net.neoforged.moddev")
    id("dev.kikugie.fletching-table.neoforge") version "0.1.0-alpha.22"
}

fletchingTable {
    j52j.register("main") {
        extension("json", "**/*.json5")
    }
}

dependencies {
}

neoForge {
    version = deps.neoforge

    accessTransformers.from(project.file("../../src/main/resources/META-INF/accesstransformer.cfg").absolutePath)

    runs {
        register("client") {
            client()
            ideName = "NeoForge Client (${project.path})"
            programArgument("--quickPlaySingleplayer wd_void")
            programArgument("--width 1280")
            programArgument("--height 720")
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
        exclude("**/*.aw")

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

    named<CreateMinecraftArtifacts>("createMinecraftArtifacts") {
        dependsOn(":neoforge:${deps.minecraft}:stonecutterGenerate")
    }
}
