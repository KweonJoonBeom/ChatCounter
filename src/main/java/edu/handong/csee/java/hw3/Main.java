package edu.handong.csee.java.hw3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
/**
 * Main function just call the method and generate the each instances
 * first get in the inputpath and read all messages, count the message and store in the outputpath file
 * @author °ÇÁØ¹ü
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		HashMap<String, ArrayList<Message>> messages = new  HashMap<String, ArrayList<Message>>();
		ArrayList<String> result = new ArrayList<String>();
		String inputpath = args[1];
		String outputpath = args[3];
		//String filepath = "C:\\git\\Chat-java"; 
		DataReader DR = new DataReader();
		Main m1 = new Main();
		//APACHE COMMONS CLI
		Options options = createOptions();
		if(m1.parseOptions(options, args)){
			boolean help = false;
			if (help){
				printHelp(options);
				return;
			}

			// path is required (necessary) data so no need to have a branch.
			System.out.println("You provided input as the value of the option i");

			// path is required (necessary) data so no need to have a branch.
			System.out.println("You provided output as the value of the option o");
		}
		//HasMap<String,ArrayList<Message>> messages = dataReader.readData(inputPath);
		//HashMap<String,Integer> counter = counterMessages(messages);

		//List<String
		messages = DR.getData(inputpath);

		MessageFilter MF = new MessageFilter(messages);

		messages = MF.MessageFilt(messages);

		MessageCounter MC = new MessageCounter(messages);
		result = MC.messageCountSort();

		WriteCSV WC = new WriteCSV();
		WC.createCSV(result,outputpath);

		System.out.println("finish!");

		for(Message m : messages.get("samer")) {
			System.out.println(m.date+ m.user+m.strMessage);
		}
	}
	// Definition Stage
	private static Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set a directory path that contains input files")
				.hasArg()
				.argName("Directory Path")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set a directory path that output file are saved")
				.hasArg()
				.argName("Directory Path")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Help")
				.build());

		return options;
	}

	private static void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "Counting messages of CacaoTalk per person program";
		String footer ="\nPlease report issues at 21700052@handong.edu";
		formatter.printHelp("CountMessages", header, options, footer, true);
	}
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			String input = cmd.getOptionValue("i");
			String output = cmd.getOptionValue("o");
			boolean help = cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}
}
