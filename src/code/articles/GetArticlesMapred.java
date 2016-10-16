package code.articles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import edu.umd.cloud9.collection.wikipedia.WikipediaPage;

/**
 * This class is used for Section A of assignment 1. You are supposed to
 * implement a main method that has first argument to be the dump wikipedia
 * input filename , and second argument being an output filename that only
 * contains articles of people as mentioned in the people auxiliary file.
 */
public class GetArticlesMapred {

	//@formatter:off
	/**
	 * Input:
	 * 		Page offset 	WikipediaPage
	 * Output
	 * 		Page offset 	WikipediaPage
	 * @author Tuan
	 *
	 */
	//@formatter:on
	public static class GetArticlesMapper extends Mapper<LongWritable, WikipediaPage, Text, Text> {
		public static Set<String> peopleArticlesTitles = new HashSet<String>();

		@Override
		protected void setup(Mapper<LongWritable, WikipediaPage, Text, Text>.Context context)
				throws IOException, InterruptedException {
			// TODO: You should implement people articles load from
			// DistributedCache here
			super.setup(context);
		}

		@Override
		public void map(LongWritable offset, WikipediaPage inputPage, Context context)
				throws IOException, InterruptedException {
			// TODO: You should implement getting article mapper here
		}
	}

	public static void main(String[] args) throws FileNotFoundException {

		// Java file object for the location of the wikipedia dump file
		File inputFile = new File(args[0]);
		File outputFile = new File(args[1]);
		File people = new File("people.txt");
		Set<String> peopleNames = createPeopleSet();


		// If the output file does not already exist, create it. Otherwise delete whatever exists and create it
		try {
			if(!outputFile.createNewFile()) {
				outputFile.delete();
				outputFile.createNewFile();
			}
		} catch (IOException e) {
			System.out.println("IOException was caught");
		}

		// TODO: you should implement the Job Configuration and Job call
		// here
	}

	private static Set<String> createPeopleSet() {
		Set<String> peopleNames = new HashSet<String>();
		Scanner peopleScanner = new Scanner("people.txt");

		// Go through the people.txt file and add each name to this hashset
		while (peopleScanner.hasNextLine()){
			peopleNames.add(peopleScanner.nextLine());
		}
		return peopleNames;
	}
}
