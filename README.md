# ArgX

Map command to schema class. Parse and validate.

## Install

### Add our repository

```xml
<repositories>
    <repository>
        <id>2lstudios-repo</id>
        <url>https://ci.2lstudios.dev/plugin/repository/everything/</url>
    </repository>
</repositories>
```

### Import as dependency

```xml
<dependencies>
    <dependency>
        <groupId>com.sammwy</groupId>
        <artifactId>argx</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

## Example

```java
// Entity.java
import com.sammwy.argx.annotations.Argument;

public class Entity {
    @Argument(key = "n", description = "Your name", required = true)
    public String name;

    @Argument(key = "a", description = "Your age", required = true)
    public int age;

    @Argument(key = "e", description = "Is Enabled?")
    public boolean enabled;
}

// Main.java
String input = "--name sammwy -a 21 --enabled yes hello world";
Entity entity = ArgX.parse(Entity.class, input);

System.out.println(entity.name);    // Output: sammwy
System.out.println(entity.age);     // Output: 21
System.out.println(entity.enabled); // Output: true
```

## Supported types

- [X] String.
- [X] Integer.
- [X] Booleans.

## 🤝 Contributing

Contributions, issues and feature requests are welcome!
Feel free to check [issues page](https://github.com/sammwyy/argx/issues).

## ❤️ Show your support

Give a ⭐️ if this project helped you!

Or buy me a coffeelatte 🙌🏾

[Ko-fi](https://ko-fi.com/sammwy) | [Patreon](https://patreon.com/sammwy)

## 📝 License

Copyright © 2023 [Sammwy](https://github.com/sammwyy).  
This project is [MIT](LICENSE) licensed.
