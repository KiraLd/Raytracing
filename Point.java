/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raytracer;

/**
 *
 * Représente un point dans l'espace
 * @author Lucas
 */

public class Point {
    
    //Coordonnées du point
    
    double x;
    double y;
    double z;
    
    
    //Constructeurs
    
    public Point()
    {
        x = 0;
        y = 0;
        z = 0;
    }
    
    public Point(double x , double y , double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Point(Point p)
    {
        x = p.x;
        y = p.y;
        z = p.z;
    }
    
    
    //getters
    
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
    
    //setters
    
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
    
    //affiche les coordonnées du point
    
    public void afficher()
    {
        System.out.println("X: " + x + " Y: " + y +" Z: " + z);
    }
    
    //renvoi la distance entre deux points
    
    public double distance(Point p)
    {
        return Math.sqrt( (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y) + (z - p.z) * (z - p.z));
    }
    
}
