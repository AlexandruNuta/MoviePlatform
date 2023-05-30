public class Movie {
    private String title;
    private /* LocalDate / LocalDateTime / Calendar */String date;
    private String genere;
    private String rating;
    private /*Integer*/String numberOfVotes;
    private String creator;
    private String runtime;
    private String parentalGuide;

    public Movie(String title, String date, String genere, String rating, String numberOfVotes, String creator, String runtime, String parentalGuide) {
        this.title=title;
        this.date=date;
        this.genere=genere;
        this.rating=rating;
        this.numberOfVotes=numberOfVotes;
        // this.numberOfVotes=Integer.valueOf(numberOfVotes);
        this.creator=creator;
        this.runtime=runtime;
        this.parentalGuide=parentalGuide;
    }
    public String getTitle(){
        return title;
    }
    public String getDate(){
        return date;
    }
    public String getGenere() {
        return genere;
    }
    public void summaryPrint() {
        System.out.println(title+" ("+date+")");
    }
    /*public void detailedPrint() {
        System.out.println(title+" ("+date+")");
        System.out.println("Genere: "+genere);
        System.out.println("Rating: "+rating);
        System.out.println("Number of Votes: "+numberOfVotes);
        System.out.println("Runtime: "+runtime+" minutes");
        System.out.println("Creator: "+creator);
        System.out.println("Parental Guide: "+parentalGuide+"+");
    }*/

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", genere='" + genere + '\'' +
                ", rating='" + rating + '\'' +
                ", numberOfVotes='" + numberOfVotes + '\'' +
                ", creator='" + creator + '\'' +
                ", runtime='" + runtime + '\'' +
                ", parentalGuide='" + parentalGuide + '\'' +
                '}';
    }
}
