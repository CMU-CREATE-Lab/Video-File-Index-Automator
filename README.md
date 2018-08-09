# Video File Index Automator

This purpose of this program is to automatically generate an index for transcription video files. The types of video files 
this program supports includes SBV, SRT, and VTT files. 

## Getting Started

In order to get started it is necessary to first have a version of Java installed preferably Java SE Development Kit 8.
If you do not have this, you can download this from here

[Download Java SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

If you have a sbv, srt, or vtt file that you wish to read through, please take this .sbv, .srt, or .vtt file, and then copy 
and paste all of its contents into a .txt file, then save this file as this is the file you will be using in the program. 

### Compiling the Project

The main program for this project is GetIndex.java.

Compile the program using:
```
javac GetIndex.java
```

## Running the project

You can run this project in 2 ways.

This is the first way to run it. Enter in three arguments as such: 
```
java GetIndex <video text file to read through> <type of video file> <text file with user-inputed words>
```
The first argument is a .txt version of a video transcription file. This program supports 3 types of video files, which
includes SBV, SRT, and VTT files. 

The second argument asks the user to input whether the video file they wish to read through is a sbv, srt, or vtt file.
Simply enter "sbv", "srt", or "vtt" as such without the quotation marks.

The third argument asks the user to create a text file that contains list of words (each seperated by a new line) the user 
wishes to find within the file provided by the first argument.


Now this is the second way to run it. This is similar to the first way except that it just does not include the third 
argument.

```
java GetIndex <video text file to read through> <type of video file>
```
The program will then ask you to list all the words you wish to find on the console itself.

The description for the first two arguments is provided above.


## Example Runs

I have included some example files for you to play around with and test to see how well this program works for yourself.
Here are three example runs that you could run on your terminal:

```
java GetIndex RecordingLITE_sbv.txt sbv fileWithWords.txt

java GetIndex RecordingLITE_srt.txt srt fileWithWords.txt

java GetIndex RecordingLITE_vtt.txt vtt fileWithWords.txt
```

## Authors

* **Mohit Jain** - [zaxway](https://github.com/zaxway)

## Any Questions, Bug Reports, or Further Improvements?

Feel free to contact me directly by sending me an email at moj10@pitt.edu


