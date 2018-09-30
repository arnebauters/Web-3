package domain.db;

import java.util.ArrayList;

import domain.model.DomainException;
import domain.model.Fiets;

public class FietsDB {

	private ArrayList<Fiets> fietsen = new ArrayList<Fiets>();

	public FietsDB() {
		Fiets fiets1 = new Fiets("emonda", "trek",  5000);
		Fiets fiets2 = new Fiets("Izalco max", "Focus",  8000);
		Fiets fiets3 = new Fiets("F10", "Pinarello", 10000);
		this.voegFietsToe(fiets1);
		this.voegFietsToe(fiets2);
		this.voegFietsToe(fiets3);

	}

	public void voegFietsToe(Fiets fiets) {
		boolean gevonden = false;
		for (Fiets f: this.fietsen){
			if (f.isGelijk(fiets)){
				gevonden = true;
			}
		}
		if(!gevonden) {
			fietsen.add(fiets);
		}
		else {
			throw new DbException("Fiets is al toegevoegd.");
		}
	}

	public void vervangFiets(Fiets fiets) {
		for (Fiets i : fietsen) {
			if (fiets.isGelijk(i)) {
				fiets.setNaam(fiets.getNaam());
			}
		}
	}

	public Fiets getFiets(String naam) {
		for (Fiets fiets : fietsen) {
			if (fiets.getNaam().equals(naam)) {
				return fiets;
			}
		}
		throw new DbException(naam + " is geen naam van een fiets die al is geregistreerd.");
	}


	public String duurste() {
		Fiets duurste = fietsen.get(0);
		for (int i = 1; i <= fietsen.size() - 1; i++) {
			if (fietsen.get(i).getPrijs() > duurste.getPrijs()) {
				duurste = fietsen.get(i);
			}
		}
		String result = duurste.getNaam();
		return result;
	}

	public ArrayList<Fiets> fietsen() {
		return this.fietsen;
	}

	public void verwijder(String naam) {
		Fiets verwijder = null;
		boolean gelijk = false;
		if (naam == null || naam.trim().isEmpty()) {
			throw new DbException("naam mag niet leeg zijn.");
		} else {
			for (Fiets fiets : fietsen) {
				if (fiets.getNaam().equals(naam)) {
					verwijder = fiets;
					gelijk = true;
				}
			}
			if (gelijk) {
				fietsen.remove(verwijder);
			}
		}
	}

	public void verander(String naam, double prijs) {
		if(prijs==0) {
			throw new DbException();
		}
		else if(naam.isEmpty()) {
			throw new DbException();
		}
		else {
			veranderPrijs(naam, prijs);


		}
	}

	private void veranderPrijs(String naam, double prijs) {
		boolean gelijk = false;
		for(Fiets fiets: fietsen) {
			if (fiets.getNaam().equals(naam)){
				fiets.veranderPrijs(prijs);
				gelijk = true;
			}
		}
		if(!gelijk) {
			throw new IllegalArgumentException();
		}

	}

}
