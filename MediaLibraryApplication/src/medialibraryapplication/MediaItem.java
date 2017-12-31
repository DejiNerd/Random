package medialibraryapplication;

//this class introduces the variables that will serve as the general parameters for both movies and books
public class MediaItem {
    
        protected String title;
	protected String reference;
	protected double price;
        
        //this constructor creates a media item with parameters title, reference and price
        public MediaItem(String head, String ref, double k){
		title = head;
		reference = ref;
                price = k;
	}
	
        
        //this group of protected methods set the title, reference and price of any new media item
	protected void SetTitle( String newTitle){
            title = newTitle;
        }
	protected void SetRef(String newRef){
            reference = newRef;
        }
	protected void SetPrice( int newPrice){
            price = newPrice;
        }
	
        //prints the title, reference and price of a media item
	public void WhoAmI() 
		{ String result = " Title : " + title + " (Ref : " + reference + ", Price : " + price + ");\n"; 
				System.out.println( result); 
				}
        
        //returns the price of a media item
	public double GetPrice(){
            return price;
        }
        
        //returns the reference of a media item
	public String GetRef(){
            return reference;
        }
        
        //returns the title of a media item
	public String GetTitle(){
            return title;
        }
        
        //returns the category of the media item
        public String category(){ 
            return("");
        }
}
