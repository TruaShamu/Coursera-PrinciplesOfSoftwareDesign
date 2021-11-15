import java.util.ArrayList;
import java.util.Random;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int myNumber;

    public MarkovModel(int Number) {
        myRandom = new Random();
        myNumber = Number;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    // based on the training text file
    // return a array list of strings (a letter) that is right next to the key.
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int start = 0;
        while (start < myText.length()) {
            int Ind = myText.indexOf(key, start);
            if (Ind == -1 || Ind + key.length() > myText.length() - 1) {
                break;
            } else {
                String text = myText.substring(Ind + key.length(), Ind + key.length() + 1);
                follows.add(text);
                start = Ind + key.length();
            }
        }
        return follows;
    }

    // return a random text with a length of numChar
    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        // generate a random index
        int index = myRandom.nextInt(myText.length() - myNumber);
        String key = myText.substring(index, index + myNumber);
        sb.append(key);
        for (int k = 0; k < numChars - myNumber; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1, myNumber) + next;
        }
        return sb.toString();
    }
}
