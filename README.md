# Spring Boot Fundamentals

This file explains the core ideas behind a Spring Boot application, how it works internally, how Java code is compiled, how dependency injection works, how Spring manages objects, and how a web request flows through a servlet-based server.

This README is designed for absolute beginners who already know the basics of backend development in Node.js, so the ideas are explained in a way that connects to familiar concepts such as Express, dependency injection, object creation, routing, and server startup.

---

## 1. What is Spring Boot?

Spring Boot is a framework built on top of the Spring Framework. It helps developers create production-ready Java applications quickly with minimal configuration.

It is designed to:
- reduce boilerplate configuration
- provide embedded web servers
- enable auto-configuration
- support REST APIs, web apps, scheduled jobs, data access, messaging, etc.
- integrate well with Maven and Gradle

In simple terms:
- Spring gives the application framework and inversion of control
- Spring Boot makes it easier to start and run the application with sensible defaults

Think of it like this in Node.js terms:
- Express is a web framework for Node.js
- Spring Boot is a full Java framework ecosystem that gives you a web server, dependency injection, configuration system, bean management, and many built-in features out of the box

If you are comfortable with Node.js, then Spring Boot is the Java equivalent of:
- Express + app bootstrap + routing + config + service architecture + dependency injection + application lifecycle management

---

## 2. Why Spring Boot is Popular

Spring Boot is widely used because it makes Java development faster and simpler.

Benefits:
- Minimal setup
- Embedded Tomcat/Jetty server
- Starter dependencies
- Production-ready features
- Easy configuration via `application.properties` or `application.yml`
- Works well with REST APIs and microservices

---

## 3. Basic Terminologies

### 3.1 Java
Java is a general-purpose, object-oriented programming language. A Spring Boot project is a Java project.

Key ideas:
- classes
- objects
- methods
- inheritance
- encapsulation
- polymorphism

---

### 3.2 Maven
Maven is a build and dependency management tool used in Java projects.

It manages:
- dependencies
- build lifecycle
- packaging
- project structure
- plugin execution

The file `pom.xml` is the central Maven configuration.

Example:
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

This tells Maven:
- download the Spring Boot web starter
- include required libraries automatically

Node.js comparison:
- In Node.js, you usually use `package.json` and `npm install`
- In Java/Spring Boot, you use `pom.xml` and Maven to download jars and manage the project

---

### 3.3 Spring Framework
Spring is a large Java ecosystem for building applications using enterprise patterns.

It provides:
- dependency injection
- AOP
- web MVC
- transactions
- security
- data access

Spring Boot is a layer on top of Spring that reduces configuration.

---

### 3.4 Spring Container
The Spring container is the runtime environment that creates, configures, and manages application objects. These objects are called beans.

The container is responsible for:
- creating beans
- wiring dependencies
- managing bean lifecycle
- destroying beans when no longer needed

Two main container types:
- `BeanFactory` — basic container
- `ApplicationContext` — advanced container used by Spring Boot

---

### 3.5 Bean
A bean is any object managed by Spring.

Examples:
- service class
- repository class
- controller class
- configuration class

Spring creates and manages these objects.

Think of a bean like a Node.js module instance that is created and shared by a framework for you.

```java
@Service
public class UserService {
    public String getMessage() {
        return "Hello";
    }
}
```

Now Spring can manage this `UserService` object for you.

---

### 3.6 Dependency
A dependency is an object required by another object.

Example:
```java
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

Here, `UserRepository` is a dependency of `UserService`.

---

### 3.7 Dependency Injection (DI)
Dependency Injection means giving an object its dependencies from outside rather than creating them internally.

Benefits:
- loose coupling
- easier testing
- cleaner code
- easier maintenance

Types of DI:
- Constructor injection
- Setter injection
- Field injection

Example:
```java
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

Spring automatically provides `UserRepository` to `UserService` if it is registered as a bean.

---

### 3.8 Inversion of Control (IoC)
Inversion of Control means the control of object creation and lifecycle is given to the framework instead of the developer writing manual `new` logic.

Instead of:
```java
UserService service = new UserService(new UserRepository());
```

Spring does this for you.

---

### 3.9 IoC vs DI

| Concept | Node.js idea | Spring idea |
|---|---|---|
| Manual object creation | `new UserService()` | Spring creates it automatically |
| Dependency passing | `const repo = require(...)` | Spring injects the dependency |
| Framework control | You decide when to instantiate | Spring controls lifecycle |

---

### 3.10 Component Scanning
Spring scans packages to find classes marked with annotations such as:
- `@Component`
- `@Service`
- `@Repository`
- `@Controller`
- `@RestController`
- `@Configuration`

These classes are registered as beans.

---

### 3.11 Annotation Types

#### `@SpringBootApplication`
```java
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

Combines:
- `@Configuration`
- `@EnableAutoConfiguration`
- `@ComponentScan`

#### `@RestController`
Used for REST API classes. Combines `@Controller` and `@ResponseBody`.

#### `@Service`
Used for business logic classes.

#### `@Repository`
Used for database access classes.

#### `@Autowired`
Injects dependency automatically. Modern best practice prefers constructor injection.

#### `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`
Specific REST mappings for HTTP methods.

#### `@Value`
Injects values from configuration files.
```java
@Value("${app.name}")
private String appName;
```

---

## 4. How a Spring Boot Application Works

The startup flow:

1. Java program starts at `main()`
2. `SpringApplication.run(...)` is called
3. Spring Boot creates the application context
4. It scans for components and beans
5. Auto-configuration loads default settings
6. Embedded web server starts
7. HTTP requests are handled by servlet and controller layers

---

## 5. Spring Boot Startup Lifecycle

| Step | What happens | Node.js analogy |
|---|---|---|
| Compile `.java` | Java compiler creates `.class` bytecode | Transpiling TypeScript |
| Maven build | Resolves dependencies from `pom.xml` | `npm install` |
| Package | Creates `.jar` artifact | Building a bundled app |
| `main()` runs | `SpringApplication.run(...)` starts Spring | `app.listen(3000)` |
| Beans created | Spring scans and registers all beans | Modules loaded in memory |
| Auto-config | Defaults configured (server, DB, security) | Framework middleware setup |
| Server ready | Tomcat listens on port `8080` | Server accepts requests |

---

## 6. Dependency Injection in Detail

### Constructor Injection (Preferred)
```java
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

Why preferred:
- immutable
- easier testing
- explicit dependencies

### Setter Injection
```java
@Autowired
public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
}
```

### Field Injection
```java
@Autowired
private UserRepository userRepository;
```

Simpler but less recommended due to testability concerns.

---

## 7. Servlet and Spring Boot

A Servlet is a Java class that handles HTTP requests and responses.

Spring Boot uses an embedded servlet container (Tomcat) — no external server needed.

### Request Flow
```
Client
  ↓
HTTP request
  ↓
Embedded Tomcat
  ↓
DispatcherServlet
  ↓
Controller
  ↓
Service
  ↓
Repository / Database
  ↓
Response
```

`DispatcherServlet` is the front controller that routes requests to controller methods.

---

## 8. Controller, Service, Repository Pattern

### Controller
```java
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
```

### Service
```java
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }
}
```

### Repository
```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA provides CRUD methods automatically
    List<User> findByName(String name);
    Optional<User> findByEmail(String email);
}
```

---

## 9. Model / Entity

```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    // Constructors, getters, setters
    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
```

---

## 10. Auto-Configuration

Spring Boot auto-configuration tries to configure common application settings automatically.

Examples:
- web server configuration
- database connection configuration
- security setup
- message converters
- actuator endpoints

This works by reading dependencies in `pom.xml` and then applying suitable defaults.

---

## 11. application.properties

```properties
server.port=8080
spring.application.name=spring-boot-lab

# H2 In-Memory Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```

---

## 12. Spring Boot Project Structure

```
spring-boot-lab/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── demo/
│   │   │               ├── DemoApplication.java
│   │   │               ├── controller/
│   │   │               │   └── UserController.java
│   │   │               ├── service/
│   │   │               │   └── UserService.java
│   │   │               ├── repository/
│   │   │               │   └── UserRepository.java
│   │   │               └── model/
│   │   │                   └── User.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── demo/
│                       └── DemoApplicationTests.java
├── pom.xml
├── Dockerfile
├── mvnw
├── mvnw.cmd
├── .gitignore
└── README.md
```

---

## 13. Project Structure in Tabular Form

| Path / File | Purpose | Explanation |
|---|---|---|
| `pom.xml` | Maven configuration | Declares project metadata, Java version, dependencies, and plugins |
| `src/main/java` | Java source code | Contains controllers, services, repositories, models |
| `src/main/resources` | Configuration and static files | Stores `application.properties`, templates, static assets |
| `src/test/java` | Unit and integration tests | Contains test files for validating app behavior |
| `DemoApplication.java` | Application entry point | Contains `main()` and `@SpringBootApplication` |
| `controller/` | Web request handling | Receives HTTP requests and calls services |
| `service/` | Business logic | Implements core logic for application features |
| `repository/` | Data access layer | Handles database interaction using Spring Data JPA |
| `model/` | Data classes | Represents entities like `User`, `Product`, etc. |
| `Dockerfile` | Container definition | Packages the app as a Docker image |
| `mvnw` / `mvnw.cmd` | Maven wrapper | Runs Maven without globally installing it |

---

## 14. Typical Spring Boot Request Flow

```
Request
  ↓
Tomcat (embedded)
  ↓
DispatcherServlet
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
Database / External API
  ↓
Response returned to client
```

---

## 15. Bean Lifecycle

1. bean instantiated
2. dependencies injected
3. initialization methods executed (`@PostConstruct`)
4. bean used in application
5. bean destroyed when context shuts down (`@PreDestroy`)

---

## 16. Common Spring Boot Starter Dependencies

| Starter | Purpose |
|---|---|
| `spring-boot-starter-web` | Web application and REST APIs |
| `spring-boot-starter-data-jpa` | Database access using JPA |
| `spring-boot-starter-security` | Security features |
| `spring-boot-starter-test` | Unit/integration tests |
| `spring-boot-starter-thymeleaf` | HTML view templates |
| `spring-boot-starter-actuator` | Monitoring endpoints |

---

## 17. Spring Boot vs Node.js Backend

| Topic | Node.js (Express) | Spring Boot |
|---|---|---|
| Runtime | Node.js runtime | Java Virtual Machine (JVM) |
| Package manager | npm / package.json | Maven / pom.xml |
| Server startup | `app.listen(3000)` | `SpringApplication.run(...)` |
| Request handling | route handlers + middleware | controllers + DispatcherServlet |
| Dependency management | `require()` / imports | Spring bean container + DI |
| Object creation | manual instantiation | container-managed beans |
| Configuration | `.env`, JSON config | `application.properties` / `application.yml` |
| Request routing | Express router | `@GetMapping`, `@PostMapping` |
| Business layer | service functions | `@Service` classes |
| Data access | Prisma / Sequelize | `@Repository` + JPA / JDBC |
| Framework style | minimal and flexible | opinionated and convention-based |
| Auto-configuration | not built-in | built-in Spring Boot auto config |
| Lifecycle management | you write your own | Spring manages bean lifecycle |

---

## 18. Running the Application

### Option 1: Maven Wrapper
```bash
./mvnw spring-boot:run
```

### Option 2: Build and Run JAR
```bash
./mvnw clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

### Option 3: Docker
```bash
docker build -t spring-boot-lab .
docker run -p 8080:8080 spring-boot-lab
```

Then open:
```
http://localhost:8080/hello
http://localhost:8080/users
http://localhost:8080/h2-console
```

---

## 19. Available REST Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/hello` | Simple hello world response |
| GET | `/users` | Get all users |
| GET | `/users/{id}` | Get user by ID |
| POST | `/users` | Create a new user |
| PUT | `/users/{id}` | Update an existing user |
| DELETE | `/users/{id}` | Delete a user |
| GET | `/h2-console` | H2 database browser UI |
| GET | `/actuator/health` | App health status |

---

## 20. Minimal Example

```java
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

```java
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot!";
    }
}
```

Node.js equivalent:
```js
const express = require('express');
const app = express();

app.get('/hello', (req, res) => {
  res.send('Hello from Spring Boot!');
});

app.listen(3000, () => {
  console.log('Server running on port 3000');
});
```

---

## 21. Final Note

A Spring Boot application is essentially a Java application running inside an embedded servlet container, with Spring controlling object creation, dependencies, and lifecycle. It uses Maven for project management and packaging.

If you understand:
- Java basics
- Maven
- Spring IoC and Dependency Injection
- Spring Boot annotations
- Servlet-based request flow

then you are already on the path to building real-world Spring Boot applications.
