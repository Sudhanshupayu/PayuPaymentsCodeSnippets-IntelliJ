plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
    id("org.jetbrains.intellij.platform") version "2.3.0"
}

group = "in.payu.payupayments"
version = "0.0.1"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

// Configure Gradle IntelliJ Plugin
dependencies {
    intellijPlatform {
        // Target IntelliJ IDEA 2022.3
        create("IC", "2022.3")
        testFramework(org.jetbrains.intellij.platform.gradle.TestFrameworkType.Platform)

        // Need Java plugin dependency for Java templates
        bundledPlugin("com.intellij.java")
    }
}

intellijPlatform {
    pluginConfiguration {
        id = "in.payu.payupayments"
        name = "Payu Payments Code Snippets"
        version = project.version.toString()

        vendor {
            name = "Payu Payments"
        }

        ideaVersion {
            sinceBuild = "192"
            untilBuild = "252.*"
        }

        description = """
            # PayU Integration Snippets for Intelli J Idea IDE

                This extension provides handy code snippets (Sample Application) for integrating PayU payment gateway in Node.js (JavaScript), PHP, Java, CSharp, Golang, ReactNative, Flutter and Python.
                
                ## Usage
                • Open a file in Java.
                • Move the cursor wherever you want the Payu Payments code to be added.
                • Type the `payu` as prefix, and search for the required code from the dropdown.
                • Modify the required and optional parameters as required and initiate payments and refunds seamlessly using the Payu hosted checkout.
                
                ## Supported languages
                 - **Java**.
        """.trimIndent()

        changeNotes = """
            # 0.0.2 - Fixes and improvement in the lines of codes. 
            # Extended support to more older versions of Java, and Intelli J Idea older versions
        """.trimIndent()
    }
}

// Configure Java/Kotlin for JDK compatibility
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}

kotlin {
    // Use the new compilerOptions DSL instead of kotlinOptions
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
        languageVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_0)
        apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_0)
    }
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }

    buildSearchableOptions {
        enabled = false
    }

    runIde {
        // Add properties to fix the Gradle JVM matrix error
        jvmArgs("-Xmx2g",
            "-Dorg.jetbrains.plugins.gradle.jvmcompat.GradleJvmSupportMatrix.forceMaxJavaVersion=22",
            "-Dorg.jetbrains.plugins.gradle.jvmcompat.GradleJvmSupportMatrix.skipJavaVersionParsing=true")
    }
}
