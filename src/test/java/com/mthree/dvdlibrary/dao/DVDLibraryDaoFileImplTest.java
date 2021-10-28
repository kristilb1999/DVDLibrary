package com.mthree.dvdlibrary.dao;

import com.mthree.dvdlibrary.dto.DVD;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DVDLibraryDaoFileImplTest {

    DVDLibraryDao testDao;

    public DVDLibraryDaoFileImplTest() {

    }

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws Exception {
        String testFile = "testDVDLibrary.txt";

        new FileWriter(testFile);
        testDao = new DVDLibraryDaoFileImpl(testFile);
    }

    @org.junit.jupiter.api.Test
    void testAddGetDVD() throws Exception {
        //create test input
        String title = "Halloweentown";
        DVD dvd = new DVD(title);
        dvd.setReleaseDate("1998");
        dvd.setRating("Not Rated");
        dvd.setDirectorName("Duwayne Dunham");
        dvd.setStudio("Disney");
        dvd.setUserNote("Classic Halloween movie for kids.");

        //add dvd to test dao
        testDao.addDVD(title, dvd);

        //get dvd from dao
        DVD retrievedDVD = testDao.getDVD(title);

        //check that data is correct
        assertEquals(dvd.getTitle(), retrievedDVD.getTitle(), "Checking dvd title.");
        assertEquals(dvd.getReleaseDate(), retrievedDVD.getReleaseDate(), "Checking release date.");
        assertEquals(dvd.getRating(), retrievedDVD.getRating(), "Checking dvd rating.");
        assertEquals(dvd.getDirectorName(), retrievedDVD.getDirectorName(), "Checking dvd director.");
        assertEquals(dvd.getStudio(), retrievedDVD.getStudio(), "Checking dvd studio.");
        assertEquals(dvd.getUserNote(), retrievedDVD.getUserNote(), "Checking user notes.");
    }

    @org.junit.jupiter.api.Test
    void testAddGetAllDVDs() throws Exception {
        //create first dvd
        String firstTitle = "Halloweentown";
        DVD firstDvd = new DVD(firstTitle);
        firstDvd.setReleaseDate("1998");
        firstDvd.setRating("Not Rated");
        firstDvd.setDirectorName("Duwayne Dunham");
        firstDvd.setStudio("Disney");
        firstDvd.setUserNote("Classic Halloween movie for kids.");

        //create second dvd
        String secondTitle = "A Nightmare on Elm Street";
        DVD secondDvd = new DVD(secondTitle);
        secondDvd.setReleaseDate("1984");
        secondDvd.setRating("R");
        secondDvd.setDirectorName("Wes Craven");
        secondDvd.setStudio("New Line Cinema");
        secondDvd.setUserNote("How scary, just in time for Halloween.");

        //add both to dao
        testDao.addDVD(firstTitle, firstDvd);
        testDao.addDVD(secondTitle, secondDvd);

        //retrieve list of all dvds in dao
        List<DVD> allDVDs = testDao.getAllDVDs();

        //first check general contents of list
        assertNotNull(allDVDs, "The list of DVDs must not be null.");
        assertEquals(2, allDVDs.size(), "List of DVDs should have 2 DVDs.");

        //then check the specifics
        assertTrue(testDao.getAllDVDs().contains(firstDvd), "The list of students should include Halloweentown.");
        assertTrue(testDao.getAllDVDs().contains(secondDvd), "The list of students should include A Nightmare on Elm Street");
    }

    @org.junit.jupiter.api.Test
    void testRemoveDVDs() throws Exception {
        //create two new DVDs
        String firstTitle = "Halloweentown";
        DVD firstDvd = new DVD(firstTitle);
        firstDvd.setReleaseDate("1998");
        firstDvd.setRating("Not Rated");
        firstDvd.setDirectorName("Duwayne Dunham");
        firstDvd.setStudio("Disney");
        firstDvd.setUserNote("Classic Halloween movie for kids.");

        String secondTitle = "A Nightmare on Elm Street";
        DVD secondDvd = new DVD(secondTitle);
        secondDvd.setReleaseDate("1984");
        secondDvd.setRating("R");
        secondDvd.setDirectorName("Wes Craven");
        secondDvd.setStudio("New Line Cinema");
        secondDvd.setUserNote("How scary, just in time for Halloween.");

        //add both to dao
        testDao.addDVD(firstTitle, firstDvd);
        testDao.addDVD(secondTitle, secondDvd);

        //remove first movie - Halloweentown
        DVD removedDVD = testDao.removeDVD(firstTitle);

        //check that correct object was removed
        assertEquals(removedDVD, firstDvd, "The removed DVD should be Halloweentown.");

        //get all dvds
        List<DVD> allDVDs = testDao.getAllDVDs();

        //first check general contents of list
        assertNotNull(allDVDs, "All dvds list must not be null");
        assertEquals(1, allDVDs.size(), "All dvds should only have 1 dvd.");

        //then check specifics
        assertFalse(allDVDs.contains(firstDvd), "All dvds should NOT include Halloweentown.");
        assertTrue(allDVDs.contains(secondDvd), "All dvds should include A Nightmare on Elm Street.");

        //remove second dvd
        removedDVD = testDao.removeDVD(secondTitle);

        //check that correct dvd was removed
        assertEquals(removedDVD, secondDvd, "The removed dvd should be A Nightmare on Elm Street.");

        //retrieve all dvds again to check list
        allDVDs = testDao.getAllDVDs();

        //check contents of list - should be empty
        assertTrue(allDVDs.isEmpty(), "The retrieved list of dvds should be empty.");

        //try to get both dvds by their title - they should be null
        DVD retrievedDVD = testDao.getDVD(firstTitle);
        assertNull(retrievedDVD, "Halloweentown was removed, should be null");

        retrievedDVD = testDao.getDVD(secondTitle);
        assertNull(retrievedDVD, "A Nightmare on Elm Street was removed, should be null.");
    }

    @org.junit.jupiter.api.Test
    void testEditDVD() throws Exception {
        //create and add dvd to dao
        String firstTitle = "Halloweentown";
        DVD firstDvd = new DVD(firstTitle);
        firstDvd.setReleaseDate("1998");
        firstDvd.setRating("Not Rated");
        firstDvd.setDirectorName("Duwayne Dunham");
        firstDvd.setStudio("Disney");
        firstDvd.setUserNote("Classic Halloween movie for kids.");

        testDao.addDVD(firstTitle, firstDvd);

        //change the dvd title
        String fieldToChange = "title";
        String newInformation = "Test";

        //edit the title of a dvd
        DVD editedDVD = testDao.editDVD(firstDvd.getTitle(), fieldToChange, newInformation);

        //check to make sure title was changed
        assertNotNull(editedDVD, "The edited DVD should have been returned.");
        assertEquals(newInformation, editedDVD.getTitle(), "The new title should now be Test.");


        //change the dvd release date
        fieldToChange = "release date";
        newInformation = "test";

        //edit the release date of the dvd
        editedDVD = testDao.editDVD(editedDVD.getTitle(), fieldToChange, newInformation);

        //check to make sure the release date was changed
        assertNotNull(editedDVD, "The edited DVD should have been returned.");
        assertEquals(newInformation, editedDVD.getReleaseDate(), "The new release date should be test.");


        //change the dvd rating
        fieldToChange = "rating";
        newInformation = "test";

        //edit the rating of the dvd
        editedDVD = testDao.editDVD(editedDVD.getTitle(), fieldToChange, newInformation);

        //check to make sure the rating was changed
        assertNotNull(editedDVD, "The edited DVD should have been returned.");
        assertEquals(newInformation, editedDVD.getRating(), "The new rating should be test.");


        //change the dvd director name
        fieldToChange = "director name";
        newInformation = "test";

        //edit the director name of the dvd
        editedDVD = testDao.editDVD(editedDVD.getTitle(), fieldToChange, newInformation);

        //check to make sure the director name was changed
        assertNotNull(editedDVD, "The edited DVD should have been returned.");
        assertEquals(newInformation, editedDVD.getDirectorName(), "The new director name should be test.");


        //change the dvd studio
        fieldToChange = "studio";
        newInformation = "test";

        //edit the studio of the dvd
        editedDVD = testDao.editDVD(editedDVD.getTitle(), fieldToChange, newInformation);

        //check to make sure the studio was changed
        assertNotNull(editedDVD, "The edited DVD should have been returned.");
        assertEquals(newInformation, editedDVD.getStudio(), "The new studio should be test,");


        //change the dvd user note
        fieldToChange = "user note";
        newInformation = "test";

        //edit the user note of the dvd
        editedDVD = testDao.editDVD(editedDVD.getTitle(), fieldToChange, newInformation);

        //check to make sure the user note was changed
        assertNotNull(editedDVD, "The edited DVD should have been returned.");
        assertEquals(newInformation, editedDVD.getUserNote(), "The user note should now be test.");
    }
}