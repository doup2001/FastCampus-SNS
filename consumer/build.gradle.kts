dependencies {
    implementation(project(mapOf("path" to ":core")))
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.springdoc:springdoc-openapi-starter-common:2.2.0")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")


}