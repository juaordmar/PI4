package us.lsi.dyv;

public class AlgoritmoDyV {
	
	/**
	 * 
	 * @param <E> El tipo de la soluci�n parcial
	 * @param <S> El tipo de la soluci�n
	 * @param p - Problema a resolver
	 * @return Algoritmo de Divide y Vencer�s Sin Memoria para resolver el problema
	 */
	public static <S, E> AlgoritmoDyVSM<S,E> createDyVSM(ProblemaDyV<S, E> p) {
		return new AlgoritmoDyVSM<S,E>(p);
	}
	
	/**
	 * 
	 * @param <E> El tipo de la soluci�n parcial
	 * @param <S> El tipo de la soluci�n
	 * @param p - Problema a resolver
	 * @return Algoritmo de Divide y Vencer�s Con Memoria para resolver el problema
	 */
	public static <S, E> AlgoritmoDyVCM<S,E> createDyVCM(ProblemaDyV<S, E> p) {
		return new AlgoritmoDyVCM<S,E>(p);
	}
	

}
