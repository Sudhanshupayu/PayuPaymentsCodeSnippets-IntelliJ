plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.8.20"
    id("org.jetbrains.intellij.platform") version "2.3.0"
}

group = "in.payu.payupayments"
version = "0.0.3"

repositories {
    mavenCentral()
    maven { url = uri("https://www.jetbrains.com/intellij-repository/releases") }
    maven { url = uri("https://www.jetbrains.com/intellij-repository/snapshots") }
    maven { url = uri("https://plugins.jetbrains.com/maven") }

    intellijPlatform {
        defaultRepositories()
    }
}

// Configure Gradle IntelliJ Platform Plugin with specific version
dependencies {
    intellijPlatform {
        create("IC", "2025.1.1") // IntelliJ IDEA (Community Edition) version
        bundledPlugin("com.intellij.java") // Java support
        bundledPlugin("org.jetbrains.kotlin") // Kotlin plugin support
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
            sinceBuild = "203" // Supports IntelliJ versions from 2020.3
            untilBuild = "300.*" // Future-proof for IntelliJ versions beyond 2025
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
            # 0.0.3 - Fixes and improvement in the lines of codes.
            # Added ICON to the Plugin
            # Improved multi-version IntelliJ compatibility
            # Updated to Kotlin JVM compatibility mode
        """.trimIndent()
    }
}

// Java configuration
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            languageVersion = "1.8"
            apiVersion = "1.8"
        }
    }

    buildSearchableOptions {
        enabled = false // Disable this task since it's causing issues
    }

    runIde {
        jvmArgs("-Xmx2g")
    }
    compileJava {
        options.release.set(8) // Generate Java 8-compatible bytecode
    }
}
