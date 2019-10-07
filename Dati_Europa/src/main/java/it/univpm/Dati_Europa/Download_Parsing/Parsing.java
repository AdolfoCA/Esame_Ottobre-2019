package it.univpm.Dati_Europa.Download_Parsing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import it.univpm.Dati_Europa.Download_Parsing.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class Parsing {
	
	ArrayList <String> Indici = new ArrayList <String>(); //lista che conterr√† gli indici che descrivono i paesi nel csv
	ArrayList <Double> Valori = new ArrayList <Double>(); //lista che conterr√† i valori relativi agli indici per tutti i paesi del csv
	private static final String DELIMETER_1 = "," ; // carattere separatore
	private static final String DELIMETER_2 = "%" ; // carattere che permetter√† di distinguere i double dalle stringhe
	public Parsing(String link)
	{
		String riga = "" ;
		BufferedReader br= null;
		boolean bool1= false, bool2= false;
		int limite=546;
		int conta = 1;
		try
		{
			URL urlCSV = new URL(link);
			br = new BufferedReader(new InputStreamReader(urlCSV.openStream()));
			while (((riga = br.readLine()) != null) && !bool2)
			{
				System.out.println(riga);
				/*if (conta == limite) 
				{
					bool2= true;  // esce dal ciclo quando la variabile count Ë uguale a limit
				}
				if (!bool1)
				{
					bool1 = true;
					continue; // permette di saltare la prima riga del csv
				}
				
				String[] Campi = riga.split(DELIMETER_1); // dopo aver letto una riga del csv la divide ogni volta che trova il carattere separatore ","
				System.out.println(Campi);
				
				
				for(String campo : Campi)
				{
					String new_campo = campo.replace(DELIMETER_2,""); // a tutti gli elementi del vettore di stringhe verr√† rimosso il carattere percentuale, se presente
					if(campo.equals(new_campo)) // se campo e new_campo sono uguali Ë perchË non Ë stato rimosso il carattere percentuale, in quanto non presente e quindi campo Ë uno degli indici
					{
						Indici.add(new_campo); // aggiunta alla lista degli indici
					}
					else // se sono diversi Ë perchË il carattere percentuale Ë stato trovato e rimosso, di conseguenza new_campo Ë un numero
					{
						Valori.add(Double.parseDouble(new_campo)); // conversione di new_campo in double e successiva aggiunta alla lista dei valori
					}
				}*/
				conta++;
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (br != null)
			{
				try
				{
					br.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		/*System.out.println("Parsing completato.\n");
		// stampa del contenuto delle due liste
		System.out.println("Stampa lista degli indici:\n");
		for(String indice : Indici)
		{
			System.out.println(indice + "\n");
		}
		System.out.println("Stampa lista dei valori:\n");
		for(double valore : Valori)
		{
			System.out.println(valore + "\n");
		}*/
	}
	// metodi per la restituzione delle liste
	public ArrayList<String> getIndici()
	{
		return Indici;
	}
	public ArrayList<Double> getValori()
	{
		return Valori;
	}
}