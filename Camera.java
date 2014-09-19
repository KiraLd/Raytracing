/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raytracer;

/**
 *Représente la caméra
 *Caméra limité en réalité, pas de gestion de l'orientation
 * @author Lucas
 */
public class Camera {
    
    Point coo;                              //Origine de la camera
    int f;                                  //focale
    int res_x;                              //résolution x*y
    int res_y;
    double pitch_x;                         //correspondance pixel/unité dans l'espace, 1.0 -> 1 pixel vaut 1 unité dans l'espace
    double pitch_y;
    
    
    //Constructeur
    public Camera()
    {
        coo = new Point();
        coo.setX(0.0);
        coo.setY(0.0);
        coo.setZ(0.0);
        f = 200;
        res_x=400;
        res_y=400;
        pitch_x=1.0;
        pitch_y=1.0;
    }
    
    
    //setters
    public void setCoo(double x, double y, double z)
    {
        coo.setX(x);
        coo.setY(y);
        coo.setZ(z);
    }
     
    public void setFocale(int f)
    {
        this.f=f;
    }
    
    public void setRes(int x, int y)
    {
        res_x=x;
        res_y=y;
    }
    
    public void setPitch(double x, double y)
    {
        pitch_x=x;
        pitch_y=y;
    }
    
    
    //getters
    public double getCoX()
    {
        return coo.getX();
    }
    
    public double getCoY()
    {
        return coo.getY();
    }
    
    public double getCoZ()
    {
        return coo.getZ();
    }
    
    public int getF()
    {
        return f;
    }
    
    public int getResX()
    {
        return res_x;
    }
    
    public int getResY()
    {
        return res_y;
    }
    
    public double getPitchX()
    {
        return pitch_x;
    }
    
    public double getPitchY()
    {
        return pitch_y;
    }
    
    public Point getOrigine()
    {
        return coo;
    }
    
    
    //affiche les paramètres de la caméra
    public void afficher()
    {
        System.out.println("Coordonnées: ");
        coo.afficher();
        System.out.println("\nFocale, en pixels: "+f);
        System.out.println("\nRésolution, en pixels: "+res_x+","+res_y);
        System.out.println("\nPitch, nombre de pixels par unité: "+pitch_x+","+pitch_y);

        
    }
    
    
    
}
