package medialibraryapplication;


public class Book extends MediaItem {
	private String author;
        
	//Book constructor
        public Book(String head, String ref, double k, String writer){
            super(head,ref,k);
            author = writer;
        }
	//prints the book item decription
        @Override
        public void WhoAmI(){
            String result = " Title : " + title + "( Ref : " + reference + ", Price : " + price + ", Author : " + author + ");\n";
            System.out.println(result); 
        }
        
        //returns the string book
        @Override
        public String category(){ 
            return("Book");
        }
}
