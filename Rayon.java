/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raytracer;

/**
 *
 * Représente le rayon utilisé pour les calculs d'intersection du moteur de raytracing
 * @author Lucas
 */

public class Rayon {
    
    private Point p;  //Origine du rayon
    private Vect3d v;   //Vecteur directeur du rayon
    
    
    //Constructeurs
    
    public Rayon()
    {
        p = new Point();
        v = new Vect3d();
    }
    
    public Rayon(Rayon r)
    {
        p = new Point(r.p);
        v = new Vect3d(r.v);
    }
    
    public Rayon(Point p, Vect3d v)
    {
        this.p = new Point(p);
        this.v = new Vect3d(v);
    }
    
    public Rayon(double x, double y, double z, double a, double b, double c)
    {
        p = new Point(x,y,z);
        v = new Vect3d(a,b,c);
    }
    
    //getters
    
    public Point getOrigine()
    {
        return p;
    }
    
    public Vect3d getDirection()
    {
        return v;
    }
    
    
    //Définit un rayon à partir de 3 points
    
    public void calculerRayon_point(Point o, Point a, Point b)
    {
        p = new Point(o);
        v = new Vect3d(a,b);
        
    }
    
    //Calcul le rayon envoyé par la caméra en fonction des coordonnées du pixel
    
    public void calculerRayon(Camera c, int i, int j)
    {
         v.setX((i - c.getResX() / 2) * c.getPitchX());
         v.setY((j - c.getResY() / 2 ) * c.getPitchY());
         v.setZ(c.getF());
    }
    
}
