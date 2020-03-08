## Development

> The project requires the jdbc mysql connector

```sh
# Debian/Ubuntu
export CLASSPATH=.:$CLASSPATH:/usr/share/java/mysql-connector-java-8.0.19.jar:$(pwd)/lib/commons-lang3-3.9.jar

# Then compile and the `JDBC.java` file
$ cd film/
$ javac Main.java && java Main
```
