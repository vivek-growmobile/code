private boolean contains(Node n, Node root){
  if (!root) return false;
  else if (root.key == n.key) return true;
  else return contains(n, root.left) || contains(n, root.right));
}


public Node lca(Node n1, Node n2, Node root){
  Node lca = root;

  if (contains(n1, root.left) && contains(n2, root.left)){
    lca = lca(n1, n2, root.left);
  }

  else if (contains(n1, root.right) && contains(n2, root.right)){
    lca = lca(n1, n2, root.right);
  }

  return lca;
}