package it.univpm.Dati_Europa.Services;

import java.util.ArrayList;

public class Stats {
	
	public static int count(ArrayList lista)
	{
		return lista.size();
	}
	
	public static double sum(ArrayList<Number> lista) 
	{
		double somma=0;
		for(Number numero : lista)
			somma += numero.doubleValue();   //scorre la lista e somma tutti i valori
		return somma;
	}
	
	public static double avg(ArrayList<Number> lista) 
	{
		return sum(lista)/count(lista);  //restituisce la media
	}
	
	public static double max(ArrayList<Number> lista) 
	{
		double max = lista.get(0).doubleValue();  //assegna il primo valore della lista a max
		for(Number numero : lista) {
			if(numero.doubleValue() > max) max = numero.doubleValue();  //scorre la lista e verifica se il valore dell'indice corrispondente � maggiore di max
		}
		return max;
	}
	
	public static double min(ArrayList<Number> lista) 
	{
		double min = lista.get(0).doubleValue();  //assegna il primo valore della lista a min
		for(Number numero : lista) {
			if(numero.doubleValue() < min) min = numero.doubleValue();  //scorre la lista e verifica se il valore dell'indice corrispondente � minore di min
		}
		return min;
	}
	
	//formula della deviazione standard: DevStd = radice della sommatoria degli (xi-xmedio)^2
	public static double DevStd(ArrayList<Number> lista) 
	{  
		double somma=0;
		for(Number numero : lista) {
			somma += Math.pow(numero.doubleValue() - avg(lista), 2);
		}
		return Math.sqrt(somma);
	}
	

}
