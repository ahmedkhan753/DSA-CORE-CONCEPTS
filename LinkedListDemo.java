/**
 * The Node class represents a single element in the list.
 */
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

/**
 * The LinkedListDemo class contains the logic and the main method.
 * FILE NAME MUST BE: LinkedListDemo.java
 */
public class LinkedListDemo { 
    Node head;

    // Method to add data to the end of the list
    public void append(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Method to print the list
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        // We create an instance of the list manager class
        LinkedListDemo list = new LinkedListDemo(); 
        
        list.append(100);
        list.append(200);
        list.append(300);
        
        list.display();
    }
}