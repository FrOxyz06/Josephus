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
         // load names from the file in order, generating a singly linked list of PersonNodes
         Scanner file = new Scanner(new File(fileName));
          PersonNode last = null;
         // make the ring circular by attaching last node's next to front
         
         while(file.hasNext()){
            String name = file.next();
           
            PersonNode current = new PersonNode(name);
            
            if(circle == null){
               circle = current;
               last = current;
            }else{
               last.next = current;
               last = current;
            
            }
            size++;
         }
          
         // remember the last node as the one in front of the next to get eliminated
        if (last != null) {
            last.next = circle; // make it circular
        }
         
         // generate, print, and save the random elimination count
         Random random = new Random();
         
         eliminationCount = random.nextInt(1 , size/2);
         System.out.println("=== Elimination count is "+ eliminationCount +" ===");
         
         

      } catch(FileNotFoundException e) {
         System.out.println("Something went wrong with " + fileName);
      }
   }
   
   // optional helper method for constructing the circle
   private void add(String val) {
   }
   
   public void eliminate() {
    for (int i = 1; i < eliminationCount; i++) {
        track = track.next;
    }

    PersonNode eliminated = track;
    System.out.println("Eliminated: " + eliminated.name);

    track.next = eliminated.next;
    if (eliminated == circle) {
        circle = eliminated.next;
    }

    track = track.next;

    size--;
}
   
   public boolean isOver() {
      // check if there's only one person left in the circle
      return size == 1;
   }
   
   public String toString() {
       if (circle == null) {
           return "";
       }
  
       String result = "";
       if (isOver()) {
           result += "Last survivor: " + circle.name;
       } else {
           if (track == null) {
               track = circle; 
           }
   
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