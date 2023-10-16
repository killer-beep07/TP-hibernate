package ma.projet.test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import ma.projet.entities.Femme;
import ma.projet.entities.Homme;
import ma.projet.entities.Mariage;
import ma.projet.entities.MariagePK;
import ma.projet.service.FemmeService;
import ma.projet.service.HommeService;
import ma.projet.service.MariageService;

public class Test {


    public static void main(String[] args) throws ParseException {
    HommeService hs = new HommeService();
    FemmeService fs = new FemmeService();
    MariageService ms = new MariageService();

    // Instanciation de Mariages
    Mariage mariage1 = new Mariage(new MariagePK(1, 9, generateDateFormat(2022, 1, 1), generateDateFormat(2023, 1, 1), 4));
    ms.create(mariage1);

    Mariage mariage2 = new Mariage(new MariagePK(2, 8, generateDateFormat(2022, 1, 1), generateDateFormat(2022, 2, 1),5));
    ms.create(mariage2);

    Mariage mariage3 = new Mariage(new MariagePK(3, 15, generateDateFormat(2022, 1, 1), generateDateFormat(2023, 1, 1), 1));
    ms.create(mariage3);

    Mariage mariage4 = new Mariage(new MariagePK(4, 13, generateDateFormat(2022, 1, 1), generateDateFormat(2023, 1, 1), 1));
    ms.create(mariage4);

    Mariage mariage5 = new Mariage(new MariagePK(6, 11, generateDateFormat(2022, 1, 1), generateDateFormat(2023, 1, 1), 0));
    ms.create(mariage5);

    Mariage mariage6 = new Mariage(new MariagePK(5, 14, generateDateFormat(2022, 3, 1), generateDateFormat(2023, 1, 1), 2));
    ms.create(mariage6);

    Mariage mariage7 = new Mariage(new MariagePK(6, 12, generateDateFormat(2022, 2, 1), generateDateFormat(2023, 1, 1), 3));
    ms.create(mariage7);

    // Instanciation de Hommes
    for (int i = 1; i <= 5; i++) {
        String nom = "NomHomme" + i;
        String prenom = "PrénomHomme" + i;
        int numero = 60000000 + i;
        String adresse = "AdresseHomme" + i;
        Date dateDeNaissance = generateDateFormat(1980 + i, i, i); 

        Homme homme = new Homme(nom, prenom, numero, adresse, dateDeNaissance);
        hs.create(homme);
    }

    // Instanciation de Femmes
    for (int i = 1; i <= 10; i++) {
        String nom = "NomFemme" + i;
        String prenom = "PrénomFemme" + i;
        int numero = 61000000 + i;
        String adresse = "AdresseFemme" + i;
        Date dateDeNaissance = generateDateFormat(1985 + i, i, i); 

        Femme femme = new Femme(nom, prenom, numero, adresse, dateDeNaissance);
        fs.create(femme);
    }

    // Afficher les femmes
    System.out.println("Les dames sont : ");
    for (Femme femmeInfo : fs.findAll()) {
        System.out.println(femmeInfo);
    }

   


    hs.displayGetWives(2, generateDateFormat(2020, 01, 01), generateDateFormat(2024, 02, 01));

    fs.affichageCountEnfantsFemmeEntreDates(8, generateDateFormat(2020, 01, 01), generateDateFormat(2023, 02, 01));

    fs.afficherMarriageFemmePlusieursFois();

    hs.displayMenMarriedByFourWomenBetweenDates( generateDateFormat(2020, 01, 01), generateDateFormat(2025, 02, 01));

    hs.displayAllMarriagesOfMan(2);
    } 
// Méthode pour générer une date de naissance aléatoire
public static Date generateDateFormat(int year, int month, int day) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month - 1, day, 0, 0, 0);
    Date date = calendar.getTime();
    return date;
}

}
