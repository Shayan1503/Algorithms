package Algorithms.DataStructures;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private ArrayList<Vertex> vertices; // adjacency list
    private boolean directed;
//    private int length;

    /**
     * Default constructor <br>
     * Initializing ArrayList of vertices and defaulting the graph as an undirected graph.
     */
    public Graph() {
        vertices = new ArrayList<>();
        directed = false;
    }

    /**
     * Parameterized constructor <br>
     * Parses a string describing a graph to construct the graph data structure. <br>
     * Example of what the string of should look like : <br>
     * 3 --> number of vertices<br>
     * A --> vertices<br>
     * B <br>
     * C <br>
     * A B --> edges; in case of a directed graph, the direction of edge moves from A to B
     * C B <br>
     *
     * @param data String containing the details of a graph
     */
    public Graph(String data) {
        this();
        String[] lines = data.split("\n");
        int V = Integer.parseInt(lines[0]);
        int i = 1;

        // adding vertices
        for (; i <= V; i++) {
            addVertex(lines[i]);
        }

        // adding edges
        for (; i < lines.length; i++) {
            String v = String.valueOf(lines[i].charAt(0));
            String u = String.valueOf(lines[i].charAt(2));
            addEdge(v, u);
        }
    }

    /**
     * Parameterized constructor<br>
     * This is a temporary constructor. Works similar to {@link #Graph(String) Graph}, except it also decides if the
     * graph will be directed or not.
     *
     * @param data String containing details of a graph
     * @param directed true if graph is directed; false otherwise
     */
    public Graph(String data, boolean directed) {
        this();
        this.directed = directed;
        String[] lines = data.split("\n");
        int V = Integer.parseInt(lines[0]);
        int i = 1;

        for (; i <= V; i++) {
            addVertex(lines[i]);
        }

        for (; i < lines.length; i++) {
            String v = String.valueOf(lines[i].charAt(0));
            String u = String.valueOf(lines[i].charAt(2));

            if(directed) {
                Vertex v1 = vertices.get(indexOf(v));
                v1.children = new Child(u, v1.children);
            } else {
                addEdge(v, u);
            }
        }
    }

    /**
     * Adds a new vertex to the Graph structure. If vertex with the same name already exists then no new vertex is added.
     *
     * @param v String name of the new vertex to be added
     */
    public void addVertex(String v) {
        if (!containsVertex(v)) {
            vertices.add(new Vertex(v));
        }
    }

    /**
     * Adds new edge to the Graph structure. If edge between vertices already exists, then no new edge is added.
     *
     * @param v1 String name of one of the ends of the edge
     * @param v2 String name of the other end of the edge
     */
    public void addEdge(String v1, String v2) {
        // adding ends(vertices) of edge if not present
        addVertex(v1);
        addVertex(v2);

        if (!containsEdge(v1, v2)) {
            Vertex v = vertices.get(indexOf(v1));
            Vertex u = vertices.get(indexOf(v2));
            v.children = new Child(v2, v.children);
            u.children = new Child(v1, u.children);
        }
    }

    /**
     * Checks if the given vertex is present in the Graph structure.
     *
     * @param v String name of the vertex to be checked
     * @return true if vertex is present, false otherwise
     */
    public boolean containsVertex(String v) {
        return indexOf(v) != -1;
    }

    /**
     * Checks if the given edge is present in the Graph structure.
     *
     * @param v String name of one of the ends of the edge
     * @param u String name of the other end of the edge
     * @return true if edge is present, false otherwise
     */
    public boolean containsEdge(String v, String u) {
        if (!containsVertex(v) || !containsVertex(u)) {
            return false;
        }

        Vertex vertexV = vertices.get(indexOf(v));
        Vertex vertexU = vertices.get(indexOf(u));

        for (Child child = vertexV.children; child != null; child = child.next) {
            if (child.getName().equals(vertexU.getName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Finding the index number of the vertex in the adjacency list
     *
     * @param v String name of a vertex
     * @return index of that vertex; -1 if no such vertex exists
     */
    public int indexOf(String v) {
        for (int i = 0; i < vertices.size(); i++) {
            Vertex vertex = vertices.get(i);
            if (v.equals(vertex.getName())) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Printing the details of the Graph structure in an adjacency list fashion.
     * <p>
     * If we have a graph with vertex set V = {A, B, C} and edge set E = {(A, B), (B, C)}, then the corresponding
     * output for the <code>show</code> method will be <br>
     * A --> B <br>
     * B --> C, A <br>
     * C --> B
     */
    public void show() {
        for (Vertex v : vertices) {
            System.out.print(v.getName() + " --> ");
            for (Child child = v.children; child != null; child = child.next) {
                if (child.next == null)
                    System.out.print(child.getName());
                else
                    System.out.print(child.getName() + ", ");
            }
            System.out.println();
        }
    }

    /**
     * @return length of the adjacency list or cardinality of vertex set
     */
    public int getLength() {
        return vertices.size();
    }

    /**
     * Returns a <code>Vertex</code> type object with the corresponding name
     *
     * @param v String name of the desired vertex
     * @return Vertex type object; null if such vertex doesn't exist
     */
    public Vertex getVertex(String v) {
        if (containsVertex(v))
            return vertices.get(indexOf(v));
        else
            return null;
    }

    /**
     * Returns a <code>Vertex</code> type object with the corresponding index in the adjacency list
     *
     * @param index index of the desired vertex
     * @return Vertex type object; null if such vertex doesn't exist
     */
    public Vertex getVertex(int index) {
        if (index >= vertices.size()) {
            return null;
        }

        return vertices.get(index);
    }

    /**
     * Returns a <code>String</code> array of vertices
     *
     * @return String array
     */
    public String[] getVertexArray() {
        String[] V = new String[vertices.size()];

        int i = 0;
        for (Vertex v : vertices) {
            V[i] = v.getName();
            i++;
        }
        return V;
    }

    public boolean isDirected() { return directed;}
}
