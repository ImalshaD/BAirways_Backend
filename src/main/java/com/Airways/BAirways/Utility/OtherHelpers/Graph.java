package com.Airways.BAirways.Utility.OtherHelpers;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    // number of vertices
    private int n;
    // 2D matrix representing edges
    private int[][] matrix;
    // list of vertices
    private Vertex[] vertices;
    // list to keep track of vertices with 0 dependencies (in-degree)
    private int[] zeroDependencyList;

    // constructor to initialize the graph with number of vertices
    public Graph(int numberOfVertices) {
        this.n = numberOfVertices;
        this.matrix = new int[numberOfVertices][numberOfVertices];
        this.vertices = new Vertex[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            this.vertices[i] = new Vertex(i);
        }
        this.zeroDependencyList = new int[numberOfVertices];
    }

    // method to create an edge between two vertices
    public void createEdge(int frm, int to) {
        this.matrix[frm][to] = 1;
        this.vertices[to].incrementDependencies();
        this.zeroDependencyList[to] = 1;
    }

    // DFS to search for 0 in degree
    public int DFS(int initNode) {
        Vertex node = this.vertices[initNode];
        if (node.getDependencies() == 0) {
            updateDependencies(initNode);
            node.setDependencies(-1);
            return initNode;
        } else {
            for (int i = 0; i < this.n; i++) {
                if (this.matrix[initNode][i] == 1) {
                    int x = DFS(i);
                    if (x != -1) {
                        return x;
                    }
                }
            }
            return -1;
        }
    }

    // method to update dependencies (in-degree) of neighbouring vertices
    public void updateDependencies(int node) {
        for (int i = 0; i < this.n; i++) {
            if (this.matrix[node][i] == 1) {
                if (this.vertices[i].getDependencies() > 0) {
                    this.vertices[i].decrementDependencies();
                }
            }
        }
    }

    // method to generate topological sort
    public List<Integer> generateList() {
        List<Integer> returnList = new ArrayList<>();
        for (int i = 0; i < this.n; i++) {
            // in case of disjoint graph
            if (this.zeroDependencyList[i] == 0) {
                int x = DFS(i);
                while (x != -1) {
                    returnList.add(x + 1);
                    x = DFS(i);
                }
            }
        }
        return returnList;
    }
}
