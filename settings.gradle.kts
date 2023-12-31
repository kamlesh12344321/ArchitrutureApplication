pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ArchitructureApplication"
include(":app")
include(":mvi")
include(":mvvm")
include(":cleanarc")
include(":utils")
include(":rxjavamo")
