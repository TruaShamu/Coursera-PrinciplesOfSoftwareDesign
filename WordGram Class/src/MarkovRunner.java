import edu.duke.FileResource;

public class MarkovRunner {


    public void runModel(IMarkovModel markov, String text, int size) {
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }


    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }


    public void runMarkovWord() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //MarkovWord markovWord = new MarkovWord(3);
        MarkovWord markovWord = new MarkovWord(0);
        //runModel(markovWord, st, 120, 643);
        //runModel(markovWord, st, 120, 621);
        //runModel(markovWord, st, 120, 844);
        runModel(markovWord, st, 120, 1024);
    }


    private void printOut(String s) {
        String[] words = s.split("\\s+"); // Split a string with any whitespace chars
        int psize = 0;
        System.out.println("----------------------------------");
        for (int k = 0; k < words.length; k++) {
            System.out.print(words[k] + " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }


    public void testHashMap() {
//        String st = "this is a test yes this is really a test";
//    	String st = "this is a test yes this is really a test yes a test this is wow";
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        EfficientMarkovWord efficientMarkovWord = new EfficientMarkovWord(2);
//        EfficientMarkovWord efficientMarkovWord = new EfficientMarkovWord(3);
//        runModel(efficientMarkovWord, st, 120, 42);
//        runModel(efficientMarkovWord, st, 50, 42);
        runModel(efficientMarkovWord, st, 50, 371);
        System.out.println("TRUTRUTRU");
        runModel(efficientMarkovWord, st, 50, 65);
    }

}
