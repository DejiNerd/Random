package medialibraryapplication;

import java.io.*;
import java.util.*;


public class MediaLibraryApplication {

	public static void main(String args[]) throws IOException{ 
			
		MediaCollection store1=new MediaCollection("Best Media",20,100.0); 
		store1.InitializeCollection(); 
                System.out.println("Welcome to "+store1.GetName()); 
		System.out.println("==================");
                store1.Menu();
                Scanner inputs = new Scanner(System.in);
                int y = inputs.nextInt();
                System.out.println("Command: " + y);
                
                while(y>=0){
                //Option 0
                if(y == 0) {System.out.println("Goodbye!");}  
                
                //Option 1
                if(y == 1){
                    store1.PrintCollection();
                    store1.Menu();
                    
                }
                
                //Option 2
                if(y==2){
                    System.out.println("Enter item:");
                    int k = inputs.nextInt();
                    store1.DetailedItem(k);
                    store1.Menu();
                    
                }
                
                //Option 3
                if(y==3){
                    System.out.println("1-Book" );
                    System.out.println("2-Movie");
                    int j = inputs.nextInt();
                    if(j==1){   //Option to add a book
                        System.out.println("Enter Book Title");
                        inputs.nextLine();
                        String bTitle = inputs.nextLine();
                        System.out.println("Enter Book Reference");
                        String bRef = inputs.nextLine();
                        System.out.println("Enter Book Price");
                        double bPrice = Double.parseDouble(inputs.nextLine());
                        System.out.println("Enter Author name");
                        String aName = inputs.nextLine();
                        store1.AddItem(bTitle, bRef, bPrice, aName);
                    }
                    if(j==2){  // Option to add a Movie
                        System.out.println("Enter Movie Title");
                        inputs.nextLine();
                        String bTitle = inputs.nextLine();
                        System.out.println("Enter Movie Reference");
                        String bRef = inputs.nextLine();
                        System.out.println("Enter Movie Price");
                        double bPrice = Double.parseDouble(inputs.nextLine());
                        System.out.println("Enter Movie writer");
                        String writer = inputs.nextLine();
                        System.out.println("Enter main Actor");
                        String mActor = inputs.nextLine();
                        store1.AddItem(bTitle, bRef, bPrice, writer, mActor);
                    }
                    store1.Menu();
                    
                }
                
                //Option 4
                if(y==4){
                    System.out.println("Which item do you want to remove");
                    int q = inputs.nextInt();
                    store1.RemoveItem(q);
                    store1.Menu();
                    
                }
                
                // Option 5
                if(y==5){
                    System.out.println("Which maximum price? (currently " + store1.GetPrice() + ")");
                    double t = inputs.nextDouble();
                    store1.MaximumPrice(t);
                    store1.Menu();
                   
                    
                }
                y = inputs.nextInt();
                System.out.println("Command: " + y);
                }
	}
		
		

}
