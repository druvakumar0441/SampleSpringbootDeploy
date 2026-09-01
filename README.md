# SampleSpringbootDeploy

This is a sample Spring Boot application named "SampleSpringbootDeploy". 

## Project Structure

```
SampleSpringbootDeploy
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── samplespringbootdeploy
│   │   │               ├── SampleSpringbootDeployApplication.java
│   │   │               └── controller
│   │   │                   └── SampleController.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── static
│   │       └── templates
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── samplespringbootdeploy
│                       └── SampleSpringbootDeployApplicationTests.java
├── pom.xml
└── README.md
```

## Setup Instructions

Prerequisites: Java 25 (the latest LTS release) and Maven 3.9 or newer.

After changing `JAVA_HOME`, close and reopen Command Prompt or the VS Code terminal. Verify Maven uses Java 25 with `mvn -version`; its `Java version` and `runtime` must point to the JDK 25 installation.

1. **Clone the repository**:
   ```
   git clone <repository-url>
   ```

2. **Navigate to the project directory**:
   ```
   cd SampleSpringbootDeploy
   ```

3. **Build the project**:
   ```
   $env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-25.0.4.101-hotspot"
   mvn clean install
   ```

4. **Run the application**:
   ```
   $env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-25.0.4.101-hotspot"
   mvn spring-boot:run
   ```

## Usage

Once the application is running, you can access the endpoints defined in the `SampleController` class. 

## Dependencies

This project uses Maven for dependency management. The dependencies are specified in the `pom.xml` file.

## License

This project is licensed under the MIT License.