package tests;

import io.qameta.allure.Story;
import org.testng.annotations.Test;
import requests.AssertionMethods;

import static org.testng.Assert.*;

public class PendingParameter extends AssertionMethods {

    @Test(priority = 1)
    @Story("This method finds pets by status as 'pending'. And puts them on a list in PendingParameter test class.")
    public void findsPetsByStatus() {

        requests.findByStatus("pending");

        assertEquals(checkStatusCode(),200);
        assertTrue(checkPath("status").contains("pending"));
    }

    @Test( priority = 2)
    @Story("This method finds pets by Id value in PendingParameter test class.")
    public void findPetById() {

        requests.findById("pending", 0);

        assertEquals(checkStatusCode(), 200);
        assertEquals(checkPath("id"),String.valueOf(requests.getId("pending").get(0)));
    }

    @Test(priority =3)
    @Story("This method updates pet's name as 'Garry' and status as 'sold' in PendingParameter test class.")
    public void updatePetById() {

        requests.updatePetById(0,"Garry","pending","sold");

        assertEquals(checkPath("code"),"200");
        assertEquals(checkPath("type"),"unknown");
    }

    @Test(priority = 4)
    @Story("This method deletes pet with Id value in PendingParameter test class.")
    public void deletePetById() {

        requests.deletePetById("pending",0);

        assertEquals(checkPath("code"),"200");
        assertEquals(checkPath("type"),"unknown");
    }
}
