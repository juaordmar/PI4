/*
 * ejercicio1.c
 *
 *  Created on: 10 ene. 2021
 *      Author: LENOVO
 */

#include "ejercicio1.h"

bool ej1(binary_tree arbol){
	bool res;
	char * etiqueta = (char *) binary_tree_label(&arbol);
	int et1 = int_parse_s(etiqueta);
	if(class_binary_tree(&arbol) == Leaf_Binary_Tree){
		res = true;
	}else if(class_binary_tree(&arbol) == Binary_Tree){
		binary_tree izq = *binary_tree_left(&arbol);
		char * etiquetaizq = (char *) binary_tree_label(&izq);
		int eti = int_parse_s(etiquetaizq);
		binary_tree der = *binary_tree_right(&arbol);
		char * etiquetader = (char *) binary_tree_label(&der);
		int etd = int_parse_s(etiquetader);
		if(et1 == eti + etd){
			res = ej1(izq) && ej1(der);
		}
	}else{
		res = false;
	}return res;
}
