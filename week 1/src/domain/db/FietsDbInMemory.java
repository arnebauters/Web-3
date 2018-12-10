package domain.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.model.Fiets;


public class FietsDbInMemory implements FietsDb {

    private Map<Integer, Fiets> products = new HashMap<>();

    public FietsDbInMemory() {
        Fiets fiets1 = new Fiets("emonda", "trek", 5000);
        Fiets fiets2 = new Fiets("Izalco max", "Focus", 8000);
        Fiets fiets3 = new Fiets("F10", "Pinarello", 10000);
        voegFietsToe(fiets1);
        voegFietsToe(fiets2);
        voegFietsToe(fiets3);

    }

    @Override
    public void voegFietsToe(Fiets fiets) {
        if(fiets == null){
            throw new DbException("No Bike given");
        }
        fiets = adaptFiets(fiets);
        if (products.containsKey(fiets.getProductId())) {
            throw new DbException("Bike already exists");
        }
        products.put(fiets.getProductId(), fiets);
    }

    private Fiets adaptFiets(Fiets fiets) {
        fiets = new Fiets(this.products.size() + 1, fiets.getNaam(), fiets.getMerk(), fiets.getPrijs());
        return fiets;
    }

    @Override
    public Fiets getFiets(int productId) {
            if(productId <= 0){
                throw new DbException("No id given");
            }
            return products.get(productId);
        }


    @Override
    public Fiets duurste() {
        Fiets duurste = new Fiets();
        for(Fiets fiets :products.values()){
            if(fiets.getPrijs() > duurste.getPrijs()){
                duurste = fiets;
            }
        }
        return duurste;
    }

    @Override
    public List<Fiets> fietsen() {
        return new ArrayList<Fiets>(products.values());
    }

    @Override
    public void verwijder(int productId) {
        if(productId <= 0){
            throw new DbException("No id given");
        }
        products.remove(productId);
    }

    @Override
    public void update(Fiets fiets) {
        if(fiets == null){
            throw new DbException("No bike given");
        }
        if(!products.containsKey(fiets.getProductId())){
            throw new DbException("No bike found");
        }
        products.put(fiets.getProductId(), fiets);
    }
}
