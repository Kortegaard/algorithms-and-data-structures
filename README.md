

compiling all (mac)
```
find . -name "*.java" -print | xargs javac -d bin
```

compile single file
```
javac -d bin -cp bin src/javads/datastructures/LinkedList.java
```

running a main function from Class (linked list here)
```
java -cp bin javads.datastructures.LinkedList
```

