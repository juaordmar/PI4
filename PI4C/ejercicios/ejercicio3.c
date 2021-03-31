/*
 * ejercicio3.c
 *
 *  Created on: 10 ene. 2021
 *      Author: LENOVO
 */

#include "ejercicio3.h"

list ej3(tree arbol, bool pred(void*)){
	return ej3Aux(arbol, pred);
}

list ej3Aux(tree arbol, bool pred(void*), list res){
	switch(arbol.tree_type){
		case Empty_Tree:
			break;
		case Leaf_Tree:
			break;
		case Tree:
			break;
	}
}

/*int getLevel(tree t){
	return getLevelAux(t, 0);
}

int getLevelAux(tree arbol, int n){
	while(!tree_is_root(&arbol)){
		return getLevelAux(tree_get_father(&arbol), n+1;
	}return n;
	tendría que implementar un atributo father para tree.c
}*/
