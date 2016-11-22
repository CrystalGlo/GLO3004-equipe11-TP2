import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class MainApp {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String nomFichier = null;
    static String action = null;
    static List<String> actionsPossibles = Arrays.asList("estOuest.vert", "estOuest.jaune", "estOuest.rouge",
    		"est.vClignotant", "ouest.rouge", "ouest.jaune", "sud.vert", "sud.vClignotant",
    		"sud.rouge", "est.rien", "ouest.rien", "sud.rien", "tous.rouge");
	  
	public static void debuterProgramme() throws IOException {
		System.out.println("Bienvenue dans notre programme de simulation d'intersection.\n");
		
		//nomFichier = br.readLine();		
		//System.out.println("Vous avez sélectionné le fichier '"+ nomFichier + "'");
		System.out.print("État Initial:\nLumiere ouest: rouge\nLumiere est: rouge\nLumiere sud: rouge\n" +
			"Voiture ouest: aucune\nVoiture est: aucune\nVoiture sud: aucune\nPieton: aucun\n\nAction effectuée : ");
		action = br.readLine();	
	}

	public static void main(String[] args) throws IOException {
		
	    do{
			if(actionsPossibles.contains(action) == false) {
		    	System.out.println("L'action que vous avez choisi n'existe pas");
		    	debuterProgramme();
			}
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
	