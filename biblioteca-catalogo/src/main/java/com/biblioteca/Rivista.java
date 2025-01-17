
public class Rivista {
    private String isbn;
    private String titolo;
    private int annoPubblicazione;
    private int numeroPagine;
    private Periodicita periodicita;

    public Rivista(String isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
        this.periodicita = periodicita;
    }

    // Getters e Setters

    public String getIsbn() {
        return isbn;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

}