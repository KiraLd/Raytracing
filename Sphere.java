/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raytracer;

/**
 *Représente une sphère
 * @author Lucas
 */
public class Sphere extends Objet{
    private double rayon; //rayon de la sphère
    
    
    //Constructeurs
    public Sphere()
    {
        super();
        rayon = 1.0;
    }
    
    public Sphere(Vect3d c, double coef, Vect3d d, double coef_d,Vect3d s,double c_s, Point p, double r)
    {
        super(c,coef,d,coef_d,s,c_s,p);
        rayon=r;
    }
    
    public double getRayon()
    {
        return rayon;
    }
    
    //Renvoi le point d'intersection entre r et la sphère, si il n'y a pas de point d'intersection -> coordonnées infinies
    
    public Point intersection(Rayon r)
    {
        Vect3d v = r.getDirection();
        Point p = r.getOrigine();
        double vx = v.getX();
        double vy = v.getY();
        double vz = v.getZ();
        double xr = p.getX();
        double yr = p.getY();
        double zr = p.getZ();
        Vect3d oc = new Vect3d(r.getOrigine(),this.getOrigine());
        double scalaire = oc.produit_scalaire(r.getDirection());
        if(scalaire<0)
        {
            return new Point(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
        }
        else
        {
            double ch = oc.module()*oc.module() - scalaire*scalaire;
            if(ch>rayon*rayon)
            {
                return new Point(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
            }
            else
            {
                double d2 = rayon*rayon -ch;
                double t1 = scalaire + Math.sqrt(d2);
                double t2 = scalaire - Math.sqrt(d2);
                Point p1 = new Point(t1 * vx + xr , t1 * vy + yr , t1 * vz + zr);
                Point p2 = new Point(t2 * vx + xr , t2 * vy + yr , t2 * vz + zr);
                if(t1<t2&&t1>0)
                {
                    return p1;
                }
                else
                {
                    return p2;
                }
            }
        }
    }
    
}
