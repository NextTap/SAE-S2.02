/*
 * GestonArbreHuffmanTest.java                               	12 mai 2024
 * IUT de Rodez, pas de copyright.
 */
package test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestion.GestionArbreHuffman;
import huffman.Noeud;

class GestionArbreHuffmanTest {

    private static final String TEMP_FILE_PATH = "testArbreHuffman.txt";

    private Noeud racine;

    @BeforeEach
    void setUp() {
	Noeud feuilleA = new Noeud('a', 5);
	Noeud feuilleB = new Noeud('b', 9);

	Noeud ArbreHuffmanInterne = new Noeud('\0', feuilleA.getFrequence() + feuilleB.getFrequence());

	racine = ArbreHuffmanInterne;
    }

    @AfterEach
    void tearDown() throws IOException {
	Files.deleteIfExists(Paths.get(TEMP_FILE_PATH));
    }

    @Test
    void testSauvegardeEtRestaurerArbreHuffman() throws IOException {

	GestionArbreHuffman.sauvegardeArbreHuffman(racine, TEMP_FILE_PATH);

	File fichierSauvegarde = new File(TEMP_FILE_PATH);
	assertTrue(fichierSauvegarde.exists(), "Le fichier de sauvegarde " + "devrait exister.");

	Object[][] dictionnaire = GestionArbreHuffman.restaurerArbreHuffman(TEMP_FILE_PATH);

	Object[][] dictionnaireAttendu = { { 'a', "0" }, { 'b', "1" } };

	assertArrayEquals(dictionnaireAttendu, dictionnaire,
		"Le dictionnaire restauré ne correspond pas au dictionnaire " + "attendu.");
    }
}