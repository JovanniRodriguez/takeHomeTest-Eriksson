# takeHomeTest-Eriksson
Take home test solution for Eriksson Software application.

# Approach:
---------
I used Java for this problem only because I am not fully comfortable with C# at the moment. I wanted to keep my code simple, relatively efficient, and easy to understand. 
First, I had no experience with accessing files in Java up until this point, so I read documentation and found that the internal FileReader class would be a good choice.
This is convenient compared to other options because it reads by individual lines instead of characters.
After trial and error I was reading from and parsing sample_log.txt. I then needed a way to save each line to manipulate later. I chose ArrayLists for their speed and functionality. I decided to use one outer arraylist that contained ArrayList<String> for each of the four event_types. For each line, the FileReader would determine if an event_type key phrase was present, and that String would be saved into the appropriate nested arraylist after stripping the string of everything but the message. I figured that no other information was needed to make the log report. Removing the unecessary timestamp and event_type would make finding the 3 most commonly-occuring messages per event_type easier for me. 
After having these nested arraylists, I still had some difficulty finding the 3 most common messages. Ultimately I would have to sort each arraylist to group the messages together, and then simply keep track of their occurrences. I then printed all of the information to the console.

# Assumptions:
-----------
- There are only four event_types in total (INFO, WARNING, ERROR, DEBUG)
- The most common messages were completely unique. For example, {INFO: user123 logged in} and {INFO: user456 logged in} are not to be treated as the same message
- Only the given sample_log.txt was being used. I didn't generalize the code to allow for more files.


# Limitations:
------------
- I used no external libraries
