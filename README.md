# OMISE-JAVA

[![Maven Central][9]][10] [![Circle CI][0]][1] [![Gitter][2]][3]

This library has been updated to v2.0, check the [v1 branch][4] for the previous version.

Omise-java provides a set of Java bindings to the [Omise REST API][5].  Please contact
[chakrit@omise.co][6] or [support@omise.co][7] if you have any questions regarding this
library and the functionality it provides.

# INSTALLATION

**WARNING:** Android users should check out our [omise-android][8] repository instead.

This library requires Java 7 and up and is meant to be used with Java server
implementations.

Adds to your `build.gradle` file.

```gradle
dependencies {
    compile 'co.omise:omise-java:2.0.7'
}
```

Or you can obtain a [`shadowJar`][11] by manually cloing the project and running the
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
omise-java-2.0.7-all.jar
```

# GETTING STARTED

Obtain a set of API keys from the [Omise Dashboard][12] and creates a `Client` object:

```java
Client client = new Client("pkey_test_123", "skey_test_123");
```

Access the API by calling methods on the provided `Resource` classes, for example to get
current balance:

```java
long money = client.balance().get().getTotal();
```

# LICENSE

[MIT license][13]

[0]: https://img.shields.io/circleci/project/omise/omise-java.Gt.svg?style=flat-square
[1]: https://circleci.com/gh/omise/omise-java/tree/master
[2]: https://img.shields.io/gitter/room/omise/omise-java.svg?style=flat-square
[3]: https://gitter.im/omise/omise-java
[4]: https://github.com/omise/omise-java/tree/v1.0
[5]: https://www.omise.co/docs
[6]: mailto:chakrit@omise.co
[7]: mailto:support@omise.co
[8]: https://github.com/omise/omise-android
[9]: https://img.shields.io/maven-central/v/co.omise/omise-java.svg?style=flat-square
[10]: http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22co.omise%22%20AND%20a%3A%22omise-java%22
[11]: https://github.com/johnrengelman/shadow
[12]: https://dashboard.omise.co/test/api-keys
[13]: https://github.com/johnrengelman/shadow
