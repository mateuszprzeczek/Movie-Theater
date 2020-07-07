package options;

import java.util.LinkedHashMap;

public class Options {

    public static LinkedHashMap<Integer, String> mainOptions = new LinkedHashMap<>() {
        {put(1, "Klient");}
        {put(2, "Administrator");}
        {put(3, "Wyjście");}
    };

    public static LinkedHashMap<Integer, String> adminOptions = new LinkedHashMap<>() {
        {put(1, "Wyświetlanie dostępnych Seansów2");}
        {put(2, "Dodaj film");}
        {put(3, "Dodaj seans");}
        {put(4, "Wyświetl zarejestrowanych użytkowników");}
        {put(5, "Wyświetl zarezerwowane filmy");}
        {put(6, "Zmień godzinę/cenę/usuń film");}
        {put(7, "Wstecz");}
    };
    public static LinkedHashMap<Integer, String> userOptions = new LinkedHashMap<>() {
        {put(1, "Wyświetl obecny repertuar.");}
        {put(2, "Rejestracja użytkownika");}
        {put(3, "Zarezerwuj bilet");}
        {put(4, "Pokaż zarezerwowane filmy");}
        {put(5, "Wstecz");}
    };
    public static LinkedHashMap<Integer, String> movieFieldsOptions = new LinkedHashMap<>() {
        {put(1, "Dodaj godzinę seansu.");}
        {put(2, "Zmień cenę biletu");}
        {put(3, "Usuń film");}
        {put(4, "Wstecz");}
    };

}
