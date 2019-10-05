package it.univpm.Dati_Europa.Download_Parsing;

import java.io.FileNotFoundException;
import java.io.IOException;

public class test_parsing {
	public static void main ( String args [])
	{
		Parsing p1;
		Download d1;
		d1 = new Download("http://data.europa.eu/euodp/data/api/3/action/package_show?id=fifth-european-working-conditions-survey-2010");
		p1 = new Parsing(d1.Getlink());
		// stampa del contenuto delle due liste
		System.out.println("Stampa lista degli indici:\n");
		for(String indice : p1.getIndici())
		{
			System.out.println(indice + "\n");
		}
		System.out.println("Stampa lista dei valori:\n");
		for(double valore : p1.getValori())
		{
			System.out.println(valore + "\n");
		}
		//prove per la classe parsing
		/*String var1 = "";
		Integer var2 = 1;
		Double var3 = 1.1;
		Boolean var4 = false;
		System.out.println(var1 + " " + var1.getClass().getSimpleName());
		System.out.println(var2 + " " + var2.getClass().getSimpleName());
		System.out.println(var3 + " " + var3.getClass().getSimpleName());
		System.out.println(var4 + " " + var4.getClass().getSimpleName());
		String errore = "333%";
		String corretto = errore.replace("%", "");
		System.out.println(corretto + " " + corretto.getClass().getSimpleName());
		if(errore!=corretto)
		{
			double Dcorretto = Double.parseDouble(corretto);
			corretto=corretto+3;
			Dcorretto=Dcorretto+3;
			System.out.println(corretto + " " + Dcorretto);
		}*/
	}
}
