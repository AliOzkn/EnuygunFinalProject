package tests;

import io.qameta.allure.Story;
import org.testng.annotations.Test;
import requests.AssertionMethods;

import static org.testng.Assert.*;

public class AvailableParameter extends AssertionMethods {

    @Test(priority = 1)
    @Story("This method finds pets by status as 'available'. And puts them on a list in AvailableParameter test class.")
    public void findPetsByStatus() {

        requests.findByStatus("available");

        assertEquals(checkStatusCode(),200);
        assertTrue(checkPath("status").contains("available"));
    }

    @Test(priority = 2)
    @Story("This method finds pets by Id value in AvailableParameter test class.")
    public void findPetById() {

        requests.findById("available", 0);

        assertEquals(checkStatusCode(), 200);
        assertEquals(checkPath("id"),String.valueOf(requests.getId("available").get(0)));
    }

    @Test(priority = 3)
    @Story("This method updates pet name as 'Garry' and status as 'pending' in AvailableParameter test class.")
    public void updatePetById() {

        requests.updatePetById(4,"Garry","available","pending");

        assertEquals(checkPath("code"),"200");
        assertEquals(checkPath("type"),"unknown");

    }

    @Test( priority = 4)
    @Story("This method deletes pet with Id value in AvailableParameter test class.")
    public void deletePetById() {

        requests.deletePetById("available",0);

        assertEquals(checkPath("code"),"200");
        assertEquals(checkPath("type"),"unknown");


    }
}
