package domain.db;

import domain.model.Fiets;

import java.util.List;

public interface FietsDb {
    void voegFietsToe(Fiets fiets);

    Fiets getFiets(int productId);

    Fiets duurste();

    List<Fiets> fietsen();

    void verwijder(int productId);

    void update(Fiets fiets);
}
