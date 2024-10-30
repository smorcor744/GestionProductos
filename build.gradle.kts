plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.jpa") version "1.7.22"

}

group = "org.ventanas"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    // https://mvnrepository.com/artifact/org.hibernate/hibernate-core
    implementation("org.hibernate:hibernate-core:6.6.0.Final")

    // https://mvnrepository.com/artifact/com.mysql/mysql-connector-j
    implementation("com.mysql:mysql-connector-j:9.0.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(18)
}