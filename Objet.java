/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raytracer;

/**
 *Base pour représenter un objet géométrique dans l'espace.
 * @author Lucas
 */

public abstract class Objet {
    
    private Vect3d couleur_ambiante;         //Représente une couleur rvb
    private double coef_couleur_ambiante;    //coefficient associer à la couleur
    private Vect3d couleur_diffuse;
    private double coef_couleur_diffuse;
    private Vect3d couleur_speculaire;
    private double coef_couleur_speculaire;
    private Point p;                //origine de l'objet(sauf pour les plans)
    
    
    //constructeurs
    
    public Objet()
    {
        couleur_ambiante = new Vect3d();
        coef_couleur_ambiante = 0.0;
        couleur_diffuse = new Vect3d();
        coef_couleur_diffuse = 0.0;
        couleur_speculaire = new Vect3d();
        coef_couleur_speculaire = 0.0;
        p = new Point();
    }
    
    public Objet(Vect3d c, double coef, Vect3d d, double coef_d,Vect3d s,double coef_s,Point p)
    {
        couleur_ambiante = new Vect3d(c);
        coef_couleur_ambiante = coef;
        couleur_diffuse = new Vect3d(d);
        coef_couleur_diffuse = coef_d;
        couleur_speculaire = new Vect3d(s);
        coef_couleur_speculaire = coef_s;
        this.p = new Point(p);
    }
    
    
    //getters
    
    public Vect3d getCouleurAmbiante()
    {
        return couleur_ambiante;
    }
    
    public double getCoefCouleurAmbiante()
    {
        return coef_couleur_ambiante;
    }
    
    public Vect3d getCouleurDiffuse()
    {
        return couleur_diffuse;
    }
    
    public double getCoefCouleurDiffuse()
    {
        return coef_couleur_diffuse;
    }
    
     public double getCoefCouleurSpeculaire()
    {
        return coef_couleur_speculaire;
    }
     
    public Vect3d getCouleurSpeculaire()
    {
        return couleur_speculaire;
    }
    
    
    public Point getOrigine()
    {
        return p;
    }
    
    //retourne le point d'intersection entre l'objet et le rayon
    
    public abstract Point intersection(Rayon r);
}





