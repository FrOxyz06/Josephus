import java.util.*;
import java.io.*;
import java.util.Random;

public class JosephusSim {
   private PersonNode circle;     // a PersonNode pointer that tracks first node
   private int size;              // the number of people in the circle
   private int eliminationCount;  // the number to count to for elimination       
   private PersonNode track;      // a PersonNode pointer to help with elimination

   public JosephusSim(String fileName) {
      try {
         // Read names from the file to build the circle
         Scanner file = new Scanner(new File(fileName));
         PersonNode last = null;

         // Build the circular linked list from the file
         while (file.hasNext()) {
            String name = file.next();
            PersonNode current = new PersonNode(name);

            // If this is the first node, set it as the circle start
            if (circle == null) {
               circle = current;
               last = current;
            } else {
               // Link the current node to the list
               last.next = current;
               last = current;
            }

            size++;  
         }

         // Complete the circle by linking the last node to the first
         if (last != null) {
            last.next = circle;
         }

         // Generate a random elimination count between 1 and size/2
         Random random = new Random();
         eliminationCount = random.nextInt(1, size / 2);
         System.out.println("=== Elimination count is " + eliminationCount + " ===");

      } catch (FileNotFoundException e) {
         System.out.println("Something went wrong with " + fileName);
      }
   }

   public void eliminate() {
      for (int i = 1; i < eliminationCount; i++) {
         track = track.next;
      }

      PersonNode eliminated = track; // The person to be eliminated
      System.out.println("Eliminated: " + eliminated.name);

      // Remove the eliminated person from the circle
      track.next = eliminated.next;

      // If the eliminated person is the start of the circle, update the circle reference
      if (eliminated == circle) {
         circle = eliminated.next;
      }

      // Move the tracker forward for the next elimination round
      track = track.next;

      // Decrease the size of the circle
      size--;
   }

   public boolean isOver() {
      return size == 1;
   }

   public String toString() {
      if (circle == null) {
         return "";
      }

      String result = "";

      // If there's only one person left, display the survivor
      if (isOver()) {
         result += "Last survivor: " + circle.name;
      } else {
         // If this is the first call, initialize the tracker
         if (track == null) {
            track = circle;
         }

         // Traverse and print all remaining people in the circle
         PersonNode start = track;
         int count = 1;

         do {
            result += count + "-" + track.name + " ";
            track = track.next;
            count++;
         } while (track != start);
      }

      return result;
   }
   

}


/*
 === Elimination count is 4 ===
 1-Muhammad 2-Beza 3-Ibrar 4-Nur 5-Krystal 6-River 7-Soham 8-Leon 9-Will 10-Qiao 
 
 Continue elimination? <press enter>
 
 Eliminated: Nur
 1-Krystal 2-River 3-Soham 4-Leon 5-Will 6-Qiao 7-Muhammad 8-Beza 9-Ibrar 10-Nur 
 
 Continue elimination? <press enter>
 
 Eliminated: Leon
 1-Will 2-Qiao 3-Muhammad 4-Beza 5-Ibrar 6-Nur 7-Krystal 8-River 9-Soham 10-Leon 
 
 Continue elimination? <press enter>
 
 Eliminated: Beza
 1-Ibrar 2-Nur 3-Krystal 4-River 5-Soham 6-Leon 7-Will 8-Qiao 9-Muhammad 10-Beza 
 
 Continue elimination? <press enter>
 
 Eliminated: River
 1-Soham 2-Leon 3-Will 4-Qiao 5-Muhammad 6-Beza 7-Ibrar 8-Nur 9-Krystal 10-River 
 
 Continue elimination? <press enter>
 
 Eliminated: Qiao
 1-Muhammad 2-Beza 3-Ibrar 4-Nur 5-Krystal 6-River 7-Soham 8-Leon 9-Will 10-Qiao 
 
 Continue elimination? <press enter>
 
 Eliminated: Nur
 1-Krystal 2-River 3-Soham 4-Leon 5-Will 6-Qiao 7-Muhammad 8-Beza 9-Ibrar 10-Nur 
 
 Continue elimination? <press enter>
 
 Eliminated: Leon
 1-Will 2-Qiao 3-Muhammad 4-Beza 5-Ibrar 6-Nur 7-Krystal 8-River 9-Soham 10-Leon 
 
 Continue elimination? <press enter>
 
 Eliminated: Beza
 1-Ibrar 2-Nur 3-Krystal 4-River 5-Soham 6-Leon 7-Will 8-Qiao 9-Muhammad 10-Beza 
 
 Continue elimination? <press enter>
 
 Eliminated: River
 Last survivor: Muhammad

*/