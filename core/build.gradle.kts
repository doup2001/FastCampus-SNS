dependencies {
    // 몽고 디비 관련 설정
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.testcontainers:testcontainers:1.19.8")

    //레디스 관련 설정
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    // 카프카 관련 설정
    implementation("org.springframework.cloud:spring-cloud-starter-stream-kafka")

}
