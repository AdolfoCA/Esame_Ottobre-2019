package it.univpm.Dati_Europa.Services;

import java.util.ArrayList;
import java.util.HashMap;

import it.univpm.Dati_Europa.Model.MainCat;
import it.univpm.Dati_Europa.Model.SubCat;

public class Filter {
	//Metodo per verificare se un dato elemento e' presente oppure no nella lista (tratta insieme gli operatori logici in e nin restituendo true o false)
	public static boolean In (ArrayList<Double> lista, double param)
	{
		boolean b = false;
		for(Double e : lista)
		{
			if(param == e)
				b = true;
		}
		return b;
	}
	//Metodo per trattare l'operatore condizionale gt
	public static ArrayList<Double> Gt (ArrayList<Double> lista, double param)
	{
		for(Double e : lista)
		{
			if(!(e > param))
			{
				lista.remove(e);
			}
		}
		return lista;
	}
	//Metodo per trattare l'operatore condizionale lt
	public static ArrayList<Double> Lt (ArrayList<Double> lista, double param)
	{
		for(Double e : lista)
		{
			if(!(e < param))
			{
				lista.remove(e);
			}
		}
		return lista;
	}
}
