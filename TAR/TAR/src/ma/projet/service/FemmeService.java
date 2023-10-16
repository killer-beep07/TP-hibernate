package ma.projet.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ma.projet.dao.IDao;
import ma.projet.entities.Femme;
import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class FemmeService implements IDao<Femme>{
    @Override
    public boolean create(Femme o) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

 
    @Override
    public List<Femme> findAll() {
        List<Femme> femmes = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            femmes = session.createQuery("from Femme").list();
            tx.commit();
            return femmes;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return femmes;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Femme findById(int id) {
        Femme femme = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            femme = (Femme) session.get(Femme.class, id);
            tx.commit();
            return femme;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return femme;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    


public int countEnfantsFemmeEntreDates(int femmeId, Date dateDebut, Date dateFin) {
    Session session = null;
    Transaction tx = null;
    int count = 0; 

    try {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        Query query = session.getNamedQuery("countEnfantsFemmeEntreDates")
            .setParameter("femmeId", femmeId)
            .setParameter("dateDebut", dateDebut)
            .setParameter("dateFin", dateFin);
        count = ((Number) query.uniqueResult()).intValue();


        tx.commit();
    } catch (HibernateException ex) {
        if (tx != null) {
            tx.rollback();
        }
    } finally {
        if (session != null) {
            session.close();
        }
    }

    return count;
}

public void affichageCountEnfantsFemmeEntreDates(int femmeId, Date dateDebut, Date dateFin) {
    Integer resultat = countEnfantsFemmeEntreDates(femmeId, dateDebut, dateFin);
    Femme femme12=findById(femmeId);
    System.out.println(femme12.getNom()+" "+femme12.getPrenom()+" has " + resultat+" children");
}

  public List<Femme> marriageFemmePlusieursFois() {
    Session session = null;
    Transaction tx = null;
    List<Femme> women = new ArrayList<>();

    try {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        Query query = session.getNamedQuery("marriageFemmePlusieursFois");
        women = query.list();

        tx.commit();
    } catch (HibernateException ex) {
        if (tx != null) {
            tx.rollback();
        }
    } finally {
        if (session != null) {
            session.close();
        }
    }

    return women;
}

  public void afficherMarriageFemmePlusieursFois() {
    List<Femme> marriedWomen = marriageFemmePlusieursFois();

    if (marriedWomen.isEmpty()) {
        System.out.println("aucune femme na ete mariee plus quune fois.");
    } else {
        System.out.println("femme mariee plus qune fois :");
        for (Femme woman : marriedWomen) {
            System.out.println("Lastname: " + woman.getNom() + " , Firstname :" + woman.getPrenom());
        }
    }
}

  
  
}
