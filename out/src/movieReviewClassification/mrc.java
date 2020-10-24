package movieReviewClassification;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;


class MovieReviewer extends AbstractReviewHandler implements Serializable {


     /**
     * Loads reviews from a given path. If the given path is a .txt file, then
     * a single review is loaded. Otherwise, if the path is a folder, all reviews
     * in it are loaded.
     *
     * @param filePath  The path to the file (or folder) containing the review(sentimentModel).
     * @param realClass The real class of the review (0 = Negative, 1 = Positive
     *                  2 = Unknown).
     * @return A list of reviews as objects.
     */


    @Override
    public void loadReviews(String filePath, int realClass) {
        boolean txt = filePath.contains(".txt");
        // Suppose case 1, the filePath is a file...
        try {
            FileInputStream fileStream = new FileInputStream(filePath);
            ObjectInputStream os = new ObjectInputStream(fileStream);
            List movieReviewList = new ArrayList<Object>();
            while (os.readBoolean()){
                 Object mr = os.readObject();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Reads a single review file and returns it as a MovieReview object.
     * This method also calls the method classifyReview to predict the polarity
     * of the review.
     *
     * @param reviewFilePath A path to a .txt file containing a review.
     * @param realClass      The real class entered by the user.
     * @return a MovieReview object.
     * @throws IOException if specified file cannot be openned.
     */
    @Override
    public MovieReview readReview(String reviewFilePath, int realClass) throws IOException {
        MovieReview mr = null;
        try {
            FileInputStream file = new FileInputStream(reviewFilePath);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                int id = UUID.randomUUID().clockSequence();
                String reviewNoPolar = reader.nextLine();
                mr = new MovieReview(reviewNoPolar, realClass);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mr;
    }

    /**
     * Deletes a review from the database, given its id.
     *
     * @param id The id value of the review.
     */
    public void deleteReview(int id) {

    }

    /**
     * Loads review database.
     */
    public void loadSerialDB() {

    }

    /**
     * Searches the review database by id.
     *
     * @param id The id to search for.
     * @return The review that matches the given id or null if the id does not
     * exist in the database.
     */
    public MovieReview searchById(int id) {
        Boolean check = this.database.containsKey(id);
        if (check) {
            return this.database.get(id);
        } else {
            System.out.println("The id was not found.");
            return null;
        }

    }

    /**
     * Searches the review database for reviews matching a given substring.
     *
     * @param substring The substring to search for.
     * @return A list of review objects matching the search criterion.
     */
    public List<MovieReview> searchBySubstring(String substring) {


    }
}
