/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.project.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.project.*;
import ma.project.entity.Produit;
import ma.project.service.ProduitService;
import org.hibernate.Query;

public class Test {

    public static void main(String[] args) throws ParseException {
        ProduitService ps = new ProduitService();
         
       

        ps.create(new Produit("Dior", "REF-1", new Date("2000/01/01"), 2000, "Produit 1"));

        ps.create(new Produit("Boss", "REF-2",  new Date("2005/01/01"), 1500, "Produit 2"));

        ps.create(new Produit("Lacoste", "REF-3", new Date("2010/01/01"), 2500, "Produit 3"));

        ps.create(new Produit("Coach", "REF-4", new Date("2015/01/01"), 1800, "Produit 4"));

        ps.create(new Produit("Louis Vuitton", "REF-5", new Date("2018/01/01"), 3000, "Produit 5"));

    // Récupérez la liste des produits depuis la base de données.
        List<Produit> produits = ps.findAll();
        for (Produit p : produits) {
            System.out.println("Nom : " + p.getNom());

        }
      
        
        
                Produit m = ps.findById(2);
        
        System.out.println("Nom : " + m.getNom());
    System.out.println("Référence : " + m.getReference());
    System.out.println("Date d'achat : " + m.getDateAchat());
    System.out.println("Prix : " + m.getPrix());
    System.out.println("Désignation : " + m.getDesignation());
Produit g = ps.findById(3);
ps.delete(g);

Produit A = ps.findById(1);
A.setNom("Hermes");
ps.update(A);

        for (Produit B : ps.findAll()) {
            if (B.getPrix() > 100) {
                System.out.println("les produits avec les prix superieurs a 100 sont les suivants:" +B.getNom());
}
        }
        
        
         Scanner sc = new Scanner(System.in);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            System.out.println("entrer date debut intervalle sous cette forme yyyy-MM-dd");
            String D1 = sc.nextLine();
            Date d1 = dateFormat.parse(D1);
            
            System.out.println("entrer date fin intervalle sous cette forme yyyy-MM-dd");
            String D2 = sc.nextLine();
            Date d2 = dateFormat.parse(D2);
            
            System.out.println(" la liste des produits Commander entre " + D1 + "et" + D2 + " sont:");
           for (Produit u : ps.findBetweenDate(d1, d2)) {
    System.out.println(u.getNom());
}
}
        
}


