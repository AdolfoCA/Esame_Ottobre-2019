package it.univpm.Dati_Europa.Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe concepita per modellare le sottocategorie del csv relative ad una macrocategoria
*/

public class SubCat 
{
	private String nameSub;
	private ArrayList<String> nPaesi;
	private ArrayList<Double> datiPaesi;
	//private Map <String, Double> paesi = new HashMap <String, Double> (35); //creo una HashMap di paesi
	
	/**
	 * Costruttore della clesse:
	 * inizializza un array con i nomi dei paesi
	*/
	
	public SubCat()
	{
		this.nameSub="";
		this.datiPaesi=new ArrayList <Double> ();	//array di dati relativi a ciascun paese
		this.nPaesi=new ArrayList <String>();	//array di paesi
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
    
	/**
	 * Getter e setter di nameSub e getter di datiPaesi e nPaesi
	*/
	
	public String getNameSub() {
		return nameSub;
	}
	public void setnameSub(String nameSub) {
		this.nameSub = nameSub;
	}
	
	/**
     * Metodo che, preso il dato restituisce il paese relativo a quel dato rispetto alla sottocategoria
     * @param dato
     * @return il paese relativo a quel dato
    */
	public String getPaese(double dato)
	{
		int i=datiPaesi.indexOf(dato);
		return nPaesi.get(i);
	}
    /**
     * Metodo che, dato il paese restituisce il dato relativo a quel paese rispetto alla sottocategoria
     * @param paese, il nome del paese
     * @return il dato relativo a quel paese
    */
	
	public double getDatoPaese(String paese) 
	{
		int i=nPaesi.lastIndexOf(paese); //determina l'indice dell'arraynPaesi relativo al paese selezionato
		return datiPaesi.get(i); //restituisce il dato relativo a quel paese
	}
	
	/**
	 * Metodo che restituisce un hashmap con i dati relativi ai paesi
	 * @return l'hashmap
	*/
	public Map <String, Double> getDati()
	{
		Map<String,Double> Dati = new HashMap <String,Double>();
		for(int i=0;i<nPaesi.size();i++)
		{
			Dati.put(this.nPaesi.get(i),this.datiPaesi.get(i));
		}
		return Dati;
	}
	
    /**
     * Metodo che assegna i valori dei dati della sottocategoria
     * @param datiPaesi, lista dei dati relativi alla sottocategoria
    */
	public void setDatiPaesi(List<Double> datiPaesi) 
	{
		for(int i=0;i<datiPaesi.size();i++)
		{
			this.datiPaesi.add(datiPaesi.get(i));
		}
	}
}
