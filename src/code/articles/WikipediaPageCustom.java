package code.articles;

public class WikipediaPageCustom {
    private final String text;
    private final String OPEN_BODY_TAG="<body>";
    private final String CLOSE_BODY_TAG="</body>";

    private final String OPEN_TITLE_TAG = "<title>";
    private final String CLOSE_TITLE_TAG="</title>";

    private String body;
    private String title;

    public WikipediaPageCustom(String badText) {
        this.text = badText;
        setTitle();
        setBody();
    }

    private void setTitle() {
        int openTitle = this.text.indexOf(OPEN_TITLE_TAG);
        int closeTitle = this.text.indexOf(CLOSE_TITLE_TAG);
        this.title = this.text.substring(openTitle+7, closeTitle);
    }

    private void setBody() {
        int openBody = this.text.indexOf(OPEN_BODY_TAG);
        int closeBody = this.text.indexOf(CLOSE_BODY_TAG);
        this.body = this.text.substring(openBody+6, closeBody);
    }

    public String getTitle() {
        if (this.title != null) {
            return this.title;
        } else {
            return "TITLE WAS NOT FOUND";
        }
    }

    public String getBody() {
        if (this.body != null) {
            return this.body;
        } else {
            return "BODY WAS NOT FOUND";
        }
    }
}
