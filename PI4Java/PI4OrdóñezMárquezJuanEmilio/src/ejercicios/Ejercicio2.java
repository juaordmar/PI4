package ejercicios;

import java.util.List;
import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio2 {

	public static Boolean ej2(BinaryTree<String> ac, List<String> lc) {
		Boolean res = false; // variable a devolver
		if(ac.getHeight()+1 < lc.size()) {
			return res;
		}else if(ac.getLabel().equals(lc.get(0))) { 
			if(ac.isLeaf() && lc.size() == 1) { // si el arbol tiene un solo elemento
				res = true;						// y la lista uno tambien y son iguales, devuelvo true
			}else {
				lc.remove(0);					//quito el primer elemento de la lista porque 
				res = ej2(ac.getLeft(), lc)		// ya he comprobado que son iguales
						|| ej2(ac.getRight(), lc); // y llamo al método para los dos hijos
			}									// si alguno tiene un camino posible devuelvo true
		}return res;
	}
	
}
