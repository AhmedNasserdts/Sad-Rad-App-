pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") } // Corrected syntax

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS) //3shan no module[build-gradle] define its own dependency
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") } // Corrected syntax

    }

}

rootProject.name = "Worldsat"
include(":app")
 