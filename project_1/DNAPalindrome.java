package project_1;

import java.util.Scanner;

public class DNAPalindrome
{
	public static void main(String args[])
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String sequence;
		System.out.println("Enter DNA sequence : ");
		sequence = scanner.nextLine();
		
		try {
		for(int i=0; i < sequence.length(); i++) 
		{
			if((int)sequence.charAt(i) != 65 
			&& (int)sequence.charAt(i) != 67 
			&& (int)sequence.charAt(i) != 71 
			&& (int)sequence.charAt(i) != 84 )
				throw new Exception();
		}
		
			StringDoubleEndedQueue<String> queue = new StringDoubleEndedQueueImpl<String>();
			
			char c;
			boolean palindromeKey = true;
		
			for(int i=0; i < sequence.length(); i++)
			{
				if(sequence.charAt(i) == 'A')
				{
					c = 'T';
				}
				else if(sequence.charAt(i) == 'T')
				{
					c = 'A';
				}
				else if(sequence.charAt(i) == 'C')
				{
					c = 'G';
				}
				else
				{
					c = 'C';
				}
				

				queue.addLast(Character.toString(c));
				
				
				if(sequence.charAt((sequence.length()-1)-i) != queue.getLast().charAt(0)) {
					palindromeKey = false;
					break;
				}
			
			}
			
		
			if(palindromeKey == true)
			{
				System.out.println("This DNA sequence is Watson-Crick complemented palindrome  .");
			}
			else
			{
				System.out.println("This DNA sequence is NOT Watson-Crick complemented palindrome  .");
			}
			
		
		}catch (Exception e){
			
			System.out.println();
			System.out.println();
			System.out.println("XXXXXXXXXXXXXXXXXXX  ERROR  XXXXXXXXXXXXXXXXXXXXXX");
			System.out.println("X                                                X");
			System.out.println("X   DNA sequence you have entered is not valid.  X");
			System.out.println("X     The program will now terminate .           X");
			System.out.println("X                 Bye bye .                      X");
			System.out.println("X                                                X");
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			
		}
		
	}
}
