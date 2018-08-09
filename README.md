# Video-File-Index-Automator
Generates an index based on user-input words

Main Program: GetIndex.java

# Compile with:
javac GetIndex.java

# Run with:
java GetIndex <video text file to read through> <TypeOfVideoFile> <text file with user-inputed words>

The first argument is a .txt version of a video transcription file. This program supports 3 types of video files, which
includes SBV, SRT, and VTT files. 

The second argument asks the user to input whether the video file they wish to read through is a sbv, srt, or vtt file. 

The third argument asks the user to create a list of words the user wishes to find within the file provided by the first argument.


# You can also run the program without the third argument as such:

java GetIndex <video text file to read through> <TypeOfVideoFile>

The program will then ask you to list all the words you wish to find on the console itself.



# Here are three example runs with the sample .txt files provided.

java GetIndex RecordingLITE_sbv.txt sbv fileWithWords.txt

java GetIndex RecordingLITE_srt.txt srt fileWithWords.txt

java GetIndex RecordingLITE_vtt.txt vtt fileWithWords.txt

Run these on your terminal to produce the sample generated output.
