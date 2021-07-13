package data_package;

public class Word {

    // English word
    private String text;

    // Persian Word
    private String translation;

    // probability word
    private int probability;

    // constructor
    public Word(String text, String translation, double probability) {
        this.text = text;
        this.translation = translation;
        this.probability = (int) (probability * 10000000);
    }

    //getter and setter
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }
}
