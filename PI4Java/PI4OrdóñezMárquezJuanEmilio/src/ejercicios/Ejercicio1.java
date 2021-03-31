package ejercicios;

import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio1 {

	public static Boolean ej1(BinaryTree<Integer> ab) {
		Boolean res = null; // el valor a devolver
		if(ab.isLeaf()) { // si el arbol es hoja devolvemos true
			res = true;
		}else if(ab.getLabel() == ab.getLeft().getLabel() + ab.getRight().getLabel() 
				&& ab.getLeft().getLabel()!=null 
				&& ab.getRight().getLabel()!=null) { // si se cumplen las condiciones del enunciado
			res = ej1(ab.getLeft()) && ej1(ab.getRight()); // llamamos de forma recursiva a nuestro
		}else {											   // método para los dos hijos
			res = false;
		}return res;
	}
	
}
