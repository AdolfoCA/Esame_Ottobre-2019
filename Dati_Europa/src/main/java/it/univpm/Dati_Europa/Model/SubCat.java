//una sottocategoria contiene una mappa con i dati dei vari paesi;
package it.univpm.Dati_Europa.Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubCat 
{
	private String nameSub;
	private ArrayList<String> nPaesi;
	private ArrayList<Double> datiPaesi;
	
	//private Map <String, Double> paesi = new HashMap <String, Double> (35); //creo una HashMap di paesi
	
	public SubCat()
	{
		this.nameSub="";
		this.datiPaesi=new ArrayList <Double> ();//array di dati relativi a ciascun paese
		/*for(int i=0;i<35;i++)
		{
			datiPaesi.add(null);
		}*/
		this.nPaesi=new ArrayList <String>();//array di paesi
		//costruisco l'array con i nomi dei paesi
		nPaesi.add("EU27");
		nPaesi.add("Belgium");
		nPaesi.add("Bulgaria");
		nPaesi.add("Czech Republic");
		nPaesi.add("Denmark");
		nPaesi.add("Germany");
		nPaesi.add("Estonia");
		nPaesi.add("Greece");
		nPaesi.add("Spain");
		nPaesi.add("France");
		nPaesi.add("Ireland");
		nPaesi.add("Italy");
		nPaesi.add("Cyprus");
		nPaesi.add("Latvia");
		nPaesi.add("Lithuania");
		nPaesi.add("Luxembourg");
		nPaesi.add("Hungary");
		nPaesi.add("Malta");
		nPaesi.add("Netherlands");
		nPaesi.add("Austria");
		nPaesi.add("Poland");
		nPaesi.add("Portugal");
		nPaesi.add("Romania");
		nPaesi.add("Slovenia");
		nPaesi.add("Slovakia");
		nPaesi.add("Finland");
		nPaesi.add("Sweden");
		nPaesi.add("United Kingdom");
		nPaesi.add("Croatia");
		nPaesi.add("FYROM");
		nPaesi.add("Turkey");
		nPaesi.add("Norway");
		nPaesi.add("Albania");
		nPaesi.add("Kosovo");
		nPaesi.add("Montenegro");
		
	}
    
	public String getNameSub() {
		return nameSub;
	}

	public void setnameSub(String nameSub) {
		this.nameSub = nameSub;
	}
	
    //dato il paese restituisce il dato relativo a quel paese rispetto alla sottocategoria
	public double getDatoPaese(String paese) 
	{
		int i=nPaesi.lastIndexOf(paese); //determina l'indice dell'arraynPaesi relativo al paese selezionato
		return datiPaesi.get(i); //restituisce il dato relativo a quel paese
	}
	//restituisce l'array con i dati
	public ArrayList<Double> getDati(ArrayList<Double> datiPaesi)
	{
		return datiPaesi;
	}
	
    //metodo che assegna i valori dei dati della sottocategoria
	public void setDatiPaesi(List<Double> datiPaesi) 
	{
		for(int i=0;i<datiPaesi.size();i++)
		{
			this.datiPaesi.add(datiPaesi.get(i));
		}
	}
}
