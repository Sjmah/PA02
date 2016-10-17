package code.articles;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SeparatePage {
    private String text;
    private final String OPEN_PAGE_TAG = "<page>";
    private final String CLOSE_PAGE_TAG="</page>";

    Set<WikipediaPageCustom> articles = new HashSet<WikipediaPageCustom>();

    public SeparatePage(String rawXML) {
        this.text = rawXML;

        getPageContents();
    }

    private void getPageContents() {
        Scanner scan = new Scanner(this.text);
        int open = 1;
        int end = 1;

        while (open > 0 && end > 0) {
            open = this.text.indexOf(OPEN_PAGE_TAG);
            String substr = this.text.substring(open);
            end = substr.indexOf(CLOSE_PAGE_TAG)+15;
            String page = this.text.substring(open, end);

            this.text = this.text.substring(end);

            this.articles.add(new WikipediaPageCustom(page));
            System.out.println(this.articles.size());
        }
    }

    public void printVals() {
        System.out.println(articles.size());
        for (WikipediaPageCustom temp : articles) {
            System.out.println("-------------------------------------------------");
            System.out.println(temp.getTitle());
            System.out.println(temp.getBody());
            System.out.println("-------------------------------------------------");
        }
    }
}
