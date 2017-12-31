
package bstbookdb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author DeiNerd
 */
public class BookApp {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        EasyIn easy = new EasyIn();
        Scanner scan = new Scanner(new File("BookDB.txt"));
        BookBST adminBST = new BookBST(); 
        BookBST userBST = new BookBST();
        adminBST.setadminSize(Integer.parseInt(scan.nextLine()));
        while(scan.hasNextLine()){
            adminBST.insert(scan.nextLine().toLowerCase(), scan.nextLine(), scan.nextLine(), scan.nextLine(), scan.nextLine());
                                          //ISBN Number              Title                  Author               Publisher                           Publisher year  
        }                           
        System.out.println();
	System.out.println("Welcome to Book Collection App ("+adminBST.getBstSize()+" items)");
	System.out.println("============================="); 
        
        String pause;     
	int command = -1;
	while (command != 0) {
//	    if (command>0) {pause = easy.readString();}   // just to create a pause before printing the menu again
	    System.out.println();
	    System.out.println();
            
	    BookBST.ToolsMenu(); // print menu
	    command = easy.readInt();


	   
            
	    
	    switch(command)
		{
		case  1: 
                    BookBST yearBST = new BookBST(); 
                    System.out.println("Which year are you interested in ?");
                    int input = easy.readInt();
                    System.out.println("Begin subtree extraction for PubYear="+input+" using in-order traversal");
                  
                    yearBST.inOrderPubYearSubTree(adminBST.getRoot(), input); //creates a subtree from the admin tree
                    System.out.println("Number of items found = "+ yearBST.getBstSize());
                    userBST=yearBST; //assigns yearBST to userBST
                    

		    break;

		case  2: 
                    BookBST pubBST = new BookBST();
                    System.out.println("Which publisher are you interested in?");
                    String j = easy.readString().toLowerCase();
                    pubBST.inOrderPubSubTree(adminBST.getRoot(), j); //creates a subtree from the admin tree
                    System.out.println("Begin subtree extraction for Publisher="+ j +" using in-order traversal");
                    System.out.println("Number of items found = "+pubBST.getBstSize());
                    userBST = pubBST;  //assigns pubBST to userBST
		    break;
		    
                case  3: 
                  
                    if(userBST.getRoot()!=null){
                        System.out.println("List in-order "+userBST.getBstSize()+" items sorted by Title");
                        userBST.inOrderTraversal(userBST.getRoot()); //prints the user binary search tree

                    }
                    else{
                        System.out.println("User Binary search tree must be initialized using option 1/2");
                    }
		    break;
		    
                case 4: 
                    if(userBST.getRoot()!=null){
                    userBST.isbnArray();}
                    else{
                        System.out.println("User Binary search tree must be initialized using option 1/2");
                    }
		    break;

		case  5: 
                    if(userBST.getRoot()!=null){
                        userBST.searchAdminUsingISBNArray(adminBST);

                    }
                    else{
                        System.out.println("User Binary search tree must be initialized using option 1/2, then 4");
                    }
		    break;
                
                case 6:
                    //create the graph using StdDraw.java
//                   StdDraw graph = new StdDraw();
                    
                        if(userBST.getRoot()!=null && userBST.getBstSize()>0 && userBST.getArraySize()>0){
                        
                        userBST.draw();
                        userBST.draw(0, 100, 190);
                        
                        }
                        else{
                            System.out.println("User Binary search tree must be initialized using option 1/2");
                    }
                    break;

		case 0: // exit
		    break;

		default:
		    System.out.println("Selection Invalid!- Try Again");
		}
	}

	System.out.println("Goodbye!");
    }


    }
    

