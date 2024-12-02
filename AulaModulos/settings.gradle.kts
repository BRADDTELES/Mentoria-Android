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
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AulaModulos"
include(":clientes")
include(":lojas")
include(":entregas")
include(":core:autenticacao")
include(":core:validacoes")
include(":data:api")
include(":data:banco")
include(":feature:autenticacao")
include(":domain")
