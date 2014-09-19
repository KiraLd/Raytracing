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
    
    private Vect3d couleur;         //Représente une couleur rvb
    private double coef_couleur;    //coefficient associer à la couleur
    private Point p;                //origine de l'objet(sauf pour les plans)
    
    
    //constructeurs
    
    public Objet()
    {
        couleur = new Vect3d();
        coef_couleur = 0.0;
        p = new Point();
    }
    
    public Objet(Vect3d c, double coef, Point p)
    {
        couleur = new Vect3d(c);
        coef_couleur = coef;
        this.p = new Point(p);
    }
    
    
    //getters
    
    public Vect3d getCouleur()
    {
        return couleur;
    }
    
    public double getCoefCouleur()
    {
        return coef_couleur;
    }
    
    public Point getOrigine()
    {
        return p;
    }
    
    //retourne le point d'intersection entre l'objet et le rayon
    
    public abstract Point intersection(Rayon r);
}


