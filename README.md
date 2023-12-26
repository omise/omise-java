# Omise-Java

[![Maven Central][5]][6] [![Github Actions][0]][1]

`omise-java` provides a set of Java bindings to the [Opn Payments REST API][2].  Please contact
 [support@opn.ooo][3] if you have any questions regarding this
library and the functionality it provides.

## Installation

### Android

**WARNING:** Android users should check out our [omise-android][4] repository instead.

This library requires Java 8 and up and is meant to be used with Java server
implementations.

### Gradle/Maven

Add to your `build.gradle` file.

```gradle
dependencies {
    compile 'co.omise:omise-java:4.0.+'
}
```

### Shadow JAR

If you have dependency conflicts with `omise-java` jar, you can try using the
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

* [v4](MIGRATING.md#migrating-from-v3-to-v4)
* [v3](MIGRATING.md#migrating-to-v3)

## Usage

Obtain a set of API keys from the [Opn Payments Dashboard][8] and create a `Client` object

```java
Client client = new Client.Builder()
                        .publicKey("pkey_test_123")
                        .secretKey("skey_test_123")
                        .build();
```

Access the API by creating a `Request` and sending it through the `Client`, for example to get
current Balance;

```java
Request<Balance> request = new Balance.GetRequestBuilder().build();
Balance balance = client.sendRequest(request);

long total = balance.getTotal();
```

Creating a charge from a token

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
