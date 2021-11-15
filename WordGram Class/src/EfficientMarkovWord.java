import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> map;

    public EfficientMarkovWord(int n) {
        myRandom = new Random();
        myOrder = n;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }


    public void setTraining(String text) {
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key.toString());
        sb.append(" ");
        for (int k = 0; k < numWords - myOrder; k++) {
//			System.out.print("Key \"" + key + "\" ");
            ArrayList<String> follows = getFollows(key);
//		    System.out.println("is followed by: " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }

        return sb.toString().trim();
    }

    private int indexOf(String[] words, WordGram target, int start) {
        for (int k = start; k < words.length - myOrder; k++) {
            WordGram wg = new WordGram(words, k, myOrder);
            if (wg.equals(target)) {
                return k;
            }
        }
        return -1;
    }

    public ArrayList<String> getFollows(WordGram kGram) {
        return map.get(kGram);
    }

    public void buildMap() {
        map = new HashMap<>();
        for (int k = 0; k < myText.length - myOrder; k++) {
            WordGram kGram = new WordGram(myText, k, myOrder);
            String follow = myText[k + kGram.length()];
            if (map.containsKey(kGram)) {
                map.get(kGram).add(follow);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(follow);
                map.put(kGram, list);
            }
        }
        WordGram kGram = new WordGram(myText, myText.length - myOrder, myOrder);
        if (!(map.containsKey(kGram))) {
            ArrayList<String> list = new ArrayList<>();
            map.put(kGram, list);
        }
    }

    public void printMap() {
        buildMap();
        System.out.println("the total key number is: " + map.size());
        for (WordGram s : map.keySet()) {
            System.out.println(s);
            System.out.println(map.get(s));

        }


    }

    public void printHashMapInfo() {

        System.out.println("It has " + map.size() + " keys in the HashMap");
        int maxSize = 0;
        for (WordGram wg : map.keySet()) {
            maxSize = Math.max(maxSize, map.get(wg).size());
//			System.out.printf("Key:\t[%s]\tvalues: ", wg);
//			System.out.println(myMap.get(wg));
        }
        System.out.println("The maximum number of elements following a key is " + maxSize);

        System.out.println("Keys with the maximum size value: ");
        for (WordGram wg : map.keySet()) {
            if (map.get(wg).size() == maxSize) {
                System.out.print(wg);
                System.out.println(" (The follow words: " + map.get(wg) + ")");
            }
        }

    }
}
