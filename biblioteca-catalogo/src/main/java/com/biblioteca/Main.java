package com.biblioteca;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Archivio archivio = new Archivio();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Seleziona un'operazione:");
            System.out.println("1. Aggiungi Elemento");
            System.out.println("2. Ricerca per ISBN");
            System.out.println("3. Rimuovi Elemento");
            System.out.println("4. Ricerca per anno pubblicazione");
            System.out.println("5. Ricerca per autore");
            System.out.println("6. Aggiorna Elemento");
            System.out.println("7. Statistiche Catalogo");
            System.out.println("0. Esci");

            int operazione = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline

            switch (operazione) {
                case 1:
                    // Aggiunta di un elemento (richiedere i dettagli)
                    // Esempio:
                    // String tipoElemento = scanner.nextLine();
                    // // Creazione Libro o Rivista a seconda del tipo
                    // archivio.aggiungiElemento(elemento);
                    break;
                case 2:
                    System.out.println("Inserisci ISBN: ");
                    String isbnRicerca = scanner.nextLine();
                    try {
                        Object elemento = archivio.ricercaPerISBN(isbnRicerca);
                        System.out.println("Elemento trovato: " + elemento);
                    } catch (EccezioneISBN e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Inserisci ISBN da rimuovere: ");
                    String isbnRimozione = scanner.nextLine();
                    try {
                        archivio.rimuoviElemento(isbnRimozione);
                        System.out.println("Elemento rimosso con successo.");
                    } catch (EccezioneISBN e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Inserisci anno di pubblicazione: ");
                    int annoRicerca = scanner.nextInt();
                    System.out.println(archivio.ricercaPerAnnoPubblicazione(annoRicerca));
                    break;
                case 5:
                    System.out.println("Inserisci autore: ");
                    String autoreRicerca = scanner.nextLine();
                    System.out.println(archivio.ricercaPerAutore(autoreRicerca));
                    break;
                case 6:
                    // Aggiornamento di un elemento (richiedere i dettagli)
                    break;
                case 7:
                    archivio.statisticheCatalogo();
                    break;
                case 0:
                    System.out.println("Uscita dal programma.");
                    return;
                default:
                    System.out.println("Operazione non valida.");
            }
        }
    }
}