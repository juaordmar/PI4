package us.lsi.pd;

import java.util.List;

import us.lsi.common.Lists2;
import us.lsi.math.Math2;
import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.tiposrecursivos.Tree;

public class AlgunosTestsPD<S, A> {
	
	

	
	/**
	 * Dado un problema se ejecuta el ?rbol de alternativas sobre el mismo
	 * consiguiendo subproblemas sucesivos y posteriormente se calcula la
	 * soluci?n parcial y la reconstruida
	 * 
	 * @param <S> Tipo de la soluci?n
	 * @param <A> Tipo de la alternativa
	 * @param <P> Tipo del problema
	 * @param p Un Estado inicial
	 * @param alternativas Un ?rbol de alternativas
	 * @return La soluci?n parcial del problema
	 */
	public static <S, A, P extends ProblemaPD<S,A,P>> Sp<A> test1(P p, Tree<A> alternativas) {
		Sp<A> r = null;
		System.out.println("Avanza");
		if (p.esCasoBase()) {
			System.out.println("Es Caso Base = " + p);
			r = p.getSolucionParcialCasoBase();
		} else if (p.estaFueraDeRango()) {
			System.out.println("Est? Fuera de Rango = " + p);
			r = null;
		} else {
			System.out.println("Problema = " + p);
			List<A> la = p.getAlternativas();
			System.out.println("Alternativas = " + la);
			if (!la.isEmpty()) {
				A a = alternativas.getLabel();
				System.out.println("Contiene la alternativa = "
						+ la.contains(a));
				Integer np = p.getNumeroSubProblemas(a);
				List<Sp<A>> lsp = Lists2.empty();
				Sp<A> solp = null;
				boolean haySolucion= true;
				for (int i = 0; i < np; i++) {
					P sp = p.getSubProblema(a, i);
					solp = test1(sp, alternativas.getChild(i));
					if(solp == null){
						haySolucion = false;
					}
					lsp.add(solp);
				}
				if (haySolucion) {
					r = p.getSolucionParcialPorAlternativa(a, lsp);
				}else {
					r = null;
				}
				System.out.println("Problema = " + p + ", Solucion Parcial = " + r);
			}
		}
		return r;
	}

	public static <S, A,P extends ProblemaPDR<S,A,P>> Sp<A> test1(P p, Tree<A> alternativas) {
		return test1(ProblemaPDRAdapt.create(p),alternativas);
	}
	
	/**
	 * Dado un problema se ejecutan acciones aleatorias, escogidas entre las
	 * posibles para cada problema, consiguiendo subproblemas sucesivos y
	 * posteriormente se calcula la soluci?n parcial y la reconstruida
	 * 
	 * @param <S> Tipo de la soluci?n
	 * @param <A> Tipo de la alternativa
	 * @param <P> Tipo del problema
	 * @param p Un Estado inicial
	 * @return Un ?rbol de alternativas escogido aleatoriamente
	 */
	public static <S, A, P extends ProblemaPD<S,A,P>> Tree<A> test2(P p) {
		Tree<A> r = null;
		if (p.esCasoBase() || p.estaFueraDeRango()) {
			r = Tree.empty();
		} else {
			List<A> alternativas = p.getAlternativas();
			A a = null;
			if (!alternativas.isEmpty()) {
				Integer n = Math2.getEnteroAleatorio(0, alternativas.size());
				a = alternativas.get(n);
				int np = p.getNumeroSubProblemas(a);
//				r = Tree.leaf(a);
				List<Tree<A>> la = Lists2.empty();
				for (int i = 0; i < np; i++) {
					P sp = p.getSubProblema(a, i);
					Tree<A> th = test2(sp);
					la.add(th);
				}
				r = Tree.nary(a,la);
			} else {
				r = Tree.empty();
			}
		}
		return r;
	}
	
	public static <S, A, P extends ProblemaPDR<S,A,P>> Tree<A> test2(P p) {
		return test2(ProblemaPDRAdapt.create(p));
	}
}

