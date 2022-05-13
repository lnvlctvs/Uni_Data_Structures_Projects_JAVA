package project_1;

import java.util.Scanner;

public class PostfixToInfix 
{
	
	public static void main (String[] args) 
	{
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String postFix;
		System.out.println("Enter postfix array here : ");
		postFix = scanner.nextLine();
		
		try {
		
		// checking if the postFix array is valid
		
		char a = postFix.charAt(postFix.length()-1);
		if(postFix.length() >= 3 && (a == '*' || a == '/' || a == '+' || a == '-')) 
		{
			for(int i=0; i < postFix.length(); i++) 
			{
				if((int)postFix.charAt(i) <= 41 ||  (int)postFix.charAt(i) >= 58 
				|| (int)postFix.charAt(i) == 44 || (int)postFix.charAt(i) == 46)
					throw new Exception();
			}
		}
		else 
		{
			throw new Exception();
		}
		

			StringDoubleEndedQueue <String> infix = new StringDoubleEndedQueueImpl <String>();
			
			String synthesis = null;
			String operand1 = null;
			String operand2 = null;
			char current;
			
			for(int i=0; i < postFix.length(); i++) {
				current = postFix.charAt(i);
				if(current == '*' || current == '/' || current == '+' || current == '-') {
					
					operand1 = infix.removeLast();
					operand2 = infix.removeLast();
					synthesis = '('+ operand2 + current + operand1 + ')';
					infix.addLast(synthesis);
					
				}else {
					infix.addLast(Character.toString(current));
				}
				
			}
			
			// print the final infix array
			
			System.out.print(infix.removeLast());
		
		}catch (Exception e) {
			
			System.out.println();
			System.out.println();
			System.out.println("|------------------  ERROR  ---------------------|");
			System.out.println("|                                                |");
			System.out.println("|   Postfix array you have entered is not valid. |");
			System.out.println("|     The program will now terminate .           |");
			System.out.println("|                Good bye !                      |");
			System.out.println("|                                                |");
			System.out.println("|------------------------------------------------|");
		}
		
	}

}
