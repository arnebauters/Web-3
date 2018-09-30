package domain.model;

public class Fiets {
	private int productId;
	private String naam;
	private String merk;
	private double prijs;

	public Fiets(String n, String m, double p){
		setNaam(n);
		setMerk(m);
		setPrijs(p);
	}

	public Fiets() {
	}

	public Fiets(int productId, String n, String m, double p) {
		setProductId(productId);
		setNaam(n);
		setMerk(m);
		setPrijs(p);
	}


	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setNaam(String naam){
			if (naam==null||naam.trim().isEmpty()){
				throw new DomainException("naam mag niet leeg zijn.");
			}
			this.naam = naam;
	}
	public String getNaam() {
			return naam;
	}

	public void setMerk(String merk){
			if (merk==null||merk.trim().isEmpty()){
				throw new DomainException("merk mag niet leeg zijn.");
			}
			this.merk = merk;
	}

	public String getMerk() {
			return merk;
	}



	public void setPrijs(double prijs){
			if (prijs<=500||prijs>15000){
				throw new DomainException("Prijs moet groter dan 500 euro zijn en kleiner dan 15000 euro.");
			}
			this.prijs=prijs;
	}

	public double getPrijs() {
			return prijs;
	}

	public boolean isGelijk(Fiets fiets){
			if(fiets.getNaam().equals(this.naam)&&fiets.getMerk().equals(this.merk)
					&&fiets.getPrijs()==this.prijs){
				return true;
			}
			else return false;
	}

	public void veranderPrijs(double prijs) {
			if (this.naam.equals(naam)) {
				this.prijs = prijs;
			}
			else {
				throw new DomainException("geef de juiste naam mee");
			}
	}


	@Override
	public String toString(){
		return getNaam() + ": " + getMerk() + " - " + getPrijs() 	;
	}

}
