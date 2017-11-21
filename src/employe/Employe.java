/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package employe;

/**
 *
 * @author Habib Youniss
 */

   abstract class Employe {
    private String nom;
    private String prenom;
    private int age;
    private String date_entree;
    
    
    public Employe(String prenom, String nom, int age, String date_entree) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.date_entree = date_entree;
    }

    public abstract double calculerSalaire();

    public String getTitre()
        {
            return "L'employé " ;
        }
    
    public String getNom() {
        return getTitre() + prenom + " " + nom;
    }
}
abstract class Commercial extends Employe {
    private double chiffreAffaire;

    public Commercial(String prenom, String nom, int age, String date_entree,
               double chiffreAffaire) {
        super(prenom, nom, age, date_entree);
        this.chiffreAffaire = chiffreAffaire;
    }

    public double getChiffreAffaire()
        {
            return chiffreAffaire;
        }
    
}
class Vendeur extends Commercial {
    private final static double POURCENT_VENDEUR = 0.2;
    private final static int BONUS_VENDEUR = 400;
    
    public Vendeur(String prenom, String nom, int age, String date_entree,
            double chiffreAffaire) {
        super(prenom, nom, age, date_entree, chiffreAffaire);
    }

    public double calculerSalaire() {
        return (POURCENT_VENDEUR * getChiffreAffaire()) + BONUS_VENDEUR;
    }

    public String getTitre()
        {
            return "Le vendeur ";
        }

}
class Representant extends Commercial {
    private final static double POURCENTAGE_REPRESENTANT = 0.2;
    private final static int PRIME_REPRESENTANT = 800;
    
    public Representant(String prenom, String nom, int age, String date_entree, double chiffreAffaire) {
        super(prenom, nom, age, date_entree, chiffreAffaire);
    }

    public double calculerSalaire() {
        return (POURCENTAGE_REPRESENTANT * getChiffreAffaire()) + PRIME_REPRESENTANT;
    }

    public String getTitre()
        {
            return "Le représentant ";
        }
}
class Technicien extends Employe {
    private final static double FACTEUR_UNITE = 5.0;
    private int unites;

    public Technicien(String prenom, String nom, int age, String date_entree, int unites) {
        super(prenom, nom, age, date_entree);
        this.unites = unites;
    }

    public double calculerSalaire() {
        return FACTEUR_UNITE * unites;
    }

    public String getTitre()
        {
            return "Le technicien ";
        }
}
class Manutentionnaire extends Employe {
    private final static double SALAIRE_HORAIRE = 65.0;
    private int heures;

    public Manutentionnaire(String prenom, String nom, int age, String date_entree,
                     int heures) {
        super(prenom, nom, age, date_entree);
        this.heures = heures;
    }

    public double calculerSalaire() {
        return SALAIRE_HORAIRE * heures;
    }

    public String getTitre()
        {
            return "Le manut. " ;
        }
}
interface ARisque {
    int PRIME = 25000;
}
class TechnARisque extends Technicien implements ARisque {

    public TechnARisque(String prenom, String nom, int age, String date_entree, int unites) {
        super(prenom, nom, age, date_entree, unites);
    }

    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME;
    }
}
class ManutARisque extends Manutentionnaire implements ARisque {
    
    public ManutARisque(String prenom, String nom, int age, String date_entree, int heures) {
        super(prenom, nom, age, date_entree, heures);
    }

    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME;
    }
}
class Personnel {
    private Employe[] staff;
    private int nbreEmploye;
    private final static int MAXEMPLOYE = 200;

    public Personnel() {
        staff = new Employe[MAXEMPLOYE];
        nbreEmploye = 0;
    }

    public void ajouterEmploye(Employe e) {
        ++nbreEmploye;
        if (nbreEmploye <= MAXEMPLOYE) {
            staff[nbreEmploye - 1] = e;
        } else {
            System.out.println("Pas plus de " + MAXEMPLOYE + " employés");
        }
    }

    public double salaireMoyen() {
        double somme = 0.0;
        for (int i = 0; i < nbreEmploye; i++) {
            somme += staff[i].calculerSalaire();
        }
        return somme / nbreEmploye;
    }

    public void afficherSalaires() {
        for (int i = 0; i < nbreEmploye; i++) {
            System.out.println(staff[i].getNom() + " gagne "
                    + staff[i].calculerSalaire() + " francs.");
        }
    }
}

class Salaires {
    public static void main(String[] args) {
        Personnel p = new Personnel();
        p.ajouterEmploye(new Vendeur("Toto", "Gardien", 35, "1999", 30000));
        p.ajouterEmploye(new Representant("Mamadou", "Kourouma", 25, "2001", 20000));
        p.ajouterEmploye(new Technicien("Emmanuel", "Congo", 28, "1998", 1000));
        p.ajouterEmploye(new Manutentionnaire("Zigba", "Molango", 32, "1998", 45));
        p.ajouterEmploye(new TechnARisque("Placca", "Jean-Baptiste", 18, "2000", 1000));
        p.ajouterEmploye(new ManutARisque("ALi", "Garga", 30, "2001", 35));

        p.afficherSalaires();
        System.out.println("Le salaire moyen dans l'entreprise est de "
                + p.salaireMoyen() + " francs.");

    }


    
}

    

