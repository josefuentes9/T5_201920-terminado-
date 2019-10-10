package model.logic;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import model.data_structures.LinearProbingHashST;
import model.data_structures.Queue;
import model.data_structures.SeparateChainingHashST;

/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private LinearProbingHashST<String,  Viaje> datosHashLinear;
	private SeparateChainingHashST<String, Viaje> datosHashSeparate;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		datosHashLinear= new LinearProbingHashST<>();
		datosHashSeparate= new SeparateChainingHashST<>();
	}

	/**
	 * Carga las dos Tablas de hash
	 * @throws IOException 
	 */

	public void cargarH() throws Exception 
	{
		System.out.println("paso 1");
		CSVReader reader = null;
		int i=1;
		String primera="";
		String key="";
		try 
		{
			
			while(i<5)
			{
				
				reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-"+i+"-WeeklyAggregate.csv"));
				int contador=0;
				Viaje val=null;
				reader.readNext();
				for(String[] nextLine : reader) 
				{
					
					System.out.println("paso "+i+"."+contador);
					val=new Viaje(i,nextLine[0],nextLine[1],nextLine[2],nextLine[3],nextLine[4]);
					key=i+nextLine[0]+nextLine[1];
					datosHashLinear.put(key, val);
					datosHashSeparate.put(key, val);
					contador++;
					if(datosHashLinear.size()==1)
					{
						primera=key;
					}
					key="";
					
					contador++;
				}
				i++;
				System.out.println("Numero actual de elementos es" + datosHashLinear.size() + "\n---------");	
				System.out.println("Primer viaje: zona de origen " + datosHashLinear.get(primera).getSourceid()+", zona de destino "+datosHashLinear.get(primera).getDstid()+", tiempor promedio "+datosHashLinear.get(primera).getMean_travel_time() + "\n---------");	
				System.out.println("Primer viaje: zona de origen " + datosHashLinear.get(key).getSourceid()+", zona de destino "+datosHashLinear.get(key).getDstid()+", tiempor promedio "+datosHashLinear.get(key).getMean_travel_time() + "\n---------");
				//Factor de carga
				System.out.println(""+(contador/datosHashLinear.size()));
				System.out.println(""+(contador/datosHashSeparate.size()));
			}
				

		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("entro en el catch 1");
			e.printStackTrace();
		} 
		finally
		{
			if (reader != null) 
			{
				try 
				{
					System.out.println("entro en el try 2");
					reader.close();
				} 
				catch (IOException e) 
				{
					System.out.println("entro en el catch 2");
					e.printStackTrace();
				}
			}
		}

	}
	

	/**
	 * Buscar tiempos promedios por trimestre, zona de origen y zona de destino
	 * @param trimestre trimestre del ano por el que se desea buscar
	 * @param zonaOrigen zona de origen del viaje
	 * @param zonaDestino zona de destino del viaje
	 * post: muestra los viajes encontrados por dia de la semana
	 */
	public void buscarTiemposLinear(String trimestre, String zonaOrigen, String zonaDestino) {
		// TODO Auto-generated method stub
		ArrayList<Viaje> aux=new ArrayList<Viaje>();
		for(int i=0; i<datosHashLinear.size();i++)
		{
			if(datosHashLinear.get(trimestre+zonaOrigen+zonaDestino)!=null){
				aux.add(datosHashLinear.get(trimestre+zonaOrigen+zonaDestino));
			}
		}
		int i=1;
		while(i<=7)
		{
			for(int j=0; j<aux.size(); j++)
			{
				if(aux.get(j).getDow()==i)
				{
					System.out.println("Viaje "+ j+": "+ trimestre+", "+aux.get(j).getSourceid()+", "+aux.get(j).getDstid()+", "+aux.get(j).getDow()+", "+aux.get(j).getMean_travel_time()+ "\n---------");	
				}
			}
			i++;
		}
	}

	public void buscarTiemposSeparate(String trimestre, String zonaOrigen, String zonaDestino) {
		// TODO Auto-generated method stub
		ArrayList<Viaje> aux=new ArrayList<Viaje>();
		for(int i=0; i<datosHashSeparate.size();i++)
		{
			if(datosHashSeparate.get(trimestre+zonaOrigen+zonaDestino)!=null){
				aux.add(datosHashSeparate.get(trimestre+zonaOrigen+zonaDestino));
			}
		}
		int i=1;
		while(i<=7)
		{
			for(int j=0; j<aux.size(); j++)
			{
				if(aux.get(j).getDow()==i)
				{
					System.out.println("Viaje "+ j+": "+ trimestre+", "+aux.get(j).getSourceid()+", "+aux.get(j).getDstid()+", "+aux.get(j).getDow()+", "+aux.get(j).getMean_travel_time()+ "\n---------");	
				}
			}
			i++;
		}
	}
	public void punto5(){
		buscarTiempoGetSetSeparateChaining();
		buscarTiempoGetSetLinearProbing();
	}
	
	public void buscarTiempoGetSetSeparateChaining(){
		String key="";
		double min=99999999;
		double max=0;
		double promedio=0;
		int j=0;
		while (j<10){
			double timeinicial=System.currentTimeMillis();
		for (int i=0;i<=10000;i++)
		{
			int num=(int) Math.random();
			key=""+num;
			datosHashSeparate.get(key);
			datosHashSeparate.put(key, new Viaje(i,""+i,""+i,""+i,""+i,""+i));	
		}
		double timefinal=System.currentTimeMillis();
		double tiempo=timefinal-timeinicial;
		if(min>tiempo){
			min=tiempo;
		}
		if(max<tiempo){
			max=tiempo;
		}
		promedio=promedio+tiempo;
		}
		System.out.println("Tiempo m�nimo de getSet SeparateChaining: "+min);
		System.out.println("Tiempo maximo de getSet SeparateChaining: "+max);
		System.out.println("Tiempo promedio de getSet SeparateChaining: "+promedio);
		
	}
	public void buscarTiempoGetSetLinearProbing(){
		String key="";
		double min=99999999;
		double max=0;
		double promedio=0;
		int j=0;
		while (j<10){
			double timeinicial=System.currentTimeMillis();
		for (int i=0;i<=10000;i++)
		{
			int num=(int) Math.random();
			key=""+num;
			datosHashLinear.get(key);
			datosHashLinear.put(key, new Viaje(i,""+i,""+i,""+i,""+i,""+i));	
		}
		double timefinal=System.currentTimeMillis();
		double tiempo=timefinal-timeinicial;
		if(min>tiempo){
			min=tiempo;
		}
		if(max<tiempo){
			max=tiempo;
		}
		promedio=promedio+tiempo;
		}
		System.out.println("Tiempo m�nimo de getSet SeparateChaining: "+min);
		System.out.println("Tiempo maximo de getSet SeparateChaining: "+max);
		System.out.println("Tiempo promedio de getSet SeparateChaining: "+promedio);
		
	}




}