import java.util.*;

public class Queue<Value extends Comparable<Value>> {

  private Node head;
  private Node tail;
  private int size;

  private class Node {
    public Node prev;
    public Node next;
    public Value val;

    public Node(Node p, Node n, Value v){
      prev = p;
      next = n;
      val = v;
    }

    public Node(Value v){
      prev = null;
      next = null;
      val = v;
    }
  }// end Node

  public Queue(Value v){
    head = Node.new(v);
    tail = head;
    size = 1;

  }

  public void enqueue(Value v){
    if (!head && !tail) {
      head = Node.new(v);
      tail = head;
    }
    else {
      if (head == tail){
        tail = Node.new(v);
        head.next = tail;
        tail.prev = head;
      }
      else {
        Node new_tail = Node.new(v);
        tail.next = new_tail;
        new_tail.prev = tail;
        tail = tail.next;
      }
    }
    size++;
  }

  public Node dequeue(){
    if (!head && !tail){
      size = 0;
      return null;
    }
    if (head == tail){
      Node node = head;
      tail == null;
      head == null;
      size--;
      return node;
    }
    else if (head.next == tail){
      Node node = head;
      tail.prev = null;
      head = tail;
      size--;
      return node;
    }
    else {
      Node node = head;
      head = head.next;
      head.prev = null;
      size--;
      return node;
    }
  }

}// end Queue