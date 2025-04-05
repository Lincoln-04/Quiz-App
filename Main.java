import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
public class Main {
	static boolean fifty = true;
	static boolean swap = true;
	static boolean poll = true;
	static int amount =0;
   public static void main(String args[]) {
	   Scanner sc = new Scanner(System.in); 
	   Player p = playerdetails(sc);
	   System.out.println("-------------------------------------------------------------------");
	   System.out.println("Dear "+p.name+"! Are you ready to take quiz?(yes/no):");
       String ch = sc.next();
	   if(ch=="no") {
		   System.out.println("Thank you for participating in this show!!");
		   System.exit(0);
	   }else {
		   displayrules(); 
		   quiz(sc);  
	   }
	}
   public static Player playerdetails(Scanner sc) {
		   System.out.println("Welcome to Quiz Show");
		   System.out.println("-------------------------------------------------------------------");
		   System.out.println("Enter your name: ");
		   String name = sc.nextLine();
		   System.out.println("Enter your phone number:");
		   String phoneno = sc.next();
		   System.out.println("Enter your address:");
		   String address = sc.next();
	  return new Player(name,address,phoneno);
   }
   public static void displayrules() {
	     System.out.println("-------------------------------------------------------------------");
	     System.out.println("Rules for quiz");
		 System.out.println("1.You have 4 lifelines\n  i.50-50 ii.swap question iii.Audience poll");
		 System.out.println("2.Remember if you choose quit,you will be out of the quiz with the amount you won.");
		 System.out.println("3.Remember the lifelines can be used only once.");
		 System.out.println("4.You will be given a question with 4 options and you need choose one option.");
		 System.out.println("5.You will move forward only if you choose correct option.");
		 System.out.println("-------------------------------------------------------------------");
   }
   public static void quiz(Scanner sc) {
	   List<Question> l = setquestions();
	   try {
		   Thread.sleep(4000);
		   System.out.println("Your quiz begins now!!\n");
		   Thread.sleep(2000);
		   for(int i=0;i<l.size();i++)
		   {
			   Question q = l.get(i);
			   System.out.println("Question : "+(i+1)+"\n"+q.question);
			   for(int j=0;j<q.options.length;j++)
				     System.out.print(q.options[j]+"  ");
			   System.out.println();
			   Thread.sleep(1000);
		       choose(sc,q);
			   if(i==l.size()-1)
				  System.out.println("Congratulations! You completed the quiz and won ₹" + amount);
		    }
		   
	   }catch(Exception e){
		   e.printStackTrace();
	   }  
   }
   public static void choose(Scanner sc,Question q) {
	   System.out.println("Enter 1-4 to answer or 5 to choose lifeline or 7 to quit:");
	   int op = sc.nextInt();
	   if(op>=1 && op <=4) {
		   if(q.options[op-1].charAt(0)==q.answer) {
			   amount+=1000;
			   System.out.println("Correct answer!!\nYou won ₹"+amount);
			   System.out.println("-------------------------------------------------------------------\n");
			   try {
				   Thread.sleep(2000); 
			   }catch(Exception e) {
				   e.printStackTrace();
			   } 
		   }else {
			   System.out.println("Wrong answer!!\nyou won ₹"+amount+"\nThanking for participating in this show!!");
			   System.exit(0);
		   }	   
       }else if(op == 5) {
    	  lifelines(sc,q); 
       }else if(op == 7) {
    	   System.out.println("You won ₹"+amount+"\nThanking for participating in this show!!");
		   System.exit(0);
       }else {
    	   System.out.println("Enter a valid option");
    	   choose(sc,q);
       }  
   }
   public static void lifelines(Scanner sc,Question q) {
			   if(!(fifty || swap || poll)) {
				   System.out.println("No lifelines are available");
				   chooseoption(sc,q);
			   }else {
				   System.out.println("Your Lifelines are:");
				   if(fifty) {
					   System.out.print("1.50-50 ");
				   }
				   if(swap) {
					   System.out.print("2.Swap question ");
				   }
				   if(poll) {
					   System.out.print("3.Audience poll");
				   }
				   System.out.println("\nWhich lifeline you want use??");
				   int abc = sc.nextInt();
				  if(abc==1 && fifty)
				   {
					  q.options = removetwooptions(q.options,q.answer);
					  try {
						  System.out.println("Remaining Options:");
						   for(int j=0;j<q.options.length;j++)
							   if(q.options[j]!=null)
							      System.out.print(q.options[j]+"  ");
						   System.out.println();
						   fifty=false;
						   choose(sc,q);   
					    }catch(Exception e) {
						  e.printStackTrace();
					    }
				   }else if(abc==2 && swap) {
					   try {
						   Question q1= new Question("Who was the captain of the Indian cricket team during its first-ever Test series win in Australia?", 
								    new String[]{"1.MS Dhoni", "2.Rahul Dravid", "3.Virat Kohli", "4.Sourav Ganguly"}, '3');
						   System.out.println(q1.question);
						   for(int j=0;j<q1.options.length;j++)
							      System.out.print(q1.options[j]+"  ");
						   System.out.println();
						   swap=false;
						   choose(sc,q1); 
					   }catch(Exception e) {
						   e.printStackTrace();
					   }
				   }else if(abc==3 && poll){
					   boolean first = true,second=true;
					   System.out.println("Audience Response:");
					   for(int i=0;i<q.options.length;i++) {
						   if(q.options[i] != null && q.options[i].charAt(0) == q.answer)
						   {
							   System.out.print(q.options[i] + "-"+"45% ");
						   }else if(q.options[i] != null && first) {
							   first = false;
							   System.out.print(q.options[i] + "-"+"25% ");
						   }else if(q.options[i] != null && second) {
							   second = false;
							   System.out.print(q.options[i] + "-"+"18% ");
						   }else if(q.options[i]!=null){
							   System.out.print(q.options[i] + "-"+"12%");
						   }
					   }
					   System.out.println();
					   poll = false;
					   choose(sc,q); 
				   }else {
					   System.out.println("Invalid choice!!");
				   }
			   }
	    	  
   }
   public static void chooseoption(Scanner sc,Question q) {
	   try {
		   System.out.println("Choose one option??");
		   int op = sc.nextInt();
		   if(q.options[op-1]!=null && q.options[op-1].charAt(0)==q.answer) {
			   amount+=1000;
			   System.out.println("Correct answer!!\nYou won ₹"+amount);
			   System.out.println("-------------------------------------------------------------------\n");
			   Thread.sleep(2000);
		   }else {
			   System.out.println("Wrong answer!!\nyou won ₹"+amount+"\nThanking for participating in this show!!");
			   System.exit(0);
		   }	  
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   
   }
  
   public static List<Question> setquestions() {
	   List<Question> ts = new LinkedList<>();
	   ts.add(new Question("How many players are there in a cricket team?", new String[]{"1.9", "2.10", "3.11", "4.12"}, '3'));
	   ts.add(new Question("What is the distance between the wickets on a cricket pitch?", new String[]{"1.22 yards", "2.20 yards", "3.24 yards", "4.18 yards"}, '1'));
	   ts.add(new Question("What is the maximum number of overs allowed per bowler in a 50-over ODI match?", new String[]{"1.10", "2.15", "3.12", "4.8"}, '1'));
	   ts.add(new Question("What does LBW stand for in cricket?", new String[]{"1.Long Ball Wide", "2.Leg Before Wicket", "3.Left Bat Wave", "4.Late Bat Win"}, '2'));
	   ts.add(new Question("Who is known as the 'God of Cricket'?", new String[]{"1.Virat Kohli", "2.MS Dhoni", "3.Sachin Tendulkar", "4.Rohit Sharma"}, '3'));
	   ts.add(new Question("Which country has won the most ICC Cricket World Cups?", new String[]{"1.India", "2.Australia", "3.England", "4.West Indies"}, '2')); 
	   ts.add(new Question("Which stadium is known as the 'Home of Cricket'?", new String[]{"1.MCG", "2.Eden Gardens", "3.Lord's", "4.Wankhede Stadium"}, '3'));
	   ts.add(new Question("In which year did India win its first Cricket World Cup?", new String[]{"1.1983", "2.2003", "3.2011", "4.1996"}, '1'));
	   ts.add(new Question("Which fast bowler holds the record for the most wickets in Test cricket?", new String[]{"1.Glenn McGrath", "2.James Anderson", "3.Stuart Broad", "4.Shaun Pollock"}, '2'));
	   ts.add(new Question("Who holds the record for the fastest century in ODIs?", new String[]{"1.Sanath Jayasuriya", "2.Shaun Pollock", "3.AB de Villiers", "4.Virat Kohli"}, '3'));
	return ts;
   }
   public static String[] removetwooptions(String[] options,char answer) {
	   int remove = 0;
		   for(int i=0;i<options.length;i++)
		   {
				   if(remove < 2 && options[i]!=null && options[i].charAt(0)!=answer) {
					   options[i] = null;
					   remove++;
				   }
	       }
	   return options;
   }
   
}
