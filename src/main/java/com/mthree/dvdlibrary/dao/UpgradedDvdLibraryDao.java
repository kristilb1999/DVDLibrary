package com.mthree.dvdlibrary.dao;

import com.mthree.dvdlibrary.dto.DVD;

import java.util.List;
import java.util.Map;

public interface UpgradedDvdLibraryDao {

    /**
     * Adds a DVD object to the library and associates it with the given title.
     * If there is already a DVD associated with that title, it will return that
     * DVD object. Otherwise, it will return null. (from original)
     *
     * @param title The title that the DVD will be associated with.
     * @param dvd The DVD to be added to the library
     * @return The DVD object previously associated with the title,
     * null otherwise.
     */
    DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException;

    /**
     * Removes the DVD associated with the given title from the library by title.
     * Returns the DVD object that is being removed or null
     * if there is no DVD associated with the given title. (from original)
     *
     * @param title The title of the DVD to be removed.
     * @return The DVD that was removed, null otherwise
     */
    DVD removeDVD(String title) throws DVDLibraryDaoException;

    /**
     * Edits the existing information of a DVD object.
     * Returns the DVD object that has been edited or null
     * if there is no DVD associated with the given title. (from original)
     *
     * @param title The title associated with the DVD to be edited
     * @return The DVD that was edited by the user, null otherwise.
     */
    DVD editDVD(String title, String fieldToChange, String newText) throws DVDLibraryDaoException;

    /**
     * Return a list of all DVDs in the library. (from original)
     *
     * @return List of all DVD objects in the library.
     */
    List<DVD> getAllDVDs() throws DVDLibraryDaoException;

    /**
     * Returns the DVD associated with the given title.
     * Returns null if that DVD does not exist. (from original)
     *
     * @param title The title of the DVD to return.
     * @return The DVD object associated with the given title,
     * null otherwise.
     */
    DVD getDVD(String title) throws DVDLibraryDaoException;

    /**
     * Returns all movies that were released in the last N years.
     *
     * @param numYears The number of years to search through.
     * @return A list of DVD objects from the last N years.
     */
    List<DVD> getDVDsInLastYears(int numYears) throws DVDLibraryDaoException;

    /**
     * Return all DVDs that have a given MPAA rating.
     *
     * @param rating The Rating to search for.
     * @return A list of DVD objects with a specified MPAA rating.
     */
    List<DVD> getDVDsWithRating(String rating) throws DVDLibraryDaoException;

    /**
     * Return all DVDs that have a given Director.
     *
     * @param director The name of the director to search for.
     * @return A list of DVD objects with a specified Director.
     */
    Map<String, List<DVD>> getDVDsByDirector(String director) throws DVDLibraryDaoException;

    /**
     * Return all DVDs that were created in a certain Studio.
     *
     * @param studio The name of the studio to search for.
     * @return A list of DVD objects created in a specified Studio.
     */
    List<DVD> getDVDsByStudio(String studio) throws DVDLibraryDaoException;

    /**
     * Finds the average age of DVDs in the DVD Library.
     *
     * @return The average age of all DVDs.
     */
    double getAverageAgeOfAll() throws DVDLibraryDaoException;

    /**
     * Finds the newest DVD in the DVD Library.
     *
     * @return The newest DVD in the collection.
     */
    DVD getNewestDVD() throws DVDLibraryDaoException;

    /**
     * Finds the oldest DVD in the DVD Library.
     *
     * @return The oldest DVD in the collection.
     */
    DVD getOldestDVD() throws DVDLibraryDaoException;

    /**
     * Finds the average number of notes associated with DVDs
     * in the DVD Library.
     *
     * @return Average number of notes in DVD Library.
     */
    double getAverageNumNotes() throws DVDLibraryDaoException;

}
