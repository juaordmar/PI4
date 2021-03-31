package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ejercicios.Ejercicio1;
import ejercicios.Ejercicio2;
import ejercicios.Ejercicio3;
import ejercicios.Ejercicio4;
import ejercicios.Ejercicio5;
import us.lsi.common.Files2;
import us.lsi.math.Math2;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;

public class Tests {

	public static void main(String[] args) {
		test1("../PI4OrdóñezMárquezJuanEmilio/ficheros/PI4Ej1DatosEntrada.txt");
		test2("../PI4OrdóñezMárquezJuanEmilio/ficheros/PI4Ej2DatosEntrada.txt");
		test3("../PI4OrdóñezMárquezJuanEmilio/ficheros/PI4Ej3DatosEntrada.txt");
		test4("../PI4OrdóñezMárquezJuanEmilio/ficheros/PI4Ej4DatosEntrada.txt");
		test5("../PI4OrdóñezMárquezJuanEmilio/ficheros/PI4Ej5DatosEntrada.txt");
	}

	public static void test1(String dirFichero) {
		List<String> ls = Files2.streamFromFile(dirFichero).collect(Collectors.toList());
		String s = ls.toString().replace("[", "").replace("]", "");
		String[] arboles = s.split(", ");
		int i = 0;
		while(i<arboles.length) {
			System.out.println("Árbol de entrada: "+arboles[i]);
			BinaryTree<Integer> ab = BinaryTree.parse(arboles[i], x->Integer.parseInt(x));
			System.out.println("¿Cumple el predicado del enunciado?: "+Ejercicio1.ej1(ab));
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			i++;
		}
	}

	public static void test2(String dirFichero) {
		List<String> ls = Files2.streamFromFile(dirFichero).collect(Collectors.toList());
		int i = 0;
		while(i<ls.size()) {
			String linea = ls.get(i);
			String[] campos = linea.split("#");
			BinaryTree<String> ab = BinaryTree.parse(campos[0]);
			List<String> camino = new ArrayList<>();
			String c2 = campos[1].trim().replaceAll(",", "").replace("[", "").replace("]", "");
			int j = 0;
			while(j<c2.length()) {
				camino.add(c2.substring(j, j+1));
				j++;
			}System.out.println("Árbol de entrada: "+campos[0]);
			System.out.println("¿Existe camino desde la raíz para "+camino+"?: "+Ejercicio2.ej2(ab, camino));
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			i++;
		}
	}

	public static void test3(String dirFichero) {
		List<String> ls = Files2.streamFromFile(dirFichero).collect(Collectors.toList());
		int i = 0;
		System.out.println("============================= Predicado \"es-par\" =============================\n");
		while(i<6) { // i<6 para no coger las lineas de fichero que no sirven
			String linea = ls.get(i);
			Tree<Integer> arbol = Tree.parse(linea).copy(x->Integer.parseInt(x));
			System.out.println("Árbol de entrada: "+arbol);
			System.out.println("¿Cumple el predicado?: " + Ejercicio3.ej3(arbol, x->Math2.esPar(x)));
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			i++;
		}System.out.print("\n\n");
		int j = 0;
		System.out.println("============================= Predicado \"es-primo\" =============================\n");
		while(j<6) { // i<6 para no coger las lineas de fichero que no sirven
			String linea = ls.get(j);
			Tree<Integer> arbol = Tree.parse(linea).copy(x->Integer.parseInt(x));
			System.out.println("Árbol de entrada: "+arbol);
			System.out.println("¿Cumple el predicado?: " + Ejercicio3.ej3(arbol, x->Math2.esPrimo(x)));
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			j++;
		}
	}
	
	public static void test4(String dirFichero) {
		List<String> ls = Files2.streamFromFile(dirFichero).collect(Collectors.toList());
		int i = 0;
		while(i<ls.size()) {
			String linea = ls.get(i);
			Tree<Integer> arbol = Tree.parse(linea).copy(x->Integer.parseInt(x));
			System.out.println("Árbol de entrada: "+arbol);
			System.out.println("Map de salida: " + Ejercicio4.ej4(arbol));
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			i++;
		}
	}

	public static void test5(String dirFichero) {
		List<String> ls = Files2.streamFromFile(dirFichero).collect(Collectors.toList());
		int i = 0;
		while(i<ls.size()) {
			String linea = ls.get(i);
			Tree<Integer> arbol = Tree.parse(linea).copy(x->Integer.parseInt(x));
			System.out.println("Árbol de entrada: "+arbol);
			System.out.println("Map de salida: " + Ejercicio5.ej5(arbol));
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
					+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			i++;
		}
	}
	
}