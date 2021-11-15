public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt " + index);
        }
        return myWords[index];
    }

    public int length() {
        return myWords.length;
    }

    // println automatically calls toString() even if not specified.
    public String toString() {
        String ret = "";
        for (int i = 0; i < myWords.length; i++) {
            ret = ret + myWords[i] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (this.length() != other.length()) {
            return false;
        }
        for (int i = 0; i < myWords.length; i++) {
            if (!myWords[i].equals(other.wordAt(i))) {
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) {
        String[] words = new String[this.length()];
        for (int i = 0; i < words.length - 1; i++) {
            // this. is used to represent current object in the class
            words[i] = this.wordAt(i + 1);
        }
        words[words.length - 1] = word;
        // calculate String array before initiate the object
        return new WordGram(words, 0, words.length);
    }

    public int hashCode() {
        return this.toString().hashCode();
    }

}
