package medialibraryapplication;


public class Movie extends MediaItem {
    
        private String director;
        private String mainActor;
        
        //Movie constructor
	public Movie(String head, String ref, double k, String writer, String actor){
            super(head,ref,k);
            director = writer;
            mainActor = actor;
        }
	//prints the movie item decription
        @Override
        public void WhoAmI() 
		{ String result = " Title : " + title + "( Ref : " + reference + ", Price : " + price + ", Director : " + director + ", Main Actor : " + mainActor + ")\n"; 
				System.out.println(result); 
				}
        
        //return the string movie
        @Override
        public String category(){ return("Movie");}

	}


