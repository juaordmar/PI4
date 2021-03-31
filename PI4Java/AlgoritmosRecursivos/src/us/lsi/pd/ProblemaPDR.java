package us.lsi.pd;

import java.util.Comparator;
import java.util.List;

import us.lsi.common.Preconditions;
import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.AlgoritmoPD.Tipo;

/**
 * <p> Una adaptaci�n del {@link us.lsi.pd.ProblemaPD ProblemaPD} para el caso de reducci�n.
 * </p>
 * 
 * <p>Una explicaci�n puede encontrarse en <a href="../../../document/ExpPD.html" target="_blank">Programaci�n Din�mica</a></p>
 * 
 * 
 * @author Miguel Toro
 *
 * @param <S> El tipo de la soluci�n
 * @param <A> El tipo de la alternativa
 */

public interface ProblemaPDR<S, A, P extends ProblemaPDR<S, A, P>>  {
	
	/**
	 * @return El tipo del problema: minimiza, maximiza o busca un valor que involucra a todas las alternativas
	 *
	 * 
	 **/
	Tipo getTipo();	
	/**
	 * @return El tama�o del problema
	 */
	int size();
	
	/**
	 * @return Si el problema est� fuera de rango y su soluci�n es null
	 */
	default boolean estaFueraDeRango(){
		return false;
	}
	/**
	 * @return Si el problema es un caso base
	 * 
	 */
	boolean esCasoBase();
	/**
	 * @post 
	 * <ul>
	 *	<li> Si el problema no tiene soluci�n se debe devolver null
	 *	<li> Si el problema tiene soluci�n asociada a una alternativa esta ser� de la forma (a,p).Siendo a la alternativa y p la propiedad correspondiente.
	 *	<li> Si el problema tiene soluci�n pero no asociada a una alternativa esta ser� de la forma (null,p).Siendo p la propiedad correspondiente.
	 *	</ul> 
	 * @return  La soluci�n del caso base
	 */
	Sp<A> getSolucionParcialCasoBase();
	/**
	 * @pre ls!=null, ls no est� vac�a, ls no contiene null, ls no contiene valores de la forma <code> (a,null) </code>
	 * 
	 * @post 
	 * <ul>
	 *  <li> Si ls es vac�a tras eliminar los valores <code> (a,null) </code> la soluci�n devuelta es null. El problema no tiene soluci�n.
	 *	<li> Si el problema tiene soluci�n asociada a una alternativa, esta ser� de la forma <code>(a,p)</code>. Siendo <code> a </code>la alternativa y <code> p </code> la propiedad correspondiente. 
	 *	<li> Si el problema tiene soluci�n pero no asociada a una alternativa esta ser� de la forma <code> (null,p)</code>. Siendo <code> p </code> la propiedad correspondiente.
	 *	</ul> 
	 * @param ls - Soluciones existentes alcanzados tras tomar las diferentes alternativas
	 * @return La soluci�n del problema
	 */
	default Sp<A> getSolucionParcial(List<Sp<A>> ls) {
		Sp<A> r = null;
		switch(getTipo()) {
		case Min : 
			r = ls.stream().min(Comparator.naturalOrder()).orElse(null); 
			break;
		case Max: 
			r = ls.stream().max(Comparator.naturalOrder()).orElse(null); 
			break;
		case Sum: 
			Double s= ls.stream().mapToDouble(x->x.valorDeObjetivo).sum();
			r = Sp.create(null,s);
			break;
		default : 
			Preconditions.checkArgument(false, "se debe implementar el m�todo getSolucionParcial");
		}
		return r;
	}
	/**
	 * @param a - Alternativa escogida
	 * @return Subproblema
	 */
	P getSubProblema(A a);
	/**
	 * @pre <code> ls.size() == getNumeroSubProblemas(a) </code>, a est� incluida en <code> getAlternativas() </code> y
	 * ls no contiene null
	 * @param a - Alternativa escogida
	 * @param s - Solucion parcial del subproblema
	 * @return La soluci�n del problema alcanzado siguiendo la alternativa <code> a </code> 
	 * y las soluciones parciales de los subproblemas del mismo est�n en ls 
	 */
	Sp<A> getSolucionParcialPorAlternativa(A a , Sp<A> s);	
	
	/**
	 * @post Si un problema no tiene soluci�n el conjunto de alternativas es vac�o 
	 * @return Las alternativas disponibles para el problema
	 */
	List<A> getAlternativas();
	/**
	 * @pre El problema es un caso base
	 * @post 
	 * <ul>
	 *  <li> Si sp es de la forma <code> (a,p) </code> la soluci�n se reconstruye a partir de esos dos valores. 
	 *  <li> Si es de la forma <code> (null,p) </code> s�lo a partir de <code> p </code>.
	 * </ul> 
	 * @param sp - Soluci�n parcial del caso base
	 * @return La soluci�n para un caso base si la soluci�n parcial es sp. 
	 */
	S getSolucionReconstruidaCasoBase(Sp<A> sp);	
	/**
	 * @pre 
	 * <p> El problema no es un caso base, <code> ls !=null </code>, <code> ls.size() &gt; 0 </code> y ls no contiene null.</p>
	 * <p> Si el tipo es Min o Max entonces sp = (a,p) </p>
	 * <p> Si tipo es Otro entonces sp es (null,p) y S debe ser Double. </p>
	 * @post
	 * <ul>
	 *  <li> Si <code> sp = (a,p) </code> la soluci�n se reconstruye a partir de <code> a, p, ls </code> estando en <code> ls </code> las 
	 *  soluciones de los subproblemas que se alcanzan siguiendo la alternativa <code> sp.alternativa </code> 
	 *  <li> Si <code> sp = (null,p) </code> la soluci�n es igual a <code> sp.propiedad </code>.
	 * </ul> 
	 * @param sp - Soluci�n parcial del problema
	 * @param s - 
	 * <ul>
	 *  <li> Si <code> sp = (a,p)  </code> La soluci�n del subproblema que se alcanza 
	 *  siguiendo la alternativa <code> sp.alternativa </code>. 
	 *  <li> Si <code> sp = (null,p) </code> entonces <code> s </code> es irrelevante. 
	 * </ul>
	 * @return La soluci�n para el problema. 
	 */
	S getSolucionReconstruidaCasoRecursivo(Sp<A> sp, S s);	
	
	/**
	 * @pre El uso del m�todo es relevante si usamos la t�cnica con filtro. Si no la usamos el m�todo es irrelevante.
	 * @post 
	 * <ul>
	 * <li> Si el problema es de minimizaci�n el valor debe ser 
	 * una cota inferior del valor de la propiedad objetivo del problema inicial, 
	 * asumiendo que estamos en el problema actual y se  va a seguir la alternativa <code> a </code>.
	 * La alternativa puede ser descartada si <code> getObjetivoEstimado(a) &gt; =  AlgoritmoPD.getMejorValor() </code>,
	 * puesto que el valor objetivo de la soluci�n obtenida estar�a por encima del mejor valor obtenido hasta el momento.
	 * Es decir nos quedamos con las alternativas que cumplen 
	 * <code> getObjetivoEstimado(a)() &lt;   AlgoritmoPD.getMejorValor() </code>.
	 * Donde <code> AlgoritmoPD.getMejorValor() </code> es el mejor valor encontrado por el algoritmo hasta ahora.
	 * <li> Si el problema original fuera de maximizaci�n el valor debe ser 
	 * una cota superior del valor de la propiedad objetivo del problema actual, 
	 * asumiendo que estamos en el problema actual y se  va a seguir la alternativa <code> a </code>.
	 *  Es decir nos quedamos con las alternativas que cumplen 
	 * <code> getObjetivoEstimado(a) &gt;   AlgoritmoPD.getMejorValor() </code>.
	 * </ul>
	 * @param a - Alternativa elegida
	 * @return Una cota para valor objetivo si seguimos <code> a </code>
	 */
	default Double getObjetivoEstimado(A a){
		return null;
	}
	/**
	 * 
	 * @pre El uso del m�todo es relevante si usamos la t�cnica con filtro. Si no la usamos el m�todo es irrelevante.
	 * @return Valor de la propiedad objetivo del problema inicial si es posible calcularlo asumiendo
	 * que estamos en el problema actual. Si no es posible null.
	 */
	default Double getObjetivo() {
		return null;
	}

	/**
	 * @return Un problema adaptado como PD
	 */
	default ProblemaPDRAdapt<S, A, P> asPD(){
		return ProblemaPDRAdapt.create(this);
	}
}
