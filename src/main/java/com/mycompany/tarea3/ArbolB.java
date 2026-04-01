/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea3;

/**
 *
 * @author HenryMendoza
 */
public class ArbolB {
    
NodoB raiz; // Nodo raíz del árbol
    int t;      // Grado mínimo

    /**
     * Constructor del árbol
     * t grado mínimo
     */
    public ArbolB(int t) {
        this.raiz = null;
        this.t = t;
    }

    /**
     * Muestra el contenido del arbol en orden.
     */
    public void mostrar() {
        if (raiz != null) {
            raiz.recorrer();
            System.out.println();
        } else {
            System.out.println("Árbol vacío");
        }
    }

    /**
     * Busca una clave en el arbol
     */
    public void buscar(int k) {
        if (raiz == null) {
            System.out.println("No encontrado");
        } else {
            NodoB resultado = raiz.buscar(k);

            if (resultado != null) {
                System.out.println("Encontrado");
            } else {
                System.out.println("No encontrado");
            }
        }
    }

    /**
     * Inserta una clave en el árbol.
     * Maneja el caso donde la raíz está llena.
     */
    public void insertar(int k) {

        // Si el arbol está vacío, se crea la raíz
        if (raiz == null) {
            raiz = new NodoB(t, true);
            raiz.claves[0] = k;
            raiz.n = 1;
        } else {

            // Si la raiz está llena, se debe dividir
            if (raiz.n == 2 * t - 1) {

                NodoB nuevaRaiz = new NodoB(t, false);

                // La raiz anterior se convierte en hijo
                nuevaRaiz.hijos[0] = raiz;

                // Se divide la raiz
                dividirHijo(nuevaRaiz, 0, raiz);

                int i = 0;

                // Determina en qué hijo insertar
                if (nuevaRaiz.claves[0] < k) {
                    i++;
                }

                insertarNoLleno(nuevaRaiz.hijos[i], k);

                raiz = nuevaRaiz;

            } else {
                insertarNoLleno(raiz, k);
            }
        }

        System.out.println("Insertado correctamente");
    }

    /**
     * Inserta una clave en un nodo que NO está lleno.
     */
    private void insertarNoLleno(NodoB nodo, int k) {

        int i = nodo.n - 1;

        // Caso 1: nodo hoja
        if (nodo.hoja) {

            // Desplaza claves mayores hacia la derecha
            while (i >= 0 && nodo.claves[i] > k) {
                nodo.claves[i + 1] = nodo.claves[i];
                i--;
            }

            // Inserta la nueva clave en posición correcta
            nodo.claves[i + 1] = k;
            nodo.n++;

        } else {

            // Busca el hijo donde debe ir la clave
            while (i >= 0 && nodo.claves[i] > k) {
                i--;
            }

            // Si el hijo está lleno, se divide
            if (nodo.hijos[i + 1].n == 2 * t - 1) {

                dividirHijo(nodo, i + 1, nodo.hijos[i + 1]);

                // Determinar cuál de los dos hijos usar
                if (nodo.claves[i + 1] < k) {
                    i++;
                }
            }

            insertarNoLleno(nodo.hijos[i + 1], k);
        }
    }

    /**
     * Divide un nodo lleno en dos y sube la clave media al padre.
     */
    private void dividirHijo(NodoB padre, int i, NodoB nodoLleno) {

        // Nuevo nodo que almacenará la mitad derecha
        NodoB nuevo = new NodoB(t, nodoLleno.hoja);
        nuevo.n = t - 1;

        // Copia las últimas claves al nuevo nodo
        for (int j = 0; j < t - 1; j++) {
            nuevo.claves[j] = nodoLleno.claves[j + t];
        }

        // Copia los hijos si no es hoja
        if (!nodoLleno.hoja) {
            for (int j = 0; j < t; j++) {
                nuevo.hijos[j] = nodoLleno.hijos[j + t];
            }
        }

        nodoLleno.n = t - 1;

        // Desplaza hijos del padre
        for (int j = padre.n; j >= i + 1; j--) {
            padre.hijos[j + 1] = padre.hijos[j];
        }

        padre.hijos[i + 1] = nuevo;

        // Desplaza claves del padre
        for (int j = padre.n - 1; j >= i; j--) {
            padre.claves[j + 1] = padre.claves[j];
        }

        // Sube la clave media
        padre.claves[i] = nodoLleno.claves[t - 1];
        padre.n++;
    }
public void eliminar(int k) {

    if (raiz == null) {
        System.out.println("Árbol vacío");
        return;
    }

    // Guardamos todos los valores excepto el eliminado
    java.util.ArrayList<Integer> lista = new java.util.ArrayList<>();
    recolectar(raiz, lista);

    if (!lista.remove(Integer.valueOf(k))) {
        System.out.println("Valor no encontrado");
        return;
    }

    // Reconstruimos el árbol
    raiz = null;

    for (int val : lista) {
        insertar(val);
    }

    System.out.println("Eliminado correctamente");
}

/**
 * Recorre el árbol y guarda todos los valores en una lista
 */
private void recolectar(NodoB nodo, java.util.List<Integer> lista) {

    int i;

    for (i = 0; i < nodo.n; i++) {

        if (!nodo.hoja) {
            recolectar(nodo.hijos[i], lista);
        }

        lista.add(nodo.claves[i]);
    }

    if (!nodo.hoja) {
        recolectar(nodo.hijos[i], lista);
    }
}
}
