package com.mthree.dvdlibrary.controller;

import com.mthree.dvdlibrary.dao.DVDLibraryDao;
import com.mthree.dvdlibrary.dao.DVDLibraryDaoException;
import com.mthree.dvdlibrary.dao.UpgradedDvdLibraryDao;
import com.mthree.dvdlibrary.dto.DVD;
import com.mthree.dvdlibrary.ui.DVDLibraryView;

import java.util.List;
import java.util.Map;

public class DVDLibraryController {

//    private DVDLibraryDao dao;
    private UpgradedDvdLibraryDao dao;

    private DVDLibraryView view;

//    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
//        this.dao = dao;
//        this.view = view;
//    }

    public DVDLibraryController(UpgradedDvdLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        addDVDToLibrary();
                        break;
                    case 2:
                        removeDVDFromLibrary();
                        break;
                    case 3:
                        editDVDInLibrary();
                        break;
                    case 4:
                        listAllDVDs();
                        break;
                    case 5:
                        displayDVDInfo();
                        break;
                    case 6:
                        searchForDVD();
                        break;
                    case 7:
                        searchForRecentDVD();
                        break;
                    case 8:
                        searchForSpecificRating();
                        break;
                    case 9:
                        searchWithDirector();
                        break;
                    case 10:
                        searchByStudio();
                        break;
                    case 11:
                        getAverageAgeOfAllDVDs();
                        break;
                    case 12:
                        findNewestDVD();
                        break;
                    case 13:
                        findOldestDVD();
                        break;
                    case 14:
                        findAveNumNotes();
                        break;
                    case 15:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (DVDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addDVDToLibrary() throws DVDLibraryDaoException {
        view.displayAddDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayAddDVDSuccessBanner();

    }

    private void removeDVDFromLibrary() throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        String title = view.getTitleToRemove();
        DVD removedDVD = dao.removeDVD(title);
        view.displayRemovedResult(removedDVD);
    }

    private void editDVDInLibrary() throws DVDLibraryDaoException {
        view.displayEditDVDBanner();
        String title = view.getTitleToEdit();
        String fieldToChange = view.getFieldToEdit();
        String newInformation = view.getChangedInformation();
        DVD changedDVD = dao.editDVD(title, fieldToChange, newInformation);
        view.displayEditSuccessBanner(changedDVD);
    }

    private void listAllDVDs() throws DVDLibraryDaoException {
        view.displayListDVDBanner();
        List<DVD> dvdLibrary = dao.getAllDVDs();
        view.displayDVDLibrary(dvdLibrary);
    }

    private void displayDVDInfo() throws DVDLibraryDaoException {
        view.displayDisplayDVDBanner();
        String title = view.getTitleToDisplay();
        DVD dvdToDisplay = dao.getDVD(title);
        view.displayDVD(dvdToDisplay);
    }

    private void searchForDVD() throws DVDLibraryDaoException {
        view.displayDVDSearchBanner();
        String title = view.getTitleToSearch();
        DVD dvd = dao.getDVD(title);
        view.displaySearchResults(dvd);
    }

    private void searchForRecentDVD() throws DVDLibraryDaoException {
        view.displayDVDReleasedRecentlyBanner();
        int numYears = view.getNumberOfYearsToSearch();
        List<DVD> recentDVDs = dao.getDVDsInLastYears(numYears);
        view.displayDVDReleasedRecently(recentDVDs);
    }

    private void searchForSpecificRating() throws DVDLibraryDaoException {
        view.displayDVDSpecificRatingBanner();
        String rating = view.getRatingToSearch();
        List<DVD> dvdWithRating = dao.getDVDsWithRating(rating);
        view.displayDVDSpecificRating(dvdWithRating);
    }

    private void searchWithDirector() throws DVDLibraryDaoException {
        view.displayDVDByDirectorBanner();
        String director = view.getDirectorToSearch();
        Map<String, List<DVD>> dvdWithDirectorByRating = dao.getDVDsByDirector(director);
        view.displayDVDByDirector(dvdWithDirectorByRating);
    }

    private void searchByStudio() throws DVDLibraryDaoException {
        view.displayDVDStudioBanner();
        String studio = view.getStudioToSearch();
        List<DVD> dvdByStudio = dao.getDVDsByStudio(studio);
        view.displayDVDStudio(dvdByStudio);
    }

    private void getAverageAgeOfAllDVDs() throws DVDLibraryDaoException {
        view.displayAllDVDAverageAgeBanner();
        double aveAge = dao.getAverageAgeOfAll();
        view.displayAllDVDAverageAge(aveAge);
    }

    private void findNewestDVD() throws DVDLibraryDaoException {
        view.displayNewestDVDBanner();
        DVD newest = dao.getNewestDVD();
        view.displayNewestDVD(newest);
    }

    private void findOldestDVD() throws DVDLibraryDaoException {
        view.displayOldestDVDBanner();
        DVD oldest = dao.getOldestDVD();
        view.displayOldestDVD(oldest);
    }

    private void findAveNumNotes() throws DVDLibraryDaoException {
        view.displayAveNumNotesBanner();
        double average = dao.getAverageNumNotes();
        view.displayAveNumNotes(average);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
