import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.File;

public class GestioFitxer {

	/**
	 * Lleguim un fitxer i ens retorna una collecio de Strings amb un String per linia
	 * @param fitxer Fitxer que volem llegir
	 * @return Collection de Strings
	 * @throws IOException Si troba algun error llegint
	 */
	public static Collection<String> llegirFitxerTxt(File fitxer) throws IOException {
		BufferedReader br;
		FileReader fr;
		Collection<String> lecturaLinia = new ArrayList<>();

		if (fitxer.exists() && fitxer.getName().contains(".txt")) {
			fr = new FileReader(fitxer);
			br = new BufferedReader(fr);

			String linia = null;
			while ((linia = br.readLine()) != null) {
				lecturaLinia.add(linia);
			}

			fr.close();
			br.close();
			return lecturaLinia;
		} else {
			System.out.println("No s'ha trobat el fitxer...");
			return null;
		}
	}

	/**
	 * Escriu en el fitxer pasat per parametre, tot el que conte la colleccio que li pasem
	 * @param fitxer Fitxer per escriure
	 * @param novaEscriptura Collection per escriure
	 * @throws IOException Error de fitxer
	 */
	public static void escriureFitxerTxt(File fitxer, Collection<String> novaEscriptura) throws IOException {
		BufferedWriter bw;

		if (fitxer.exists() && fitxer.getName().contains(".txt")) {
			bw = new BufferedWriter(new FileWriter(fitxer));

			for (String string : novaEscriptura) {
				bw.write(string + "\n");
			}

			bw.close();
		} else {
			System.out.println("No s'ha trobat el fitxer...");
		}
	}

	/**
	 * Ordena el fitxer de text alfabeticament i amb case insensitive
	 * @param fitxer Fitxer .txt a ordenar
	 */
	public static void ordenaAlfabeticamentTxt(File fitxer) {
		List<String> llistaLliguesFitxer = new ArrayList<>();

		try {
			if (fitxer.exists() && fitxer.getName().contains(".txt")) {
				BufferedReader br = new BufferedReader(new FileReader(fitxer));

				String linia = null;

				while ((linia = br.readLine()) != null) {
					llistaLliguesFitxer.add(linia);
				}

				Collections.sort(llistaLliguesFitxer, String.CASE_INSENSITIVE_ORDER);
				escriureFitxerTxt(fitxer, llistaLliguesFitxer);

				for (String lliga : llistaLliguesFitxer) {
                    System.out.println(lliga);
                }

				br.close();
			} else {
				System.out.println("El fitxer no existeix!");
			}
		} catch (IOException e) {
			System.out.println("Hi ha hagut algun problema amb el fitxer...");
		}
	}
	
	/**
	 * Comprova si a un fitxer hi ha res escrit o no
	 * @param fitxer Fitxer a comprovar
	 * @return true si esta buit o false si esta escrit
	 */
	public static boolean fitxerTXTBuit(File fitxer) {
		boolean estaBuit = false;
		Collection<String> lectura = null;

		try {
			lectura = llegirFitxerTxt(fitxer);
			
			if (lectura == null) {
				estaBuit = true;
			}
		} catch (IOException e) {
			System.out.println("Error de fitxer.");
		}

		return estaBuit;
	}

	/**
	 * Llegim una llista d'objectes de un fitxer XML
	 * @param fitxer fitxer per agafar objecte
	 * @return Objecte serialitzat
	 * @throws JAXBException
	 */
	public static List<Covid> llegirObjectesJAXB(File fitxer) throws JAXBException {
		if (fitxer.exists() && fitxer.getName().contains(".xml")) {
			JAXBContext jaxbContext = JAXBContext.newInstance(Covids.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			Covids llistaObjectes = (Covids) unmarshaller.unmarshal(fitxer);

			return llistaObjectes.getLlistaRow();
		} else {
			System.out.println("No s'ha trobat el fitxer...");
			return null;
		}
	}

	/**
	 * Escriure un objecte a un fitxer XML
	 * @param fitxer Fitxer XML on volem escriure
	 * @param escriptura Objecte a escriure
	 */
	public static void escriureObjectesJAXB(File fitxer, Object escriptura) {
		if (fitxer.exists() && fitxer.getName().contains(".xml")) {
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(Covid.class);
				Marshaller marshallerObj = jaxbContext.createMarshaller();
				marshallerObj.marshal(escriptura, new FileOutputStream(fitxer));
			} catch (JAXBException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				System.out.println("No s'ha trobat el fitxer!");
			}
		} else {
			System.out.println("No existeix o no es un arxiu xml...");
		}
	}

	/**
	 * Llegir objecte serialitzat, s'agafa d'un fitxer binari
	 * @param fitxerBIN Fitxer serialitzat
	 * @return Objecte dins d'aquest fitxer
	 */
	public static Object llegirObjecteBin(File fitxerBIN) {
		if (fitxerBIN.getName().contains(".dat")) {
			ObjectInputStream lectorBin;
			Object obj = new Object();
			Object insti = new Institut();
			Object covid = new Covid();

			try {
				lectorBin = new ObjectInputStream(new FileInputStream(fitxerBIN));

				do {
					obj = lectorBin.readObject();

					if (obj instanceof Institut) {
						insti = (Institut) obj;
					} else if (obj instanceof Covid) {
						covid = (Covid) obj;
					}
				} while (obj != null);

				lectorBin.close();
			} catch (EOFException e) {
				System.out.println("\nFitxer acabat de llegir");
			} catch (IOException e) {
				System.out.println("Error de fitxer");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			if (obj instanceof Institut) {
				return insti;
			} else {
				return covid;
			}
		} else {
			System.out.println("No es un fitxer .bin");
			return null;
		}
	}

	/**
	 * Escriure un objecte a un fitxer BIN, serialitzar objecte.
	 * @param fitxer Fitxer binari on posem l'objecte
	 * @param objecte Objecte a escriure
	 */
	public static void escriureABinariObjecte(File fitxer, Object objecte) {
		if (fitxer.exists() && fitxer.getName().contains(".bin")) {
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fitxer))) {
				oos.writeObject((Institut) objecte);

				oos.flush();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No es un fitxer binari o no existeix!");
		}
	}

	/**
	 * Llista els fitxers que hi ha a una carpeta.
	 * @param carpeta Carpeta passada per parametre.
	 * @return Llista de fitxers.
	 */
	public static Collection<File> conseguirFitxersCarpeta(File carpeta) {
		Collection<File> fitxersBinJugador = new ArrayList<>();
		
		for (File ficheroEntrada : carpeta.listFiles()) {
			if (ficheroEntrada.isFile()) {
				fitxersBinJugador.add(ficheroEntrada);
			}
		}

		return fitxersBinJugador;
	}

	/**
	 * Crea un fitxer al disc dur
	 * 
	 * @param fitxerNou Fitxer que volem instanciar
	 * @return Fitxer instanciat
	 * @throws IOException Si no troba el fitxer llença excepcio
	 */
	public static void crearFisicamentFitxer(File fitxerNou) throws IOException {
		if (fitxerNou.createNewFile()) {
			System.out.println("\nS'ha creat correctament!");
		} else {
			System.out.println("\nNo s'ha pogut crear o ja existeix!");
		}
	}
	
	/**
	 * Esborra el fitxer que li pasem per parametre.
	 * @param fitxer Fitxer a esborrar.
	 */
	public static void esborrarFitxer(File fitxer) {
		if (fitxer.delete()) {
            System.out.println("S'ha esborrat correctament!");
        } else {
            System.out.println("No s'ha pogut esborrar...");
        }
	}

}
