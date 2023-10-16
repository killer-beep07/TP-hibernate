package ma.projet.test;

import java.util.Calendar;
import java.util.Date;
import ma.projet.classes.Employe;
import ma.projet.classes.EmployeTache;
import ma.projet.classes.EmployeTachePK;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.services.EmployeService;
import ma.projet.services.EmployeTacheService;
import ma.projet.services.ProjetService;
import ma.projet.services.TacheService;

public class Test2 {
    public static void main(String[] args) {
        EmployeService es = new EmployeService();
        EmployeTacheService ets = new EmployeTacheService();
        ProjetService ps = new ProjetService();
        TacheService ts = new TacheService();

        // Création des employés
        Employe e1 = new Employe("anwar", "Bliout", "0667805006");
        Employe e2 = new Employe("mohamed", "fezzazi", "064572219");
        es.create(e1);
        es.create(e2);

        // Création d'un projet
        Calendar calendarDebut1 = Calendar.getInstance();
        calendarDebut1.set(2022, Calendar.OCTOBER, 5);
        Calendar calendarDebut2 = Calendar.getInstance();
        calendarDebut2.set(2022, Calendar.DECEMBER, 16);
        Projet p = new Projet("Finance", calendarDebut1.getTime(), calendarDebut2.getTime(), e2);
        ps.create(p);
Projet p1 = new Projet("Finance", calendarDebut1.getTime(), calendarDebut2.getTime(), e2);
ps.create(p1);

// Projet 2
Projet p2 = new Projet("Ressources Humaines", calendarDebut1.getTime(), calendarDebut2.getTime(), e2);
ps.create(p2);

// Projet 3
Projet p3 = new Projet("Informatique", calendarDebut1.getTime(), calendarDebut2.getTime(), e2);
ps.create(p3);

// Projet 4
Projet p4 = new Projet("Marketing", calendarDebut1.getTime(), calendarDebut2.getTime(), e2);
ps.create(p4);
        // Création de tâches
        Tache t = new Tache("Bilan", calendarDebut1.getTime(), calendarDebut2.getTime(), 20000, p);
        Tache t2 = new Tache("comptabilite", calendarDebut1.getTime(), calendarDebut2.getTime(), 10000, p);
        ts.create(t);
        ts.create(t2);
        // Tâche 3
Tache t3 = new Tache("Révision des finances", calendarDebut1.getTime(), calendarDebut2.getTime(), 15000, p);
ts.create(t3);

// Tâche 4
Tache t4 = new Tache("Audit comptable", calendarDebut1.getTime(), calendarDebut2.getTime(), 12000, p);
ts.create(t4);
        // Tâche 5
Tache t5 = new Tache("Préparation du rapport financier", calendarDebut1.getTime(), calendarDebut2.getTime(), 18000, p);
ts.create(t5);

// Tâche 6
Tache t6 = new Tache("Analyse des dépenses", calendarDebut1.getTime(), calendarDebut2.getTime(), 9000, p);
ts.create(t6);

        // Attribution des tâches à "mohamed"
        Date dateAttribution = new Date();

        EmployeTachePK employeTachePK1 = new EmployeTachePK(e2.getId(), t.getId(), dateAttribution);
        EmployeTache employeTache1 = new EmployeTache(employeTachePK1);
        ets.create(employeTache1);

        EmployeTachePK employeTachePK2 = new EmployeTachePK(e2.getId(), t2.getId(), dateAttribution);
        EmployeTache employeTache2 = new EmployeTache(employeTachePK2);
        ets.create(employeTache2);

        System.out.println("Tâches assignées à 'mohamed'.");
        es.getTasksPerformedByEmployee(e2);
        es.getProjectsManagedByEmployee(e2);
    }
}

