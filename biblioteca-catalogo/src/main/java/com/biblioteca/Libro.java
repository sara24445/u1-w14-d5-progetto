
public class Libro {
    private String isbn;
    private String titolo;
    private int annoPubblicazione;
    private int numeroPagine;
    private String autore;
    private String genere;

    public Libro(String isbn, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
        this.autore = autore;
        this.genere = genere;
    }

    // Getters e Setters

    public String getIsbn() {
        return isbn;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

}