package bstbookdb;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DeiNerd
 */

public class BookBST {
    
    private Book root;
            
    private int adminbooksize,bstSize,isbncnt;
    private String [] isbnArray;
            
    
    
    public static void ToolsMenu() {
	System.out.println("Menu");
	System.out.println("====");
	System.out.println("1-From admin BST database, extract user custom BST database using PubYear");          
	System.out.println("2-From admin BST database, extract the user custom BST databse using Publisher");
	System.out.println("3-Display in-order use BST database");
	System.out.println("4-Convert user BST database into array of ISBN");
	System.out.println("5-Search admin database using the ISBN array");
	System.out.println("6-Plot user BST database using ISBN array");
	System.out.println("0-Exit");
	System.out.println("");
	System.out.print("Command: ");
    }

    public BookBST() {
       
    }
    public void setadminSize(int i){adminbooksize=i;}

    public Book getRoot(){
        return root;
    }

    public int getBstSize(){//return size of the binary tree
        return bstSize;
    }
    public int getadminSize(){//return size of the binary tree
        return adminbooksize;
    }
    public int getArraySize(){
        return isbncnt;
    }
    
    /**
     *
     * @param current
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     */
    public void recAdminInsert(Book current, String a, String b, String c,String d, String e) {
    if (a.compareTo(current.isbnNo)<0)
        { // search left
        if (current.left==null)// node needs to be inserted
            current.left= new Book(a,b,c,d,e);
        else
            recAdminInsert(current.left,a,b,c,d,e); // keep searching
        }   
    else if (a.compareTo(current.isbnNo)>=0)
        {// search right
        if (current.right==null)// node needs to be inserted
            current.right= new Book(a,b,c,d,e);
        else
            recAdminInsert(current.right,a,b,c,d,e);// keep searching
        }
   
    } 
    
   public void insert(String a, String b, String c,String d, String e)
    {
    if (root==null)// node needs to be inserted (case 1)
        root= new Book(a,b,c,d,e);
    else // (case 2)
        recAdminInsert(root,a,b,c,d,e);// initiate the recursion
    bstSize++;
    }
   
    public void insert(Book neo)
    {
    if (root==null)// node needs to be inserted (case 1)
        root= neo;
    else // (case 2)
        recinsert(root,neo);// initiate the recursion
    bstSize++;
    }

    private void recinsert(Book current, Book neo) {
        if (neo.title.toLowerCase().compareTo(current.title.toLowerCase())<0)
        { // search left
        if (current.left==null)// node needs to be inserted
            current.left= neo;
        else
            recinsert(current.left,neo); // keep searching
        }   
    else if (neo.title.toLowerCase().compareTo(current.title.toLowerCase())>=0)
        {// search right
        if (current.right==null)// node needs to be inserted
            current.right= neo;
        else
            recinsert(current.right,neo);// keep searching
        }
    }


    public void inOrderPubSubTree(Book current, String pub) //for option 3
    {
        
        if (current!=null){ //traverse left node
            inOrderPubSubTree(current.left,pub.toLowerCase()); 
            if(current.publisher.toLowerCase().equals(pub.toLowerCase())){
                //finds the publisher we're interested in
                //create a new link to hold the details without affecting the source 
                Book hold = new Book(current.isbnNo,current.title,current.author,current.publisher,current.year);
                insert(hold);       
            }
            inOrderPubSubTree(current.right,pub); //traverse right node
        }
    }
    public void inOrderPubYearSubTree(Book current, int year)//make SubTree based on publishing year
    { 
        String years =""+year; 
        if(current!=null){//while current node isn't null
            inOrderPubYearSubTree(current.left,year);
            if(current.year.equals(years)){
                //finds the published year we're interested in
                //create a new link to hold the details without affecting the source 
                Book hold = new Book(current.isbnNo,current.title,current.author,current.publisher,current.year);
                insert(hold);   
            }
            inOrderPubYearSubTree(current.right,year);
        }
    }
    public void inOrderTraversal(Book someRoot){ //for option 3
            Book current = someRoot;
            if(current != null){
                //traverse left node
                inOrderTraversal(current.left);
                
                System.out.println(current.isbnNo + " " +  current.title + "; " + current.author+"; "+current.publisher+"; "+current.year);//print out the book information
                
                inOrderTraversal(current.right);//move to right node
            }
    }
    
    public void isbnArray(){
        isbnArray = new String [1023];
        makeArray(root, 0, isbnArray);
        System.out.println("[i]    "+"ISBN");
        int hold = 0;
        for(int i=0; i<isbnArray.length; i++){
            if(isbnArray[i]!=null && isbnArray[i]!="N/A"){  //doesn't print if the array is null or "N/A"
            System.out.println(i+"     "+ isbnArray[i]);
            hold = i; //holds the last relevant index
            }     
        }
        int level = (int)(Math.log10((double)hold) / Math.log10((double)2)); //log_2 hold
        System.out.println("Number of items: "+isbncnt+";" +"  "+ "Number of levels: "+level);
    }
    
    //method converts the tree to an array recursively
    public void makeArray(Book current, int i, String [] BSTarray ) {  
    //method stops if the index is greater than length of the array    
    if (i<1023) {
        
        
        if(current!=null){
            BSTarray[i] = current.isbnNo;   
            isbncnt++;
        }
        else{ 
            BSTarray[i] = "N/A";return; //stops if a leaf is found
        }
        
        makeArray(current.left, 2*i+1, BSTarray);  //go left
        makeArray(current.right, 2*i+2, BSTarray); //go right
        }   
    }
    
    
//    public void makeArray(Book node, int i, String[] BSTarray){
//    if(node == null){  // recursion anchor: when the node is null an empty leaf was reached (doesn't matter if it is left or right, just end the method call
//       
//       return;
//    }
//    makeArray(node.left, i, BSTarray);   // first do every left child tree
//    BSTarray[i++]= node.isbnNo; isbncnt++;         // then write the data in the array
//    makeArray(node.right, i, BSTarray);  // do the same with the right child
//}

    public void searchAdminUsingISBNArray(BookBST admin) {
        if(bstSize>0 && isbnArray!=null){
        long startTime = System.nanoTime(); //start timer
        int duration, count = 0;

        for(String isbn : isbnArray){
            //search user bst for values
            //"All the non “N/A” ISBN numbers in the array are searched for in the administrator BST database"
            if(isbn == null || isbn.equals("N/A")){
                //System.out.println("Skip");
            }else{
                Book node = find(isbn, admin.root); 
                System.out.println(node.isbnNo + " " + node.title + "; " + node.author + "; " + node.publisher + "; " + node.year + " " + count++);
            }
        }
        long endTime = System.nanoTime(); // end timer
        
        duration = (int)(endTime - startTime); //computes duration
        String dur = Integer.toString(duration);
        String durMs = dur.substring(0, dur.length()-6);
        System.out.println("Search Time " + durMs + "ms");
        }
    }//searchAdminUsingISBNArray
    
    private Book find(String isbn, Book node){//based on Admin BST & isbn number, method which finds book nodes based on isbn number
        
        if(node.isbnNo.compareTo(isbn)==0){   //found book       
            return node;
        }else if(node.isbnNo.compareTo(isbn) > 0){ //search left 
            return find(isbn, node.left);
        }else if(node.isbnNo.compareTo(isbn) < 0){ //search right 
            return find(isbn, node.right);
        }else{
            System.out.println("fatal error");
            System.exit(99999);
        }
        return node;
    }//end find

    public void draw() {
        StdDraw.setCanvasSize(1900,950);
        StdDraw.setScale(0, 200);

    }
                    double hold = 65;
                    //double hold2 = 65;
                    
    //Honestly, we tried but this method is purely based on trial by error
    //It was harder than we expected :((((
    public void draw(int i,double j, double k){

        if(i>=isbnArray.length) // if the index is greater than array length, method stop
            {return;}
        else{
            
            if(isbnArray[i]==null || "N/A".equals(isbnArray[i])){} // skips the empty arrays elements
            else{
                
             //test
//                int level;
//                int parent = (int) Math.floor((i-1)/2);
//                if(i == 0){ level = 0;}
//                else{
//                    
//                level = (int)(Math.log10((double)i) / Math.log10((double)2));
//                }
//                System.out.println(isbnArray[i] + " level is " + level); // used for testing
                StdDraw.filledCircle(j, k, 0.55); //draw a circle for the current node
                if((2*i+1)<1023){
                    if((isbnArray[(2*i)+1]==null || "N/A".equals(isbnArray[(2*i)+1]))){} //// skips the empty arrays elements
                    else{
                    hold = hold/(2); //1.6
                    StdDraw.line(j, k,j-hold,k-14); //draws the line from the child to the parent
                    draw((2*i)+1,j-hold,k-14);
                    hold = hold*1.9; //restores hold to for the right child
                    
                    }
                }
                
                if((2*i+2)<1023){
                    if((2*i+2)<1023 && (isbnArray[(2*i)+2]==null || "N/A".equals(isbnArray[(2*i)+2]))){} // skips the empty arrays elements
                    else{
                    //hold = hold/(1.6);
                    StdDraw.line(j, k,j+hold,k-14 ); //draws the line from the child to the parent 
                    draw((2*i)+2,j+hold,k-14);
                    
                    }
                }

            }
        }
    }

   

    
}
