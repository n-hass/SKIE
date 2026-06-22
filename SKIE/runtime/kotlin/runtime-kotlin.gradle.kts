plugins {
    id("skie.runtime.kotlin")
    id("utility.skie-publishable")
}

skiePublishing {
    name = "SKIE Runtime - Kotlin"
    description = "Kotlin Multiplatform part of the SKIE runtime. It's used to facilitate certain features of SKIE."
}

kotlin {
    // The klib's unique_name (`${group}:${name}`) leaks into SKIE's generated Swift namespace
    // (`Skie.co_touchlab_skie__runtime_kotlin`) and into `belongsToSkieKotlinRuntime`, both of which are
    // hardcoded to `co.touchlab.skie`. Pin the K/N module name so the rebranded publish group
    // (see BasePlugin: `com.nickhassan.skie`) doesn't change the runtime module's identity. The Maven
    // coordinate stays the rebranded group; only the on-klib name is held to the upstream value.
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>().configureEach {
        compilations.named("main").configure {
            compileTaskProvider.configure {
                compilerOptions.moduleName.set("co.touchlab.skie:runtime-kotlin")
            }
        }
    }

    iosArm64()
    iosX64()
    iosSimulatorArm64()

    watchosArm32()
    watchosArm64()
    watchosX64()
    watchosSimulatorArm64()
    watchosDeviceArm64()

    tvosArm64()
    tvosX64()
    tvosSimulatorArm64()

    macosX64()
    macosArm64()

    sourceSets.configureEach {
        languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
    }
}

dependencies {
    commonMainImplementation(libs.kotlinx.coroutines.core)
}
