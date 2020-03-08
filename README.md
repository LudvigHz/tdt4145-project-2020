## Development

> The project requires the jdbc mysql connector

```sh
# Debian/Ubuntu
export CLASSPATH=.:$CLASSPATH:/usr/share/java/mysql-connector-java-8.0.19.jar

# Then compile and the `JDBC.java` file
$ cd film/
$ javac Main.java && java Main
```
