package com.mthree.dvdlibrary.ui;

import com.mthree.dvdlibrary.dto.DVD;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DVDLibraryView {

    private UserIO io;

    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu: ");
        io.print("\t1. Add DVD to library");
        io.print("\t2. Remove DVD from library");
        io.print("\t3. Edit DVD within library");
        io.print("\t4. List all DVDs in library");
        io.print("\t5. Display information of a DVD");
        io.print("\t6. Search for DVD by title");
        io.print("\t7. Exit Program");

        return io.readInt("Please select the operation you wish to perform: ", 1, 7);
    }

    public void displayAddDVDBanner() {
        io.print("\n===== Add DVD =====\n");
    }

    public DVD getNewDVDInfo() {
        String title = io.readString("Please enter DVD title: ");
        String releaseDate = io.readString("Please enter DVD release date: ");
        String rating = io.readString("Please enter DVD MPAA rating: ");
        String directorName = io.readString("Please enter the Director's name: ");
        String studio = io.readString("Please enter the studio that created this DVD: ");
        String userNotes = io.readString("Please enter any extra user notes: ");

        DVD newDVD = new DVD(title);

        newDVD.setReleaseDate("1998");
        newDVD.setRating(rating);
        newDVD.setDirectorName(directorName);
        newDVD.setStudio(studio);
        newDVD.setUserNote(userNotes);

        return newDVD;

    }

    public void displayAddDVDSuccessBanner() {
        io.readString("Added successfully. Press 1 to return to Main Menu.");
    }

    public void displayRemoveDVDBanner() {
        io.print("\n===== Remove DVD =====\n");
    }

    public String getTitleToRemove() {
        return io.readString("Please enter title of DVD to remove from library: ");
    }

    public void displayRemovedResult(DVD removedDVD) {
        if(removedDVD != null) {
            io.print("DVD removed successfully.");
        } else {
            io.print("No DVD by that title was found in the library.");
        }
        io.readString("Press 1 to return to Main Menu.");
    }

    public void displayEditDVDBanner() {
        io.print("\n===== Edit DVD =====\n");
    }

    public String getTitleToEdit() {
        return io.readString("Please enter the title of the DVD you wish to edit: ");
    }

    public String getFieldToEdit() {
        io.print("title, release date, rating, director name, studio, user note");
        return io.readString("Which field would you like to edit? ");
    }

    public String getChangedInformation() {
        return io.readString("Enter new text for this field: ");
    }

    public void displayEditSuccessBanner(DVD changedDVD) {
        if(changedDVD != null) {
            io.print("DVD edited successfully.\n");
            io.print(changedDVD.toString());
        } else {
            io.print("No DVD by that title was found in the library.");
        }
        io.readString("Press 1 to return to Main Menu.");
    }

    public void displayListDVDBanner() {
        io.print("\n===== List DVD Library =====\n");
    }

    public void displayDVDLibrary(List<DVD> dvdLibrary) {
        for(DVD dvd : dvdLibrary) {
            String dvdInfo = dvd.toString();
            io.print(dvdInfo);
        }
        io.readString("Press 1 to return to Main Menu.");
    }

    public void displayDisplayDVDBanner() {
        io.print("\n===== Display DVD =====\n");
    }

    public String getTitleToDisplay() {
        return io.readString("Please enter the title of the DVD you wish to display: ");
    }

    public void displayDVD(DVD dvdToDisplay) {
        if(dvdToDisplay != null) {
            io.print(dvdToDisplay.toString());
        } else {
            io.print("No DVD by that title was found in the library.");
        }
        io.readString("Press 1 to return to Main Menu.");
    }

    public void displayDVDSearchBanner() {
        io.print("\n===== Search for DVD =====\n");
    }

    public String getTitleToSearch() {
        return io.readString("Please enter the title of the DVD you wish to search the library for: ");
    }

    public void displaySearchResults(DVD searchedDVD) {
        if(searchedDVD != null) {
            io.print("DVD was found in library.");
        } else {
            io.print("No DVD by that title was found in the library.");
        }
        io.readString("Press 1 to return to Main Menu.");
    }

    public void displayExitBanner() {
        io.print("\n===== Good Bye! =====");
    }

    public void displayUnknownCommandBanner() {
        io.print("\n===== Unknown Command! =====\n");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("\n===== ERROR =====");
        io.print(errorMsg);
    }

}
