plugins {
    id("java")
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.4"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

ext["springCloudVersion"] = "2023.0.0"


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

allprojects{
    group = "com.fc"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects{
    apply{
        plugin("java")
        plugin("java-library")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")

    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    tasks.test {
        useJUnitPlatform()
    }

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.30")
        annotationProcessor("org.projectlombok:lombok:1.18.30")

        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

}