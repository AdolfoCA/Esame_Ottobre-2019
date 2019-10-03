package it.univpm.Dati_Europa.Model;

public class MainCat {
	 private int Nsubcat; //numero delle sottocategorie
	 private String nameCat; //nome della macrocategoria
	 
	 public MainCat(String nameCat, int Nsubcat)
	 {
		 this.nameCat=nameCat;
		 this.Nsubcat=Nsubcat;
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
