package tests;

import io.qameta.allure.Story;
import org.testng.annotations.Test;
import requests.AssertionMethods;

import static org.testng.Assert.*;

public class SoldParameter extends AssertionMethods {



    @Test(priority = 1)
    @Story("This method finds pets by status as 'sold'. And puts them on a list in SoldParameter test class.")
    public void findsPetsByStatus() {
        requests.findByStatus("sold");

        assertEquals(checkStatusCode(),200);
        assertTrue(checkPath("status").contains("sold"));
    }

    @Test(priority = 2)
    @Story("This method finds pets by Id value in SoldParameter test class.")
    public void findPetById() {
        requests.findById("sold", 0);

        assertEquals(checkStatusCode(), 200);
        assertEquals(checkPath("id"),String.valueOf(requests.getId("sold").get(0)));
    }

    @Test(priority = 3)
    @Story("This method updates pet's name as 'Garry' and status as 'available' in SoldParameter test class.")
    public void updatePetById() {
        requests.updatePetById(0,"Garry","sold","available");

        assertEquals(checkPath("code"),"200");
        assertEquals(checkPath("type"),"unknown");
    }

    @Test(priority = 4)
    @Story("This method deletes pet with Id value in SoldParameter test class.")
    public void deletePetById() {
        requests.deletePetById("sold",0);

        assertEquals(checkPath("code"),"200");
        assertEquals(checkPath("type"),"unknown");
    }
}
