# Prims
File Package for implementation of Prim's Algorithm for Finding Miniumum Weight Spanning Trees
To run program:
Simply type "Make" into terminal to build top level file.
Run program with file name for input data as well as file name to which output data will be written included as command line arguments.
Input data file should be command line arg 1.  Output data file should be command line arg 2.

The input data file must of course contain the graph you wish to extract a minimum weight spanning tree from.
In the file, your graph must be represented as a list of edges.  The first two lines of this list will represent the order (number of vertices in the graph 
and the size (number of edges in the graph) of the graph, respectively.  Below is featured an example of how the input file must be formatted.

7  
11  
1 2 6  
1 4 3  
2 3 5  
2 5 8  
3 5 1  
3 7 4  
4 5 7  
4 6 9  
5 6 6  
5 7 5  
6 7 2  

The order of the above graph is 7.  The size of the above graph is 11.  This means that there are 7 vertices and 11 edges in the graph.
Each of the subsequent lines are formatted in the folling way:

<vertex_1> <vertex_2> <edge_weight>

This means that there is an edge between <vertex_1> and <vertex_2> and its weight is <edge_weight>.
For instance, from the above example, the first edge listed is "1 2 6".  This means that there is an edge between vertex 1 and vertex 2 and its weight is 6.

The output file will have the following data in it upon the completion of the algorithm;

 5: (3, 5) 1.0  
 11: (6, 7) 2.0  
 2: (1, 4) 3.0  
 6: (3, 7) 4.0  
 3: (2, 3) 5.0  
 1: (1, 2) 6.0   
Total Weight = 21.00  

As you can see, each of the edges included in the minimum weight spanning tree are included and their edge numbers coincide with the order in which they were encountered
in the input file.  The total weight of the entire spanning tree is also listed at the bottom with the title "Total Weight".

Please note that both your input file and output file must be included in the local directory with the application as specified in the first paragraph of this README file.

Happy tree spanning!
