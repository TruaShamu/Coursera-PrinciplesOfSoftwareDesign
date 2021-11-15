import java.util.ArrayList;
import java.util.HashMap;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private HashMap<String, ArrayList<String>> myMap;

    public EfficientMarkovModel(int n) {
        super(n);
        myMap = new HashMap<String, ArrayList<String>>();
    }

    @Override
    public void setTraining(String s) {
        super.setTraining(s);
        buildMap();
        printHashMapInfo();
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length() - myOrder);
        String current = myText.substring(index, index + myOrder);
        sb.append(current);
        for (int k = 0; k < numChars - myOrder; k++) {
            ArrayList<String> follows = getFollows(current);
            if (follows == null) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            current = current.substring(1) + next;
        }
        return sb.toString();
    }

    private void buildMap() {
        for (int i = 0; i < myText.length() - (myOrder - 1); i++) {

            String current = myText.substring(i, i + myOrder);
//			System.out.println(current);
            String follow = "";
            if (i + myOrder < myText.length())
                follow = myText.substring(i + myOrder, i + myOrder + 1);

            if (myMap.containsKey(current)) {
                myMap.get(current).add(follow);
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(follow);
                myMap.put(current, list);
            }
        }

    }

    @Override
    public ArrayList<String> getFollows(String key) {
        return myMap.get(key);
    }

    public void printHashMapInfo() {
        System.out.printf("Map size:\t%d\nMax size:\t%d\n", mapSize(), longestFollowsSize());
//		for (String key : map.keySet()) {
//			System.out.printf("Key:\t[%s]\tvalues: ", key);
//			System.out.println(map.get(key));
//		}

    }

    public int mapSize() {
        return myMap.size();
    }

    public int longestFollowsSize() {
        int maxSize = 0;
        for (String key : myMap.keySet()) {
            maxSize = Math.max(maxSize, myMap.get(key).size());
        }

        return maxSize;
    }

    @Override
    public String toString() {
        return String.format("Efficient MarkovModel of order %d", myOrder);
    }

}
