package e4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EuroCoinTest {

    private EuroCoinCollection collection;
    private EuroCoin coin1;
    private EuroCoin coin2;
    private EuroCoin coin3;

    @BeforeEach
    public void setUp() {
        collection = new EuroCoinCollection();
        coin1 = new EuroCoin(200, Country.ES, "Juan Carlos I", CoinColor.GOLD, 2002);
        coin2 = new EuroCoin(50, Country.FR, "La Semeuse", CoinColor.BRONZE, 2013);
        coin3 = new EuroCoin(100, Country.DE, "Eagle", CoinColor.GOLD_SILVER, 0);
    }

    @Test
    public void testAddCoin() {
        assertTrue(collection.addCoin(coin1));
        assertTrue(collection.addCoin(coin2));
        assertTrue(collection.addCoin(coin3));
        assertFalse(collection.addCoin(coin1));
        assertEquals(3, collection.countCoins());
    }

    @Test
    public void testRemoveCoin() {
        collection.addCoin(coin1);
        collection.addCoin(coin2);
        assertTrue(collection.removeCoin(coin1));
        assertFalse(collection.hasCoin(coin1));
        assertFalse(collection.removeCoin(coin3));
    }

    @Test
    public void testCountCoins() {
        assertEquals(0, collection.countCoins());
        collection.addCoin(coin1);
        collection.addCoin(coin2);
        assertEquals(2, collection.countCoins());
        collection.removeCoin(coin2);
        assertEquals(1, collection.countCoins());
    }

    @Test
    public void testTotalNominalValue() {
        assertEquals(0, collection.totalNominalValue());
        collection.addCoin(coin1);
        collection.addCoin(coin2);
        collection.addCoin(coin3);
        assertEquals(350, collection.totalNominalValue());
        collection.removeCoin(coin2);
        assertEquals(300, collection.totalNominalValue());
    }

    @Test
    public void testHasCoin() {
        collection.addCoin(coin1);
        assertTrue(collection.hasCoin(coin1));
        assertFalse(collection.hasCoin(coin3));
    }

    @Test
    public void testAddCoinWithDifferentCountry() {
        EuroCoin coin4 = new EuroCoin(200, Country.FR, "Juan Carlos I", CoinColor.GOLD, 2002);
        collection.addCoin(coin1);
        assertTrue(collection.addCoin(coin4));
        assertEquals(2, collection.countCoins());
    }

    @Test
    public void testEmptyCollection() {
        assertEquals(0, collection.countCoins());
        assertEquals(0, collection.totalNominalValue());
        assertFalse(collection.hasCoin(coin1));
        assertFalse(collection.removeCoin(coin1));
    }

    @Test
    public void testAddDuplicateCoin() {
        collection.addCoin(coin1);
        EuroCoin duplicateCoin = new EuroCoin(200, Country.ES, "Juan Carlos I", CoinColor.GOLD, 2005);
        assertFalse(collection.addCoin(duplicateCoin)); // Moneda con el mismo valor, país y diseño, no debería añadirse
        assertEquals(1, collection.countCoins());
    }

    @Test
    public void testAddCoinWithNegativeValue() {
        EuroCoin invalidCoin = new EuroCoin(-50, Country.ES, "Felipe VI", CoinColor.GOLD, 2015);
        assertFalse(collection.addCoin(invalidCoin)); // Moneda con valor negativo, no debería añadirse
        assertEquals(0, collection.countCoins());
    }

    @Test
    public void testAddCoinWithInvalidYear() {
        EuroCoin invalidYearCoin = new EuroCoin(100, Country.IT, "Dante Alighieri", CoinColor.BRONZE, -1);
        assertTrue(collection.addCoin(invalidYearCoin)); // El año es irrelevante, debería añadirse
        assertEquals(1, collection.countCoins());
    }

    @Test
    public void testRemoveNonExistingCoin() {
        assertFalse(collection.removeCoin(coin3)); // Moneda no añadida, no debería poder eliminarse
    }

    @Test
    public void testAddCoinWithDifferentDesign() {
        EuroCoin coinWithDifferentDesign = new EuroCoin(200, Country.ES, "Felipe VI", CoinColor.GOLD, 2005);
        assertTrue(collection.addCoin(coinWithDifferentDesign)); // Diseño diferente, debería añadirse
        assertEquals(1, collection.countCoins());
    }

    @Test
    public void testAddCoinWithSameCountryDifferentValue() {
        EuroCoin coinWithSameCountryDifferentValue = new EuroCoin(100, Country.ES, "Felipe VI", CoinColor.GOLD_SILVER, 2005);
        assertTrue(collection.addCoin(coinWithSameCountryDifferentValue)); // Valor diferente, debería añadirse
        assertEquals(1, collection.countCoins());
    }

    @Test
    public void testRemoveCoinNotInCollection() {
        assertFalse(collection.removeCoin(coin2)); // Intentar eliminar una moneda que no está en la colección
    }

    @Test
    public void testAddCoinWithNullDesign() {
        EuroCoin nullDesignCoin = new EuroCoin(50, Country.DE, null, CoinColor.BRONZE, 2010);
        assertTrue(collection.addCoin(nullDesignCoin)); // Diseño null, pero debería permitirse la adición
        assertEquals(1, collection.countCoins());
    }

    @Test
    public void testAddCoinWithSameValueDifferentColor() {
        EuroCoin coinWithSameValueDifferentColor = new EuroCoin(50, Country.FR, "La Semeuse", CoinColor.GOLD, 2014);
        assertTrue(collection.addCoin(coinWithSameValueDifferentColor)); // Color diferente, debería añadirse
        assertEquals(1, collection.countCoins());
    }@Test
    public void testAddCoinWithInvalidCountry() {
        EuroCoin invalidCountryCoin = new EuroCoin(100, null, "Eagle", CoinColor.GOLD, 2005);
        assertFalse(collection.addCoin(invalidCountryCoin), "Expected addCoin to return false for invalid country");
    }

    @Test
    public void testAddCoinWithInvalidColor() {
        EuroCoin invalidColorCoin = new EuroCoin(50, Country.FR, "La Semeuse", null, 2010);
        assertFalse(collection.addCoin(invalidColorCoin), "Expected addCoin to return false for invalid color");
    }


}