@file:Suppress("UnstableApiUsage")

plugins {
    id("multiloader-loader")
    id("dev.kikugie.loom-back-compat")
    id("dev.kikugie.fletching-table.fabric") version "0.1.0-alpha.23"
}

stonecutter {
}

fletchingTable {
    j52j.register("main") {
        extension("json", "**/*.json5")
    }
}

dependencies {
    fun fabricModules(vararg modules: String) = modules.forEach {
        modImplementation(fabricApi.module("fabric-$it", "${deps.fapi}+${deps.minecraft}"))
    }

    minecraft("com.mojang:minecraft:${deps.minecraft}")

    if (stonecutter.eval(deps.minecraft, "<=1.21.11")) {
        mappings(loom.layered {
            officialMojangMappings()
            deps.parchment?.let { version ->
                parchment("org.parchmentmc.data:parchment-${deps.minecraft}:$version@zip")
            }
        })
    }

    modImplementation("net.fabricmc:fabric-loader:${deps.floader}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${deps.fapi}+${deps.minecraft}")
}

//Mixin hotswap
afterEvaluate {
    loom.runs.configureEach {
        // https://fabricmc.net/wiki/tutorial:mixin_hotswaps
        vmArg("-javaagent:${configurations.compileClasspath.get().find { it.name.contains("sponge-mixin") }}")
    }
}

loom {
    accessWidenerPath = common.project.file("../../src/main/resources/accesswideners/${mod.aw_version}.aw")

    runs {
        getByName("client") {
            client()
            configName = "Fabric Client"
            ideConfigGenerated(true)
            programArgs("--quickPlaySingleplayer", "wd_void", "--width", "1280", "--height", "720")
            if (sc.current.parsed > "1.21.1") {
                vmArgs("-XX:+AllowEnhancedClassRedefinition")
            }
            // "-Dfabric.log.level=debug"
        }
    }

    if (stonecutter.eval(deps.minecraft, "<=1.21.11")) {
        mixin {
            useLegacyMixinAp = true
            defaultRefmapName = "${mod.id}.refmap.json"
        }
    }
}

tasks.named<ProcessResources>("processResources") {
    if (stonecutterBuild.eval(stonecutterBuild.current.version, "<1.21")) {
        doLast {
            moveAndDeleteFileOrFolder(
                file("${layout.buildDirectory.get().toString()}/resources/main/"),
                "data/fixbookgui/function",
                "data/fixbookgui/functions"
            )
        }
    }

    val awFile = common.project.file("../../src/main/resources/accesswideners/${mod.aw_version}.aw")

    from(awFile.parentFile) {
        include(awFile.name)
        rename(awFile.name, "${mod.id}.aw")
        into("")
    }
}

