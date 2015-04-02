import java.util.*;

public class BST<Key extends Comparable<Key>> {

  private Node root;

  private class Node {
    public Node left;
    public Key key;
    public Node right;

    public Node(Node lt, Key k, Node rt){
      left = lt;
      key = k;
      right = rt;
    }

    public Node(Key k){
      left = null;
      key = k;
      right = null;
    }
  }// End node

  public BST(Node rt){
    root = rt;
  }

  public Key bstSearch(Key k){
    node = search(root, k);
    if (node) return node.key;
    else return null;
  }

  private Node search(Node n, Key k){
    if (n == null) return null;

    int cmp = k.compareTo(n.key);

    if (cmp == 0){
      return k;
    }
    else if (cmp < 0){
      return search(this.left, k);
    }
    else if (cmp > 0){
      return search(this.right, k);
    }
  }//end search

  public void bstAdd(Key k){
    root = add(root, k);
  }

  private Node add(Node n, Key k){
    if (n == null) {
      return Node.new(k);
    }

    int cmp = k.compareTo(n.key);
    if (cmp < 0){
        n.left = add(n.left, k);
    }
    else if (cmp > 0){
        n.right = add(n.right, k);
    }
    return n;
  }//end add

  private Node max(Node n){
    if (n.right){
      return max(n.right);
    }
    else {
      return n;
    }
  }

  private Node deleteMax(Node n){
    if (n.right){
      n.right = deleteMax(n.right);
    }
    else {
      return null;
    }
    return n;
  }

  public Node delete(Key k){
    root = delete(root, key);
  }

  private Node delete(Node n, Key k){
    if (n == null){
      return null;
    }

    int cmp = k.compareTo(n.key);
    if (cmp == 0){
      if (!n.left && !n.right){
        return null;
      }
      else if (n.left && !n.right){
        return n.left;
      }
      else if (!n.left && n.right){
        return n.right;
      }
      else {
        Node replace = max(n.left);
        replace.left = deleteMax(n.left);
        replace.right = n.right;
        return replace;
      }
    }
    else if (cmp < 0){
      n.left = delete(n.left, k);
    }
    else if (cmp > 0){
      n.right = delete(n.right, k)
    }
    return n;
  }

  private void preOrder(Node n, ArrayList<Node> traversal){
    if (n == null) return;
    traversal.add(n);
    preOrder(n.left, traversal);
    preOrder(n.right, traversal);
  }

  private void inOrder(Node n, ArrayList<Node> traversal){
    if (n == null) return;
    inOrder(n.left, traversal);
    traversal.add(n);
    inOrder(n.right, traversal);
  }

  private void postOrder(Node n, ArrayList<Node> traversal){
    if (n == null) return;
    postOrder(n.left, traversal);
    postOrder(n.right, traversal);
    traversal.add(n);
  }

  public ArrayList<Node> bstTraversal(int type){
    ArrayList<Node> traversal = new ArrayList<Node>();
    if (type == 2){
      inOrder(root, traversal);
    }
    else if (type == 3){
      postOrder(root, traversal);
    }
    else {
      preOrder(root, traversal)
    }
    return traversal;
  }

  public boolean isValidBst(BST b){
    return isValid(b.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private boolean isValid(Node n, int MIN, int MAX){
    if (n == null) return true;

    if (n.key < min ||
        n.key > max ||
        !isValid(n.left, MIN, n.key) ||
        !isValid(n.right, n.key, MAX)){
      return false;
    }
    else {
      return true;
    }
  }
}// end BST




