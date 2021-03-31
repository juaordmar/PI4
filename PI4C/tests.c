/*
 * tests.c
 *
 *  Created on: 10 ene. 2021
 *      Author: LENOVO
 */

#include "tests.h"

int main(){
	//test1("ficheros/PI4Ej1DatosEntrada.txt");
	//test2("ficheros/PI4Ej2DatosEntrada.txt");
	//test3("ficheros/PI4Ej3DatosEntrada.txt");
	//test4("ficheros/PI4Ej4DatosEntrada.txt");
	//test5("ficheros/PI4Ej5DatosEntrada.txt");
}

void test1(char * file){
	iterator f = file_iterable_pchar(file);
	char memoria[500];
	while(iterable_has_next(&f)){
		char * linea = (char *)iterable_next(&f);
		memory_heap mem = memory_heap_create();
		binary_tree arbol = *binary_tree_parse(linea, &mem);
		bool res = ej1(arbol);
		printf("Árbol de entrada: %s\n", binary_tree_tostring(&arbol, memoria));
		printf("¿Cumple el predicado del enunciado?: %s\n", bool_tostring(&res, memoria));
		printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
}

void test2(char * file){
	iterator f = file_iterable_pchar(file);
	char memoria[500];
	while(iterable_has_next(&f)){
		char * linea = (char *)iterable_next(&f);
		memory_heap mem = memory_heap_create();
		iterator it = text_to_iterable_pchar(linea, "#");
		char * ab = (char *)iterable_next(&it);
		binary_tree arbol = *binary_tree_parse(ab, &mem);
		char * c = (char *)iterable_next;
		char * lineacamino = substring(c, c, 1, strlen(c)-1); // le quito los corchetes al camino
		list ls = list_empty(pchar_type);
		iterator it2 = text_to_iterable_pchar(lineacamino, ","); // le quito las comas
		while(iterable_has_next(&it2)){
			char * letra = (char*)iterable_next(&it2);
			list_add(&ls, letra);
		}bool res = ej2(arbol, ls);
		printf("Árbol de entrada: %s\n", binary_tree_tostring(&arbol, memoria));
		printf("¿Existe camino desde la raiz para %s?: %s\n", list_tostring(&ls, memoria),
				bool_tostring(&res, memoria));
		printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
}

void test4(char * file){ // aunque no tengo el ejercicio 4 he puesto el test
	char memoria[500];   // porque es igual que el del ejercicio 5
	iterator f = file_iterable_pchar(file);
	while(iterable_has_next(&f)){
		char * linea = (char *)iterable_next(&f);
		memory_heap mem = memory_heap_create();
		tree arbol = *tree_parse(linea, &mem);
		hash_table res = ej4(arbol);
		printf("Arbol de entrada: %s\n", tree_tostring(&arbol, memoria));
		printf("Map de salida: %s\n", hash_table_tostring(&res, memoria));
		printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
}

void test5(char * file){
	char memoria[500];
	iterator f = file_iterable_pchar(file);
	while(iterable_has_next(&f)){
		char * linea = (char *)iterable_next(&f);
		memory_heap mem = memory_heap_create();
		tree arbol = *tree_parse(linea, &mem);
		hash_table res = ej5(arbol);
		printf("Arbol de entrada: %s\n", tree_tostring(&arbol, memoria));
		printf("Map de salida: %s\n", hash_table_tostring(&res, memoria));
		printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
}



