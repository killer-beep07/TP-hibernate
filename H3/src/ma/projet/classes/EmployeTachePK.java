/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.classes;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;

/**
 *
 * @author HP
 */
@Embeddable
public class EmployeTachePK implements Serializable {
    private int employe;
 private int tache;
 private Date dateDebut;

    public EmployeTachePK() {
    }

    public EmployeTachePK(int employe, int tache, Date dateDebut) {
        this.employe = employe;
        this.tache = tache;
        this.dateDebut = dateDebut;
    }

    public int getEmploye() {
        return employe;
    }

    public void setEmploye(int employe) {
        this.employe = employe;
    }

    public int getTache() {
        return tache;
    }

    public void setTache(int tache) {
        this.tache = tache;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

}