public class Main {
    public static void main(String[] args) {
        String review1 = "This product is excellent! I love it. excellent";
        String review2 = "The quality of the product is bad, very disappointing";
        String review3 = "The delivery was on time. So far, so good good bad bad.";

        SentimentAnalysis sentimentAnalysis = new SentimentAnalysis();

        SentimentType sentiment1 = sentimentAnalysis.getSentiment(review1);
        SentimentType sentiment2 = sentimentAnalysis.getSentiment(review2);
        SentimentType sentiment3 = sentimentAnalysis.getSentiment(review3);

        System.out.println("Review 1 Sentiment: " + sentiment1);
        System.out.println("Review 2 Sentiment: " + sentiment2);
        System.out.println("Review 3 Sentiment: " + sentiment3);
    }
}
