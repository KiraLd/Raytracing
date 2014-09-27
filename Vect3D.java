/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raytracer;

/**
 *
 * Représente un vecteur en 3 dimensions
 * @author Lucas
 */

public class Vect3d {
    
    //Composantes x,y,z du vecteur
    private double x;
    private double y;
    private double z;
    
    
    //Constructeurs
    public Vect3d()
    {
        x = 0.0;
        y = 0.0;
        z = 0.0;
    }
    
    public Vect3d(Vect3d v)
    {
        x = v.x;
        y = v.y;
        z = v.z;
    }
    
    public Vect3d(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    //Construction d'un vecteur à partir de 2 points: VecteurAB = B - A
    public Vect3d(Point a, Point b)
    {
        x = b.getX() - a.getX();
        y = b.getY() - a.getY();
        z = b.getZ() - a.getZ();
    }
    
    
    
    //Getters
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public double getZ()
    {
        return z;
    }
    
    
    //Setters
    public void setX(double x)
    {
        this.x = x;
    }
    
    public void setY(double y)
    {
        this.y = y;
    }
    
    public void setZ(double z)
    {
        this.z = z;
    }
    
    
    //Retourne le produit scalaire de 2 vecteurs
    public double produit_scalaire(Vect3d v)
    {
        return x * v.x + y * v.y + z * v.z;
    }
    
    //Retourne la norme du vecteur
    public double module()
    {
        return Math.sqrt(x * x + y * y + z * z);
    }
    
    public void normalise()
    {
        double n = module();
        x = x/n;
        y = y/n;
        z = z/n;
    }
    
    public void afficher()
    {
        System.out.println(x+" "+y+" "+z);
    }
            
    
}
