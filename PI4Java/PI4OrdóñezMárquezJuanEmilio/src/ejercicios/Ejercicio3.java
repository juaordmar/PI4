package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import us.lsi.tiposrecursivos.Tree;

public class Ejercicio3 {
	
	public static <E> List<Boolean> ej3(Tree<E> arbol, Predicate<E> p){
		return ej3Aux(arbol, p, new ArrayList<>());
	}
	
	private static <E> List<Boolean> ej3Aux(Tree<E> arbol, Predicate<E> p, List<Boolean> res){
		switch(arbol.getType()) {
			case Empty:
				break;
			case Leaf:
				Integer n = getLevel1(arbol);
				if(res.size() > n) { // si ya hay un elemento en la lista para el nivel n,
					res.set(n, res.get(n) && p.test(arbol.getLabel()));	// lo actualizo con 		
				}else { // el que estoy viendo ahora
					res.add(p.test(arbol.getLabel())); // si no, pongo un primer resultado 
				}break;							// para el predicado en ese nivel
			case Nary: 
				Integer j = getLevel1(arbol);
				if(res.size() > j) { 
					res.set(j, res.get(j) && p.test(arbol.getLabel()));	
					res.add(p.test(arbol.getLabel())); 
				}int i = 0;	// para el caso n-ario hago igual y después 
				while(i<arbol.getNumOfChildren()) {	// voy llamando al método para sus hijos
					ej3Aux(arbol.getChild(i), p, res);
					i++;
				}break;
		}return res;
	}
	
	public static <E> Integer getLevel1(Tree<E> t) {
		return getLevelAux(t, 0);
	}
	
	private static <E> Integer getLevelAux(Tree<E> t, Integer n) {
		while(!t.isRoot()) {
			return getLevelAux(t.getFather(), n+1);
		}return n;
	}	
	
}
