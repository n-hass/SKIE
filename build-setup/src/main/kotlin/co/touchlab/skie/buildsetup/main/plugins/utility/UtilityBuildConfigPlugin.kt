package co.touchlab.skie.buildsetup.main.plugins.utility

import com.github.gmazzo.buildconfig.BuildConfigExtension
import com.github.gmazzo.buildconfig.BuildConfigPlugin
import com.github.gmazzo.buildconfig.generators.BuildConfigKotlinGenerator
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

abstract class UtilityBuildConfigPlugin : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        apply<BuildConfigPlugin>()

        extensions.configure<BuildConfigExtension> {
            // Package of the generated BuildConfig must track the source-code root
            // (co.touchlab.skie), not the published Maven group, which may be forked/rebranded.
            packageName(("co.touchlab.skie.${project.name}").replace("-", "_"))

            generator.set(BuildConfigKotlinGenerator())
        }
    }
}
