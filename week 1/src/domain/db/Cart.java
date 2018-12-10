package domain.db;

import domain.model.Fiets;

import java.util.*;

public class Cart {
    private Map<Integer, Fiets> fietsen;

    public Cart(){
        fietsen =new HashMap<>();
    }

    public List<Fiets> getShoppingBasket(){
        return new ArrayList<Fiets>(fietsen.values());
    }

    public void addFietsCart(Fiets fiets){
        if(fiets == null){
            throw new DbException("No Bike given");
        }
        fiets = adaptFiets(fiets);
        if (fietsen.containsKey(fiets.getProductId())) {
            throw new DbException("Bike already exists");
        }
        fietsen.put(fiets.getProductId(), fiets);
    }

    private Fiets adaptFiets(Fiets fiets) {
        fiets = new Fiets(this.fietsen.size() + 1, fiets.getNaam(), fiets.getMerk(), fiets.getPrijs());
        return fiets;
    }

    public void deleteFietsCart(int productId){
        if(productId <= 0){
            throw new DbException("No id given");
        }
        fietsen.remove(productId);
    }

    public double getTotPrijs() {
        double prijs = 0;
        for (Fiets fiets: fietsen.values()){
            prijs+=fiets.getPrijs();
        }
        return prijs;
    }
}
