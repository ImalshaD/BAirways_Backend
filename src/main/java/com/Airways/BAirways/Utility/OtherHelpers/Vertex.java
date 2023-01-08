package com.Airways.BAirways.Utility.OtherHelpers;

public class Vertex {
    // number of the vertex
    private int number;
    // number of dependencies (in-degree) of the vertex
    private int dependencies;

    // constructor to initialize the vertex
    public Vertex(int number) {
        this.number = number;
        this.dependencies = 0;
    }

    // getter method for number of dependencies (in-degree)
    public int getDependencies() {
        return this.dependencies;
    }

    // method to increment number of dependencies (in-degree)
    public void incrementDependencies() {
        this.dependencies++;
    }

    // method to decrement number of dependencies (in-degree)
    public void decrementDependencies() {
        this.dependencies--;
    }

    // setter method for number of dependencies (in-degree)
    public void setDependencies(int dependencies) {
        this.dependencies = dependencies;
    }
}
