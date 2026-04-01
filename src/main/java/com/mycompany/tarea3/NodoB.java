/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea3;

/**
 *
 * @author Henry Mendoza
 */
public class NodoB {
 int[] claves;        // Arreglo donde se almacenan las claves
    int t;               // Grado mínimo del árbol
    NodoB[] hijos;       // Arreglo de referencias a nodos hijos
    int n;               // Número actual de claves en el nodo
    boolean hoja;        // Indica si el nodo es hoja (true) o interno (false)

    /**
     * Constructor del nodo
     *  t grado mínimo del árbol
     *  hoja indica si el nodo es hoja
     */
    public NodoB(int t, boolean hoja) {
        this.t = t;
        this.hoja = hoja;
        this.claves = new int[2 * t - 1]; // Máximo de claves
        this.hijos = new NodoB[2 * t];    // Máximo de hijos
        this.n = 0;
    }

    /**
     * Recorre el árbol en orden e imprime las claves.
     * Se utiliza un recorrido in-order adaptado a Árbol B.
     */
    public void recorrer() {
        int i;
        for (i = 0; i < n; i++) {

            // Si no es hoja, primero recorrer hijo izquierdo
            if (!hoja) {
                hijos[i].recorrer();
            }

            // Imprime la clave actual
            System.out.print(claves[i] + " ");
        }

        // Recorre el ultimo hijo
        if (!hoja) {
            hijos[i].recorrer();
        }
    }

    /**
     * Busca una clave dentro del nodo o sus hijos.
     * k clave a buscar
     * @return nodo donde se encuentra la clave o null
     */
    public NodoB buscar(int k) {
        int i = 0;

        // Busca la posición donde podría estar la clave
        while (i < n && k > claves[i]) {
            i++;
        }

        // Si la clave se encuentra en este nodo
        if (i < n && claves[i] == k) {
            return this;
        }

        // Si es hoja y no se encontró, no existe
        if (hoja) {
            return null;
        }

        // Continúa la búsqueda en el hijo correspondiente
        return hijos[i].buscar(k);
    }
}