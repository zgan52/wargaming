package com.example.euj.wargaming.entites;

/**
 * Created by Bouba on 15/11/2015.
 */
public class Joueur {
    public String cote;
    public String batailles;
    public String exrerience;
    public String frags;
    public String arbres;
    public String degat;

    public Joueur() {
        super();
    }

    public Joueur(String cote, String batailles, String exrérience, String frags, String arbres, String dégat) {
        super();
        this.cote = cote;
        this.batailles = batailles;
        this.exrerience = exrérience;
        this.frags = frags;
        this.arbres = arbres;
        this.degat = degat;
    }

    public String getCote() {
        return cote;
    }

    public void setCote(String cote) {
        this.cote = cote;
    }

    public String getBatailles() {
        return batailles;
    }

    public void setBatailles(String batailles) {
        this.batailles = batailles;
    }

    public String getExrerience() {
        return exrerience;
    }

    public void setExrerience(String exrérience) {
        this.exrerience = exrérience;
    }

    public String getFrags() {
        return frags;
    }

    public void setFrags(String frags) {
        this.frags = frags;
    }

    public String getArbres() {
        return arbres;
    }

    public void setArbres(String arbres) {
        this.arbres = arbres;
    }

    public String getDegat() {
        return degat;
    }

    public void setDegat(String degat) {
        this.degat = degat;
    }
}
