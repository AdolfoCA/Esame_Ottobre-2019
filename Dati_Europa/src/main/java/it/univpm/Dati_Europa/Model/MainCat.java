package it.univpm.Dati_Europa.Model;
import java.util.ArrayList;
import java.util.List;

import it.univpm.Dati_Europa.Model.SubCat;

/**
 * Classe concepita per modellare le macrocategorie del csv
*/

public class MainCat {
	 private int Nsubcat; //numero delle sottocategorie
	 private String nameCat; //nome della macrocategoria
	 private ArrayList <SubCat> sottocategorie; //la dim dell'array è pari a Nsubcat
	 
	 /**
	  * Costruttore:
	  * @param nameCat
	  * @param Nsubcat
	 */
	 
	 public MainCat(String nameCat, int Nsubcat)
	 {
		 this.nameCat=nameCat;
		 this.Nsubcat=Nsubcat;
		 this.sottocategorie=new ArrayList<SubCat> ();
		 for(int i=0;i<Nsubcat;i++)
		 {
			 sottocategorie.add(new SubCat());
		 }
	 }
	 
	 /**
	  * Metodo che permette di attribuire i nomi alle sottocategorie
	  * @param names, una lista degli indici di una macrocategoria, compreso il nome della stessa
	 */
	 
	 public void SubcatNames(ArrayList <String> names)
	 {
		 for(int i=0;i<Nsubcat;i++)
		 {
			 (this.sottocategorie.get(i)).setnameSub(names.get(i+1));
			 //l'array names è formato da Nsubcat+1 elementi
		 }
	 }
	 
    /**
     * Metodo che permette di assegnare i dati alle sottocategorie
     * @param dati, una lista di tutti i dati relativi alla macrocategoria
    */
	 
	public void SubcatDati(ArrayList <Double> dati)
	{
	  for(int i=0;i<sottocategorie.size();i++) //scorro gli elementi di sottocategorie
		{
		    List<Double> d = new ArrayList<Double>();
		    d=dati.subList(35*i,35*i+35);//quando i =0 prende da 0 a 34
			(sottocategorie.get(i)).setDatiPaesi(d); //seleziono la parte del vettore dati relativa alla sottocategoria
		}
	}
	
	/**
	 * Getter e setter delle variabili Nsubcat, nameCat e la lista sottocategorie
	*/
	
	public void setNsubcat(int Nsubcat) {
		this.Nsubcat = Nsubcat;
	}
	public String getNameCat() {
		return nameCat;
	}
	public void setNameCat(String nameCat) {
		this.nameCat = nameCat;
	}
	public ArrayList<SubCat> getSottocategorie() 
	{
		return sottocategorie;
	}
	
	/**
	 * Metodo che restituice la sottocategoria cercata
	 * @param nomeSub, nome della sottocategoria
	 * @return un oggetto della classe SubCat, ossia un elemento dell'array sottocategorie
	*/
	
	public SubCat getSubcat(String nomeSub)
	{
		int i=this.sottocategorie.indexOf(nomeSub);
		return this.sottocategorie.get(i);
	}
}
