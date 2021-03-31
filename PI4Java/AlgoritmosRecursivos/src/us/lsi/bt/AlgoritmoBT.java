package us.lsi.bt;


import java.util.List;
import java.util.Set;


import java.util.function.Predicate;
import java.util.stream.Collectors;

import us.lsi.bt.EstadoBT.Tipo;
import us.lsi.common.Lists2;
import us.lsi.common.Metricas;
import us.lsi.common.Sets2;


/**
 * <p> Algoritmo que implementa la t�cnica de Bactracking con sus variantes. 
 * Un problema que se quiera resolver con esta t�cnica debe implementar el interface ProblemaBT &lt; S,A &gt;</p>
 * 
 * <p>La documentaci�n puede encontarse en el: <a href="../../../document/Tema15.pdf" target="_blank">Tema15</a></p>
 * 
 * @author Miguel Toro
 *
 * @param <S> El tipo de la soluci�n
 * @param <A> El tipo de la alternativa
 */
public class AlgoritmoBT<S, A, E extends EstadoBT<S,A,E>> {
	
	
	/**
	 * @param <S> El tipo de la soluci�n
	 * @param <A> El tipo de la alternativa
	 * @param <E> El tipo del estado
	 * @param e - El estado Inicial del Problema a resolver
	 * @return Algoritmo de Backtracking para resolver el problema
	 */
	public static <S, A, E extends EstadoBT<S,A,E>> AlgoritmoBT<S,A, E> create(E e) {
		return new AlgoritmoBT<S,A,E>(e);
	}
	
	/**
	 * Soluci�n obtenida si es �nica o la mejor soluci�n si hay varias
	 */
    private S solucion = null;
    /**
	 * Conjunto de soluciones encontradas
	 */
	public Set<S> soluciones; 
	/**
	 * N�mero de soluciones que se buscan si no se buscan todas y el problema es de tipo Otro
	 */
	public static int numeroDeSoluciones = 1;
	/**
	 * Si se quiere aplicar la t�cnica aleatoria para escoger una de las alternativas
	 */
	public static boolean isRandomize = false;
	/**
	 * Tama�o umbral a partir del cual se escoge aleatoriamente una de las alternativas
	 */
	public static Integer sizeRef = 10;
	/**
	 * Si se usa la t�cnica con filtro
	 */
	public static Boolean conFiltro = false;
	private Double mejorValor = Double.MAX_VALUE;   
   	public static Metricas metricas = new Metricas();
   	public static boolean metricasOK = false;
	
	
    private E estado;
	private boolean exito;
	private Tipo tipo;
	
	
	 private boolean isMin(){
	    	return tipo.equals(Tipo.Min);
	 }
	    
	 private boolean isMax(){
	    	return tipo.equals(Tipo.Max);
	 }
	 
	 private boolean isTodasLasSoluciones(){
	    	return tipo.equals(Tipo.TodasLasSoluciones);
	 }
	
	 private boolean isAlgunasSoluciones(){
	    	return tipo.equals(Tipo.AlgunasSoluciones);
	 }
	/**
	 * @return El mejor valor de la propiedad objetivo del problema inicial encontrado 
	 * por el algoritmo hasta este momento
	 */
	public Double getMejorValor() {
		return mejorValor;
	}

	/**
	 * @param e - El esatod inicial del problema a resolver
	 */
	public AlgoritmoBT(E e){
    	this.estado = e; 
    	this.tipo = e.getTipo();
    }
    
    /**
     * Ejecuci�n del algoritmo
     */
	public void ejecuta() {
		if(AlgoritmoBT.metricasOK) AlgoritmoBT.metricas.setTiempoDeEjecucionInicial();
		tipo = estado.getTipo();
	    mejorValor = isMin()? Double.MAX_VALUE: Double.MIN_VALUE;  
    	soluciones =  Sets2.empty(); 	
		do {
			estado = estado.getEstadoInicial();
			exito = false;
			bt();
		} while(isRandomize && isAlgunasSoluciones()&& soluciones.size()<AlgoritmoBT.numeroDeSoluciones);
		if(AlgoritmoBT.metricasOK) AlgoritmoBT.metricas.setTiempoDeEjecucionFinal();
	}

    private List<A> filtraRandomize(E p, List<A> alternativas){
    	List<A> alt;
		if(isRandomize && p.size()>sizeRef){
			List<A> ls = Lists2.ofCollection(alternativas);
			alt = Lists2.randomUnitary(ls);
		}else{
			alt = alternativas;
		}
		return alt;
	}
    
	private void actualizaSoluciones() {			
		S s = estado.getSolucion();		
		if (s != null) {	
			Double objetivo = estado.getObjetivo();
			if ((this.isTodasLasSoluciones() || 
					 this.isAlgunasSoluciones() || 
					(this.isMin() && objetivo!=null && objetivo <= this.mejorValor) || 
					(this.isMax() && objetivo!=null && objetivo >= this.mejorValor))) {
				solucion = s;
				soluciones.add(solucion);
				this.mejorValor = objetivo;
			}
		}
//		System.out.println("En actualiza == "+this.soluciones);
	}
    
    private void bt() {	
    	if(estado.esCasoBase()){ 
    		if(AlgoritmoBT.metricasOK) 
    			AlgoritmoBT.metricas.addCasoBase();
    		actualizaSoluciones();
    		if(this.isAlgunasSoluciones() && soluciones.size()>=AlgoritmoBT.numeroDeSoluciones) 
    			exito = true;
    	} else if(estado.estaFueraDeRango()){
    		if(AlgoritmoBT.metricasOK) AlgoritmoBT.metricas.addFueraDeRango();
    	} else {
    			for(A a: filtraRandomize(estado,estado.getAlternativas())){  
    					if(conFiltro && isMin() && estado.getObjetivoEstimado(a) >= mejorValor) {
    						AlgoritmoBT.metricas.addUnFiltro();
    						continue;
    					}
    					if(conFiltro && isMax() && estado.getObjetivoEstimado(a) <= mejorValor) {
    						AlgoritmoBT.metricas.addUnFiltro();
    						continue;
    					}
    					estado = estado.avanza(a); 
    					bt();  
    					estado = estado.retrocede(a);
    					if (exito) break;
    			}
    	}
   }

    
	/**
	 * @param p - Predicado con las propiedades de las soluciones buscadas
	 * @return Conjunto de soluciones con las propiedades requeridas
	 */
	public Set<S> getSolucionesFiltradas(Predicate<S> p) {
		return soluciones.stream()
				.filter(p)
				.collect(Collectors.<S>toSet());
	}
	
	/**
	 * @param p Predicado para filtrar las soluciones
	 * @return Conjunto de las mejores soluciones
	 */
	public Set<S> getMejoresSoluciones(Predicate<S> p) {
		return soluciones.stream()
				.filter(p)
				.collect(Collectors.<S>toSet());
	}

	public S getSolucion() {
		return solucion;
	}

	public Set<S> getSoluciones() {
		return soluciones;
	}

	public Tipo getTipo() {
		return tipo;
	}
	
	
}
