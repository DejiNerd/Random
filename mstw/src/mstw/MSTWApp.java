package mstw;
import java.io.*;
import java.util.*;


// mstw.java
// demonstrates minimum spanning tree with weighted graphs
// to run this program: C>java MSTWApp
////////////////////////////////////////////////////////////////
class Edge
{
    public int start;   // index of a vertex starting edge
    public int end;     // index of a vertex ending edge
    public int weight;  // weight from start to end
    // -------------------------------------------------------------
    public Edge(int sv, int dv, int d)  // constructor
    {
	start = sv;
	end = dv;
	weight = d;
    }
    
    public int getWeight(){
        return weight;
    }
    // -------------------------------------------------------------
}  // end class Edge
////////////////////////////////////////////////////////////////

class Heap
{
    
    private Edge[] heapArray; 
    private int maxSize,currentSize; // current size
    
    public Heap(int MaxSize)            // constructor
    {
	heapArray = new Edge[MaxSize];
        maxSize=MaxSize;
        currentSize = 0;
    }
    
    
    public boolean insert(Edge item) // insert item in heap (assumed not full)
    {
       //to complete
       if(currentSize == maxSize){ return false;} //if array is full
       Edge newEdge = item;            //fail
       heapArray[currentSize] = newEdge;        //make new node
       trickleUp(currentSize++);                //put it at end
       return true;                             //trickle up
    }//end insert()             
    
   
    public Edge remove()
    {
      // to complete
        Edge root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }//end remove()
    
    private void trickleUp(int index) {
      // to complete
      int parent = (index-1)/2;
      Edge bottom = heapArray[index];
      
      while(index > 0 && heapArray[parent].getWeight() <bottom.getWeight()){
          heapArray[index] = heapArray[parent];
          index = parent;
          parent = (parent-1)/2;
      }//end while
      heapArray[index] = bottom;
    }//end trickleUp()

    
    private void trickleDown(int index) {
     // to complete
     int largerChild;
     Edge top = heapArray[index];
     while(index < currentSize/2){
        int leftChild = 2*index + 1;
        int rightChild = leftChild +1;
        if(rightChild < currentSize && heapArray[leftChild].getWeight() < heapArray[rightChild].getWeight())
            largerChild = rightChild;
        else
            largerChild = leftChild;
            
        if(top.getWeight()>=heapArray[largerChild].getWeight()) break;
        
        heapArray[index] = heapArray[largerChild];
        index = largerChild;
    }//end while
     heapArray[index] = top;
    } // end trickleDown()
  
    // -------------------------------------------------------------
    public int size()              // return number of items
    { return currentSize; }
    // -------------------------------------------------------------
    public boolean isEmpty()      // true if queue is empty
    { return (currentSize==0); }
    // -------------------------------------------------------------
    
}

////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////


class Vertex
{
    public int i,j;       // points coordinate (i,j)
    public boolean isInTree;
    // -------------------------------------------------------------
    public Vertex(int i,int j)   // constructor
    {
	this.i = i;
	this.j=j;
	isInTree = false;
    }
    // -------------------------------------------------------------
    public void display()
    {
	System.out.print("("+i+","+j+")");
    }
    public int getX(){ //returns x-coordinate of the node
        return i;
    }
    public int getY(){ //returns y-coordinate of the node
        return j;
    }
}  // end class Vertex

class PriorityQ
{
    // array in sorted order, from max at 0 to min at size-1
    private final int SIZE;
    private Edge[] queArray;
    private int size;
    // -------------------------------------------------------------
    public PriorityQ(int Nvertice) // constructor
    {
    queArray = new Edge[Nvertice];
    SIZE = Nvertice;
    size = 0;
    }
    // -------------------------------------------------------------
    public void insert(Edge item) // insert item in sorted order
    {
        int j;
        for(j=0; j<size; j++) // find place to insert
        if( item.weight >= queArray[j].weight )
            break;
        for(int k=size-1; k>=j; k--) // move items up
               queArray[k+1] = queArray[k];
        queArray[j] = item; // insert item
        size++;
    }
    // -------------------------------------------------------------
    public Edge removeMin() // remove minimum item
        { return queArray[--size]; }
    // -------------------------------------------------------------
    public void removeN(int n) // remove item at n
    {
        for(int j=n; j<size-1; j++) // move items down
        queArray[j] = queArray[j+1];
        size--;
    }
    // -------------------------------------------------------------
    public Edge peekMin() // peek at minimum item
    { return queArray[size-1]; }
    // -------------------------------------------------------------
    public int size() // return number of items
    { return size; }
    // -------------------------------------------------------------
    public boolean isEmpty() // true if queue is empty
    { return (size==0); }
    // -------------------------------------------------------------
    public Edge peekN(int n) // peek at item n
    { return queArray[n]; }
    // -------------------------------------------------------------
    public int find(int findDex) // find item with specified
    { // destVert value
        for(int j=0; j<size; j++)
            if(queArray[j].end == findDex)
            return j;
        return -1;
    }
// -------------------------------------------------------------
} // end class PriorityQ
////////////////////////////////////////////////////////////////

//
class Graph
{
    private Vertex vertexList[]; // list of vertices
    private int adjMat[][];      // adjacency matrix
    private int Nvertex;          // Number of vertices/nodes
    private int Nedges;           // Number of vertices/nodes
    private int Height;         //ny
    private int Width;          //nx
//    private StackX theStack = new StackX();
    private Graph mst;
    private int currentVert;
    private PriorityQ thePQ;    //the priority queue
    private int nTree;        //number of verts in tree
    private final int INFINITY = 1000000;
    private int[] Starter;
    private int[] Ender;
    public void setHeight(int height){
        this.Height = height;
    }
    public int getHeight(){
        return Height;
    }
    public void setWidth(int width){
        this.Width = width;
    }
    public int getWidth(){
        return Width;
    }
    // -------------------------------------------------------------   
    public Graph(int maxVertex)               // basic constructor
    {	  
	vertexList = new Vertex[maxVertex];
	// adjacency matrix initialization
	adjMat = new int[maxVertex][maxVertex];
	Nvertex = 0;
	for(int j=0; j<maxVertex; j++)      // set adjacency
	    for(int k=0; k<maxVertex; k++)   // matrix to 0 by default
		adjMat[j][k] = 0;
    }  // end constructor



    // -------------------------------------------------------------
    public Graph(String name)               // constructor- load Graph
    {
        //load .v file
        
	try {
	    File file = new File(name + ".v"); //begin reading .v file with the verticies
	    Scanner scanner = new Scanner(file);
            int size = scanner.nextInt(); //the first line has the number of nodes/verticies
            int width = (int)Math.sqrt(size);
            setWidth(width);
            setHeight(width);
            Nvertex = 0; //assign the number to the Nvertex
            vertexList = new Vertex[size]; //construct the array of Verticies
            for(int count = 0; count<size; count++){
                addVertex(scanner.nextInt(), scanner.nextInt());  //adds vertices
                
            }
	    scanner.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
        
        //load .e file
        try {
	    File file = new File(name + ".e"); //begin reading .e file with the edges
	    Scanner scanner = new Scanner(file);
            int size = scanner.nextInt(); //the first line has the number of edges
            Nedges = 0; //assign the number to the Nedges
            adjMat = new int[size][size]; //construct the array of edges
            for(int count = 0; count<size; count++){
                addEdge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            } 
	    scanner.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
    }// end constructor


    public int totalNumber;
    // -------------------------------------------------------------
    public Graph(int nx,int ny)               // constructor with random weight grid generator
    {	  
        
        vertexList = new Vertex[nx*ny]; 
         int Nodes = nx*ny; //number of nodes within the graph
         totalNumber = Nodes; 
         Height = ny;
         Width = nx;
        adjMat = new int[Nodes][Nodes];
        Nvertex = 0; // number of nodes        
        
        for(int i = 0; i<ny; i++){
            for(int j = 0; j<nx; j++){
                addVertex(j,i); // adds vertices
            }
        }
        System.out.println("number of vertexes: " + vertexList.length);
        for(int Start = 0; Start <Nodes;Start++){
            final int random = (int)((nx+ny)*Math.random()+1);
            int End = Start+1; //horizontal connection variable
            if(End%nx ==0 || End == Nodes){
            }else{
                //System.out.println("HORIZONTAL: "+Start + ", " + End + " with weight "+ random);
                addEdge(Start,End,random);  //horizontal edge
            }
            
            final int random2 = (int)((nx+ny)*Math.random()+1); 
            int EndV = Start+nx; //vertical connection variable
            if(EndV >= Nodes){}
            else{
                //System.out.println("VERTICAL: "+Start + ", " + EndV + " with weight "+ random2);
                addEdge(Start,EndV,random2); //vertical edge
            }
        }//end for
    }  // end constructor

              
    // -------------------------------------------------------------
    public void addVertex(int i, int j)
    {
	vertexList[Nvertex++] = new Vertex(i,j);
    }
    // -------------------------------------------------------------
    public void addEdge(int start, int end, int weight)
    {
	adjMat[start][end] = weight;
	adjMat[end][start] = weight;
	Nedges++;
    }

    public int[][] getAdjMat()
    {
	return(adjMat);
    }

    public Vertex[] getVertexList()
    {
	return(vertexList);
    }

    public int getNvertex() // return number of vertex/nodes
    {
	return(Nvertex);
    }

    public int getNedges() // return number of edges (connected to the number of non-zero elements in matrix)
    {
	return(Nedges);
    }
       
    
    public Graph mstw() // minimum spanning tree
    {
        int[] Start = new int[Nvertex-1]; //collects data for option 4
        int[] End = new int[Nvertex-1]; //collects data for option 4
        int counter = 0;
        mst=new Graph(Nvertex);       // mst returns as a graph
        thePQ = new PriorityQ(Nvertex);
        int totalWeights = 0;
        currentVert = 0; // start at 0
        
        while(nTree < Nvertex-1) // while not all verts in tree
        { // put currentVert in tree
            vertexList[currentVert].isInTree = true;
            nTree++;
        // insert edges adjacent to currentVert into PQ
            for(int j=0; j<Nvertex; j++) // for each vertex,
            {
                if(j==currentVert) // skip if it’s us
                    continue;
                if(vertexList[j].isInTree) // skip if in the tree
                    continue;
                int weight = adjMat[currentVert][j]; //distance = weights
                if( weight == 0) // skip if no edge
                    continue;
                putInPQ(j, weight); // put it in PQ (maybe)
            }
            if(thePQ.size()==0) // no vertices in PQ?
            {
                System.out.println(" GRAPH NOT CONNECTED ");
                break;
            }
        // remove edge with minimum distance, from PQ
            Edge theEdge = thePQ.removeMin();

            mst.addEdge(theEdge.end, theEdge.start, theEdge.weight);
            int sourceVert = theEdge.start;
            currentVert = theEdge.end;
            vertexList[sourceVert].display(); 
            Start[counter] = sourceVert; //data for option 4
            System.out.print("<-->");// display edge from source to current
             vertexList[currentVert].display();
            End[counter] = currentVert; //data for option 4
            counter++;
            totalWeights = totalWeights+ theEdge.weight;
             System.out.print(" Weights:"+" " +theEdge.weight);
            System.out.println("");
        } // end while(not all verts in tree)
        System.out.println("MST Total weight: "+ totalWeights);
    // mst is complete
        for(int j=0; j<Nvertex; j++) // unmark vertices
            vertexList[j].isInTree = false;
        mst.Starter = Start;
        mst.Ender = End;
        mst.vertexList = vertexList;
        return mst;
    } // end mstw
    
    // -------------------------------------------------------------
    public void putInPQ(int newVert, int newWeight)
    {
        // is there another edge with the same destination vertex?
        
        int queueIndex = thePQ.find(newVert);
        if(queueIndex != -1) // got edge’s index
        {
            Edge tempEdge = thePQ.peekN(queueIndex); // get edge
            int oldWeight = tempEdge.weight;
            if(oldWeight > newWeight) // if new edge shorter,
            {
                thePQ.removeN(queueIndex); // remove old edge
                Edge theEdge = new Edge(currentVert, newVert, newWeight);
                thePQ.insert(theEdge); // insert new edge
            }
        // else no action; just leave the old vertex there
        } // end if
        else // no edge with same destination vertex
        { // so insert new one
        Edge theEdge = new Edge(currentVert, newVert, newWeight);
        thePQ.insert(theEdge);
        }
} // end putInPQ()

    
    public void plot(int xmin,int xmax,int ymin,int ymax){//// Plot the Graph using the StdDraw.java library  

            StdDraw.setCanvasSize(1000,1000); // size canvas	
       
        int scale = xmax;
        if(xmax <= ymax){
            scale = ymax;
        }
	StdDraw.setXscale(xmin-1, scale+1);    //  x scale
	StdDraw.setYscale( ymin-1, scale+1);   //  y scale
	StdDraw.setPenColor(StdDraw.BLUE);  // change pen color
        
        for(int y = 0; y <ymax; y++){//make node circles
            for(int x = 0; x <xmax; x++){
                StdDraw.filledCircle(x, y, .25);   //draws the nodes as circles
            }
        }
        for(int i = 0; i < Ender.length; i++){ //create the lines
            StdDraw.line(vertexList[Starter[i]].getX(), vertexList[Starter[i]].getY(), vertexList[Ender[i]].getX(), vertexList[Ender[i]].getY());
            //draws the edge from one vertice to another
        }
    }//End plot()
    
}  // end class Graph


public class MSTWApp
{
    public static void ToolsMenu() {
	System.out.println("Menu");
	System.out.println("====");
	System.out.println("1- Read Graph from File");
	System.out.println("2- Generate a Graph using a Grid with Random weights");
	System.out.println("3- Compute the Minimum Spanning Tree");
	System.out.println("4- Plot the Maze");
	System.out.println("0-Exit");
	System.out.println("");
	System.out.print("Command: ");
    }

    ////////////    MAIN METHOD//////////////////////// 
    public static void main(String[] args)
    {
	Graph theGraph=null; // original graph
	Graph mst = null;   // MST stored as graph
        EasyIn easy = new EasyIn();
        int N;
	int mat[][];
	Vertex nodes[];
	
	
	System.out.println("\nWelcome to Maze Generator App");
	System.out.println("===============================\n");

      
	 
	int command = -1;
	while (command != 0) {
	    if (command!=-1) easy.readString(); //just a pause
	    ToolsMenu();         // Print Option Menu
	    command = easy.readInt();
	    switch(command)
		{
		case  1:// Read Graph from File
		    System.out.println("Enter File name: ");
		    theGraph=new Graph(easy.readString());
		    System.out.println("List of edges + weights: ");
		    N=theGraph.getNvertex();

		    nodes=new Vertex[N];
		    nodes=theGraph.getVertexList();

		    // Obtain Matrix
		    mat=new int[N][N];
		    mat=theGraph.getAdjMat();

		    for (int i=0;i<N;i++)
			for (int j=0;j<=i;j++) if(mat[i][j]!=0){
				nodes[i].display();
				System.out.print("<-->");
				nodes[j].display();
				System.out.println("  "+mat[i][j]);
			    }
		  
		    break;

		case  2:    //Generate a Graph using a Grid with Random weights
                    System.out.println("Enter Total Grid Size x: ");
		    int nx=easy.readInt();
                    System.out.println("Enter Total Grid Size y: ");
		    int ny=easy.readInt(); 
                    if(nx <= 0 || ny <= 0){System.out.println("please type a non-zero, non-negative number"); break;}
		    theGraph=new Graph(nx,ny);
                    theGraph.setHeight(ny);
                    theGraph.setWidth(nx);
		    break;

		case  3://   Compute the Minimum Spanning Tree
		    if (theGraph==null){ System.out.println("Graph not defined"); break;}
		    System.out.println("Minimum spanning tree: ");
		    N=theGraph.getNvertex();
                    System.out.println("List of edges + weights: ");
                    mst=theGraph.mstw();
		                // minimum spanning tree
		    System.out.println("Number of vertices: "+ N);
		    System.out.println("Number of edges: "+mst.getNedges());
		    break;
		    
	        case 4: //Plot the maze
		    if (mst==null && theGraph==null){ System.out.println("MST not defined"); break;}
		    mst.plot(0,theGraph.getWidth(),0,theGraph.getHeight());
		                   
		    break;
		    
		case 0: 
		    break;
		default:
		    System.out.println("Selection Invalid!- Try Again");
		}
	}

	System.out.println("Goodbye!");
    }
}



