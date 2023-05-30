import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.*;
import java.io.IOException;

public class Main {
    public static User signIn(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Username: ");
        String username = scan.nextLine();
        System.out.println("Password: ");
        String password = scan.nextLine();
        System.out.println("Role: ");
        String role = scan.nextLine();
        User user= new User(username, password, role);
        System.out.println("\n"+"Welcome back, "+username+"!");
        return user;
    }
    public static Vector<Movie> addMovies() throws FileNotFoundException {
        File file=new File("JavaCred/movies.txt");
        Scanner scan = new Scanner(file);
        Vector<Movie> movies = new Vector<Movie>();
        while(scan.hasNextLine()) {
            String title=scan.nextLine();
            String date=scan.nextLine();
            String genere=scan.nextLine();
            String rating=scan.nextLine();
            String numberOfVotes=scan.nextLine();
            String creator=scan.nextLine();
            String runtime=scan.nextLine();
            String parentalGuide=scan.nextLine();
            Movie movie= new Movie(title, date, genere, rating, numberOfVotes, creator, runtime, parentalGuide);
            movies.add(movie);
        }
        return movies;
    }
    public static void main(String[] args) throws FileNotFoundException {
        Interface i=new Interface();
        i.setUser(signIn());
        i.setMovies(addMovies());
        i.initializeFavorite();
        Scanner scan = new Scanner(System.in);
        int x=0;
        String aux, answer;
        while(x!=4) {
            System.out.println();
            i.feed();
            System.out.println("\nIf you want to filter the movies by release date, press key 1.");
            System.out.println("If you want to select a movie, press key 2.");
            System.out.println("If you want to add a movie, press key 3.");
            System.out.println("If you want to leave the application, press key 4.");
            aux = scan.nextLine();
            x=Integer.parseInt(aux);
            switch (x) {
                case 1:
                    i.sortDate();
                    break;
                case 2:
                    System.out.println("Search ");
                    aux = scan.nextLine();
                    System.out.println();
                    if(i.search(aux)==null) {
                        System.out.println("The movie does not exist.");
                        break;
                    }
                    //i.search(aux).detailedPrint();
                    System.out.println(i.search(aux));
                    System.out.println("If you want to rate this movie, press key 1.");
                    System.out.println("If you want to go back, press key 2.");
                    answer = scan.nextLine();
                    x=Integer.parseInt(answer);
                    if(x==1){
                        System.out.println("Do you like this movie?\n(Yes / No)");
                        answer= scan.nextLine();
                        if(answer.equalsIgnoreCase("Yes")) {
                            i.incrementFavorite(i.search(aux));
                        }
                        else {
                            i.decrementFavorite(i.search(aux));
                        }
                    }
                    break;
                case 3:
                    if(!i.getUser().getRole().equalsIgnoreCase("Admin")){
                        System.out.println("\nYou cannot add a movie because you are not an admin.");
                        break;
                    }
                    System.out.println("Title: ");
                    String title=scan.nextLine();
                    System.out.println("Date: ");
                    String date=scan.nextLine();
                    System.out.println("Genere: ");
                    String genere=scan.nextLine();
                    System.out.println("Rating: ");
                    String rating=scan.nextLine();
                    System.out.println("Number of Votes: ");
                    String numberOfVotes=scan.nextLine();;
                    System.out.println("Creator: ");
                    String creator=scan.nextLine();
                    System.out.println("Runtime: ");
                    String runtime=scan.nextLine();;
                    System.out.println("Parental Guide: ");
                    String parentalGuide=scan.nextLine();
                    try {
                        Movie movie = new Movie(title, date, genere, rating, numberOfVotes, creator, runtime, parentalGuide);
                        i.addMovie(movie);
                        i.updateFavorite(movie);
                        BufferedWriter out = new BufferedWriter(new FileWriter("movies.txt", true));
                        out.write("\n"+title+"\n"+date+"\n"+genere+"\n"+rating+"\n"+numberOfVotes+"\n"+creator+"\n"+runtime+"\n"+parentalGuide);
                        out.close();
                    }
                    catch (IOException e) {
                        System.out.println(e);
                    }
                    break;
            }
        }
    }
}