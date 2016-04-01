# OMISE-JAVA

[![Circle CI](https://circleci.com/gh/omise/omise-java/tree/master.svg?style=svg)](https://circleci.com/gh/omise/omise-java/tree/master)
[![Gitter](https://badges.gitter.im/omise/omise-java.svg)](https://gitter.im/omise/omise-java?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=body_badge)

This library has been updated to v2.0, check the
[v1 branch](https://github.com/omise/omise-java/tree/v1.0)
for the previous version.

Omise-java provides a set of Java bindings to the
[Omise REST API](https://www.omise.co/docs).

Please contact [chakrit@omise.co](mailto:chakrit@omise.co) or
[support@omise.co](support@omise.co) if you have any questions regarding this library and
the functionality it provides.

# REQUIREMENTS

This library supports Java 7 and up and Android version 4 and up.

# INSTALLATION

Adds to your `build.gradle` file.

```gradle
dependencies {
    compile 'co.omise:omise-java:2.0.0'
}
```

# GETTING STARTED

Obtain a set of API keys from the
[Omise Dashboard](https://dashboard.omise.co/test/api-keys) and creates a `Client` object:

```java
Client client = new Client("pkey_test_123", "skey_test_123");
```

Access the API by calling methods on the provided `Resource` classes:

```java
long money = client.balance().get().getTotal();
```

# TASKS

TBD

# LICENSE

MIT, See [LICENSE](https://github.com/omise/omise-java/blob/master/LICENSE) file for the
full text.

* Targets 1.6 in `build.gradle` and IntelliJ Gradle settings.
  http://stackoverflow.com/questions/16679593/gradle-compilejava-task-warning-options-bootstrap-class-path-not-set-in-conju
