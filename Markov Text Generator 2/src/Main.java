import edu.duke.FileResource;

public class Main {
    public static void main(String[] args) {
        //EfficientMarkovModel em = new EfficientMarkovModel(6);
        EfficientMarkovModel em = new EfficientMarkovModel(5);
        MarkovRunnerWithInterface mr = new MarkovRunnerWithInterface();
        FileResource fr = new FileResource();
        String st = fr.asString();


    }
}
