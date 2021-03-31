/*
 * ejercicio5.c
 *
 *  Created on: 10 ene. 2021
 *      Author: LENOVO
 */

#include "ejercicio5.h"

hash_table ej5(tree arbol){
	hash_table mapa = hash_table_empty(int_type, set_type);
	return ej5Aux(arbol, mapa);
}

hash_table ej5Aux(tree t, hash_table m){
	switch(t.tree_type){
		case Empty_Tree:
			int j = tree_child_number(&t);
			if(!hash_table_contains(&m, &j)){
				set s2 = set_empty(tree_type);
				set_add(&s2, &t);
				hash_table_put(&m, &j, &s2);
			}else{
				set_add(*(set*)hash_table_get(&m, &j), &t);
			}
			break;
		case Leaf_Tree:
			int n = tree_child_number(&t);
			if(!hash_table_contains(&m, &n)){
				set s1 = set_empty(tree_type);
				set_add(&s1, &t);
				hash_table_put(&m, &n, &s1);
			}else{
				set_add(*(set*)hash_table_get(&m, &n), &t);
			}
			break;
		case Tree:
			int k = tree_child_number(&t);
			if(hash_table_contains(&m, &k)){
				set_add(*(set*)hash_table_get(&m, &k), &t);
			}else{
				set s = set_empty(tree_type);
				set_add(&s, &t);
				hash_table_put(&m, &k, &s);
			}int i = 0;
			while(i<tree_child_number(&t)){
				ej5Aux(tree_get_child(&t, i), m);
			}
			break;
	}return m;
}
