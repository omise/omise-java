# Omise-Java

[![Maven Central][5]][6] [![Github Actions][0]][1]

`omise-java` provides a set of Java bindings to the [Opn Payments REST API][2]. Please contact
[support@opn.ooo][3] if you have any questions regarding this
library and the functionality it provides.

## Security Warning

**Please do NOT use Omise Java library versions less than 3.1.1, as they are outdated and have security vulnerabilities.**

## BREAKING CHANGE

As of version `v5.0.0`, `joda-time` has been deprecated in favor of java-time so you might see a different format for the string output of date parameters and some functions might not be available anymore. For more reference view this PR([#173](https://github.com/omise/omise-java/pull/173))

# Versioning and Compatibility

Although you can use omise-java with other Java versions, we have validated the following versions in our CI pipeline to ensure compatibility and stability.

### JRE Supported

Represents the Java version you must install on your local system to run the precompiled Java byte code inside your project. i.e., install the SDK from Maven for your project and run it on your custom project.

### Java Version (Compilation)

Represents the Java version required to compile the SDK after you clone the SDK's repo or fork it.

| Library Version | Support           | Supported Omise API Version | JRE Supported | Java Version (Compilation) | Compilation Tested | Notes                                                                                  |
| --------------- | ----------------- | --------------------------- | ------------- | -------------------------- | ------------------ | -------------------------------------------------------------------------------------- |
| 1.x             | End of Life (EOL) | 2014-07-27                  | >= 5          | >= 7                       | N/A                | Initial release with basic features. No longer maintained.                             |
| 2.x             | End of Life (EOL) | 2017-11-02                  | >= 6          | >= 8                       | N/A                | Ground-up rewrite onto Java7 to be more robust and maintainable. No longer maintained. |
| 3.x             | End of Life (EOL) | 2019-05-29                  | >= 8          | >= 8                       | Java 8             | Used latest API version (`2019-05-29`). No longer maintained.                          |
| 4.x             | Maintenance Mode  | 2019-05-29                  | >= 8          | >= 8                       | Java 8             | v4 migration. Check [guide][10]                                                        |
| 5.x             | Active            | 2019-05-29                  | >= 8          | >= 11                      | Java 21            | Update gradle to 8.7 and JDK to 21 and replace deprecated joda time.                   |

<sup><b>\*Maintenance mode includes only bug fixes and security updates.</b></sup>

## Installation

You can use your preferred method of managing dependencies in order to install the omise-java library. Below you will find some popular examples:

### Android

**WARNING:** Android users should check out our [omise-android][4] repository instead.

This library requires Java 8 and higher and is meant to be used with Java server
implementations.

### Gradle

Add the following line to your `build.gradle` file:

```gradle
dependencies {
    compile 'co.omise:omise-java:4.+'
}
```

### Kotlin

Add the following dependency to your `build.gradle.kts` file:

```kotlin
dependencies {
    implementation("co.omise:omise-java:4.+")
}
```

### Maven

Add the following dependency to your `pom.xml` file:

```xml
<dependency>
    <groupId>co.omise</groupId>
    <artifactId>omise-java</artifactId>
    <version>4.0.0</version>
</dependency>
```

### Shadow JAR

If you have dependency conflicts with the `omise-java` jar, you can try using the
[shadowed JAR][7] version that has the JAR dependencies relocated to the
`co.omise.dependencies` package.

You can obtain a [`shadowed jar`][7] by manually cloning the project and running the
`shadowJar` task:

```sh
$ git clone git://github.com/omise/omise-java
$ cd omise-java
$ gradle shadowJar
:compileJava
:processResources
:classes
:shadowJar

BUILD SUCCESSFUL

$ ls builds/libs
omise-java-4.0.0-all.jar
```

### Migration guide

- [v4](MIGRATING.md#migrating-from-v3-to-v4)
- [v3](MIGRATING.md#migrating-to-v3)

## Usage

Obtain a set of API keys from the [Opn Payments Dashboard][8] and create a `Client` object:

```java
Client client = new Client.Builder()
                        .publicKey("pkey_test_123")
                        .secretKey("skey_test_123")
                        .build();
```

Access the API by creating a `Request` and sending it through the `Client`, for example to get
current Balance:

```java
Request<Balance> request = new Balance.GetRequestBuilder().build();
Balance balance = client.sendRequest(request);

long total = balance.getTotal();
```

Creating a charge from a token:

```java
Client client = new Client.Builder()
                        .publicKey("pkey_test_123")
                        .build();

Request<Charge> request =
                    new Charge.CreateRequestBuilder()
                            .amount(100000) // 1,000 THB
                            .currency("thb")
                            .card("card_test_4xtsoy2nbfs7ujngyyq")
                            .build();
Charge charge = client.sendRequest(request);

System.out.println("created charge: " + charge.getId());
```

## License

[MIT license][9]
On February 23, 2023, we started redirecting users from search.maven.org to central.sonatype.com. Launched in September of 2022, central.sonatype.com provides the main functionality of search.maven.org with enhanced search results, including security vulnerability and software quality information.

[0]: https://github.com/omise/omise-java/workflows/Java%20CI%20with%20Gradle/badge.svg
[1]: https://github.com/omise/omise-java/actions
[2]: https://docs.opn.ooo/
[3]: mailto:support@opn.ooo
[4]: https://github.com/omise/omise-android
[5]: https://img.shields.io/maven-central/v/co.omise/omise-java.svg?style=flat-square
[6]: https://central.sonatype.com/artifact/co.omise/omise-java/4.2.0/versions
[7]: https://github.com/johnrengelman/shadow
[8]: https://dashboard.omise.co/test/api-keys
[9]: https://github.com/johnrengelman/shadow
[10]: https://github.com/omise/omise-java/blob/master/MIGRATING.md#migrating-from-v3-to-v4
