//main container. contiene il nome della categorie e un array di sottocategorie
package it.univpm.Dati_Europa.Model;
import java.util.ArrayList;
import it.univpm.Dati_Europa.Model.SubCat;


public class MainCat {
	 private int Nsubcat; //numero delle sottocategorie
	 private String nameCat; //nome della macrocategoria
	 private ArrayList <SubCat> sottocategorie; //la dim dell'array è pari a Nsubcat
	 
	 public MainCat(String nameCat, int Nsubcat)
	 {
		 this.nameCat=nameCat;
		 this.Nsubcat=Nsubcat;
		 sottocategorie=new ArrayList<SubCat> (Nsubcat);
	 }
	 
	 //metodo che permette di attribuire i nomi alle sottocategorie
	 public void SubcatNames(ArrayList <String> names)
	 {
		 for(int i=0;i<Nsubcat;i++)
		 {
			 sottocategorie.get(i).setnameSub(names.get(i+1));
			 //l'array names è formato da Nsubcat+1 elementi
		 }
	 }
    //metodo che permette di asseganre i dati alle sottocategorie
	public void SubcatDati(ArrayList <Double> dati)//l'array dati contiene tutti i dati relativi alla macrocategoria
	{
	  for(int i=0;i<sottocategorie.size();i++) //scorro gli elementi di sottocategorie
		{
			sottocategorie.get(i).setDatiPaesi((ArrayList<Double>) dati.subList(35*i,35*i+34 )); //seleziono la parte del vettore dati relativa alla sottocategoria
		}
	}
	
	public int getNsubcat() {
		return Nsubcat;
	}

	public void setNsubcat(int nsubcat) {
		Nsubcat = nsubcat;
	}

	public String getNameCat() {
		return nameCat;
	}

	public void setNameCat(String nameCat) {
		this.nameCat = nameCat;
	}

	public ArrayList<SubCat> getSottocategorie() {
		return sottocategorie;
	}

}
