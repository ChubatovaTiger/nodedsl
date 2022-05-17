import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.2"

project {

    vcsRoot(Composite)

    buildType(TrivialLoong)
    buildType(TrivialRegular)
    buildType(Fgfg)

    params {
        password("sec", "credentialsJSON:12315edf-7a6e-41f0-905f-7799fb08e9cd")
        param("a", "a")
        param("cd", "cdsf")
        param("b", "b")
    }
}

object Fgfg : BuildType({
    name = "fgfg"
})

object TrivialLoong : BuildType({
    name = "trivial_loong"

    vcs {
        root(Composite)
    }

    steps {
        script {
            scriptContent = """
                echo "before sleep"
                ping -n 110 localhost > NUL
                echo "after sleep"
            """.trimIndent()
        }
    }
})

object TrivialRegular : BuildType({
    name = "trivial_regular"

    vcs {
        root(Composite)
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            scriptContent = """
                for /l %%%%X in (1, 1, 100) do (
                  echo message %%%%X
                  ping -n 3 localhost > NUL
                )
            """.trimIndent()
        }
    }
})

object Composite : GitVcsRoot({
    name = "composite"
    url = "https://github.com/AChubatova/composite"
    branch = "refs/heads/master"
    param("useAlternates", "true")
})
