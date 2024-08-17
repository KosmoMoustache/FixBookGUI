architectury {
    forge()
}

val common: Configuration by configurations.creating {
    configurations.create("commonForgeApi")
    configurations.create("shadowCommonForgeApi")

    configurations.compileClasspath.get().extendsFrom(this)
    configurations.runtimeClasspath.get().extendsFrom(this)
    configurations["developmentForge"].extendsFrom(this)
}

loom {
    accessWidenerPath.set(project(":common").loom.accessWidenerPath)
}

dependencies {
    common(project(":common", configuration = "namedElements")) {
        isTransitive = false
    }
    shadowCommon(project(path = ":common", configuration = "transformProductionForge")) {
        isTransitive = false
    }

    val minecraftVersion: String by project
    val forgeVersion: String by project
    forge(group = "net.minecraftforge", name = "forge" , version = "${minecraftVersion}-${forgeVersion}")
}

configurations.all {
    resolutionStrategy.force("net.sf.jopt-simple:jopt-simple:5.0.4")
}