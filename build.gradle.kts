plugins {
	kotlin("jvm") version "2.3.21"
}

group = "com.basova"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

