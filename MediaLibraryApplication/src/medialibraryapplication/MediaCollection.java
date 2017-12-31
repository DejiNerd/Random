package medialibraryapplication;
import java.util.*;
import java.io.*;


public class MediaCollection {

	private String name; 
        private MediaItem[] item;
	
        protected double price;
        protected double highPrice;
	
	
	public MediaCollection(String name, int maxItems, double maxItemPrice) {
            this.name = name;
            item = new MediaItem[maxItems];
            price = maxItemPrice;
        }
	//returns name of mediacollection object
	public String GetName() {
            return(name);
        }
        
        //returns price
        public double GetPrice(){
            return price;
        }
        
        // Scans files and stores data into an array
	public void InitializeCollection() throws IOException{
                File media = new File("C:\\Users\\DeiNerd\\Dropbox\\LESSON\\Ece 242\\MediaLibraryApplication\\src\\medialibraryapplication\\bestmedia.txt");
                Scanner scan = new Scanner(media);
                
                
                
                
                for(int i = 0;scan.hasNextLine();i++){
                    String movieOrBook = scan.nextLine();
                    if("Movie".equals(movieOrBook)){
                        
                            item[i] = new Movie(scan.nextLine(),scan.nextLine(),Double.parseDouble(scan.nextLine()),scan.nextLine(),scan.nextLine());

                         }
                    if("Book".equals(movieOrBook)){
                        
                            item[i] = new Book(scan.nextLine(),scan.nextLine(),Double.parseDouble(scan.nextLine()),scan.nextLine());

                        }
                    
                }
        }
        
        //prints out every media item
	public void PrintCollection(){
            System.out.println("Media/Title/Price");
            System.out.println("----------------");
            int y =0;
            int q =0;
            if(price<101.0){
                for (int i = 0; i < item.length; i++){ 
                if(item[i]!=null && item[i].GetPrice() < price){
                int r = i+1;
               System.out.println(r + " " + item[i].category() + " " + "'" + item[i].GetTitle() + "' " + "$" + item[i].GetPrice());
                    
                 }
                }
            }
        }
        
        //prints out an item description
	public void DetailedItem( int n ){
            int k = n-1;
            if (k < item.length && item[k]!= null){
            item[k].WhoAmI();
            }
            else System.out.println("This item is not in the Library!");
        }
        
        //adds book item
	public void AddItem(String title, String ref, double price, String name){
            int i=0;
            while(item[i]!= null){i++;}
                item[i] = new Book(title,ref,price,name);
        }
        
        //adds movie item
        public void AddItem(String title, String ref, double price, String writer, String actor){
            int i=0;
            while(item[i]!= null){i++;}
                item[i] = new Movie(title,ref,price,writer,actor);
        }
        
        //removes item and rearranges the array
	public void RemoveItem(int j){  
            int n = item.length;
            int q = j-1;
            
            // USer inputs j, then it's converted to j-1 which is the index to be removed.
            if(q <item.length && item[q]!= null){
                for(int k = q; k<n; k++){
                    item[k]=item[k+1];
                    n--;
             }
        }
            else System.out.println("This item is not in the Library!");

            
        }
        
        //sets the maximum price
	public void MaximumPrice(double maxPrice){
            price = maxPrice;
            
        } 
        
        //prints out the menu
        public void Menu(){
            System.out.println("Menu");
            System.out.println("==================");
            System.out.println("1-See List of Items");
            System.out.println("2-See Item Description ");
            System.out.println("3-Add one Item");
            System.out.println("4-Remove one Item");
            System.out.println("5-Set Maximum Price ");
            System.out.println("0-Exit");

        }


}
