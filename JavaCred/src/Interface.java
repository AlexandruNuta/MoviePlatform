import java.util.*;

//import javafx.util.Pair;

public class Interface {
    private User user;

    /* move to user */
    private Vector<Movie> movies = new Vector<Movie>();
    private Vector<Pair<String, Integer>> favorites =new Vector<Pair<String, Integer>>();
    // How to do a OrderList & AutoSortableListUnique in Java
    // HashMap - implement hashCode & equals
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user=user;
    }
    public void setMovies(Vector<Movie> movies){
        this.movies=movies;
    }
    public void addMovie(Movie movie) {
        movies.add(movie);
    }
    public void sortDate(){
        for(int i = 0; i < movies.size() - 1; i++) {
            for(int j = i; j < movies.size(); j++){
               if(Integer.parseInt(movies.get(i).getDate())<Integer.parseInt(movies.get(j).getDate())){
                   Collections.swap(movies, i, j);
               }
            }
        }
    }
    public void initializeFavorite(){
        boolean ok=false;
        for(Movie i:movies) {
            ok=false;
            for(Pair<String, Integer> j: favorites) {
                if(i.getGenere().equalsIgnoreCase(j.getKey())) {
                    ok=true;
                }
            }
            if(!ok){
                favorites.add(new Pair <String,Integer>(i.getGenere(), 0));
            }
        }
    }
    public void updateFavorite(Movie movie){
        /* stream.find -> Optional.get() -> isEmpty() -> favorite.add */
        boolean ok=false;
        for(Pair<String, Integer> it: favorites) {
            if(ok)
                break;
            if (movie.getGenere().equalsIgnoreCase(it.getKey())) {
                ok=true;
            }
        }
        if(!ok) {
            favorites.add(new Pair<String, Integer>(movie.getGenere(), 0));
        }
        sortMovies();
    }
    public void incrementFavorite(Movie movie) {
        int n=0; //int incrementedValue;
        for(int i = 0; i< favorites.size(); i++) {
            if (movie.getGenere().equalsIgnoreCase(favorites.get(i).getKey())) {
                n = favorites.get(i).getValue()+1;
                favorites.set(i, new Pair<String, Integer>(movie.getGenere(), n));
                n = i;
                break;
            }
        }
        // n becomes favorite index
        while(n-1>= 0 && favorites.get(n).getValue()> favorites.get(n-1).getValue()) {
            Collections.swap(favorites, n, n - 1);
            n--;
        }
        sortMovies();

       /* Collections.sort(favorites, new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });*/
    }
    public void decrementFavorite(Movie movie) {
        int n=0;
        for (int i = 0; i < favorites.size(); i++) {
            if (movie.getGenere().equalsIgnoreCase(favorites.get(i).getKey())) {
                n = favorites.get(i).getValue() - 1;
                favorites.set(i, new Pair<String, Integer>(movie.getGenere(), n));
                n = i;
                break;
            }
        }
        while (n + 1 < favorites.size() && favorites.get(n).getValue() < favorites.get(n + 1).getValue()) {
            Collections.swap(favorites, n, n + 1);
            n++;
        }
        sortMovies();
    }
    public void sortMovies() {
        int n=0;
        for(Pair<String, Integer> i: favorites){
            for(int j=0;j<movies.size();j++) {
                if (i.getKey().equalsIgnoreCase(movies.get(j).getGenere())) {
                    Collections.swap(movies, j, n);
                    n++;
                }
            }
        }
    }
    public Movie /*Optional<Movie>*/ search(String title){
        // stream.filter
        for(Movie it:movies) {
            if(title.equalsIgnoreCase(it.getTitle())) {
                return it;
            }
        }
        Optional<Movie> movie;
        return null; // Suggestion: user Optional<Movie>
    }
    public void feed(){
        for(Movie it:movies) {
                it.summaryPrint();
        }
    }
}
