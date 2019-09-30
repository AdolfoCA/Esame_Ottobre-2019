package it.univpm.Dati_Europa.Download_Parsing;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
//import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class Download 
{
	private String link = "";
	
	public Download(String url) {
		try {
			URLConnection openConnection = new URL(url).openConnection();  // crea una connesione tra applicazioe e url
			openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			InputStream input = openConnection.getInputStream();  // crea il flusso di input
			
			 String data = "";
			 String line = "";
			 try {
			   InputStreamReader inputReader = new InputStreamReader(input);  // legge i byte e li decodifica in caratteri
			   BufferedReader br = new BufferedReader(inputReader);  // legge un file di testo
			   
			   while ((line = br.readLine())!=null) {  // ad ogni interazione line assume il valore della linea corrente fino alla fine del file
				   data += line; // ad ogni passo viene aggiunta a data una nuova linea di caratteri
				   
			   }
			 } finally {
			   input.close();
			 }
			 
			 JSONObject objectdata = (JSONObject) JSONValue.parseWithException(data); // converte il dataset in oggetto json
			 JSONObject objectresult= (JSONObject) (objectdata.get("result"));  // assegna alla variabile objectresult il json del dataset denominato result
			 JSONArray  objectArray = (JSONArray) (objectresult.get("resources"));  // assegna al vettore json objectArray il valore di resources che contiene l'url del csv da estrarre
					 
			 for(Object object : objectArray) {
				 if (object instanceof JSONObject) {
					 JSONObject object1 = (JSONObject)object;
					if(((String)object1.get("format")).toLowerCase().contains("csv") && ((String)object1.get("url")).contains("table5"))  // estrae il csv desiderato verificando la presenza della stringa table5 nell'url che si vuole estrarre
						 link = (String)object1.get("url");
				 }
			 }			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// azioni di controllo per vedere se tutto è andato a buon fine
		System.out.println( "\nDownload completato." );
		System.out.println(link);
	}
	// restituisce il link del csv cercato
	public String Getlink()
	{
		return link;
	}

}
