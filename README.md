## Development

> The project requires the jdbc mysql connector

```sh
# Debian/Ubuntu
export CLASSPATH=.:$CLASSPATH:$(pwd)/lib/mysql-connector-java-8.0.19.jar:$(pwd)/lib/commons-lang3-3.9.jar

# Make sure to be in the film/ directory!
$ cd film/

# Then compile and run the `Main` class
$ javac Main.java && java Main
```

### Compiling the program

```sh
$ jar -cvmf filmdb.mf filmdb.jar *.class
```

## Running the program

```sh
$ java -jar filmdb.jar
```

## Demo

![demo](./demo.svg)
