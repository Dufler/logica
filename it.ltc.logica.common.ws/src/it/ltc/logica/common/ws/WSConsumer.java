package it.ltc.logica.common.ws;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;

public abstract class WSConsumer {
	
	/**
	 * Contiene l'elenco dei possibili metodi http con cui effettuare le richieste REST
	 * @author Damiano Bellucci - damiano.bellucci@gmail.com
	 *
	 */
	public enum Method { GET, POST, PUT, DELETE }
	
	protected final String authString;
	protected final String domain;
	protected final String contextPath;
	
	public WSConsumer(String authString, String domain, String contextPath) {
		this.authString = authString;
		this.domain = domain;
		this.contextPath = contextPath;
	}
	
	protected static String getAuthString() {
		String authstring = ControllerVariabiliGlobali.getInstance().getString("utente.authstring");
		return authstring;
	}
	
	/**
	 * Esegue la chiamata con il metodo indicato alla risorsa indicata e restituisce un oggetto contenente il codice e la risposta. In caso di errore la stringa restituita è nulla e il codice -1.
	 * @param method Una delle possibili enum della classe: GET, POST, PUT o DELETE.
	 * @param urlPath la url completa della risorsa con tanto di protocollo.
	 * @param input l'input sottoforma di stringa, null se non necessario.
	 * @param authString la stringa necessaria per l'autenticazione secondo BASE64 (username:password)
	 * @return un oggetto che incarta la risposta ricevuta dal server.
	 */
	public WSAnswer call(Method method, String urlPath, String input, String authString) {
		WSAnswer wsAnswer;
		StringBuilder answer = new StringBuilder();
		try {
			URL url = new URL(urlPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(method.name());
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "Basic " + authString);

			if (input != null && !input.isEmpty()) {
				OutputStream os = conn.getOutputStream();
				os.write(input.getBytes());
				os.flush();
			}
			
			int responseCode = conn.getResponseCode();
			InputStream stream = responseCode >= 400 ? conn.getErrorStream() : conn.getInputStream();
			
			if (stream != null) {
				BufferedReader br = new BufferedReader(new InputStreamReader(stream));
				String line = br.readLine();
				while (line != null) {
					answer.append(line);
					line = br.readLine();
				}
				br.close();
			}
			
			conn.disconnect();
			
			wsAnswer = new WSAnswer(responseCode, answer.toString());

		} catch (Exception e) {
			e.printStackTrace();
			wsAnswer = new WSAnswer(-1, null);
		}
		return wsAnswer;
	}
	
	/**
	 * Metodo di convenienza per le chiamate GET
	 * @param resource
	 * @return
	 */
	public WSAnswer get(String resource) {
		return call(Method.GET, domain + contextPath + resource, null, authString);
	}
	
	/**
	 * Metodo di convenienza per le chiamate POST
	 * @param resource
	 * @param input
	 * @return
	 */
	public WSAnswer post(String resource, String input) {
		return call(Method.POST, domain + contextPath + resource, input, authString);
	}
	
	/**
	 * Metodo di convenienza per le chiamate PUT
	 * @param resource
	 * @param input
	 * @return
	 */
	public WSAnswer put(String resource, String input) {
		return call(Method.PUT, domain + contextPath + resource, input, authString);
	}
	
	/**
	 * Metodo di convenienza per le chiamate DELETE
	 * @param resource
	 * @param input
	 * @return
	 */
	public WSAnswer delete(String resource, String input) {
		return call(Method.DELETE, domain + contextPath + resource, input, authString);
	}

}
