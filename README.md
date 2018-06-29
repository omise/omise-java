# OMISE-JAVA

[![Maven Central][9]][10] [![Circle CI][0]][1] [![Discourse Forum][14]][15]

This library has been updated to v2.7.5, check the [v1 branch][4] for the previous version.

Omise-java provides a set of Java bindings to the [Omise REST API][5].  Please contact
 [support@omise.co][7] if you have any questions regarding this
library and the functionality it provides.

# INSTALLATION

### Android

**WARNING:** Android users should check out our [omise-android][8] repository instead.

This library requires Java 7 and up and is meant to be used with Java server
implementations.

### Gradle\Maven

Adds to your `build.gradle` file.

```gradle
dependencies {
    compile 'co.omise:omise-java:2.7.5'
}
```

### Shadow JAR

If you have dependency conflicts with `omise-java` jar you can try using the
[shadowed JAR][11] version which has the JAR dependencies relocated to the
`co.omise.dependencies` package.

You can obtain a [`shadowed jar`][11] by manually cloing the project and running the
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
omise-java-2.7.5-all.jar
```

# USAGE

Obtain a set of API keys from the [Omise Dashboard][12] and creates a `Client` object:

```java
Client client = new Client("pkey_test_123", "skey_test_123");
```

Access the API by calling methods on the provided `Resource` classes, for example to get
current balance:

```java
long money = client.balance().get().getTotal();
```

Creating a charge from a token:

```java
Client client = new Client("pkey_test_123");

try {
    Charge charge = client.charges().create(new Charge.Create()
            .amount(100000) // THB 1,000.00
            .currency("THB")
            .card("tokn_test_123"));
    System.out.println("created charge: " + charge.getId());

} catch (IOException e) {
    e.printStackTrace();
}
```

# LICENSE

[MIT license][13]

[0]: https://img.shields.io/circleci/project/omise/omise-java.svg?style=flat-square
[1]: https://circleci.com/gh/omise/omise-java/tree/master
[2]: https://img.shields.io/gitter/room/omise/omise-java.svg?style=flat-square
[3]: https://gitter.im/omise/omise-java
[4]: https://github.com/omise/omise-java/tree/v1.0
[5]: https://www.omise.co/docs
[7]: mailto:support@omise.co
[8]: https://github.com/omise/omise-android
[9]: https://img.shields.io/maven-central/v/co.omise/omise-java.svg?style=flat-square
[10]: http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22co.omise%22%20AND%20a%3A%22omise-java%22
[11]: https://github.com/johnrengelman/shadow
[12]: https://dashboard.omise.co/test/api-keys
[13]: https://github.com/johnrengelman/shadow
[14]: https://img.shields.io/badge/discourse-forum-1a53f0.svg?style=flat-square&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAOCAYAAAAfSC3RAAAAAXNSR0IArs4c6QAAAAlwSFlzAAALEwAACxMBAJqcGAAAAVlpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IlhNUCBDb3JlIDUuNC4wIj4KICAgPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4KICAgICAgPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIKICAgICAgICAgICAgeG1sbnM6dGlmZj0iaHR0cDovL25zLmFkb2JlLmNvbS90aWZmLzEuMC8iPgogICAgICAgICA8dGlmZjpPcmllbnRhdGlvbj4xPC90aWZmOk9yaWVudGF0aW9uPgogICAgICA8L3JkZjpEZXNjcmlwdGlvbj4KICAgPC9yZGY6UkRGPgo8L3g6eG1wbWV0YT4KTMInWQAAAqlJREFUKBU9UVtLVFEU%2FvY%2B27mPtxl1dG7HbNRx0rwgFhJBPohBL9JTZfRQ0YO9RU%2FVL6iHCIKelaCXqIewl4gEBbEyxSGxzKkR8TbemmbmnDlzVvsYtOHbey1Y317fWh8DwCVMCfSHww3ElCs7CjuzbOcNIaEo9SbtlDRjZiNPY%2BvrqSWrTh7l3yPvrmh0KBZW59HcREjEqcGpElAuESRxopU648dTwfrIyH%2BCFXSH1cFgJLqHlma6443SG0CfqYY2NZjQnkV8eiMgP6ijjnizHglErlocdl5VA0mT3v102dseL2W14cYM99%2B9XGY%2FlQArd8Mo6JhbSJUePHytvf2UdnW0qen93cKQ4nWXX1%2FyOkZufsuZN0L7PPzkthDDZ4FQLajSA6XWR8HWIK861sCfj68ggGwl83mzfMclBmAQ%2BktrqBu9wOhcD%2BB0ErSiFFyEkdcYhKD27mal9%2F5FY36b4BB%2FTvO8XdQhlUe11F3WG2fc7QLlC8wai3MGGQCGDkcZQyymCqAPSmati3s45ygWseeqADwuWS%2F3wGS5hClDMMstxvJFHQuGU26yHsY6iHtL0sIaOyZzB9hZz0hHZW71kySSl6LIJlSgj5s5LO6VG53aFgpOfOFCyoFmYsOS5HZIaxVwKYsLSbJJn2kfU%2BlNdms5WMLqQRklX0FX26eFRnKYwzX0XRsgR0uUrWxplM7oqPIq8r8cZrdLNLqaABayxZMTTx2HVfglbP4xkcvqZEMNfmglevRi1ny5mGfJfTuQiBEq%2FMBvG0NqDh2TY47sbtJAuO%2Fe9%2Fn3STRFosm2WIxsFSFrFUfwHb11JNBNcaZSp8yb%2FEhHW3suWRNZRzDGvxb0oifk5lmnX2V2J2dEJkX1Q0baZ1MvYXPXHvhAga7x9PTEyj8a%2BF%2BXbxiTn78bSQAAAABJRU5ErkJggg%3D%3D
[15]: https://forum.omise.co
