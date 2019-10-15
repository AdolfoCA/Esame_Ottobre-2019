package it.univpm.Dati_Europa.Services;

import java.util.ArrayList;
import java.util.HashMap;

import it.univpm.Dati_Europa.Model.MainCat;
import it.univpm.Dati_Europa.Model.SubCat;

public class Filter {
	String MainCat;
	String SubCat;
	String Filtro;
	Double[] Param;
	public Filter(String MainCat, String SubCat, String Filtro, Double[] Param)
	{
		this.MainCat=MainCat;
		this.SubCat=SubCat;
		this.Filtro=Filtro;
		this.Param=Param;
	}
	public String getMainCat() {
		return MainCat;
	}
	public void setMainCat(String mainCat) {
		MainCat = mainCat;
	}
	public String getSubCat() {
		return SubCat;
	}
	public void setSubCat(String subCat) {
		SubCat = subCat;
	}
	public String getFiltro() {
		return Filtro;
	}
	public void setFiltro(String filtro) {
		Filtro = filtro;
	}
	public Double[] getParam() {
		return Param;
	}
	public void setParam(Double[] param) {
		Param = param;
	}
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
