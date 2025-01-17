package com.biblioteca;

import java.util.*;
import java.util.stream.Collectors;

public class Archivio {
    private List<Libro> libri = new ArrayList<>();
    private List<Rivista> riviste = new ArrayList<>();

    public void aggiungiElemento(Object elemento) throws EccezioneISBN {
        if (elemento instanceof Libro) {
            Libro libro = (Libro) elemento;
            if (libri.stream().anyMatch(l -> l.getIsbn().equals(libro.getIsbn()))) {
                throw new EccezioneISBN("Libro con ISBN già esistente");
            }
            libri.add(libro);
        } else if (elemento instanceof Rivista) {
            Rivista rivista = (Rivista) elemento;
            if (riviste.stream().anyMatch(r -> r.getIsbn().equals(rivista.getIsbn()))) {
                throw new EccezioneISBN("Rivista con ISBN già esistente");
            }
            riviste.add(rivista);
        }
    }

    public Object ricercaPerISBN(String isbn) throws EccezioneISBN {
        Optional<Libro> libro = libri.stream().filter(l -> l.getIsbn().equals(isbn)).findFirst();
        if (libro.isPresent())
            return libro.get();

        Optional<Rivista> rivista = riviste.stream().filter(r -> r.getIsbn().equals(isbn)).findFirst();
        if (rivista.isPresent())
            return rivista.get();

        throw new EccezioneISBN("Elemento non trovato per ISBN: " + isbn);
    }

    public void rimuoviElemento(String isbn) throws EccezioneISBN {
        if (!libri.removeIf(l -> l.getIsbn().equals(isbn)) && !riviste.removeIf(r -> r.getIsbn().equals(isbn))) {
            throw new EccezioneISBN("Elemento non trovato per ISBN: " + isbn);
        }
    }

    public List<Object> ricercaPerAnnoPubblicazione(int anno) {
        List<Object> elementi = new ArrayList<>();
        elementi.addAll(libri.stream()
                .filter(l -> l.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList()));
        elementi.addAll(riviste.stream()
                .filter(r -> r.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList()));
        return elementi;
    }

    public List<Libro> ricercaPerAutore(String autore) {
        return libri.stream()
                .filter(l -> l.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }

    public void aggiornaElemento(String isbn, Object nuovoElemento) throws EccezioneISBN {
        if (nuovoElemento instanceof Libro) {
            for (int i = 0; i < libri.size(); i++) {
                if (libri.get(i).getIsbn().equals(isbn)) {
                    libri.set(i, (Libro) nuovoElemento);
                    return;
                }
            }
        } else if (nuovoElemento instanceof Rivista) {
            for (int i = 0; i < riviste.size(); i++) {
                if (riviste.get(i).getIsbn().equals(isbn)) {
                    riviste.set(i, (Rivista) nuovoElemento);
                    return;
                }
            }
        }
        throw new EccezioneISBN("Elemento non trovato per ISBN: " + isbn);
    }

    public void statisticheCatalogo() {
        int numeroLibri = libri.size();
        int numeroRiviste = riviste.size();
        Optional<Object> elementoMaxPagine = Stream.concat(libri.stream(), riviste.stream())
                .max(Comparator.comparingInt(o -> {
                    if (o instanceof Libro)
                        return ((Libro) o).getNumeroPagine();
                    else
                        return ((Rivista) o).getNumeroPagine();
                }));

        Double mediaPagine = Stream.concat(libri.stream(), riviste.stream())
                .mapToInt(o -> {
                    if (o instanceof Libro)
                        return ((Libro) o).getNumeroPagine();
                    else
                        return ((Rivista) o).getNumeroPagine();
                })
                .average()
                .orElse(0.0);

        System.out.println("Numero totale libri: " + numeroLibri);
        System.out.println("Numero totale riviste: " + numeroRiviste);
        elementoMaxPagine.ifPresent(e -> System.out.println("Elemento con maggior numero di pagine: " + e));
        System.out.println("Media delle pagine di tutti gli elementi: " + mediaPagine);
    }
}