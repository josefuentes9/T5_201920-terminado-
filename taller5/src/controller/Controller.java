package controller;

import java.io.FileNotFoundException;
import java.util.Scanner;

import model.logic.MVCModelo;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;

	/* Instancia de la Vista*/
	private MVCView view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new MVCView();
		modelo = new MVCModelo();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		System.out.println("run");
		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 1:
				
				try {
					
					modelo.cargarH();
				} catch (Exception e) {
					
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}					
				break;

			case 2:
				System.out.println("--------- \n Buscar tiempos de viaje por trimestre, zona de origen y zona de destino (Tabla de Hash Linear Probing): ");
				System.out.println("--------- \n Seleccionar el trimestre por el que desea buscar: ");
				String trimestre = lector.next();
				System.out.println("--------- \n Seleccionar la zona de origen por la que desea buscar: ");
				String zonaOrigen = lector.next();
				System.out.println("--------- \n Seleccionar la zona de destino por la que desea buscar: ");
				String zonaDestino= lector.next();
				modelo.buscarTiemposLinear(trimestre, zonaOrigen, zonaDestino);
				break;

			case 3:
				System.out.println("--------- \n Buscar tiempos de viaje por trimestre, zona de origen y zona de destino (Tabla de Hash Separate Chaining): ");
				System.out.println("--------- \n Seleccionar el trimestre por el que desea buscar: ");
				trimestre = lector.next();
				System.out.println("--------- \n Seleccionar la zona de origen por la que desea buscar: ");
				zonaOrigen = lector.next();
				System.out.println("--------- \n Seleccionar la zona de destino por la que desea buscar: ");
				zonaDestino= lector.next();
				modelo.buscarTiemposSeparate(trimestre, zonaOrigen, zonaDestino);
				break;
			case 4: 
				System.out.println("--------- \n Punto 5 \n---------"); 
				modelo.punto5();
				break;

			case 5: 
				System.out.println("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	

			default: 
				System.out.println("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}