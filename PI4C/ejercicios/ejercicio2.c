/*
 * ejercicio2.c
 *
 *  Created on: 10 ene. 2021
 *      Author: LENOVO
 */

#include "ejercicio2.h"

bool ej2(binary_tree arbol, list lc){
	bool res = false;
	if(getHeight(arbol)+1 < list_size(&lc)){
		return res;
	}else if(*(char*)binary_tree_label(&arbol) == *(char*)list_get(&lc, 0)){
		if(class_binary_tree(&arbol) == Leaf_Binary_Tree
				&& list_size(&lc)){
			res = true;
		}else{
			list_remove(&lc, 0);
			res = ej2(*binary_tree_left(&arbol), lc)
					|| ej2(*binary_tree_right(&arbol), lc);
		}
	}return res;
}

int getHeight(binary_tree tree){
	int r = 0;
	switch(tree.tree_type){
		case Empty_Binary_Tree:
			r = -1;
			break;
		case Leaf_Binary_Tree:
			r = 0;
			break;
		case Binary_Tree:
			r = 1 + MAX(getHeight(*binary_tree_left(&tree)),
					getHeight(*binary_tree_right(&tree)));
			break;
	}return r;
}

void list_remove(list *l, int elem) {
    if (elem < 0 || elem >= l->size) return;
    l->elements[elem] = NULL;
    l->size--;
    l->elements = realloc(l->elements, sizeof(void*) * l->size);
}
