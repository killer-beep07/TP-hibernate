/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Mariage  implements Serializable {

    @EmbeddedId
    private MariagePK id;
    @JoinColumn(name = "homme", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    private Homme homme;
    @JoinColumn(name = "femme", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    private Femme femme;

    public Mariage() {
    }

    public Mariage(MariagePK id) {
        this.id = id;
    }

    public MariagePK getId() {
        return id;
    }

    public void setId(MariagePK id) {
        this.id = id;
    }

    public Homme getHomme() {
        return homme;
    }

    public void setHomme(Homme homme) {
        this.homme = homme;
    }

    public Femme getFemme() {
        return femme;
    }

    public void setFemme(Femme femme) {
        this.femme = femme;
    }

    @Override
    public String toString() {
        return "Mariage{" + "id=" + id + ", homme=" + homme + ", femme=" + femme + '}';
    }

}
