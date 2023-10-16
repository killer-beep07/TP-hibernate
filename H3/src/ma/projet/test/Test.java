/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.test;

import java.util.Calendar;
import ma.projet.classes.Employe;
import ma.projet.classes.EmployeTache;
import ma.projet.classes.EmployeTachePK;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.services.EmployeService;
import ma.projet.services.EmployeTacheService;
import ma.projet.services.ProjetService;
import ma.projet.services.TacheService;
import ma.projet.util.HibernateUtil;
import sun.applet.Main;

/**
 *
 * @author Lachgar
 */
public class Test {
    public static void main (String [] args){
        EmployeService es = new EmployeService();
        EmployeTacheService ets = new EmployeTacheService();
        ProjetService ps = new ProjetService();
        TacheService ts = new TacheService();
        Employe e1 = new Employe("anwar", "Bliout", "0667805006");
          Employe e2 = new Employe("mohamed", "fezzazi", "064572219");
          Calendar calendarDebut1 = Calendar.getInstance();
calendarDebut1.set(2022, Calendar.OCTOBER, 5); 
   Calendar calendarDebut2 = Calendar.getInstance();
calendarDebut2.set(2022, Calendar.DECEMBER, 16); 
        Projet p = new Projet("Finance",calendarDebut1.getTime(), calendarDebut2.getTime(), e2);
        Tache t = new Tache("Bilan", calendarDebut1.getTime(), calendarDebut2.getTime(),20000 , p);
        Tache t2 = new Tache("comptabilite", calendarDebut1.getTime(), calendarDebut2.getTime(),10000 , p);
        es.create(e2);
        es.create(e1);
        ps.create(p);
        ts.create(t);
        ts.create(t2);
                EmployeTachePK pk = new EmployeTachePK(e1.getId(), t.getId(), calendarDebut1.getTime());
        EmployeTache et = new EmployeTache(pk);
        ets.create(et);
        System.out.println(p);
            System.out.println(e2.getProjets());
            Employe e = es.getById(1);
            e.getProjets().add(ps.getById(1));
            e.getEmployetaches().add(et);
        System.out.println(e.getProjets());
       // e2.getProjets().add(p);
        es.getProjetsParEmploye(e);
                System.out.println("=====================");
                es.getTasksPerformedByEmployee(e);
    }
}
