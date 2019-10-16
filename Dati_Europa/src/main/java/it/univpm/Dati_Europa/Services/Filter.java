package it.univpm.Dati_Europa.Services;

import java.util.ArrayList;
import java.util.HashMap;

import it.univpm.Dati_Europa.Model.MainCat;
import it.univpm.Dati_Europa.Model.SubCat;

/**
 * Classe concepita per implementare i due operatori condizionali (in e nin) e i due operatore logici (gt e lt)
*/

public class Filter {
	String MainCat;
	String SubCat;
	String Filtro;
	Double[] Param;
	
	/**
	 * Costruttore della classe:
	 * setta i valori degli attributi
	*/
	
	public Filter(String MainCat, String SubCat, String Filtro, Double[] Param)
	{
		this.MainCat=MainCat;
		this.SubCat=SubCat;
		this.Filtro=Filtro;
		this.Param=Param;
	}
	
	/**
	 * Getter e setter degli attributi
	*/
	
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
	
	/**
	 * Metodo per l'implementazione degli operatori in e nin, in quanto due facce della stessa medaglia
	 * @param lista e param, corrispondenti alla lista in cui verificare se un dato elemento e' presente (o assente) e l'elemento in questione (e' possibile passare piu' elementi in un array di cui verificare la presenza)
	 * @return un valore booleano dal valore true se il parametro e' presente, false se assente
	*/
	
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
	
	/**
	 * Metodo per l'implementazione dell'operatore gt
	 * @param lista e param, la lista da filtrare e il numero sulla base del quale applicare il filtro
	 * @return una lista di valori piu' piccola di quella di partenza, in cui sono stati rimossi gli elementi minori o uguali a param
	*/
	
	public static ArrayList <Double> Gt (ArrayList<Double> lista, double param)
	{
		ArrayList<Double> Nlista= new ArrayList <Double>();
		for(Double e : lista)
		{
			if((e > param))
			{
				Nlista.add(e);
			}
		}
		return Nlista;
	}
	
	/**
	 * Metodo per l'implementazione dell'operatore lt
	 * @param lista e param, la lista da filtrare e il numero sulla base del quale applicare il filtro
	 * @return una lista di valori piu' piccola di quella di partenza, in cui sono stati rimossi gli elementi maggiori o uguali a param
	*/

	public static ArrayList<Double> Lt (ArrayList<Double> lista, double param)
	{
		ArrayList<Double> Nlista= new ArrayList <Double>();
		for(Double e : lista)
		{
			if((e < param))
			{
				Nlista.add(e);
			}
		}
		return Nlista;
	}
}
