package org.launchcode.bookworm;

import org.launchcode.bookworm.data.BookRepository;

import java.util.ArrayList;

public class BookData {

    /**
     * Returns the results of searching the Jobs data by field and search term.
     *
     * For example, searching for employer "Enterprise" will include results
     * with "Enterprise Holdings, Inc".
     *
     * @param searchTerm Value of the field to search for.
     * @param allBooks The list of books to search.
     * @return List of all books matching the criteria.
     */
    public static ArrayList<Book> findBySearchTerm(String searchTerm, Iterable<Book> allBooks) {

        ArrayList<Book> results = new ArrayList<>();

        if (searchTerm.toLowerCase().equals("all")){
            return (ArrayList<Book>) allBooks;
        }

        for (Book book : allBooks) {

            if(book.getTitle().toLowerCase().contains(searchTerm.toLowerCase())){
                results.add(book);
            } else if (book.getAuthor().toLowerCase().contains(searchTerm.toLowerCase())){
                results.add(book);
            }

        }

        return results;
    }

    public static String getFieldValue(Book book, String fieldName){
        String theValue = "";
        if (fieldName.equals("title")){
            theValue = book.getTitle();
        } else if (fieldName.equals("author")){
            theValue = book.getAuthor();
        }

        return theValue;
    }

    /**
     * Search all Book fields for the given term.
     *
     * @param value The search term to look for.
     * @param allBooks The list of books to search.
     * @return      List of all books with at least one field containing the value.
     */
    public static ArrayList<Book> findByValue(String value, Iterable<Book> allBooks) {
        String lower_val = value.toLowerCase();

        ArrayList<Book> results = new ArrayList<>();

        for (Book book : allBooks) {

            if (book.getTitle().toLowerCase().contains(lower_val)) {
                results.add(book);
            } else if (book.getAuthor().toLowerCase().contains(lower_val)) {
                results.add(book);
            } else if (book.toString().toLowerCase().contains(lower_val)) {
                results.add(book);
            }

        }

        return results;
    }

}
