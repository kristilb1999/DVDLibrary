package com.mthree.dvdlibrary.dao;

import com.mthree.dvdlibrary.dto.DVD;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

//@Component
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    public final String DVD_LIBRARY_FILE;
    public static final String DELIMITER = "::";

    private Map<String, DVD> dvdLibrary = new HashMap<>();

    public DVDLibraryDaoFileImpl() {
        DVD_LIBRARY_FILE = "C:\\Users\\Kristi\\IdeaProjects\\DVDLibrary\\src\\main\\java\\com\\mthree\\dvdlibrary\\dvdLibrary.txt";
    }

    public DVDLibraryDaoFileImpl(String libraryTextFile) {
        DVD_LIBRARY_FILE = libraryTextFile;
    }

    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        loadDVDLibrary();
        DVD newDVD = dvdLibrary.put(title, dvd);
        writeDVDLibrary();
        return newDVD;
    }

    public DVD removeDVD(String title) throws DVDLibraryDaoException {
        loadDVDLibrary();
        DVD removedDVD = dvdLibrary.remove(title);
        writeDVDLibrary();
        return removedDVD;
    }

    public DVD editDVD(String title, String fieldToChange, String newText) throws DVDLibraryDaoException {
        loadDVDLibrary();

        DVD editedDVD = dvdLibrary.remove(title);

        if(editedDVD != null) {
            if(fieldToChange.equalsIgnoreCase("title")) {
                editedDVD.setTitle(newText);
            } else if (fieldToChange.equalsIgnoreCase("release date")) {
                editedDVD.setReleaseDate(newText);
            } else if (fieldToChange.equalsIgnoreCase("rating")) {
                editedDVD.setRating(newText);
            } else if (fieldToChange.equalsIgnoreCase("director name")) {
                editedDVD.setDirectorName(newText);
            } else if (fieldToChange.equalsIgnoreCase("studio")) {
                editedDVD.setStudio(newText);
            } else if (fieldToChange.equalsIgnoreCase("user note")) {
                editedDVD.setUserNote(newText);
            }

            dvdLibrary.put(editedDVD.getTitle(), editedDVD);
        }

        writeDVDLibrary();

        return editedDVD;
    }

    public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
        loadDVDLibrary();
        return new ArrayList<>(dvdLibrary.values());
    }

    public DVD getDVD(String title) throws DVDLibraryDaoException {
        loadDVDLibrary();
        return dvdLibrary.get(title);
    }

    //TODO: Add a search functionality that will return all DVD with titles with searched word included
    //TODO:This will also be added to interface


    private void loadDVDLibrary() throws DVDLibraryDaoException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVD_LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException("-_- Could not load DVD library data.", e);
        }

        String currentLine;
        DVD currentDVD;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDVD = unmarshallDVD(currentLine);

            dvdLibrary.put(currentDVD.getTitle(), currentDVD);
        }
        scanner.close();
    }

    private DVD unmarshallDVD(String dvdAsText) {
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        //index 0 holds the title
        String title = dvdTokens[0];

        //create a new DVD object with title
        DVD dvdFromFile = new DVD(title);

        //add the rest of the info into the object in correct order:
        //[1]:release date, [2]: rating, [3]: director name, [4]: studio, [5]: extra notes
        dvdFromFile.setReleaseDate(dvdTokens[1]);
        dvdFromFile.setRating(dvdTokens[2]);
        dvdFromFile.setDirectorName(dvdTokens[3]);
        dvdFromFile.setStudio(dvdTokens[4]);
        dvdFromFile.setUserNote(dvdTokens[5]);

        return dvdFromFile;
    }

    private void writeDVDLibrary() throws DVDLibraryDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_LIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException("Could not save dvd library data.", e);
        }

        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDs();
        for(DVD currentDVD : dvdList) {
            dvdAsText = marshallDVD(currentDVD);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }

    private String marshallDVD(DVD aDVD) {

        String dvdAsText = aDVD.getTitle() + DELIMITER;
        dvdAsText += aDVD.getReleaseDate() + DELIMITER;
        dvdAsText += aDVD.getRating() + DELIMITER;
        dvdAsText += aDVD.getDirectorName() + DELIMITER;
        dvdAsText += aDVD.getStudio() + DELIMITER;
        dvdAsText += aDVD.getUserNote();

        return dvdAsText;

    }
}
