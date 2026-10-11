import dev.kikugie.stonecutter.build.StonecutterBuildExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.maven
import java.io.File

fun Project.prop(key: String): String? = findProperty(key)?.toString()
val Project.stonecutterBuild get() = extensions.getByType<StonecutterBuildExtension>()

val Project._mod: ModData get() = ModData(this)
val Project._deps: DepsData get() = DepsData(this)

val Project.common get() = requireNotNull(stonecutterBuild.node.sibling("common"))
val Project.lproject get() = rootProject.project(stonecutterBuild.current.project)

// 'loader' is declared in the branch project's gradle.properties (e.g. common/gradle.properties);
// read it from the parent project explicitly to avoid the deprecated implicit parent lookup.
val Project.loader: String? get() = parent?.prop("loader")

val Project.mod get() = lproject._mod
val Project.deps get() = lproject._deps

fun RepositoryHandler.strictMaven(url: String, alias: String, vararg groups: String) = exclusiveContent {
    forRepository { maven(url) { name = alias } }
    filter { groups.forEach(::includeGroup) }
}

@JvmInline
value class ModData(private val project: Project) {
    val id: String get() = globalProp("id")
    val version: String get() = globalProp("version")
    val name: String get() = globalProp("name")
    val group: String get() = globalProp("group")
    val description: String get() = globalProp("description")
    val license: String get() = globalProp("license")
    val github: String get() = globalProp("github")
    val aw_version: String get() = modProp("aw_version")
    val fabric_mc_range: String get() = modProp("fabric_range")
    val neoforge_mc_range: String get() = modProp("neoforge_range")

    // Global mod.* properties are declared in the root gradle.properties;
    // read them from rootProject explicitly to avoid the deprecated implicit parent lookup.
    fun globalPropOrNull(key: String) = project.rootProject.prop("mod.$key")?.takeIf { it.isNotEmpty() && it != ""}
    fun globalProp(key: String) = requireNotNull(globalPropOrNull(key)) { "Missing 'mod.$key'" }

    // Version-specific mod.* properties are declared in versions/<version>/gradle.properties.
    fun modPropOrNull(key: String) = project.prop("mod.$key")?.takeIf { it.isNotEmpty() && it != ""}
    fun modProp(key: String) = requireNotNull(modPropOrNull(key)) { "Missing 'mod.$key'" }
}

@JvmInline
value class DepsData(private val project: Project) {
    val minecraft: String get() = get("minecraft")
    val parchment: String? get() = getOrNull("parchment")
    val floader: String get() = get("fabric-loader")
    val fapi: String get() = get("fabric-api")
    val neoforge: String get() = get("neoforge")

    fun getOrNull(key: String): String? = project.prop("deps.$key")?.takeIf { it.isNotEmpty() && it != ""}
    fun get(key: String) = requireNotNull(getOrNull(key)) { "Missing 'deps.$key'" }
}

/**
 * Moves a file or folder to a new destination and optionally deletes the source after moving.
 *
 * @param buildMetaInf The base directory containing the files or folders.
 * @param src The relative path of the source file or folder to move.
 * @param dest The relative path of the destination where the file or folder will be moved.
 * @param delete Specifies whether the source file or folder should be deleted after moving. Default is `true`.
 *
 * <pre>
 * moveAndDeleteFileOrFolder(
 *  file("${layout.buildDirectory.get().toString()}/resources/main/"),
 *      "data/modname/function",
 *      "data/modname/functions"
 *  )
 *  </pre>
 */
fun moveAndDeleteFileOrFolder(
    buildMetaInf: File,
    src: String,
    dest: String,
    delete: Boolean = true
) {
    val srcFile = buildMetaInf.resolve(src)
    val destFile = buildMetaInf.resolve(dest)
    if (srcFile.exists() && !destFile.exists()) {
        srcFile.renameTo(destFile)
//        srcFile.copyRecursively(destFile, overwrite = true)
//        if (delete) {
//            srcFile.deleteRecursively()
//        }
    }
}
