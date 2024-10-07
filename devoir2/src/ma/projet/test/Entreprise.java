package ma.projet.test;
import ma.projet.beans.Developpeur;
import ma.projet.beans.Manager;
import ma.projet.service.DeveloppeurService;
import ma.projet.service.ManagerService;
public class Entreprise {
    public static void main(String[] args) {
        // Création des services
        ManagerService managerService = new ManagerService();
        DeveloppeurService developpeurService = new DeveloppeurService();
        
        // Création des Développeurs
        Developpeur developpeur1 = new Developpeur(0, "Ahmed", 7000);
        Developpeur developpeur2 = new Developpeur(0, "Zineb", 8500);
        
        // Ajouter les 2 développeurs à la base de données entreprise 
        developpeurService.create(developpeur1);
        developpeurService.create(developpeur2);
        
        // Création du manager qui gère les 2 Développeurs
        Manager manager = new Manager(0, "Achouak", 18000);
        managerService.create(manager);
        
        // Création un 3 ème développeur
        Developpeur developpeur3 = new Developpeur(0, "Fatima", 8000);
        developpeurService.create(developpeur3);
        
        
        // Création du directeur général qui gère le manager et le 3 ème développeur
        Manager directeurGeneral = new Manager(0, "Mohammed", 45000);
        managerService.create(directeurGeneral);
        
       
        System.out.println("************* L'hiérarchie des employés *************");
        // Affichage du directeur général
        System.out.println("Le directeur général:");
        System.out.printf("Nom: %s, Salaire: %.2f\n", directeurGeneral.getNom(), directeurGeneral.getSalaire());
        // Affichage du manager
        System.out.println("\nManager:");
        System.out.printf("Nom: %s, Salaire: %.2f\n", manager.getNom(), manager.getSalaire());
        // Affichage des développeurs sous le Manager
        System.out.println("\nLes développeurs supervisés par " + manager.getNom() + ":");
        for (Developpeur d : developpeurService.getAll()) {
            System.out.printf("Développeur: Nom: %s, Salaire: %.2f\n", d.getNom(), d.getSalaire());
        }
        // Affichage du 3 ème développeur
        System.out.println("\n3ème Développeur (non supervisé par le Manager):");
        System.out.printf("Nom: %s, Salaire: %.2f\n", developpeur3.getNom(), developpeur3.getSalaire());

        // Message de cloture
        System.out.println("\n************* Fin de la hiérarchie des employés *************");
    }
}
