@SuppressWarnings("overrides")




public class Graph{

    List[] listOfAdjacencies;

    public int order; //number of vertices in graph

    public int size; //number of edges in graph

    public class Vertex{

        public int vert;

        public float weight;

        public Vertex(float w, int v){

            vert = v;

            weight = w;
        }

        public int getVert(){

            return vert;
        }

        public float getWeight(){

            return weight;
        }

        public String toString(){

            return("(" + "vertex: " + vert + ", " + "weight: " + weight + ")");
        }
    }

    public Graph(int n){

        order = n;

        size = 0;

        listOfAdjacencies = new List[(order + 1)]; //dynamically allocates "order + 1" list objects.
                                                       //This is so vertex 1 can be represented by index 1, etc.

        for(int x = 0; x < (n + 1); x++){

            listOfAdjacencies[x] = new List();


        }


    }

    public int getOrder(){

        return order;

    }

    public int getSize(){

        return size;
    }

    public void print(){

        for(int x = 1; x < order + 1; x++){

            System.out.print(x + ": ");

            listOfAdjacencies[x].peek();

            System.out.println("\r\n");
        }
    }

    public void addEdge(int u, int v, float w){

        Vertex temp;

        Vertex temp2;

        if((u > getOrder() || u < 1) || (v > getOrder() || v < 1 )){

            System.out.println("Cannot add edge to undefined vertex.");
        }

        else{

            if(listOfAdjacencies[u].length() == 0 && listOfAdjacencies[v].length() != 0){ //Main condition 1: List[u] is empty but List[v] is not.

                temp = new Vertex(w, v);

                listOfAdjacencies[u].append(temp);

                temp2 = new Vertex(0, 0);

                temp2 = (Vertex)listOfAdjacencies[v].back();

                if(u > temp2.getVert()){

                    temp = new Vertex(w, u);

                    listOfAdjacencies[v].append(temp);
                }

                else{

                    listOfAdjacencies[v].moveFront();

                    temp2 = (Vertex)listOfAdjacencies[v].get();

                    while(u > temp2.getVert()){

                        listOfAdjacencies[v].moveNext();

                        temp2 = (Vertex)listOfAdjacencies[v].get();
                    }

                    temp = new Vertex(w, u);

                    listOfAdjacencies[v].insertBefore(temp);
                }

                size++;

            }

            else if(listOfAdjacencies[v].length() == 0 && listOfAdjacencies[u].length() != 0){//Main condition 2: List[v] is empty but List[u] is not.

                temp = new Vertex(w, u);

                listOfAdjacencies[v].append(temp);

                temp2 = (Vertex)listOfAdjacencies[u].back();

                if(v > temp2.getVert()){

                    temp = new Vertex(w, v);

                    listOfAdjacencies[u].append(temp);
                }

                else{

                    listOfAdjacencies[u].moveFront();

                    temp2 = (Vertex)listOfAdjacencies[u].get();

                    while(v > temp2.getVert()){

                        listOfAdjacencies[u].moveNext();

                        temp2 = (Vertex)listOfAdjacencies[u].get();
                    }

                    temp = new Vertex(w, v);

                    listOfAdjacencies[u].insertBefore(temp);
                }

                size++;

            }

            else if(listOfAdjacencies[u].length() == 0 && listOfAdjacencies[v].length() == 0){ //Main condition 3: Both List[u] and List[v] are empty.

                temp = new Vertex(w, v);

                Vertex temp3 = new Vertex(w, u);

                listOfAdjacencies[u].append(temp);

                listOfAdjacencies[v].append(temp3);

                size++;
            }

            else if(listOfAdjacencies[u].length() != 0 && listOfAdjacencies[v].length() != 0){ //Main condition 4:  Neither List[u] nor List[v] are empty.

                temp2 = (Vertex)listOfAdjacencies[u].back();

                if(v > temp2.getVert()){

                    temp = new Vertex(w, v);

                    listOfAdjacencies[u].append(temp);
                }

                else{

                    listOfAdjacencies[u].moveFront();

                    temp2 = (Vertex)listOfAdjacencies[u].get();

                    while(v > temp2.getVert()){

                        listOfAdjacencies[u].moveNext();

                        temp2 = (Vertex)listOfAdjacencies[u].get();

                    }

                    temp = new Vertex(w, v);

                    listOfAdjacencies[u].insertBefore(temp);

                }

                temp2 = (Vertex)listOfAdjacencies[v].back();

                if(u > temp2.getVert()){

                    temp = new Vertex(w, u);

                    listOfAdjacencies[v].append(temp);
                }

                else{

                    listOfAdjacencies[v].moveFront();

                    temp2 = (Vertex)listOfAdjacencies[v].get();

                    while(u > temp2.getVert()){

                        listOfAdjacencies[v].moveNext();

                        temp2 = (Vertex)listOfAdjacencies[v].get();
                    }

                    temp = new Vertex(w, u);

                    listOfAdjacencies[v].insertBefore(temp);

                }

                size++;

            }

        }

    }

}









