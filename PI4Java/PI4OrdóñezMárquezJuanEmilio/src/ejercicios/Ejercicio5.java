package ejercicios;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import us.lsi.tiposrecursivos.Tree;

public class Ejercicio5 {
	
	public static <E> Map<Integer, Set<Tree<E>>> ej5(Tree<E> arbol){
		return ej5Aux(arbol, new HashMap<>());
	}
	
	private static <E> Map<Integer, Set<Tree<E>>> ej5Aux(Tree<E> t, Map<Integer, Set<Tree<E>>> m){
		switch(t.getType()) {
			case Empty:
				Integer j = t.getNumOfChildren();	// algoritmo similar al anterior
				if(!m.containsKey(j)) {	
					Set<Tree<E>> s2 = new HashSet<Tree<E>>(); // voy creando un diccionario
					s2.add(t);		// viendo si existen valores o no para cada clave
					m.put(j, s2);	// o si existe la clave
				}else {
					m.get(j).add(t);
				}
				break;
			case Leaf: 
				Integer n = t.getNumOfChildren();
				if(!m.containsKey(n)) {
					Set<Tree<E>> s1 = new HashSet<Tree<E>>();
					s1.add(t);
					m.put(n, s1);
				}else {
					m.get(n).add(t);
				}
				break;
			case Nary: // hago lo mismo para cada caso excepto en el n-ario en el 
				Integer k = t.getNumOfChildren();	// que llamo al método para cada 
				if(m.containsKey(k)) {				// uno de sus hijos
					m.get(k).add(t);
				}else {
					Set<Tree<E>> s = new HashSet<Tree<E>>();
					s.add(t);
					m.put(k, s);
				}int i = 0;
				while(i<t.getNumOfChildren()) {
					ej5Aux(t.getChild(i), m);
					i++;
				}
				break;
		}return m;
	}	
	
}
