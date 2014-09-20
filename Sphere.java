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
    
    public Sphere(Vect3d c, double coef, Point p, double r)
    {
        super(c,coef,p);
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
        double x0 = getOrigine().getX();
        double y0 = getOrigine().getY();
        double z0 = getOrigine().getZ();
        double vx = v.getX();
        double vy = v.getY();
        double vz = v.getZ();
        double xr = p.getX();
        double yr = p.getY();
        double zr = p.getZ();
        double a = vx * vx + vy * vy + vz * vz;
        double b = 2 * (vx * (xr - x0) + vy * (yr - y0) + vz * (zr - z0));
        double c = (xr - x0) * (xr - x0) + (yr - y0) * (yr - y0) + (zr - z0) * (zr - z0) - rayon * rayon;
        double d = b * b - 4 * a * c;
        double alpha1;
        double alpha2;
        if(d > 0)
        {
            alpha1 = (-b + Math.sqrt(d)) / 2 * a;
            alpha2 = (-b - Math.sqrt(d)) / 2 * a;
            Point p1 = new Point(alpha1 * vx + xr , alpha1 * vy + yr , alpha1 * vz + zr);
            Point p2 = new Point(alpha2 * vx + xr , alpha2 * vy + yr , alpha2 * vz + zr);
            if(p1.distance(r.getOrigine()) == rayon)
            {
                return p1;
            }
            else
            {
                return p2;
            }
        }
        if(d == 0)
        {
            alpha1 = -b / 2 * a;
            return new Point(alpha1 * vx + xr , alpha1 * vy + yr , alpha1 * vz + zr);
        }
        else
        {
            return new Point(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
        }
        
    }
    
}
