import java.io.*;
import java.util.*;

public class GetIndex {

  public static void main(String [] args) throws IOException{
    Scanner initialScan;
    ArrayList<String> tokens = new ArrayList<String>();
    String wordToken = "";
    if (args.length == 0 || args.length < 2 || args.length > 3) {
      System.out.println("Enter the sbv.txt, srt.txt, or vtt.txt file for which you wish to scan through.");
      System.out.println("Then enter what the type of the file is: either it is sbv, srt, or vtt");
      System.out.println("OPTIONAL Enter a fileWithWords.txt or some file that contains a list of words that you wish to scan through");
      System.exit(1);
    }

    if (args.length == 3) { // if a file with just words to find, each word seperated with a new line was given
      try {
        File fileWithWords = new File(args[2]);
        initialScan = new Scanner(fileWithWords);
        while(initialScan.hasNextLine()) {
          wordToken = initialScan.nextLine();
          wordToken = wordToken.toLowerCase();
          tokens.add(wordToken);
        }
      }
      catch (FileNotFoundException e) {
        System.out.println("File not Found");
      }
    }
    else {
      Scanner console = new Scanner(System.in);
      System.out.println();
      System.out.println();
      System.out.println("Enter all words you wish to find.");
      System.out.println("Type IAMDONE if you are finished with adding words.");
      System.out.println();
      String userInput = "";
      while(!(userInput.equals("IAMDONE"))) {
        if (userInput.equals("IAMDONE")) {

        }
        else {
          userInput = console.nextLine();
          tokens.add(userInput);
        }
      }
    }
    String info = "";
    String info2 = "";
    String timeStamp = "";
    String testWord = "";
    String line = "";
    int bmSearch = 0;
    BoyerMoore bm = new BoyerMoore();
    ArrayList<String> listOfTimeStamps = new ArrayList<String>();
    File f = new File(args[0]); // get the fileName
    Scanner scan; // scan through file

    System.out.println();
    System.out.println();
    System.out.println("This is your index");
    System.out.println();
    for (int i = 0; i < tokens.size(); i++) {
      scan = new Scanner(f); // reset the scanner's position to the top of file.
      testWord = tokens.get(i); // patternToSearch = testWord
      if (args[1].equals("sbv")) {
        listOfTimeStamps = scanSBV(info, info2, timeStamp, testWord, line, bmSearch, bm, listOfTimeStamps, scan);
      }
      else if (args[1].equals("srt")) {
        listOfTimeStamps = scanSRT(info, info2, timeStamp, testWord, line, bmSearch, bm, listOfTimeStamps, scan);
      }
      else if (args[1].equals("vtt")) {
        listOfTimeStamps = scanVTT(info, info2, timeStamp, testWord, line, bmSearch, bm, listOfTimeStamps, scan, f);
      }
      showIndex(listOfTimeStamps, testWord);
      testWord="";
      listOfTimeStamps.clear();
    }
  }



  public static ArrayList<String> scanVTT(String info, String info2, String timeStamp, String testWord,
                                          String line, int bmSearch, BoyerMoore bm,
                                          ArrayList<String> listOfTimeStamps, Scanner scan, File f)
  {
    testWord = testWord.toLowerCase();
    // Check to see if the WEBVTT three lines are on top.
    // If so, skip through them, then do normal scanning.
    // otherwise, just do normal scanning.
    String checkWebVTT = scan.nextLine();
    if (checkWebVTT.equals("WEBVTT")) {
      scan.nextLine(); // kind: captions
      scan.nextLine(); // language: end
      scan.nextLine(); // new line space
    }
    else {
      try{
        scan = new Scanner(f);
      }
      catch(FileNotFoundException e) {
        System.out.println("Error");
        System.exit(0);
      }
    }
    while(scan.hasNextLine()) {
      timeStamp = scan.nextLine();
      timeStamp = timeStamp.replaceAll(" -->", ","); // replaces all arrows with commas to keep consistency

      info = scan.nextLine();
      line = info.replaceAll("[\\p{Punct}]", "");
      line = line.toLowerCase(); // text = line
      bmSearch = bm.findPattern(line, testWord);

      if (bmSearch == 1) {
        // then match this word that is found with its timestamp.
        listOfTimeStamps.add(timeStamp);
      }
      if (scan.hasNextLine()) {
        info2 = scan.nextLine();
      }
      // want to check if info2 is a whitespace,
      // or more words
      // if its a whitespace, move on
      // if its more words, then run the check for tokens loop again
      if (info2.matches("[0-9]+") == false && info2.length() > 0) {
        line = info2.replaceAll("[\\p{Punct}]", "");
        //line = line.replaceAll("\\s","");
        line = line.toLowerCase();
        bmSearch = bm.findPattern(line, testWord);
        if (bmSearch == 1) {
          // then match this word that is found with its timestamp.
          listOfTimeStamps.add(timeStamp);
        }
        if (scan.hasNextLine()) {
          scan.nextLine();
        }
      }

    } // end of searching through file
    return listOfTimeStamps;
  }

  public static ArrayList<String> scanSRT(String info, String info2, String timeStamp, String testWord,
                                          String line, int bmSearch, BoyerMoore bm,
                                          ArrayList<String> listOfTimeStamps, Scanner scan)
  {
    String skipInt = "";
    testWord = testWord.toLowerCase();
    while(scan.hasNextLine()) {
      skipInt = scan.nextLine();
      timeStamp = scan.nextLine();
      timeStamp = timeStamp.replaceAll(" -->", ","); // replaces all arrows with commas to keep consistency

      info = scan.nextLine();
      line = info.replaceAll("[\\p{Punct}]", "");
      //line = line.replaceAll("\\s","");
      line = line.toLowerCase(); // text = line
      bmSearch = bm.findPattern(line, testWord);

      if (bmSearch == 1) {
        // then match this word that is found with its timestamp.
        listOfTimeStamps.add(timeStamp);
      }
      if (scan.hasNextLine()) {
        info2 = scan.nextLine();
      }
      // want to check if info2 is a whitespace,
      // or more words
      // if its a whitespace, move on
      // if its more words, then run the check for tokens loop again
      if (info2.matches("[0-9]+") == false && info2.length() > 0) {
        line = info2.replaceAll("[\\p{Punct}]", "");
        //line = line.replaceAll("\\s","");
        line = line.toLowerCase();
        bmSearch = bm.findPattern(line, testWord);
        if (bmSearch == 1) {
          // then match this word that is found with its timestamp.
          listOfTimeStamps.add(timeStamp);
        }
        if (scan.hasNextLine()) {
          scan.nextLine();
        }
      }

    } // end of searching through file
    return listOfTimeStamps;

  }

  public static ArrayList<String> scanSBV(String info, String info2, String timeStamp, String testWord,
                                          String line, int bmSearch, BoyerMoore bm,
                                          ArrayList<String> listOfTimeStamps, Scanner scan)
  {
    testWord = testWord.toLowerCase();
    while(scan.hasNextLine()) {
      timeStamp = scan.nextLine();

      info = scan.nextLine();
      line = info.replaceAll("[\\p{Punct}]", "");
      //line = line.replaceAll("\\s","");
      line = line.toLowerCase(); // text = line
      bmSearch = bm.findPattern(line, testWord);

      if (bmSearch == 1) {
        // then match this word that is found with its timestamp.
        listOfTimeStamps.add(timeStamp);
      }
      if (scan.hasNextLine()) {
        info2 = scan.nextLine();
      }
      // want to check if info2 is a whitespace,
      // or more words
      // if its a whitespace, move on
      // if its more words, then run the check for tokens loop again
      if (info2.matches("[0-9]+") == false && info2.length() > 0) {
        line = info2.replaceAll("[\\p{Punct}]", "");
        //line = line.replaceAll("\\s","");
        line = line.toLowerCase();
        bmSearch = bm.findPattern(line, testWord);
        if (bmSearch == 1) {
          // then match this word that is found with its timestamp.
          listOfTimeStamps.add(timeStamp);
        }
        if (scan.hasNextLine()) {
          scan.nextLine();
        }
      }

    } // end of searching through file
    return listOfTimeStamps;
  }

  public static void showIndex(ArrayList<String> listOfTimeStamps, String testWord) {
    // print index to console
    if (listOfTimeStamps.size() == 1) {
      System.out.print(testWord + ": " + "(" + listOfTimeStamps.get(0) + ")");
      System.out.println();
    }
    else {
      System.out.print(testWord + ": ");
      for (int j = 0; j < listOfTimeStamps.size(); j++) {
        System.out.print("(" + listOfTimeStamps.get(j) + ")" + ", ");
      }
      System.out.println();
    }
  }

}
