package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestionFichier.GestionFichierTexte;

public class GestionFichierTexteTest {

    private static final String TEMP_FILE_PATH = "testFile.txt";
    private static final String FILE_CONTENT = "Ligne 1\nLigne 2\nLigne 3";

    @BeforeEach
    public void setUp() throws IOException {
	Files.write(Paths.get(TEMP_FILE_PATH), FILE_CONTENT.getBytes());
    }

    @AfterEach
    public void tearDown() throws IOException {
	Files.deleteIfExists(Paths.get(TEMP_FILE_PATH));
    }

    @Test
    public void testLireFichier() throws IOException {
	String contenu = GestionFichierTexte.lireFichier(TEMP_FILE_PATH);
	assertEquals(FILE_CONTENT, contenu, "Le contenu lu ne correspond pas au contenu attendu.");
    }

    @Test
    public void testEcrireFichier() throws IOException {
	String nouveauContenu = "Nouveau Contenu\nPour Test";
	String nouveauFichierPath = "nouveauTestFile.txt";

	GestionFichierTexte.ecrireFichier(nouveauContenu, nouveauFichierPath);

	File nouveauFichier = new File(nouveauFichierPath);
	assertTrue(nouveauFichier.exists(), "Le fichier devrait exister.");

	String contenuLu = GestionFichierTexte.lireFichier(nouveauFichierPath);
	assertEquals(nouveauContenu, contenuLu, "Le contenu du fichier ne correspond pas au contenu écrit.");

	Files.deleteIfExists(Paths.get(nouveauFichierPath));
    }
}
