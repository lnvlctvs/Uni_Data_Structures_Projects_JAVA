package project_1;

import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringDoubleEndedQueueImpl<M> implements StringDoubleEndedQueue<M> {
	

	private int currentSize;
	private Node head , tail;
	
	private class Node {
		M item;
		Node next;
		Node prev;
		Node(M item){
			this.item = item;
			next = null;
			prev = null;
		}
	}
	
	public StringDoubleEndedQueueImpl(){
		currentSize = 0;
		head = null;
		tail = null;
	}
	
	
	public boolean isEmpty()
	{
		return (head == null);
	}
	
	public void addFirst(M item){
		
		Node character = new Node(item);
		currentSize++;
		if(isEmpty())
		{
			head = character;
			tail = head;
		}
		else
		{
			character.next = head;
			head.prev = character;
			head = character;
		}
	}
	
	public M removeFirst() throws NoSuchElementException
	{
		if(isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		
		Node character = head;
		if( head == tail)
		{
			head = tail = null;
		}
		else
		{
			head = head.next;
			head.prev = null;
		}
		currentSize--;
		return character.item;
	}
	
	public void addLast(M item)
	{
		Node character = new Node(item);
		currentSize++;
		if(isEmpty())
		{
			tail = character;
			head = tail;
		}
		else
		{
			character.prev = tail;
			tail.next = character;
			tail = character;
		}
	}
	
	public M removeLast() throws NoSuchElementException
	{
		if(isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		
		Node character = tail;
		if( head == tail)
		{
			head = tail = null;
		}
		else{
			tail = tail.prev;
		    tail.next = null;
		}
	
		currentSize--;
		
		return character.item;
	}
	
	public M getFirst()
	{
		if(isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		return head.item;
	}
	
	public M getLast()
	{
		if(isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		return tail.item;
	}
	
	public void printQueue(PrintStream stream)
	{
		for(Node i=head; i != null; i=i.next)
		{
			System.out.print(i.item);
		}
	}
	
	
	public int size(){
		return currentSize;
	}
	
	
}
