package gestion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GestionFichierTexte {

    public static String lireFichier(String cheminFichier) throws IOException {
	StringBuilder contenuBuilder = new StringBuilder();
	try (BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier))) {
	    String ligne;
	    boolean premiereLigne = true;
	    while ((ligne = lecteur.readLine()) != null) {
		if (!premiereLigne) {
		    contenuBuilder.append('\n');
		}
		contenuBuilder.append(ligne);
		premiereLigne = false;
	    }
	}
	return contenuBuilder.toString();
    }

    public static void ecrireFichier(String contenu, String nomDeFichier) {
	BufferedWriter contenuFichier = null;
	try {
	    contenuFichier = new BufferedWriter(new FileWriter(nomDeFichier));
	    contenuFichier.write(contenu);
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (contenuFichier != null) {
		    contenuFichier.close();
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
}