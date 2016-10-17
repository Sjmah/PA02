package code.articles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import edu.umd.cloud9.collection.wikipedia.WikipediaPageInputFormat;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;

import edu.umd.cloud9.collection.wikipedia.WikipediaPage;

import code.articles.WikipediaPageCustom;

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

	public static void main(String[] args) throws Exception {

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

		ClassLoader cl = GetArticlesMapred.class.getClassLoader();

		Configuration conf = new Configuration();

		Job job = Job.getInstance(conf, "get articles");

		job.setJarByClass(GetArticlesMapred.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		job.setMapperClass(GetArticlesMapred.GetArticlesMapper.class);

		for (int i = 0; i < args.length - 1; ++i) {
			FileInputFormat.addInputPath(job, new Path(args[i]));
		}

		// Submit the job, then poll for progress until the job is complete
		System.exit(job.waitForCompletion(true) ? 0 : 1);
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

	public void getArticle() {
		String dummyExample = "" +
				"<pages>\n" +
				"<page>\n" +
				"<title>United States of America>\n" +
				"<id >617</id>\n" +
				"<body>The United States of America (USA), commonly referred to as the United States (U.S.) or America, is a country composed of 50 states, a federal district, five major self-governing territories, and various possessions.[fn 1] The 48 contiguous states and federal district are in central North America between Canada and Mexico, with the state of Alaska in the northwestern part of North America and the state of Hawaii comprising an archipelago in the mid-Pacific. The territories are scattered about the Pacific Ocean and the Caribbean Sea. At 3.8 million square miles (9.8 million km2)[17] and with over 324 million people, the United States is the world's third-largest country by total area (and fourth-largest by land area)[fn 2] and the third-most populous. It is one of the world's most ethnically diverse and multicultural nations, the product of large-scale immigration from many other countries.[23] The country's capital is Washington, D.C. and its largest city is New York City; other major metropolitan areas include Los Angeles, Chicago, Dallas, San Francisco, Boston, Philadelphia, Houston, Atlanta, and Miami. The geography, climate and wildlife of the country are extremely diverse.[24]\n" +
				"Paleo-Indians migrated from Asia to the North American mainland at least 15,000 years ago,[25] with European colonization beginning in the 16th century. The United States emerged from 13 British colonies along the East Coast. Numerous disputes between Great Britain and the colonies in the aftermath of the Seven Years War led to the American Revolution, which began in 1775. On July 4, 1776, as the colonies were fighting Great Britain in the American Revolutionary War, delegates from the 13 colonies unanimously adopted the Declaration of Independence. The war ended in 1783 with recognition of the independence of the United States by Great Britain, and was the first successful war of independence against a European colonial empire.[26] The current constitution was adopted in 1788, after the Articles of Confederation, adopted in 1781, were felt to have provided inadequate federal powers. The first ten amendments, collectively named the Bill of Rights, were ratified in 1791 and designed to guarantee many fundamental civil liberties.\n" +
				"The United States embarked on a vigorous expansion across North America throughout the 19th century,[27] displacing American Indian tribes, acquiring new territories, and gradually admitting new states until it spanned the continent by 1848.[27] During the second half of the 19th century, the American Civil War led to the end of legal slavery in the country.[28][29] By the end of that century, the United States extended into the Pacific Ocean,[30] and its economy, driven in large part by the Industrial Revolution, began to soar.[31] The Spanish–American War and World War I confirmed the country's status as a global military power. The United States emerged from World War II as a global superpower, the first country to develop nuclear weapons, the only country to use them in warfare, and a permanent member of the United Nations Security Council. The end of the Cold War and the dissolution of the Soviet Union in 1991 left the United States as the world's sole superpower.[32]\n" +
				"The United States is a highly developed country, with the world's largest economy by nominal GDP. It ranks highly in several measures of socioeconomic performance, including average wage,[33] human development, per capita GDP, and productivity per person.[34] While the U.S. economy is considered post-industrial, characterized by the dominance of services, the manufacturing sector remains the second-largest in the world.[35] Though its population is only 4.4% of the world total,[36] the United States accounts for nearly a quarter of world GDP[37] and almost a third of global military spending,[38] making it the world's foremost military and economic power. The United States is a prominent political and cultural force internationally, and a leader in scientific research and technological innovations.[39]</body\n" +
				"</page>" +
				"<page>\n" +
				"<title>iPhone</title>\n" +
				"<id >617</id>\n" +
				"<body>iPhone is a line of smartphones designed and marketed by Apple Inc. They run Apple's iOS mobile operating system.[14] The first generation iPhone was released on June 29, 2007; the most recent iPhone model is the iPhone 7, which was unveiled at a special event on September 7, 2016.[15][16]\n" +
				"The user interface is built around the device's multi-touch screen, including a virtual keyboard. The iPhone has Wi-Fi and can connect to cellular networks. An iPhone can shoot video (though this was not a standard feature until the iPhone 3GS), take photos, play music, send and receive email, browse the web, send and receive text messages, follow GPS navigation, record notes, perform mathematical calculations, and receive visual voicemail.[17] Other functions—video games, reference works, social networking, etc.—can be enabled by downloading mobile apps. As of June 2016, Apple's App Store contained more than 2 million applications available for the iPhone.[18]\n" +
				"Apple has released ten generations of iPhone models, each accompanied by one of the ten major releases of the iOS operating system. The original 1st-generation iPhone was a GSM phone and established design precedents, such as a button placement that has persisted throughout all releases and a screen size maintained for the next four iterations. The iPhone 3G added 3G network support, and was followed by the 3GS with improved hardware, the 4 with a metal chassis, higher display resolution and front-facing camera, and the 4S with improved hardware and the voice assistant Siri. The iPhone 5 featured a taller, 4-inch display and Apple's newly introduced Lightning connector. In 2013, Apple released the 5S with improved hardware and a fingerprint reader, and the lower-cost 5C, a version of the 5 with colored plastic casings instead of metal. They were followed by the larger iPhone 6, with models featuring 4.7 and 5.5-inch displays. The iPhone 6S was introduced the following year, which featured hardware upgrades and support for pressure-sensitive touch inputs, as well as the SE—which featured hardware from the 6S but the smaller form factor of the 5S. In 2016, Apple unveiled the iPhone 7 and 7 Plus, which add water resistance, improved system and graphics performance, a new dual-camera setup on the Plus model, new color options, and remove the 3.5 mm headphone jack.[19]\n" +
				"The iPhone's commercial success has been credited with reshaping the smartphone industry and helping to make Apple one of the world's most valuable publicly traded companies by 2011.[20] The original iPhone was one of the first phones to use a design featuring a slate format with a touchscreen interface.[21] Almost all modern smartphones have replicated this style of design.[citation needed]\n" +
				"In the US, the iPhone holds the largest share of the smartphone market. As of late 2015, the iPhone had a 43.6% market share, followed by Samsung (27.6%), LG (9.4%), and Motorola (4.8%).[22]</body\n" +
				"</page>\n" +
				"</pages>";

		SeparatePage test = new SeparatePage(dummyExample);

		test.printVals();


	}
}
