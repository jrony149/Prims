
import java.util.*;

import java.lang.*;

import java.io.*;

import java.math.*;

import java.text.DecimalFormat;


public class MWST {

    int iterator = 0;

    public float runningSum = 0;

    public int[][] edgeLabels; //The two dimensional array that houses the edge lables for each pair of adjacent nodes.

    List mwst = new List(); //This list that houses the Minimum Weight Spanning tree.

    float inf = Float.POSITIVE_INFINITY;

    public BinHeap Q; //The priority queue.

    public int lineCount = 0;

    public Scanner s;

    public PrintWriter printWrite;

    private String str;

    public Graph G;

    private int order;

    private int size;

    public String a;

    //public minBHeap minHeap;

    public class Qelement{

        public float key;

        public int parent;

        public int vert;

        public Qelement(float k, int p, int v){

            key = k;

            parent = p;

            vert = v;

        }

        public int getParent(){

           return parent;
        }

        public float getKey(){

            return key;
        }

        public int getVert(){

            return vert;
        }

        public String toString(){

            return("(" + parent + ", " + vert + ")" + " " + key);
        }
    }

    public void openInputFile(String str) {

        try {

            s = new Scanner(new File(str));

        } catch (Exception e) {

            System.out.println("Could not find file." + System.getProperty("user.dir"));
        }
    }

    public void openOutputFile(String str) {

        try {

            printWrite = new PrintWriter(new FileWriter(str));
        } catch (Exception e) {

            System.out.println("Could not find file." + System.getProperty("user.dir"));
        }
    }

    public void writeString(String str) {

        printWrite.print(str);
    }

    public void writeMst(List mwSet) { //takes the List that is the min spanning tree set of edges.

        DecimalFormat df = new DecimalFormat("#.00");
        df.setRoundingMode(RoundingMode.HALF_EVEN);

        DecimalFormat df2 = new DecimalFormat("#.0");
        df2.setRoundingMode(RoundingMode.HALF_EVEN);

        int lineCount2 = 1;

        mwSet.moveFront();

        for (int x = 0; x < mwSet.length(); x++) {

            String input = mwSet.get().toString();

            Qelement temp = new Qelement(0, 0, 0);

            temp = (Qelement) mwSet.get();

            printWrite.format("%4d%2s%s%d%s%d%s%s\n", edgeLabels[temp.parent][temp.vert], ": ", "(", temp.parent, ", ", temp.vert, ") ", df2.format(temp.key));
            //System.out.printf(edgeLabels[temp.parent][temp.vert] + ": " + input);

            //printWrite.print("\r\n");

            mwSet.moveNext();

        }

        printWrite.format("%s\n", "Total Weight = " + df.format(runningSum));

    }

    public void closeInputFile() { //closes input file.

        s.close();
    }

    public void closeOutputFile() { //closes output file.

        printWrite.close();
    }

    public void createGraph() {

        String empty = "";

        while (s.hasNextLine()) {

            a = s.nextLine();


            if( a.equals(empty)){

                break;
            }

            else {

                if (lineCount == 0) {

                    str = a; //a.substring(0, a.indexOf(" "));

                    order = Integer.parseInt(str);

                    G = new Graph(order);

                    Q = new BinHeap(order);

                    edgeLabels = new int[(order + 1)][(order + 1)];


                }

                if (lineCount == 1) {

                    str = a;

                    int size = Integer.parseInt(str);

                    if (size <= (order - 2)) {

                        throw new RuntimeException("Error.  Cannot derive minimum weight spanning tree from disconnected graph.\n");
                    }


                }

                if (lineCount > 1) {

                    String[] params = a.split(" ");//params[0] = parent or 'u'; params[1] = child or 'v'; params[2] = weight

                    G.addEdge(Integer.parseInt(params[0]), Integer.parseInt(params[1]), Float.parseFloat(params[2]));

                    edgeLabels[Integer.parseInt(params[0])][Integer.parseInt(params[1])] = (lineCount - 1);
                    edgeLabels[Integer.parseInt(params[1])][Integer.parseInt(params[0])] = (lineCount - 1);

                }

            }

            lineCount++;
        }

    }

    public void peekPrint(Graph G){

        for(int x = 1; x < (G.getOrder()); x++){

            G.listOfAdjacencies[x].peek();

            System.out.println("\r\n");
        }

    }

    public void mst(Graph g){

        for(int x = 0; x < order; x++) {

            Qelement temp;

            if (x == 0) {

                temp = new Qelement(0, -2, 1); //-2 is a stand-in for "null"

                Q.insert(temp);
            }


            if (x > 0) {

                temp = new Qelement(inf, -2, (x + 1));

                Q.insert(temp);
            }
        }

        Graph verty = new Graph(1);

        Graph.Vertex temp4 = verty.new Vertex(0, 0);

        Qelement temp3 = new Qelement(0, 0, 0);

        while(!Q.isEmpty()) {

            Qelement temporary = new Qelement(0, 0, 0);

            temporary = (Qelement) Q.findMin();

            Q.deleteMin();

            int u = temporary.getVert();

            if (iterator > 0) {

                mwst.append(temporary);

                runningSum = runningSum + temporary.getKey();
            }

            iterator++;

            g.listOfAdjacencies[u].moveFront();

            for (int z = 0; z < g.listOfAdjacencies[u].length(); z++) {

                temp4 = (Graph.Vertex) g.listOfAdjacencies[u].get();

                for (int y = 0; y < Q.heapSize + 1; y++) {

                    temp3 = (Qelement) Q.heap[y];

                    if (temp4.getVert() == temp3.getVert()
                            && temp4.getWeight() < temp3.getKey()) {

                        temp3.parent = u; //Qelement parent variable gets changed

                        temp3.key = temp4.weight; //Qelement key variable gets changed

                        Q.delete(y); //deleting element in Q because it is going to be replaced

                        Q.insert(temp3); //using insert so that the min element always ends up at index 0 in the Q.

                        break;

                    }

                }

                g.listOfAdjacencies[u].moveNext();

            }

        }
        
    }


    public static void main(String[] args) {

        MWST connect = new MWST();

        connect.openInputFile(args[0]);

        connect.createGraph();

        connect.closeInputFile();

        connect.mst(connect.G);

        connect.openOutputFile(args[1]);

        connect.writeMst(connect.mwst);

        connect.closeOutputFile();














    }

}