import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.*;


public class Main {
    /***
     * Reads and parses log entries from sample_log.txt
     * Counts the occurrences of each EVENT_TYPE, prints in a clear, readable way
     * Reports the 3 most frequent messages for each EVENT_TYPE
     * @param args
     */
    public static void main(String[] args) {
        //spacing for testing delete later
        System.out.println();
        System.out.println();
        //access the sample_log.txt file, make sure it exists
        try
        {
            FileReader reader = new FileReader("src/sample_log.txt");
            BufferedReader bufReader = new BufferedReader(reader);

            //save each line into ArrayList of ArrayLists, one for each event_type
            //might be a more efficient way to do this **
            //(INFO, WARNING, ERROR, DEBUG)
            ArrayList<ArrayList<String>> logs = new ArrayList<ArrayList<String>>();

            //create nested arraylists
            ArrayList<String> infoLogs = new ArrayList<String>();
            ArrayList<String> warningLogs = new ArrayList<String>();
            ArrayList<String> errorLogs = new ArrayList<String>();
            ArrayList<String> debugLogs = new ArrayList<String>();

            //add to outer logs arraylist
            logs.add(infoLogs);
            logs.add(warningLogs);
            logs.add(errorLogs);
            logs.add(debugLogs);


            String line = "";
            int infoCount = 0, warningCount = 0, errorCount = 0, debugCount = 0;


            //loop over .txt file until reader is null
            while(true)
            {
                try {
                    line = bufReader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if (line == null)
                    break;
                else
                {
                    /* see what event_type current line is, add to corresponding nested list
                       take count of each type occurrence
                       strip the unnecessary info - only the message is needed
                     */
                    if (line.contains("INFO"))
                    {
                        String[] parts = line.split(" ");
                        String message = String.join(" ", Arrays.copyOfRange(parts, 3, parts.length));
                        logs.get(0).add(message);
                        infoCount++;
                    }
                    else if (line.contains("WARNING"))
                    {
                        String[] parts = line.split(" ");
                        String message = String.join(" ", Arrays.copyOfRange(parts, 3, parts.length));
                        logs.get(1).add(message);
                        warningCount++;
                    }
                    else if (line.contains("ERROR"))
                    {
                        String[] parts = line.split(" ");
                        String message = String.join(" ", Arrays.copyOfRange(parts, 3, parts.length));
                        logs.get(2).add(message);
                        errorCount++;
                    }
                    else //contains DEBUG
                    {
                        String[] parts = line.split(" ");
                        String message = String.join(" ", Arrays.copyOfRange(parts, 3, parts.length));
                        logs.get(3).add(message);
                        debugCount++;
                    }

                }

            }


            //printing format
            System.out.println("=======================");
            System.out.println("||    LOG RESULTS    ||");
            System.out.println("=======================");
            System.out.println("EVENT OCCURRENCES");
            System.out.printf("INFO:    %d occurrences\n", infoCount);
            System.out.printf("WARNING: %d occurrences\n", warningCount);
            System.out.printf("ERROR:   %d occurrences\n", errorCount);
            System.out.printf("DEBUG:   %d occurrences\n", debugCount);
            System.out.println();
            System.out.println("MOST FREQUENT MESSAGES");

            System.out.println("INFO:");
            //print 3 most frequent messages
            printMostCommonMessages(logs.get(0));
            System.out.println("WARNING:");
            //print 3 most frequent messages
            printMostCommonMessages(logs.get(1));
            System.out.println("ERROR:");
            //print 3 most frequent messages
            printMostCommonMessages(logs.get(2));
            System.out.println("DEBUG");
            //print 3 most frequent messages
            printMostCommonMessages(logs.get(3));








        }
        //file not found
        catch (FileNotFoundException e)
        {
            throw new RuntimeException("That file doesn't exist");
        }
        //various exceptions
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /***
     * finds the 3 most common messages of an event_type list
     * and prints them to the console
     * @param messages
     */
    public static void printMostCommonMessages(ArrayList<String> messages){
        // Sort the messages for easier counting
        Collections.sort(messages);

        // ArrayLists to save unique messages and their counts
        ArrayList<String> uniqueMessages = new ArrayList<>();
        ArrayList<Integer> messageCounts = new ArrayList<>();

        String currentMessage = messages.get(0);
        int counter = 1;

        // Traverse the messages from [1] and keep track of count
        for (int i = 1; i < messages.size(); i++) {
            //message already seen; increment
            if (messages.get(i).equals(currentMessage)) {
                counter++;
            } else {
                //new message; save old counts
                uniqueMessages.add(currentMessage);
                messageCounts.add(counter);

                currentMessage = messages.get(i);
                counter = 1;
            }
        }


        uniqueMessages.add(currentMessage);
        messageCounts.add(counter);

        // Traverse through uniqueMessages & messageCounts to find top 3
        for (int i = 0; i < 3; i++) {

            int max = 0;
            for (int j = 1; j < messageCounts.size(); j++) {
                if (messageCounts.get(j) > messageCounts.get(max)) {
                    max = j;
                }
            }

            System.out.printf("{%s}\n", uniqueMessages.get(max));

            //reset the max and repeat
            uniqueMessages.remove(max);
            messageCounts.remove(max);
        }
        System.out.println();
    }

}