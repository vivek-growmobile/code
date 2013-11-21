/*
Given  an  array  of  distinct  integers, give  an algorithm  to  randomly  reorder  the 
integers  so  that  each  possible  reordering  is  equally  likely.  In  other  words,  given  a 
deck  of  cards,  how  can  you  shuffle  them  such  that  any  permutation  of  cards  is 
equally  likely?
*/
public int[] deckSuffling(Integer[] in){
  HashSet<Integer> deck = new HashSet<Integer>();
  for (int i = 0; i < in.length; i++){
    deck.add(in.toInteger());
  }

  int[] out = new int[in.length];
  for (int j = 0; j < out.length; j++){
    int shuffle = Math.random() * deck.size();
    out[j] = deck.remove(shuffle);
  }
  return out;
}

public int[] deckShuffling2(int[] in){
  for (int i = 0; i < in.lenth; i++){
    int shuffle = Math.random() * in.lengt();
    if (shuffle != i){
      int tmp = in[shuffle];
      in[shuffle] = i;
      in[i] = tmp;
    }
  }
}

//Design  an algorithm  to  find  a  path  from  one  node  in  a  binary  tree  to  another. 

public boolean contains(Node n1, Node root){
  if (!root) return false;
  if (root.key == n1.key) {
    return true;
  }
  else {
    if (n1.key < root.key){
      contains(n1, root.left);
    }
    else if (n1.key > root.key){
      contains(n1, root.right);
    }
  }
}

public Node lca(Node n1, Node n2, Node root){
  Node lca = root;
  if (n1.key < root.key && n2.key < root.key){
    lca = lca(n1, n2, root.left);
  }
  if (n1.key > root.key && n2.key > root.key){
    lca = lca(n1, n2, root.right);
  }
  return lca;
}

public ArrayList<Node> path(Node n1, Node n2, Node root){
  ArrayList<Node> path = new ArrayList<Node>();
  if (contains(n1, root) && contains(n2, root)){
    Node lca = lca(n1, n2, root);
    Node curr = lca;
    while (curr != n1){
      path.add(0, curr);
      if (n1 < curr) curr = curr.left;
      else if (n1 > curr) curr = curr.right;
    }
    Node curr = lca;
    while (curr != n2){
      path.add(curr);
      if (n2 < curr) curr = curr.left;
      else if (n2 > curr) curr = curr.right;
    }
  }
  return path;
}

/*
Say  you  have  an  array  for  which  the  ith  element  is  the  price  of  a  given stock on day i.  
If  you  were  only  permitted  to  buy  one  share  of  the  stock  and  sell  one  share  of  the 
stock,  design  an  algorithm  to  find  the  best  times  to  buy  and  sell. 
*/

public int buy(int[] shares){
  int largestIdx = 0;
  int largest = in[0];
  int smallestIdx = 0;
  int smallest = in[0];
  for (int i = 1; i < shares.length; i++){
    if (largest < shares[i]){
      largest = shares[i];
      largestIdx = i;
    }
    if (smallest > shares[i]){
      smallest = shares[i];
      smallestIdx = i;
    }
  }

}

/*
Given  an  arbitrary ransom  note  string  and  another  string  containing  all  the 
contents  of  all  the  magazines,  write  a  function  that  will  return  true  if  the  ransom 
note  can be made  from  the magazines;  otherwise, it  will  return  false
*/

public boolean ransom(String note, String magazines){
  HashMap<Character, Integer> dictionary = new HashMap<Character, Integer>();
  for (int i < 0; i < magazines.length; i++){
    Integer count = 1;
    if (dictionary.containsKey(magazines.charAt(i).toCharacter())){
      count = dictionary.get(magazines.charAt(i).toCharacter()) + 1;
    }
    dictionary.put(magazines.charAt(i).toCharacter(), count);
  }

  for (int j = 0; j < note.length; j++){
    if (dictionary.containsKey(note.charAt(i).toCharacter())){
      Integer left = dictionary.get(note.charAt(i).toCharacter());
      left--;
      if (left == 0) dictionary.remove(note.charAt(i).toCharacter());
      else dictionary.put(note.charAt(i).toCharacter(), left);
    }
    else return false;
  }
  return true;
}

public int search(int key, int[] in){
  int low = 0;
  int high = in.length() - 1;
  while (low <= high){
    int mid = low + (high - low) / 2;
    if (key == in[mid]) return mid;
    else if (key < in[mid]) high = mid - 1;
    else if (key > in[mid]) low = mid + 1;
  }
  return -1;
}

public String reverseWords(String in){
  String[] words = in.split(" ");
  for (int i = 0; i < words.length / 2; i++){
    String tmp = words[words.length - 1 - i];
    words[words.length - 1 - i] = words[i];
    words[i] = tmp;
  }
  in = words[0];
  for (int j = 1; j < words.length; j++){
    in.concat(words[j]);
  }
  return in;
}

1 2 3 4 5

0 1 2 3 4

public String reverseWords2(char[] in){
  for (int i = 0; i < in.length / 2){
    char tmp = in[in.length - 1 - i];
    in[in.length - 1 - i] = in[i];
    in[i] = tmp;
  }

  int first = 0;
  int last = 0;
  for (int i = 0; i < in.length; i++){
    if (in[i] == " "){
      for (int j = first; j < first + ((last - first) / 2); j++){
        char tmp = in[last - 1 - j];
        in[last - 1 - j] = in[j];
        in[j] = tmp;
      }
      first = i + 1;
      last = i + 1;
    }
    else last++;
  }
  return in;
}

public boolean cycle(LinkedList<Node> in){
  Node moveOne = in.getFirst();
  Node moveTwo = in.getFirst();
  while (moveOne != in.getLast() && moveTwo != in.getLast()){
    moveOne = moveOne.next();
    moveTwo = moveTwo.next().next();
    if (moveOne == moveTwo) return true;
  }
  return false;
}

public int toInt(char[] s){
  int negative = false;
  int num = 0;
  for (int i = 0; i < s.length; s++){
    if (i == 0 && s[i] == "-") negative = true;
    else {
      if (num != 0) num *= 10;
      num += s[i];
    }
  }
  if (negative) return -num;
  else return num;
}


