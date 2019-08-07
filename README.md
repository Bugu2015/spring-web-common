# spring-web-common

```mxml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>spring.web.boot</groupId>
  <artifactId>spring-web-boot</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>spring-web-boot</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
    <boot.version>2.0.1.RELEASE</boot.version>
  </properties>

  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-dependencies</artifactId>
        <version>Finchley.RC1</version>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>

  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.0.1.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
  </parent>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
          <jvmArguments>-Dfile.encoding=UTF-8</jvmArguments>
        </configuration>
      </plugin>
    </plugins>
  </build>

  <dependencies>
    <dependency>
      <groupId>spring.web.common</groupId>
      <artifactId>spring-web-commom</artifactId>
      <version>0.0.1-SNAPSHOT</version>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>
  </dependencies>

</project>
```

```java
package spring.web.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import spring.web.common.TaskExecutor;
import spring.web.common.context.ContextBody;
import spring.web.common.context.ContextFilter;
import spring.web.common.exception.ExceptionHandle;
import spring.web.common.logging.LogBackConverter;

@EnableAsync
@SpringBootApplication
public class BootApp {

    @Bean
    ThreadPoolTaskExecutor taskExecutor(){
        return new TaskExecutor();
    }

    @Bean
    ContextFilter contextFilter(){
        return new ContextFilter();
    }

    @Bean
    ContextBody contextBody(){
        return new ContextBody();
    }

    @Bean
    LogBackConverter logBackConverter(){
        return new LogBackConverter();
    }

    @Bean
    ExceptionHandle exceptionHandle(){
        return new ExceptionHandle();
    }

    public static void main( String[] args ) {
        SpringApplication.run(BootApp.class, args);
    }

}
```

```properties
# application.properties
server.port=11000
spring.mvc.throw-exception-if-no-handler-found=true
spring.resources.add-mappings=false
```