package movieReviewClassification;

import java.io.IOException;

class MovieReview {
    String txt;
    int polarity;

    MovieReview(String input, Integer polar)throws IOException {
        txt = input;
        polarity = polar;
    }

    public String getText(){
        return this.txt;
    }

    public void setPredictedPolarity(int polar) {
        this.polarity = polar;
    }
}
