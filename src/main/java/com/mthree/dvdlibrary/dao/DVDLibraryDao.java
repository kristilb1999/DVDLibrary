package com.mthree.dvdlibrary.dao;

import com.mthree.dvdlibrary.dto.DVD;

import java.util.List;

public interface DVDLibraryDao {

    /**
     * Adds a DVD object to the library and associates it with the given title.
     * If there is already a DVD associated with that title, it will return that
     * DVD object. Otherwise, it will return null.
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
     * if there is no DVD associated with the given title.
     *
     * @param title The title of the DVD to be removed.
     * @return The DVD that was removed, null otherwise
     */
    DVD removeDVD(String title) throws DVDLibraryDaoException;

    /**
     * Edits the existing information of a DVD object.
     * Returns the DVD object that has been edited or null
     * if there is no DVD associated with the given title.
     *
     * @param title The title associated with the DVD to be edited
     * @return The DVD that was edited by the user, null otherwise.
     */
    DVD editDVD(String title, String fieldToChange, String newText) throws DVDLibraryDaoException;

    /**
     * Return a list of all DVDs in the library.
     *
     * @return List of all DVD objects in the library.
     */
    List<DVD> getAllDVDs() throws DVDLibraryDaoException;

    /**
     * Returns the DVD associated with the given title.
     * Returns null if that DVD does not exist.
     *
     * @param title The title of the DVD to return.
     * @return The DVD object associated with the given title,
     * null otherwise.
     */
    DVD getDVD(String title) throws DVDLibraryDaoException;

}
