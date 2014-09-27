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
        int x = 800;
        int y = 600;
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
        c.setFocale(300);
        
        //Création des sphères
        Point p1 = new Point(-250,0,50000);
        Point p2 = new Point(-200,0,500);
        Point p3 = new Point(550,0,1000);
        Vect3d bleu = new Vect3d(0,0,255);
        Vect3d rouge = new Vect3d(255,0,0);
        Vect3d vert = new Vect3d(0,255,0);
        Vect3d blanc = new Vect3d(255,255,255);
        Sphere s1 = new Sphere(bleu,0.01,bleu,0.8,bleu,0.19,p1,15000);
        Sphere s2 = new Sphere(rouge,0.01,rouge,0.8,rouge,0.19,p2,75);
        Sphere s3 = new Sphere(vert,0.01,vert,0.8,vert,0.19,p3,500);
        int n = 15;
        
        double r2;
        double v;
        double b;
        Vect3d normal;
        double coef_diffuse;
        
        //tableau d'objet représentant la scène à affiché
        Objet[] scene = {s1,s2,s3};
        int taille = 3;
        Objet courant;
        
        //origine de la source de lumière
        Point lumiere = new Point(500,0,0);
        
        
        //Rayons utilisés pour les calculs
        Rayon r = new Rayon();
        Rayon ombre = new Rayon();
        Rayon reflechi;
        Rayon objet;
        double x_r;
        double y_r;
        double z_r;
        double scalaire;
        boolean ombre2;
        
        //variable utilisé pour déterminer l'objet à afficher
        double min_distance;
        double distance;
        double distance2;
        
        //Point d'intersection
        Point inter;
        Point inter2;
        
        //Utilisé pour la couleur de l'objet à afficher
        Color couleur_final;
        double coef_ambiante;
        double attenuation;
        double coef_speculaire;
        Point temp=null;
        long start; 

        start = System.nanoTime();

        for(int i = 0 ; i < x ; i++)
        {
            courant = null;
            for(int j = 0;j < y ; j++)
            {
                min_distance=Double.POSITIVE_INFINITY;
                r.calculerRayon(c, i, j);                                       //on calcul le rayon partant de la caméra, pour chaque pixels de l'écran
                r.getDirection().normalise();
                for(int k = 0 ; k < taille ; k++)                                       //on détermine l'objet le plus proche de la caméra et en intersection avec le rayon
                {
                    inter = scene[k].intersection(r);
                    if(inter.getX() < Double.POSITIVE_INFINITY)                   //inter.getX()==INFINITY si le rayon n'est pas en intersection avec l'objet scene[k]
                    {
                        distance = inter.distance(c.getOrigine());
                      //  inter.afficher();
                        
                        if(distance < min_distance)
                        {
                            temp=new Point(inter);
                            //temp.afficher();
                            courant = scene[k];
                            min_distance = distance;
                        }
                    }   
                }
                
                if(min_distance < Double.POSITIVE_INFINITY&&courant!=null&&temp!=null)                       //min_distance=INFINITY si le rayon ne coupe aucun objet
                {
                    
                    inter2 = new Point(temp); 
                    ombre.calculerRayon_point(inter2, lumiere, inter2);           //on calcul le rayon entre le point d'intersection et l'origine de la lumière
                    normal = new Vect3d(courant.getOrigine(),inter2);
                    normal.normalise();
                    ombre.getDirection().normalise();
                    objet = new Rayon(r.getOrigine(),new Vect3d(r.getOrigine(),courant.getOrigine()));
                    objet.getDirection().normalise();
                    if(objet.getDirection().produit_scalaire(normal)<0)
                    {
                        //calcul de la lumière diffuse
                        coef_diffuse = normal.produit_scalaire(ombre.getDirection());
                        if(coef_diffuse>0)
                        {
                            coef_diffuse=0;
                        }
                        
                        //coef associé à l'objet courant
                        coef_ambiante = courant.getCoefCouleurAmbiante();
                        coef_speculaire=courant.getCoefCouleurSpeculaire();
                        attenuation = courant.getCoefCouleurDiffuse();
                        
                        //calcul du rayon réfléchi
                        scalaire = 2*normal.produit_scalaire(ombre.getDirection());
                        x_r = normal.getX() * scalaire;
                        y_r = normal.getY() * scalaire;
                        z_r = normal.getZ() * scalaire;
                        x_r = r.getDirection().getX()- x_r;
                        y_r = r.getDirection().getY() - y_r;
                        z_r = r.getDirection().getZ() - z_r;
                        reflechi = new Rayon(inter2,new Vect3d(x_r,y_r,z_r));
                        reflechi.getDirection().normalise();
                        
                        //calcul reflexion speculaire
                        scalaire = Math.pow(reflechi.getDirection().produit_scalaire(r.getDirection()), n);
                        if(scalaire>0)
                        {
                            scalaire=0;
                        }

                        //calcul de la couleur
                        r2 = courant.getCouleurAmbiante().getX()*coef_ambiante + courant.getCouleurDiffuse().getX()*coef_diffuse*attenuation + scalaire*coef_speculaire*courant.getCouleurSpeculaire().getX();
                        v = courant.getCouleurAmbiante().getY()*coef_ambiante + courant.getCouleurDiffuse().getY()*coef_diffuse*attenuation + scalaire*coef_speculaire*courant.getCouleurSpeculaire().getY();
                        b = courant.getCouleurAmbiante().getZ()*coef_ambiante + courant.getCouleurDiffuse().getZ()*coef_diffuse*attenuation + scalaire*coef_speculaire*courant.getCouleurSpeculaire().getZ();

                        
                        //vérification composantes valides
                        if(r2>255)
                        {
                            r2=255;
                        }
                        if(r2<0)
                        {
                            r2=-r2%255;
                        }
                        if(v>255)
                        {
                            v=255;
                        }
                        if(v<0)
                        {
                            v=-v%255;
                        }
                        if(b>255)
                        {
                            b=255;
                        }
                        if(b<0)
                        {
                            b=-b%255;
                        }
                        
                        //affichage du pixel (i,j)
                        couleur_final = new Color( (int)r2,(int)v,(int)b);
                        g.setColor(couleur_final);
                        g.fillRect(i, j, 1, 1);
                    }
               }
            }
        }
        
        //temps d'execution
        long duree = System.nanoTime() - start;

        System.out.println((double)duree/1000000000);      
    }
}
