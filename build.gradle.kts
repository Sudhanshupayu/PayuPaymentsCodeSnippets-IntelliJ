plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.8.20"
    id("org.jetbrains.intellij.platform") version "2.3.0"
}

group = "in.payu.payupayments"
version = "0.0.2"

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
        // Use a specific version number (2020.3.4) for better compatibility with the platform plugin
        create("IC", "2020.3.4") // Using 2020.3.4 as it's well-supported by the platform plugin

        // Required plugins
        bundledPlugin("com.intellij.modules.java")
        bundledPlugin("com.intellij.java")

        // Optional plugins
        bundledPlugin("com.intellij.maven")
        bundledPlugin("org.jetbrains.plugins.gradle")
        bundledPlugin("org.jetbrains.kotlin")
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
            // While we're building against 2020.3.4, we can still support back to 203
            sinceBuild = "203" // IntelliJ IDEA 2020.3
            untilBuild = "252.*" // IntelliJ IDEA 2025.2
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

// Java configuration
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
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
}
