# OMISE-JAVA

[![Maven Central][9]][10] [![Github Actions][0]][1]

`omise-java` provides a set of Java bindings to the [Omise REST API][5].  Please contact
 [support@opn.ooo][7] if you have any questions regarding this
library and the functionality it provides.

## INSTALLATION

### Android

**WARNING:** Android users should check out our [omise-android][8] repository instead.

This library requires Java 8 and up and is meant to be used with Java server
implementations.

### Gradle/Maven

Adds to your `build.gradle` file.

```gradle
dependencies {
    compile 'co.omise:omise-java:4.0.+'
}
```

### Shadow JAR

If you have dependency conflicts with `omise-java` jar you can try using the
[shadowed JAR][11] version which has the JAR dependencies relocated to the
`co.omise.dependencies` package.

You can obtain a [`shadowed jar`][11] by manually cloning the project and running the
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

### Migration Guide

* [v4](MIGRATING.md#migrating-from-v3-to-v4)
* [v3](MIGRATING.md#migrating-to-v3)

## USAGE

Obtain a set of API keys from the [Omise Dashboard][12] and create a `Client` object

```java
Client client = new Client.Builder()
                        .publicKey("pkey_test_123")
                        .secretKey("skey_test_123")
                        .build();
```

Access the API by creating a `Request` and sending it through the `Client`, for example to get
current Balance

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

## LICENSE

[MIT license][13]

[0]: https://github.com/omise/omise-java/workflows/Java%20CI%20with%20Gradle/badge.svg 
[1]: https://github.com/omise/omise-java/actions
[4]: https://github.com/omise/omise-java/tree/v1.0
[5]: https://www.omise.co/docs
[7]: mailto:support@opn.ooo
[8]: https://github.com/omise/omise-android
[9]: https://img.shields.io/maven-central/v/co.omise/omise-java.svg?style=flat-square
[10]: http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22co.omise%22%20AND%20a%3A%22omise-java%22
[11]: https://github.com/johnrengelman/shadow
[12]: https://dashboard.omise.co/test/api-keys
[13]: https://github.com/johnrengelman/shadow
