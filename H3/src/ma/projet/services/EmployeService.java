/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.services;

import java.util.List;
import ma.projet.classes.Employe;
import ma.projet.classes.EmployeTache;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class EmployeService  implements IDao<Employe> {
    private Object session;

    @Override
    public boolean create(Employe o) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally {
            if(session != null)
                session.close();
        }
        return false;
    }

   

    @Override
    public Employe getById(int id) {
        Employe employe = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            employe = (Employe) session.get(Employe.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally {
            if(session != null)
                session.close();
        }
        return employe;
    }

    @Override
    public List<Employe> getAll() {
        List<Employe> employes = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            employes = session.createQuery("from Employe").list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally {
            if(session != null)
                session.close();
        }
        return employes;
    }
public void getProjetsParEmploye(Employe employe) {
        List<Projet> projetsGeres = employe.getProjets(); 
       for(Projet p : projetsGeres){
        System.out.println(p); 
    }
}
 public  List<EmployeTache> getTacheList(int id) {
        List<EmployeTache> laListe=null;
          Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
          String hql = "SELECT et FROM EmployeTache et " +
             "JOIN et.tache AS t " +
             "JOIN et.employe AS e " +
             "WHERE e.id = :id";


            laListe = session.createQuery(hql)
                    .setParameter("id",id).list();
            
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
          return laListe;
      }
// public void printTasksPerformedByEmployee(int employeId) {
//        List<EmployeTache> tacheList = getTacheList(employeId);
//
//        if (tacheList.isEmpty()) {
//            System.out.println("Aucune tâche réalisée par l'employé avec l'ID " + employeId);
//        } else {
//            System.out.println("Tâches réalisées par l'employé avec l'ID " + employeId + ":");
//            for (EmployeTache employeTache : tacheList) {
//                  System.out.println("----------------------------------------------");
//                System.out.println("Tâche ID : " + employeTache.getTache().getId()+"    Tâche Nom : " + employeTache.getTache().getNom());
//             
//            }
//        }}
    public void getTasksPerformedByEmployee(Employe employe) {
        List<Tache> tasks = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            Query query = session.getNamedQuery("getTasksPerformedByEmployee");
            query.setParameter("employeId", employe.getId());
            
            tasks = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        if (tasks.isEmpty()) {
            System.out.println("Aucune tâche réalisée par " + employe.getNom());
        } else {
            System.out.println("Tâches réalisées par " + employe.getNom() + ":");
            for (Tache task : tasks) {
                System.out.println(task.getNom());
            }
        }
    }
       public void getProjectsManagedByEmployee(Employe employe) {
         List<Projet> projets = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            Query query = session.getNamedQuery("getProjectsByEmployee");
            query.setParameter("employeeId", employe.getId());
            
            projets = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        if (projets.isEmpty()) {
            System.out.println("Aucune tâche réalisée par " + employe.getNom());
        } else {
            System.out.println("Tâches réalisées par " + employe.getNom() + ":");
            for (Projet p : projets) {
                System.out.println(p.getNom());
            }
        }
    }
}