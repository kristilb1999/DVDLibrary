package com.mthree.dvdlibrary.controller;

import com.mthree.dvdlibrary.dao.DVDLibraryDao;
import com.mthree.dvdlibrary.dao.DVDLibraryDaoException;
import com.mthree.dvdlibrary.dto.DVD;
import com.mthree.dvdlibrary.ui.DVDLibraryView;

import java.util.List;

public class DVDLibraryController {

    private DVDLibraryDao dao;
    private DVDLibraryView view;

    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
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

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
