package MovieReviewer;

import java.io.*;
import java.util.*;



public class main implements Serializable {

    public static void main(String[] args) throws IOException {

        // method to check if a serialized object exists in its working directory
        // if it exists then it should load its contents (movie reviews) in main memory
        // using hashmap.

        int choice;
        Scanner input = new Scanner(System.in);
        AbstractReviewHandler mv = new ReviewHandler();

        do {
            choice = -1;
            System.out.println("Please just one of the following options via their number. \n");
            System.out.println("0: Exit Program");
            System.out.println("1: Load new movie review collection");
            System.out.println("2: Delete movie review from database");
            System.out.println("3: Search movie review in database by ID or substring");

            choice = input.nextInt();

            if (choice < 0 || choice > 3) {
                System.out.println("Please choose a valid option from the list.");
            }
            switch (choice) {
                case 0://save database contents back to database file method
                    break;

                case 1:
                    System.out.println("Please type in a folder or file path.");
                    String folderOrFile = input.nextLine();
                    System.out.println("Please type the real class: neg 0, pos 1, unknown 2");
                    int realClass = input.nextInt();
                    mv.loadReviews(folderOrFile, realClass);
                    break;

                case 2:
                    // delete movie review from database given its id
                    System.out.println("What is the ID of the Movie Review you would like to delete?");
                    Integer id = input.nextInt();
                    // mcr.deleteReview(id);
                    break;

                case 3:
                    // search movie reviews in database by id or by matching a substring
                    System.out.println("Please type either 0 for ID or 1 for substring of the review you wish to search for.");
                    Integer idOrSub = input.nextInt();
                    do {
                        if (idOrSub == 0) {
                            System.out.println("Please enter the ID number of the review you want to search for");
                            id = input.nextInt();
                            // MovieReview found = mcr.searchById(id);

                        } else if (idOrSub == 1) {
                            System.out.println("Please enter a substring of the review you want to search for");
                            String substring = input.nextLine();

                        } else {
                            System.out.println("Please enter a valid choice.");
                            continue;
                        }
                    } while (idOrSub != 0 || idOrSub != 1);
                    break;
            }


        } while (choice < 0 || choice > 3);


    }
}