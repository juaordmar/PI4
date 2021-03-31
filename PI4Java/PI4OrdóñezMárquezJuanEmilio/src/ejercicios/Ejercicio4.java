package ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.lsi.tiposrecursivos.Tree;

public class Ejercicio4 {
	
	public static <E> Map<Integer, List<E>> ej4(Tree<E> arbol){
		return ej4Aux(arbol, new HashMap<>());
	}
	
	private static <E> Map<Integer, List<E>> ej4Aux(Tree<E> t, Map<Integer, List<E>> m){
		switch(t.getType()) {
			case Empty:
				break;
			case Leaf: 
				Integer k = Ejercicio3.getLevel1(t); //si el árbol es una hoja, solamente creamos
				if(!m.containsKey(k)) {				// una lista vacía cuando no hay un valor
					m.put(k, new ArrayList<>());	// para el nivel de ese árbol
				}break;
			case Nary:
				Integer n = Ejercicio3.getLevel1(t); //en el caso de que sea n-ario,
				if(t.getNumOfChildren()%2==0) {		// veo si tiene 2 hijos y añado o creo 
					if(m.containsKey(n)) {			// valores si existe o no el nivel 
						m.get(n).add(t.getLabel());	// de ese árbol
					}else {
						List<E> ls = new ArrayList<>();
						ls.add(t.getLabel());
						m.put(n, ls);
					}
				}else {								// si no tiene 2 hijos lo trato como una hoja
					if(!m.containsKey(n)) {	
						m.put(n, new ArrayList<E>());
					}
				}int i = 0;
				while(i<t.getNumOfChildren()) {		// llamo al método para cada hijo
					ej4Aux(t.getChild(i), m);		// y le paso el mapa actualizado
					i++;
				}break;
		}return m;
	}
	
}
