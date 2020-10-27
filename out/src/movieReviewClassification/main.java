package movieReviewClassification;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * CS3354 Fall 2020 main to run ReviewHandler
 *
 * @author garza
 */

public class main implements Serializable {

    public static void main(String[] args) {
        int choice;
        AbstractReviewHandler ReviewHandler = new ReviewHandler();
        ReviewHandler.loadSerialDB();
        do {
            int databaseSize = ReviewHandler.database.size();
            System.out.println("\n---- " + databaseSize + " Movie Reviews have been loaded. ----");
            Scanner input = new Scanner(System.in);
            choice = -1;
            System.out.println("\nPlease choose one of the following options via their number. \n");
            System.out.println("0: Exit Program");
            System.out.println("1: Load new movie review collection");
            System.out.println("2: Delete movie review from database");
            System.out.println("3: Search movie review in database by ID or substring");

            try {
                choice = input.nextInt();
                if (choice < 0 || choice > 3) {
                    System.out.println("Please choose a valid option from the list. \n");
                    continue;
                }
            }catch (InputMismatchException e){
                System.out.println("Please choose a valid option from the list. \n");
                continue;
            }


            switch (choice) {
                case 0://save database contents back to database file method
                    if (!ReviewHandler.database.isEmpty()) {
                        ReviewHandler.saveSerialDB();
                    }
                    System.exit(0);

                case 1:
                    Scanner reader = new Scanner(System.in);
                    System.out.println("Please type in a folder or file path.");
                    String folderOrFile = reader.nextLine();
                    System.out.println("Please type in the real class: neg 0, pos 1, unknown 2");

                    try {
                        int realClass = input.nextInt();
                        if (realClass < 0 || realClass > 3) {
                            System.out.println("ERROR, Input must be either 0, 1, or 2");
                            break;
                        }
                        try {
                            ReviewHandler.loadReviews(folderOrFile, realClass);
                        } catch (NullPointerException n) {
                            System.out.println("ERROR, The file or folder was not found.");
                            continue;
                        }
                    }catch (InputMismatchException e){
                        System.out.println("ERROR, Please follow the prompts.");
                        continue;
                    }
                    ReviewHandler.saveSerialDB();
                    int match = 0;
                    int mismatch = 0;


                    for (Map.Entry<Integer, MovieReview> integerMovieReviewEntry : ReviewHandler.database.entrySet()) {
                        MovieReview movieReview = (MovieReview) ((Map.Entry) integerMovieReviewEntry).getValue();
                        if (Objects.equals(movieReview.getRealClass(), movieReview.getPolarity())) {
                            match++;
                        } else {
                            mismatch++;
                        }
                    }

                    int total = match + mismatch;
                    double overallAcc = ((double) match / (double) total) * 100;
                    DecimalFormat format = new DecimalFormat("#.00");

                    System.out.println("\nOverall Classification Accuracy: " + format.format(overallAcc) + "%\n");

                    break;


                case 2:
                    // delete movie review from database given its id
                    System.out.println("\nWhat is the ID of the Movie Review you would like to delete?");
                    try {
                        int id = input.nextInt();
                        ReviewHandler.deleteReview(id);
                    }catch(InputMismatchException e) {
                        System.out.println("ERROR: Please enter a valid ID.");
                    }
                    break;

                case 3:
                    // search movie reviews in database by id or by matching a substring
                    System.out.println("\n0: Search by ID \n1: Search by substring");
                    try {
                        int idOrSub = input.nextInt();
                        if (idOrSub == 0) {
                            System.out.println("Please enter the ID number of the review you want to search for.");
                            System.out.println("\nThe ID is based on the file name of the text file\n " +
                                    "EX:'0_3.txt' implies the ID is 3.\n");
                            int id = input.nextInt();
                            try {
                                MovieReview foundMovieReview = ReviewHandler.searchById(id);
                                foundMovieReview.displayMovieReview();
                            } catch (NullPointerException n) {
                                System.out.println("\nSorry that review wasn't found, please load it into the database and try again.\n");
                                continue;
                            }

                        } else if (idOrSub == 1) {
                            System.out.println("\nPlease enter a substring of the review you want to search for.");
                            Scanner read = new Scanner(System.in);
                            String substring = read.nextLine();
                            List<MovieReview> movieReviews;
                            movieReviews = ReviewHandler.searchBySubstring(substring);

                            match = 0;
                            mismatch = 0;


                            for (MovieReview mR : movieReviews) {
                                mR.displayMovieReview();
                                if (Objects.equals(mR.getPolarity(), mR.getRealClass())) {
                                    match++;
                                } else {
                                    mismatch++;
                                }
                            }

                            total = match + mismatch;
                            if (total == 0) {
                                System.out.println("No matches were found with that substring.");
                            } else {
                                overallAcc = ((double) match / (double) total) * 100;
                                format = new DecimalFormat("#.00");
                                System.out.println("\nOverall Classification Accuracy of the set of searched Reviews: " + format.format(overallAcc) + "%\n");
                            }

                        } else {
                            System.out.println("Please enter a valid choice. \n");
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid choice. \n");
                        break;
                    }
                    break;
            }
        } while (choice != -1);
    }
}



