/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raytracer;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Lucas
 */
public class Affichage extends JFrame{
    public Affichage(){
        
        //résolution 
        int x = 500;
        int y = 500;
        //création de la fenêtre
        this.setTitle("Raytracer");
        this.setSize(x,y);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
        this.setVisible(true);
        
        //création du canvas de dessins
        JPanel pan = new JPanel();
        Graphics g = this.getGraphics();
        pan.setBackground(Color.BLACK);     //couleur de fond     
        this.setContentPane(pan);               

        //Création de la caméra
        Camera c = new Camera();
        c.setRes(x, y);
        c.setFocale(200);
        
        //Création des sphères
        Point p1 = new Point(0,0,2000);
        Point p2 = new Point(400,0,1500);
        Vect3d bleu = new Vect3d(0,0,255);
        Vect3d rouge = new Vect3d(255,0,0);
        Sphere s1 = new Sphere(bleu,1.0,p1,500);
        Sphere s2 = new Sphere(rouge,0.5,p2,150);
        
        
        //tableau d'objet représentant la scène à affiché
        Objet[] scene = {s1,s2};
        int taille = 2;
        Objet courant = null;
        
        //origine de la source de lumière
        Point lumiere = new Point(0,100,0);
        
        
        //Rayons utilisés pour les calculs
        Rayon r = new Rayon();
        Rayon ombre = new Rayon();
        boolean ombre2;
        
        //variable utilisé pour déterminer l'objet à afficher
        double min_distance;
        double distance;
        double distance2;
        
        //Point d'intersection
        Point inter = null;
        
        //Utilisé pour la couleur de l'objet à afficher
        Color couleur = new Color(255,0,0);
        double coef;
        
        for(int i = 0 ; i < x ; i++)
        {
            courant = null;
            for(int j = 0;j < y ; j++)
            {
                min_distance=Double.POSITIVE_INFINITY;
                r.calculerRayon(c, i, j);                                       //on calcul le rayon partant de la caméra, pour chaque pixels de l'écran
                for(int k = 0 ; k < taille ; k++)                                       //on détermine l'objet le plus proche de la caméra et en intersection avec le rayon
                {
                    inter = scene[k].intersection(r);
                    if(inter.getX() < Double.POSITIVE_INFINITY)                   //inter.getX()==INFINITY si le rayon n'est pas en intersection avec l'objet scene[k]
                    {
                        distance = inter.distance(c.getOrigine());
                        if(distance < min_distance)
                        {
                            courant = scene[k];
                            min_distance = distance;
                        }
                    }   
                }
                if(min_distance < Double.POSITIVE_INFINITY)                       //min_distance=INFINITY si le rayon ne coupe aucun objet
                {
                    distance = inter.distance(lumiere); 
                    ombre.calculerRayon_point(inter, inter, lumiere);           //on calcul le rayon entre le point d'intersection et l'origine de la lumière
                    ombre2 = false;
                    
                    
                    for(int k = 0 ; k < taille ; k++)                                 //pour chaque objet on vérifie qu'il n'y a pas d'objet entre l'objet à afficher et la source de lumière
                    {
                            inter = scene[k].intersection(ombre);               
                            distance2 = inter.distance(lumiere);
                            if(distance2 < distance && inter.getX() < Double.POSITIVE_INFINITY)
                            {
                                
                                ombre2=true;                                    
                                break;
                            }
                            
                    }
                    if(!ombre2)                                                 //si il n'y a pas d'objet entre la lumière et l'objet à afficher, on calcul la couleur de l'objet puis on affiche
                    {
                        coef = courant.getCoefCouleur();
                        couleur = new Color( (int)  (courant.getCouleur().getX() * coef) , (int) (courant.getCouleur().getY() * coef) , (int) (courant.getCouleur().getZ() * coef));
                        g.setColor(couleur);
                        g.fillRect(i, j, 1, 1);
                    }
                }
            }
        }
    }
}
