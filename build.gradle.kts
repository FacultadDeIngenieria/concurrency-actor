plugins {
    scala
    application
}

group = "ar.edu.austral.fu.concurrency.actor"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor
    implementation("com.typesafe.akka:akka-actor_2.13:2.9.0-M2")
    // https://mvnrepository.com/artifact/com.typesafe.akka/akka-testkit
    testImplementation("com.typesafe.akka:akka-testkit_2.13:2.9.0-M2")
    // https://mvnrepository.com/artifact/org.asynchttpclient/async-http-client
    implementation("org.asynchttpclient:async-http-client:2.12.3") {
        exclude("io.netty", "netty-codec-http") // Exclude vulnerability
    }
    // https://mvnrepository.com/artifact/io.netty/netty-codec-http
    implementation("io.netty:netty-codec-http:4.1.108.Final")
    // https://mvnrepository.com/artifact/org.jsoup/jsoup
    implementation("org.jsoup:jsoup:1.17.2")
    // https://mvnrepository.com/artifact/org.scalatest/scalatest
    testImplementation("org.scalatest:scalatest_2.13:3.2.18")
}

//tasks.test {
//    useJUnitPlatform()
//}

application {
    mainClass = "ar.edu.austral.fi.concurrency.actor.counter.CounterMain"
}