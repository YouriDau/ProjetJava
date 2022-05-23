package test;

import model.BusinessTaskModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusinessTaskModelTest {
    //  Ici test unitaire peu utile ici car pas de calcul dans notre tâche métier suite au conseils du pro
    //  mais pour prouver que on sait les faire
    private BusinessTaskModel businessTaskModel;

    @BeforeEach
    void setUp() throws Exception {
        businessTaskModel = new BusinessTaskModel();
    }

    @Test
    public void wordingItem() throws Exception{
        businessTaskModel.setWordingItem("potatoes");
        assertEquals("potatoes", businessTaskModel.getWordingItem());
    }
}