import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SentimentAnalysis {

    private Map<String, Integer> positiveWords;
    private Map<String, Integer> negativeWords;

    public SentimentAnalysis() {
        try {
            positiveWords = readWordsFromFile("positive.txt");
            negativeWords = readWordsFromFile("negative.txt");
        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
        }
    }

    private Map<String, Integer> readWordsFromFile(String fileName) throws IOException {
        Map<String, Integer> words = new HashMap<>();
        Path filePath = Paths.get(fileName);

        try (var stream = Files.lines(filePath)) {
            stream.flatMap(line -> Arrays.stream(line.split(", ")))
                    .map(String::trim)
                    .filter(word -> !word.isEmpty())
                    .forEach(word -> words.put(word, 1));
        }
        return words;
    }

    public SentimentType getSentiment(String text) {
        int positiveScore = 0;
        int negativeScore = 0;

        String[] words = text.toLowerCase().replaceAll("[^a-zA-Z\\s]", "").split("\\s+");
        for (String word : words) {
            if (positiveWords.containsKey(word)) {
                positiveScore++;
            } else if (negativeWords.containsKey(word)) {
                negativeScore++;
            }
        }

        return getSentimentType(positiveScore, negativeScore);
    }

    private SentimentType getSentimentType(int positiveScore, int negativeScore) {
        if (positiveScore > negativeScore) {
            return SentimentType.POSITIVE;
        } else if (negativeScore > positiveScore) {
            return SentimentType.NEGATIVE;
        } else {
            return SentimentType.NEUTRAL;
        }
    }
}
