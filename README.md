![Build and deployment](https://github.com/MessiasLima/soccer-time-converter/workflows/Build%20and%20deployment/badge.svg)
[![Coverage Status](https://coveralls.io/repos/github/MessiasLima/soccer-time-converter/badge.svg?branch=master)](https://coveralls.io/github/MessiasLima/soccer-time-converter?branch=master)
[![License](https://img.shields.io/github/license/MessiasLima/soccer-time-converter)](https://img.shields.io/github/license/MessiasLima/soccer-time-converter)

# ⚽ Soccer time converter

Soccer timer converter is a service that helps you on soccer match time conversion.

The application is available on https://soccer-time-converter.herokuapp.com/swagger-ui.html

## 🧑‍💻 Environment

To set up a development environment for this application you may need to set up the [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

You also need the [Gradle build tool](https://gradle.org/). You can also use the Gradle wrapper configured on this project.

## 🚀 Running the application

I developed this application using Gradle build tool and the Spring Boot framework. To run the application you need to:

1. On command line (or on your favorite Java IDE) run `bootRun` gradle task.

    ```
    ./gradlew bootRun
    ```

1. The application will be available on `http://localhost:8080`

    - You can access http://localhost:8080/swagger-ui.html to see the swagger interface to the service

1. To convert a value, you need to execute a `GET` request to http://localhost:8080/converter passing a input on `matchTime` query parameter.

```
Input: [H1] 45:00.001
URL: http://localhost:8080/converter?matchTime=%5BH1%5D%203%3A07.513
```

**_Note: the input must be url encoded_**

**_Note 2: you don't need to worry about the input encoding if you are using swagger-ui to make the request._**

## 🧪 Running unit tests

To run the tests you need to run the `test` gradle task.

```
./gradlew test
```

After the test, testing reports are generated. You can check this on `/build/reports/jacoco/test/html` folder.

## 🛠️ Building the application

To build the application you just need to run the `build` task.

```
./gradlew build
```

## 😎 Other interesting stuff

-   The [Spotless](https://github.com/diffplug/spotless) to verify the code style.
-   The [JaCoCo test coverage verification](https://docs.gradle.org/current/userguide/jacoco_plugin.html) is configured to ensure that at least 80% of the lines and branches are covered by tests.
-   I configured [Netris commit lint plugin](https://plugins.gradle.org/plugin/ru.netris.commitlint) to lint the commit messages to meet the [Conventional commits](https://www.conventionalcommits.org/en/v1.0.0/) specification.
-   [Gradle Git Hook](https://github.com/STAR-ZERO/gradle-githook) is being used to integrate the code style and commit message verification on git hooks.
-   The pull request or push in master branch will trigger a pipeline automation that build the application (run tests and check style) and deploy it on [Heroku](https://www.heroku.com/home)

## License

    MIT License

    Copyright (c) 2020 Messias Junior

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
