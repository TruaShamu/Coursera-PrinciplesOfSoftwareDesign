import edu.duke.FileResource;

import java.util.ArrayList;

public class Tester {

    public void testGetFollows() {
        MarkovOne one = new MarkovOne();
        one.setTraining("this is a test yes this is a test.");
        ArrayList<String> follows = one.getFollows("t");
        System.out.println(follows);
    }

    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne one = new MarkovOne();
        one.setRandom(42);
        one.setTraining(st);
        ArrayList<String> follows = one.getFollows("o");
        System.out.println(follows.size());
    }


}
