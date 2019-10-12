package it.univpm.Dati_Europa.Download_Parsing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import it.univpm.Dati_Europa.Download_Parsing.*;
import it.univpm.Dati_Europa.Model.MainCat;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class Parsing {
	private ArrayList <String> Indici = new ArrayList <String>(); //lista che conterra'� gli indici che descrivono i paesi nel csv
	private ArrayList <Double> Valori = new ArrayList <Double>(); //lista che conterra'� i valori relativi agli indici per tutti i paesi del csv
	private ArrayList <MainCat> Categorie = new ArrayList <MainCat>(); //lista di oggetti
	private int numCat;
	private static final String DELIMETER_1 = "," ; // carattere separatore
	private static final String DELIMETER_2 = "%" ; // carattere che permettera' di distinguere i double dalle stringhe
	private static final String DELIMETER_3 = ",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,," ; // separatori delle macrocategorie
	private static final String	DELIMETER_4 =  "\"";
	public Parsing()
	{
		numCat=0;
		String riga = "" ;
		String testo = "";
		String longCat = "";
		BufferedReader br= null;
		int conta = 1;
		try
		{
			//URL urlCSV = new URL(link);
			//br = new BufferedReader(new FileReader("/Users/adolfocafaro/Desktop/table5.csv"));
			br = new BufferedReader(new FileReader("C:/Users/andre/Desktop/UNIVERSITA'/Programmazione ad oggetti/Lavori/table5.csv"));
			while ((riga = br.readLine()) != null)
			{
				if(conta==1)
				{
					conta++;
					continue;
				}
				testo += riga;
				conta++;
			}
			String[] MacroCategorie = testo.split(DELIMETER_3);
			for(String MacroCategoria : MacroCategorie)
			{
				String[] Campi = MacroCategoria.split(DELIMETER_1);
				for(int i=0; i< Campi.length ; i++)
				{
					String new_campo = Campi[i].replace(DELIMETER_2,""); // a tutti gli elementi del vettore di stringhe verra'� rimosso il carattere percentuale, se presente
					if(Campi[i].equals(new_campo)) // se campo e new_campo sono uguali e' perche' non e' stato rimosso il carattere percentuale, in quanto non presente e quindi campo e' uno degli indici
					{
						if(Campi[i].contains(DELIMETER_4))
						{
							longCat += Campi[i];
							do
							{
								i++;
								longCat += Campi[i];
							} while(!Campi[i].contains(DELIMETER_4));
							Indici.add(longCat);
							continue;
						}
						Indici.add(new_campo); // aggiunta alla lista degli indici
					}
					else // se sono diversi perch� il carattere percentuale e' stato trovato e rimosso, di conseguenza new_campo e' un numero
					{
						Valori.add(Double.parseDouble(new_campo)); // conversione di new_campo in double e successiva aggiunta alla lista dei valori
					}
				}
				Categorie.add(new MainCat(Indici.get(0), (Indici.size()-1 )));//salva il nome della macrocategoria
				//per debug
				//System.out.println(Categorie.get(0).getSottocategorie().size());
				//
				(Categorie.get(numCat)).SubcatNames(Indici);//salva i nomi della sottocategorie				
				(Categorie.get(numCat)).SubcatDati(Valori);//salva i valori percentuali
				numCat++; //mi serve per capire a quale elemento dell'array categorie accedere
				//svuotamento delle due liste
				Indici.clear();
				Valori.clear();
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
		System.out.println("Parsing completato.\n");
	}
	// metodi per la restituzione della lista
	public ArrayList <MainCat> getData()
	{
		return Categorie;
	}	
}