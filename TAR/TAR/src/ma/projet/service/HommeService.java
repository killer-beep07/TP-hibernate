package ma.projet.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ma.projet.dao.IDao;
import ma.projet.entities.Femme;
import ma.projet.entities.Homme;
import ma.projet.entities.Mariage;
import ma.projet.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


public class HommeService implements IDao<Homme> {

    @Override
    public boolean create(Homme o) {
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
    public List<Homme> findAll() {
        List<Homme> hommes = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            hommes = session.createQuery("from Homme").list();
            tx.commit();
            return hommes;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return hommes;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Homme findById(int id) {
        Homme homme = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            homme = (Homme) session.get(Homme.class, id);
            tx.commit();
            return homme;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return homme;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public List<Femme> getWives(int id, Date dateDebut, Date dateFin) throws ParseException {
        List<Femme> wives = new ArrayList<>();
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            wives = session.createCriteria(Femme.class)
                    .createAlias("mariages", "mar")
                    .createAlias("mar.homme", "hm")
                    .add(Restrictions.eq("hm.id", id))
                    .list();

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

        return wives;
    }

    public void displayGetWives(int idHomme, Date dateDebut, Date dateFin) throws ParseException {
        List<Femme> wives = getWives(idHomme, dateDebut, dateFin);

        if (wives.isEmpty()) {
            System.out.println("homme celibataire!");
        } else {
            System.out.println("cet homme a ");
            for (Femme femme : wives) {
                System.out.println(femme.getNom() +" "+ femme.getPrenom() +" comme epouse");
            }
        }
    }

 public int countMenMarriedByFourWomenBetweenDates(Date startDate, Date endDate) {
    Session session = null;
    int count = 0;

    try {
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Mariage.class)
        .createAlias("homme", "h")
        .add(Restrictions.lt("id.dateFin", endDate))
        .add(Restrictions.gt("id.dateDebut", startDate));
  

        List<Mariage> mariages = criteria.list();

        for (Mariage mariage : mariages) {
           
            Homme homme = mariage.getHomme();

            if (homme.getMariages().size() >= 4) {
                count++;
            }
        }
        count=count/4;
    } finally {
        if (session != null) {
            session.close();
        }
    }

    return (count);
}


    public void displayMenMarriedByFourWomenBetweenDates(Date startDate, Date endDate) {
    int count = countMenMarriedByFourWomenBetweenDates(startDate, endDate);

    System.out.println("Nomber of men married to four women between : " + startDate + " and " + endDate + " are : " + count);
}

    
public void displayAllMarriagesOfMan(int hommeId) {
    Session session = null;
    try {
        session = HibernateUtil.getSessionFactory().openSession();

        Homme homme = (Homme) session.get(Homme.class, hommeId);

        if (homme != null) {
            System.out.println("lastname : " + homme.getNom() + " , firstname : " + homme.getPrenom());

            List<Mariage> mariages = session.createCriteria(Mariage.class)
                    .add(Restrictions.eq("homme", homme))
                    .list();

            if (!mariages.isEmpty()) {
                System.out.println("cet homme a ete marie :");
                int mariageEnCoursCount = 1;
                int mariageEchoueCount = 1;
                System.out.println("marriage en cours :");
                for (Mariage mariage : mariages) {
                    if (mariage.getId().getDateDebut() != null && mariage.getId().getDateFin() == null) {
                        System.out.println("firstname : " + mariage.getFemme().getNom() + " , lastname : " + mariage.getFemme().getPrenom());
                        System.out.println("Date start : " + mariage.getId().getDateDebut());
                        System.out.println();
                        mariageEnCoursCount++;
                    }
                }
                System.out.println("mariages échoués : :");
                for (Mariage mariage : mariages) {
                    if (mariage.getId().getDateDebut() != null && mariage.getId().getDateFin() != null) {
                        System.out.println("lastname : " + mariage.getFemme().getNom() + " , firstname : " + mariage.getFemme().getPrenom());
                        System.out.println("Date start : " + mariage.getId().getDateDebut());
                        System.out.println("Date end : " + mariage.getId().getDateFin());
                        System.out.println();
                        mariageEchoueCount++;
                    }
                }
            } else {
                System.out.println("cet homme est encore celibataire");
            }
        } else {
            System.out.println("introuvable");
        }
    } finally {
        if (session != null) {
            session.close();
        }
    }
}


    
    
}
