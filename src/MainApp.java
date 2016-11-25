import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class MainApp {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String nomFichier = null;
    static String action = null;
	  
	public static void debuterProgramme() throws IOException {
		System.out.println("Bienvenue dans notre programme de simulation d'intersection.\n");
		
		//nomFichier = br.readLine();		
		//System.out.println("Vous avez sélectionné le fichier '"+ nomFichier + "'");
		System.out.print("État Initial:\nLumiere ouest: aucune\nLumiere est: aucune\nLumiere sud: aucune\n" +
			"Voiture ouest: aucune\nVoiture est: aucune\nVoiture sud: aucune\nPieton: aucune\n\nAction effectuée : ");
		action = br.readLine();	
	}

	public static void main(String[] args) throws IOException {
    	debuterProgramme();

	    do{
	    	String[] parties = action.split("\\.");

	    	String debutAction = ""; 
	    	String finAction = ""; 
	    	if(parties != null) {
	    		debutAction = parties[0];
	    		finAction = parties[1];
	    	}
	    	else {
    			System.out.println("L'action est invalide\n");
    			debuterProgramme();
	    	}
	    	
	    	// actions concernant les voitures: traitées avec les Threads
	    	// Voitures Est
	    	if(debutAction.equals("voitureEst")) {
	    		Voiture voitureEst = new Voiture("est", finAction);
	    		voitureEst.controllerVoitureEst();
	    		if(voitureEst.threadExecute() == true) {
	    			System.out.print("Les voitures est ne peuvent jamais tourner à droite\n");
	    			debuterProgramme();
	    		}
	    		else {
	    			if(finAction.equals("continuer")) {
	    				System.out.print("Il y'a deux traces de cette action :\nTrace 1 de l'action:\nLumiere ouest: vert\nLumiere est: "
	    						+ "vert\nLumiere sud: rouge\n" +
			    				"Voiture ouest: continuer ou tourner à droite\nVoiture est: continuer\nVoiture sud: attendre\n" +
			    				"Pieton: attendre\nTrace 2 de l'action:\nLumiere ouest: rouge\nLumiere est: vert clignotant\nLumiere sud: vert\n" +
			    				"Voiture ouest: attendre\nVoiture est: continuer ou tourner à gauche\nVoiture sud: tourner à droite\n" +
			    				"Pieton: attendre\n\nAction effectuée : ");
		    			action = br.readLine();
	    			}
	    			else if(finAction.equals("attendre")) {
	    				System.out.print("Trace de l'action:\nLumiere ouest: rouge\nLumiere est: rouge\nLumiere sud: vert clignotant\n" +
			    				"Voiture ouest: attendre\nVoiture est: attendre\nVoiture sud: tourner à gauche ou tourner à droite\n" +
			    				"Pieton: attendre\n\nAction effectuée : ");
		    			action = br.readLine();
	    			}
	    			else if(finAction.equals("tournerGauche")) {
	    				System.out.print("Trace de l'action:\nLumiere ouest: rouge\nLumiere est: vert clignotant\nLumiere sud: vert\n" +
			    				"Voiture ouest: attendre\nVoiture est: continuer ou tourner à gauche\nVoiture sud: tourner à droite\n" +
			    				"Pieton: attendre\n\nAction effectuée : ");
		    			action = br.readLine();
	    			}
	    		}
	    	}
	    	// Voitures Ouest
	    	else if(debutAction.equals("voitureOuest")) {
	    		Voiture voitureOuest = new Voiture("ouest", finAction);
	    		voitureOuest.controllerVoitureOuest();
	    		if(voitureOuest.threadExecute() == true) {
	    			System.out.print("Les voitures ouest ne peuvent jamais tourner à gauche\n");
	    			debuterProgramme();
	    		}
	    		else {
	    			if(finAction.equals("continuer") || finAction.equals("tournerDroite")) {
	    				System.out.print("Trace de l'action:\nLumiere ouest: vert\nLumiere est: vert\nLumiere sud: rouge\n" +
			    				"Voiture ouest: continuer ou tourner à droite\nVoiture est: continuer\nVoiture sud: attendre\n" +
			    				"Pieton: attendre\n\nAction effectuée : ");
		    			action = br.readLine();
	    			}
	    			else if(finAction.equals("attendre")) {
	    				System.out.print("Il y'a deux traces de cette action :\nTrace 1 de l'action:\nLumiere ouest: rouge\nLumiere est: "
	    						+ "rouge\nLumiere sud: vert clignotant\nVoiture ouest: attendre\nVoiture est: attendre\nVoiture sud: "
	    						+ "tourner à gauche ou tourner à droite\nPieton: attendre\nTrace 2 de l'action:\nLumiere ouest: rouge\n"
	    						+ "Lumiere est: vert clignotant\nLumiere sud: vert\nVoiture ouest: attendre\nVoiture est: continuer ou "
	    						+ "tourner à gauche\nVoiture sud: tourner à droite\nPieton: attendre\n\nAction effectuée : ");
		    			action = br.readLine();
	    			}
	    		}
	    	}
	    	// Voitures Sud
	    	else if(debutAction.equals("voitureSud")) {
	    		Voiture voitureSud = new Voiture("sud", finAction);
	    		voitureSud.controllerVoitureSud();
	    		if(voitureSud.threadExecute() == true) {
	    			System.out.print("Les voitures sud ne peuvent jamais continuer tout droit\n");
	    			debuterProgramme();
	    		}
	    		else {
	    			if(finAction.equals("attendre")) {
	    				System.out.print("Trace de l'action:\nLumiere ouest: vert\nLumiere est: vert\nLumiere sud: rouge\n" +
			    				"Voiture ouest: continuer ou tourner à droite\nVoiture est: continuer\nVoiture sud: attendre\n" +
			    				"Pieton: attendre\n\nAction effectuée : ");
		    			action = br.readLine();
	    			}
	    			else if(finAction.equals("tournerDroite")) {
	    				System.out.print("Il y'a deux traces de cette action :\nTrace 1 de l'action:\nLumiere ouest: rouge\nLumiere est: "
	    						+ "rouge\nLumiere sud: vert clignotant\nVoiture ouest: attendre\nVoiture est: attendre\nVoiture sud: "
	    						+ "tourner à gauche ou tourner à droite\nPieton: attendre\nTrace 2 de l'action:\nLumiere ouest: rouge\n"
	    						+ "Lumiere est: vert clignotant\nLumiere sud: vert\nVoiture ouest: attendre\nVoiture est: continuer ou "
	    						+ "tourner à gauche\nVoiture sud: tourner à droite\nPieton: attendre\n\nAction effectuée : ");
		    			action = br.readLine();
	    			}
	    			else if(finAction.equals("tournerGauche")) {
	    				System.out.print("Trace de l'action:\nLumiere ouest: rouge\nLumiere est: rouge\nLumiere sud: vert clignotant\n"
	    						+ "Voiture ouest: attendre\nVoiture est: attendre\nVoiture sud: tourner à gauche ou tourner à droite\n"
	    						+ "Pieton: attendre\n\nAction effectuée : ");
		    			action = br.readLine();
	    			}
	    		}
	    	}
	    	
	    	// actions concernant les lumières, pas encore traitées avec les threads: il faut faire cette partie comme celle de Voiture
		    else {
		    	if(action.equals("estOuest.rouge") || action.equals("sud.vClignotant")) {
		    		System.out.print("Trace de l'action:\nLumiere ouest: rouge\nLumiere est: rouge\nLumiere sud: vert clignotant\n" +
		    				"Voiture ouest: attendre\nVoiture est: attendre\nVoiture sud: tourner gauche ou tourner droite\n" +
		    				"Pieton: attendre\n\nAction effectuée : ");
	    			action = br.readLine();	    		
		    	}
		    	else if(action.equals("est.vClignotant") || action.equals("ouest.rouge") || action.equals("sud.vert")) {
		    		System.out.print("Trace de l'action:\nLumiere ouest: rouge\nLumiere est: vert clignotant\nLumiere sud: vert\n" +
		    				"Voiture ouest: attendre\nVoiture est: continuer tout droit ou tourner gauche\nVoiture sud: tourner droite\n" +
		    				"Pieton: attendre\n\nAction effectuée : ");
	    			action = br.readLine();	  
		    	}
		    	else if(action.equals("estOuest.vert") || action.equals("sud.rouge")) {
		    		System.out.print("Trace de l'action:\nLumiere ouest: vert\nLumiere est: vert\nLumiere sud: rouge\n" +
		    				"Voiture ouest: continuer tout droit\nVoiture est: continuer tout droit\nVoiture sud: attendre\n" +
		    				"Pieton: attendre\n\nAction effectuée : ");
	    			action = br.readLine();	  
		    	}
		    	else if(action.equals("estOuest.jaune") || action.equals("sud.rouge")) {
		    		System.out.print("Trace de l'action:\nLumiere ouest: jaune\nLumiere est: jaune\nLumiere sud: rouge\n" +
		    				"Voiture ouest: attendre\nVoiture est: attendre\nVoiture sud: attendre\n" +
		    				"Pieton: attendre\n\nAction effectuée : ");
	    			action = br.readLine();	  
		    	}
		    	else if(action.equals("tous.rouge")) {
		    		System.out.print("Trace de l'action:\nLumiere ouest: rouge\nLumiere est: rouge\nLumiere sud: rouge\n" +
		    				"Voiture ouest: attendre\nVoiture est: attendre\nVoiture sud: attendre\n" +
		    				"Pieton: passer\n\nAction effectuée : ");
	    			action = br.readLine();	  
		    	}
		    }
	    	
	    } while(action.equals("quitter") == false);
		
	}
}
	