/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.entities;

/**
 *
 * @author Mariam
 */
public class HWCalculator {
    private String nomanimal;
    private float poidanimal;
    private int typeanimal;
    private int niveauactivite;
    private int neutred;

    public String getNomanimal() {
        return nomanimal;
    }

    public void setNomanimal(String nomanimal) {
        this.nomanimal = nomanimal;
    }

    public float getPoidanimal() {
        return poidanimal;
    }

    public void setPoidanimal(float poidanimal) {
        this.poidanimal = poidanimal;
    }

    public int getTypeanimal() {
        return typeanimal;
    }

    public void setTypeanimal(int typeanimal) {
        this.typeanimal = typeanimal;
    }

    public int getNiveauactivite() {
        return niveauactivite;
    }

    public void setNiveauactivite(int niveauactivite) {
        this.niveauactivite = niveauactivite;
    }

    public int getNeutred() {
        return neutred;
    }

    public void setNeutred(int neutred) {
        this.neutred = neutred;
    }

    public HWCalculator(String nomanimal, float poidanimal, int typeanimal, int niveauactivite, int neutred) {
        this.nomanimal = nomanimal;
        this.poidanimal = poidanimal;
        this.typeanimal = typeanimal;
        this.niveauactivite = niveauactivite;
        this.neutred = neutred;
    }

    public HWCalculator() {
    }
    
    
}
