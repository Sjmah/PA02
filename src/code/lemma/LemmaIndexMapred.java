package code.lemma;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import util.StringIntegerList;
import edu.umd.cloud9.collection.wikipedia.WikipediaPage;

import code.lemma.Tokenizer;

/**
 *
 */
public class LemmaIndexMapred {
	public static class LemmaIndexMapper extends Mapper<LongWritable, WikipediaPage, Text, StringIntegerList> {

		@Override
		public void map(LongWritable offset, WikipediaPage page, Context context) throws IOException,
				InterruptedException {
			// TODO: implement Lemma Index mapper here

			// gets the raw xml and stores it in a string
			String text = page.getContent();

			// Gets the index of the where the opening and closing body tags are
			int openBody = text.indexOf("<body>");
			int closeBody = text.indexOf("</body>");

			String[] stopWords = {"and", "nor", "by", "the", "in", "around", "to"};

			// String representing regex for special characters
			String regexSpecialChars = "\\,|\\||\\.|\\!|\\?|\\(|\\)|\\;|\\{|\\}|\\[|\\]|\\:|\\/|\\'";
			String regexSuperSecialChars = "\\-";

			// Generate a regex String containing all the stop words given an input array
			String regexStopWords = stopWords[0] + "|";
			for (int i = 1; i < stopWords.length; i++) {
				if (i != stopWords.length-1) {
					regexStopWords += stopWords[i] + "|";
				} else {
					regexStopWords += stopWords[i];
				}
			}

			// Get all the text in between <body> </body> --> This is just the xml in between the body
			String body = text.substring(openBody+6, closeBody);

			// Remove stop words and special characters defined above
			body = body.replaceAll(regexStopWords, "\b");
			body = body.replaceAll(regexSpecialChars, "");
			body.replaceAll(regexSuperSecialChars, " ");

			// TODO: Remove bold, italic and underline xml tags from body

		}
	}
}