import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.xml.bind.JAXBException;

public class Main {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int opcio;
		File fitxerBIN = new File("ExamenUF1XaviPadilla/instituts.dat");
		File fitxerRegistresCovid = new File("ExamenUF1XaviPadilla/registrecovid.dat");
		File fitxerXML = new File("ExamenUF1XaviPadilla/covid_escoles.xml");
		File fitxerPropietats = new File("ExamenUF1XaviPadilla/ModelCXaviPL.properties");

		do {
			opcio = Menu.menuPrincipal(sc);

			sc.nextLine();
			switch (opcio) {
				case 1:
					fitxersBuits();
					break;
				case 2:
					propietats(fitxerPropietats);
					break;
				case 3:
					veureFitxerConf(fitxerPropietats);
					break;
				case 4:
					crearPropietats(fitxerPropietats);
					break;
				case 5:
					Covid insCovid = new Covid();

					System.out.print("Quin sera el codi del centre? ");
					insCovid.setCodiCentre(sc.nextLong());
					sc.nextLine();
					System.out.println("");
					break;
				case 6:
					List<Covid> llista;
					try {
						llista = GestioFitxer.llegirObjectesJAXB(fitxerXML);
						for (Covid covid : llista) {
							System.out.println(covid.toString() + "\n");
						}
					} catch (JAXBException e) {
						e.printStackTrace();
					}
					break;
				case 7:
					break;
				case 8:
					if (fitxerBIN.exists()) {
						Institut insti = (Institut) GestioFitxer.llegirObjecteBin(fitxerBIN);
						System.out.println(insti.toString());
					} else {
						System.out.println("No existeix!");
					}
					break;
				case 9:
					
					break;
				case 10:
					if (fitxerRegistresCovid.exists()) {
						Covid objSerialitzat = (Covid) GestioFitxer.llegirObjecteBin(fitxerRegistresCovid);
						System.out.println(objSerialitzat.toString());
					} else {
						System.out.println("No existeix!");
					}
					
					break;
				default: System.out.println("Introdueix una opcio d'aquestes!");
					break;
			}
		} while (opcio != 0);

		sc.close();
	}
	
	public static void fitxersBuits() {
		File fitxer;
		String nomFitxer;

		System.out.print("Introdueix la ruta i el nom del fitxer que vols crear: ");
		nomFitxer = sc.nextLine();

		fitxer = new File(nomFitxer);

		try {
			if (fitxer.exists()) {
				GestioFitxer.crearFisicamentFitxer(fitxer);
			} else {
				System.out.println("Ja existeix!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void propietats(File fitxerPropietats) {
		Properties properties = new Properties();
		
		try {
			GestioFitxer.crearFisicamentFitxer(fitxerPropietats);
			if (fitxerPropietats.exists()) {
				llegirPorpietats(properties, fitxerPropietats);

				properties.setProperty("Foto_Generalitat", "Mineral.jpg");
				properties.setProperty("Tamany_Pantalla", "1200x800");
				properties.setProperty("Fons_Corporatiu", "Blau");

				escriurePropietats(properties, fitxerPropietats);

				veureFitxerConf(fitxerPropietats);
			} else {
				System.out.println("No existeix fitxer");
			}
		} catch (IOException e) {
			System.out.println("Error de fitxer");
		}
	}

	public static void crearPropietats(File fitxerPropietats) {
		Properties properties = new Properties();

		try {
			if (fitxerPropietats.exists()) {
				veureFitxerConf(fitxerPropietats);

				System.out.print("\nDigues l'atribut que vols afegir: ");
                String atribut = sc.nextLine();
                System.out.print("\nDigues ara el valor de l'atribut: ");
				String valor = sc.nextLine();
				
				llegirPorpietats(properties, fitxerPropietats);

				properties.setProperty(atribut, valor);

				escriurePropietats(properties, fitxerPropietats);
			} else {
				System.out.println("No existeix fitxer");
			}
		} catch (IOException e) {
			System.out.println("Error de fitxer");
		}
	}

	public static void escriurePropietats(Properties properties, File fitxerConf) throws IOException {
		FileWriter fw = new FileWriter(fitxerConf);
		properties.store(fw, null);
		fw.close();
	}
	
	public static void llegirPorpietats(Properties properties, File fitxerConf) throws IOException {
		FileReader fr = new FileReader(fitxerConf);
		properties.load(fr);
		fr.close();
	}
	
	public static void veureFitxerConf(File fitxerPropietats) {
		Properties properties = new Properties();

		try {
			llegirPorpietats(properties, fitxerPropietats);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e);
		}

		Enumeration<Object> keys = properties.keys();

		System.out.println();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			System.out.println(key + "=" + properties.get(key));
		}
	}

	public static void incrementaAlumneConfinat(Long codcentre, File fitxerXML) {
		Collection<Covid> nouAlumne = new HashSet<>();

		try {
			nouAlumne = GestioFitxer.llegirObjectesJAXB(fitxerXML);

			for (Covid covid : nouAlumne) {
				Long codiABuscar = covid.getCodiCentre();
				
				if (codiABuscar.equals(codcentre)) {
					List<Confinats> confinats = covid.getConfinats();

				}
			}
		} catch (Exception e) {
			System.out.println("Error de fitxer");
		}
	}
	
	public static void generaFitxerDAT(File fitxerDAT) {
		
	}
	
}
