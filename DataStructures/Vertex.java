package Algorithms.DataStructures;

public class Vertex {
    private final String name;
    Child children;

    Vertex(String name) {
        this.name = name;
        children = null;
    }

    String getName() {
        return this.name;
    }
}

class Child {
    private final String name;
    Child next;

    Child (String name, Child child) {
        this.name = name;
        next = child;
    }

    String getName() {
        return this.name;
    }
}