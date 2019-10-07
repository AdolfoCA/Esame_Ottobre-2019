//main container. contiene il nome della categorie e un array di sottocategorie
package it.univpm.Dati_Europa.Model;
import java.util.ArrayList;
import it.univpm.Dati_Europa.Model.SubCat;

public class MainCat {
	 private int Nsubcat; //numero delle sottocategorie
	 private String nameCat; //nome della macrocategoria
	 //definisco un array di sottocategorie;
	 private ArrayList <SubCat> data; //la dim dell'array è pari a Nsubcat
	 
	 public MainCat(String nameCat, int Nsubcat)
	 {
		 this.nameCat=nameCat;
		 this.Nsubcat=Nsubcat;
		 data= new ArrayList<SubCat> (Nsubcat);
	 }

	public ArrayList<SubCat> getData() {
		return data;
	}

	public void setData(ArrayList<SubCat> data) {
		this.data = data;
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

}
