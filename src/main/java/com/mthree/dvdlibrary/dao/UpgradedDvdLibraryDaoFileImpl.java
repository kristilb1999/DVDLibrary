package com.mthree.dvdlibrary.dao;

import com.mthree.dvdlibrary.dto.DVD;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class UpgradedDvdLibraryDaoFileImpl implements UpgradedDvdLibraryDao {

    public final String DVD_LIBRARY_FILE;
    public static final String DELIMITER = "::";

    private Map<String, DVD> dvdLibrary = new HashMap<>();

    public UpgradedDvdLibraryDaoFileImpl() {
        DVD_LIBRARY_FILE = "C:\\Users\\Kristi\\IdeaProjects\\DVDLibrary\\src\\main\\java\\com\\mthree\\dvdlibrary\\dvdLibrary.txt";
    }

    public UpgradedDvdLibraryDaoFileImpl(String libraryTextFile) {
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

        if (editedDVD != null) {
            if (fieldToChange.equalsIgnoreCase("title")) {
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

    public List<DVD> getDVDsInLastYears(int numYears) throws DVDLibraryDaoException {
        loadDVDLibrary();

        int yearRange = LocalDate.now().getYear() - numYears;

        List<DVD> dvdsInLastYears = dvdLibrary.values().stream()
                .filter((d) -> Integer.parseInt(d.getReleaseDate()) >= yearRange)
                .collect(Collectors.toList());

        return dvdsInLastYears;
    }

    public List<DVD> getDVDsWithRating(String rating) throws DVDLibraryDaoException {
        loadDVDLibrary();

        List<DVD> dvdsWithRating = dvdLibrary.values().stream()
                .filter((d) -> d.getRating().equalsIgnoreCase(rating))
                .collect(Collectors.toList());

        return dvdsWithRating;
    }

    public Map<String, List<DVD>> getDVDsByDirector(String director) throws DVDLibraryDaoException {
        loadDVDLibrary();

        Map<String, List<DVD>> dvdWithDirectorAndRating = dvdLibrary.values().stream()
                .filter((d) -> d.getDirectorName().equalsIgnoreCase(director))
                .collect(Collectors.groupingBy((d) -> d.getRating()));

        return dvdWithDirectorAndRating;
    }

    public List<DVD> getDVDsByStudio(String studio) throws DVDLibraryDaoException {
        loadDVDLibrary();

        List<DVD> dvdByStudio = dvdLibrary.values().stream()
                .filter((d) -> d.getStudio().equalsIgnoreCase(studio))
                .collect(Collectors.toList());

        return dvdByStudio;
    }

    //TODO: All methods below here until loadDVDLibrary
    public double getAverageAgeOfAll() throws DVDLibraryDaoException {
        loadDVDLibrary();

        double averageAge = dvdLibrary.values().stream()
                .mapToInt((d) -> LocalDate.now().getYear() - Integer.parseInt(d.getReleaseDate()))
                .average().getAsDouble();

        return averageAge;
    }

    public DVD getNewestDVD() throws DVDLibraryDaoException {
        loadDVDLibrary();

        Map<Integer, List<DVD>> dvdByReleaseDate = dvdLibrary.values().stream()
                .collect(Collectors.groupingBy((d) -> LocalDate.now().getYear() -Integer.parseInt(d.getReleaseDate())));

        Set<Integer> dvdAgeSet = dvdByReleaseDate.keySet();
        Integer[] dvdAge = dvdAgeSet.toArray(new Integer[dvdAgeSet.size()]);

        Integer currentAge = dvdAge[0];

        for(Integer age : dvdAge) {
            if(age < currentAge) {
                currentAge = age;
            }
        }

        DVD toReturn = dvdByReleaseDate.get(currentAge).get(0);

        return toReturn;
    }

    public DVD getOldestDVD() throws DVDLibraryDaoException {
        loadDVDLibrary();

        Map<Integer, List<DVD>> dvdByReleaseDate = dvdLibrary.values().stream()
                .collect(Collectors.groupingBy((d) -> LocalDate.now().getYear() -Integer.parseInt(d.getReleaseDate())));

        Set<Integer> dvdAgeSet = dvdByReleaseDate.keySet();
        Integer[] dvdAge = dvdAgeSet.toArray(new Integer[dvdAgeSet.size()]);

        Integer currentAge = dvdAge[0];

        for(Integer age : dvdAge) {
            if(age > currentAge) {
                currentAge = age;
            }
        }

        DVD toReturn = dvdByReleaseDate.get(currentAge).get(0);

        return toReturn;
    }

    public double getAverageNumNotes() throws DVDLibraryDaoException {
        return 1.0;
    }

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
        for (DVD currentDVD : dvdList) {
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
