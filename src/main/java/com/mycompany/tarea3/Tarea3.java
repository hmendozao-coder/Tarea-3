/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tarea3;

/**
 *
 * @author HenryMendoza
 */
import java.util.Scanner;

public class Tarea3 {


        public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el grado del arbol B: ");
        int grado = sc.nextInt();

        ArbolB arbol = new ArbolB(grado);

        int opcion, valor;

        do {
            System.out.println("\n--- MENU ARBOL B ---");
            System.out.println("1. Insertar");
            System.out.println("2. Buscar");
            System.out.println("3. Mostrar");
            System.out.println("4. Eliminar");
            System.out.println("0. Salir");

            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese valor: ");
                    valor = sc.nextInt();
                    arbol.insertar(valor);
                    break;

                case 2:
                    System.out.print("Buscar valor: ");
                    valor = sc.nextInt();
                    arbol.buscar(valor);
                    break;

                case 3:
                    arbol.mostrar();
                    break;

                case 4:
                    System.out.print("Eliminar valor: ");
                    valor = sc.nextInt();
                    arbol.eliminar(valor);
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 0);

        sc.close();
    }
}