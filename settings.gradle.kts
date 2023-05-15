pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "TodoApp"
include(
    "app",
    "core",
    "core_task_data_source",
    "core_task_repository",
    "domain",
    "core_users_data_source",
    "core_users_repository",
    "core_settings_repository",
    "core_settings_data_source"
)

file("features").walkTopDown().maxDepth(1).forEach { dir ->
    if (dir.isDirectory) {
        include(dir.name)
        project(":${dir.name}").projectDir = dir
    }
}
