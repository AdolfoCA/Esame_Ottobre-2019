package it.univpm.Dati_Europa.Services;

import java.util.ArrayList;

/**
 * Classe concepita per definire le operazioniche genereranno le statistiche
*/

public class Stats 
{
	public static int count(ArrayList lista)
	{
		return lista.size();
	}
	
	/**
	 * Metodo che effettua la somma di tutti gli elementi di una lista di valori
	 * @param lista, la lista di elementi da sommare
	 * @return somma, il risultato della somma
	*/
	
	public static double sum(ArrayList<Double> lista) 
	{
		double somma=0;
		for(Double numero : lista)
			somma += numero.doubleValue();   //scorre la lista e somma tutti i valori
		return somma;
	}
	
	/**
	 * Metodo che effettua la media di tutti gli elementi di una lista di valori
	 * @param lista, la lista di elementi su cui fare la media
	 * @return la media matematica degli elementi della lista
	*/
	
	public static double avg(ArrayList<Double> lista) 
	{
		return sum(lista)/count(lista);  //restituisce la media
	}
	
	/**
	 * Metodo che trova il massimo elemento di una lista
	 * @param lista, la lista di elementi in cui cercare il massimo
	 * @return max, il massimo elemento della lista
	*/
	
	public static double max(ArrayList<Double> lista) 
	{
		double max = lista.get(0).doubleValue();  //assegna il primo valore della lista a max
		for(Double numero : lista) {
			if(numero.doubleValue() > max) max = numero.doubleValue();  //scorre la lista e verifica se il valore dell'indice corrispondente � maggiore di max
		}
		return max;
	}
	
	/**
	 * Metodo che trova il minimo elemento di una lista
	 * @param lista, la lista di elementi in cui cercare il minimo
	 * @return min, il minimo elemento della lista
	*/
	
	public static double min(ArrayList<Double> lista) 
	{
		double min = lista.get(0).doubleValue();  //assegna il primo valore della lista a min
		for(Double numero : lista) {
			if(numero.doubleValue() < min) min = numero.doubleValue();  //scorre la lista e verifica se il valore dell'indice corrispondente � minore di min
		}
		return min;
	}
	
	/**
	 * Metodo per il calcolo della deviazione standard degli elementi di una lista
	 * @param lista, la lista di elementi di cui calcolare la deviazione standard
	 * @return il valore della deviazione standard degli elementi della lista
	*/
	
	//formula della deviazione standard: DevStd = radice della sommatoria degli (xi-xmedio)^2
	public static double DevStd(ArrayList<Double> lista) 
	{  
		double somma=0;
		for(Double numero : lista) {
			somma += Math.pow(numero.doubleValue() - avg(lista), 2);
		}
		return Math.sqrt(somma);
	}

}
