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
   static String inputPath;
   static String outputPath;
   static boolean help;
   static String numberOfThread;
  /**
   * main method of this program
   * and you can use this program as start with apache common cli
   * @param args
   * @throws IOException
   */
   public static void main(String[] args) throws IOException {
      Options options = createOptions();
      if(parseOptions(options, args)) {
         help = false;
         if(help) {
            printHelp(options);
            return;
         }
      }

      HashMap<String, ArrayList<Message>> messages = new  HashMap<String, ArrayList<Message>>();
      ArrayList<String> result = new ArrayList<String>();
      DataReader DR = new DataReader();
      messages = DR.getData(inputPath, numberOfThread);

      MessageFilter MF = new MessageFilter(messages);
      messages = MF.MessageFilt(messages);

      MessageCounter MC = new MessageCounter(messages);
      result = MC.messageCountSort();

      WriteCSV WC = new WriteCSV();
      WC.createCSV(result, outputPath);

      System.out.println("finish!");
      
   }

   private static boolean parseOptions(Options options, String[] args) {
      CommandLineParser parser = new DefaultParser();
      try {
         CommandLine cmd = parser.parse(options, args);

         inputPath = cmd.getOptionValue("i");
         numberOfThread = cmd.getOptionValue("c");
         outputPath = cmd.getOptionValue("o");
         help = cmd.hasOption("h");
      }catch(Exception e) {
         printHelp(options);
         return false;
      }
      return true;
   }

   private static void printHelp(Options options) {
      HelpFormatter formatter = new HelpFormatter();
      String header = "Chat Counter Program";
      String footer = "\nPlease report issues at https://github.com/LimSujin0/ChatCounter/Issues";
      formatter.printHelp("ChatCounter", header, options, footer, true);

   }

   private static Options createOptions() {
      Options options = new Options();
      options.addOption(Option.builder("i").longOpt("input")
            .desc("Set a path of directory that contain that data files.")
            .hasArg()
            .argName("A directory path")
            .required()
            .build());
      options.addOption(Option.builder("c").longOpt("numberOfThread")
            .desc("Set a number of thread.")
            .hasArg()
            .argName("A number of thread")
            .required()
            .build());
      options.addOption(Option.builder("o").longOpt("output")
            .desc("Set a file path name where the count results are saved.")
            .hasArg()
            .argName("A file path")
            .required()
            .build());
      options.addOption(Option.builder("h").longOpt("help")
            .desc("Help")
            .build());

      return options;
   }
}