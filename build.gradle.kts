plugins {
    application
    id("org.beryx.jlink") version "3.1.3"
}

group = "com.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

application {
    mainClass = "jgon.mazes.Main"
    mainModule = "jgon.mazes.app"
}

jlink {
    options = listOf("--strip-debug", "--compress", "zip-6", "--no-header-files", "--no-man-pages")
    launcher {
        name = "myapp"
        jvmArgs = listOf("-XX:+AutoCreateSharedArchive", "-XX:SharedArchiveFile=app.jsa", "-Xlog:cds*=off", "-XX:+UseZGC")
    }

    // Uncomment if you need to merge non-modular dependencies
    mergedModule {
        requires("java.logging")
        requires("java.desktop")
    }
}

tasks.register<Exec>("generateCdsArchiveNoCompressed") {
    dependsOn(tasks.jlink)
    
    val jlinkImageDir = jlink.imageDir.get().asFile
    val javaExe = jlinkImageDir.resolve("bin/java")
    
    commandLine(javaExe.absolutePath, "-Xshare:dump", "-XX:-UseCompressedOops")
}

tasks.register<Exec>("generateCdsArchiveCompressed") {
    dependsOn("generateCdsArchiveNoCompressed")
    
    val jlinkImageDir = jlink.imageDir.get().asFile
    val javaExe = jlinkImageDir.resolve("bin/java")
    
    commandLine(javaExe.absolutePath, "-Xshare:dump")
}

tasks.jlink {
    finalizedBy("generateCdsArchiveCompressed")
}

tasks.test {
    useJUnitPlatform()
}
