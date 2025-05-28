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
         last = circle;
         
         // generate, print, and save the random elimination count
         Random random = new Random();
         
         eliminationCount = 1 + random.nextInt(size/2);
         System.out.println("=== Elimination count is "+ eliminationCount +" ===");
         
         

      } catch(FileNotFoundException e) {
         System.out.println("Something went wrong with " + fileName);
      }
   }
   
   // optional helper method for constructing the circle
   private void add(String val) {
   }
   
   public void eliminate() {
      // count to the elimination count
      for(int i = 0 ; i < eliminationCount ; i++){
         track = track.next;
      }
      // print who will be eliminated
      
      PersonNode eliminated = track.next;
      
      // eliminate the person and update "front" of the circle and size
      track = eliminated.next;
      size--;
   }
   
   public boolean isOver() {
      // check if there's only one person left in the circle
      
      return size == 1;
   }
   
   public String toString() {
      // if there's only one person left, print them as the last survivor
      
      // print the remaining survivors (watch out for infinite loop since list is circular)

      return "";
   }

}