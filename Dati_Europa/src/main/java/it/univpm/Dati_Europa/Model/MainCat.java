//main container. contiene il nome della categorie e un array di sottocategorie
package it.univpm.Dati_Europa.Model;
import java.util.ArrayList;
import java.util.List;

import it.univpm.Dati_Europa.Model.SubCat;


public class MainCat {
	 private int Nsubcat; //numero delle sottocategorie
	 private String nameCat; //nome della macrocategoria
	 private ArrayList <SubCat> sottocategorie; //la dim dell'array è pari a Nsubcat
	 
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
	 
	 //metodo che permette di attribuire i nomi alle sottocategorie
	 public void SubcatNames(ArrayList <String> names)
	 {
		 for(int i=0;i<Nsubcat;i++)
		 {
			 (this.sottocategorie.get(i)).setnameSub(names.get(i+1));
			 //l'array names è formato da Nsubcat+1 elementi
		 }
	 }
    //metodo che permette di asseganre i dati alle sottocategorie
	public void SubcatDati(ArrayList <Double> dati)//l'array dati contiene tutti i dati relativi alla macrocategoria
	{
	  for(int i=0;i<sottocategorie.size();i++) //scorro gli elementi di sottocategorie
		{
		    List<Double> d = new ArrayList<Double>();
		    d=dati.subList(35*i,35*i+35);//quando i =0 prende da 0 a 34
			(sottocategorie.get(i)).setDatiPaesi(d); //seleziono la parte del vettore dati relativa alla sottocategoria
		}
	}
	
	/*public int getNsubcat() {
		return Nsubcat;
	}*/

	public void setNsubcat(int nsubcat) {
		Nsubcat = nsubcat;
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
	//metodo che restituice la sottocategoria cercata
	public SubCat getSubcat(String nomeSub)
	{
		int i=this.sottocategorie.indexOf(nomeSub);
		return this.sottocategorie.get(i);
	}
 
}
