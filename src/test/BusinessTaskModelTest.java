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
    void setUp(){
        businessTaskModel = new BusinessTaskModel();
    }

    @Test
    public void wordingItem(){
        businessTaskModel.setWordingItem("potatoes");
        assertEquals("potatoes", businessTaskModel.getWordingItem());
    }
}