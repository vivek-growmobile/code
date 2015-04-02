procedure DFS(G,v):
    label v as discovered
    for all edges from v to w in G.adjacentEdges(v) do
        if vertex w is not labeled as discovered then
            recursively call DFS(G,w)


public int distance (int user_id1, int user_id2) throws IllegalArgumentException{
    Set<Integer> users = db.users();
    if (!users.contains(user_id1) || !users.contains(user_id2)){
      throw new IllegalArgumentException();
    }
    //A tracker for the distance
    int dist = 0;
    //Check if the user_1 is user_2
    if (user_id1 == user_id2){
      return dist;
    }
    //Increment the distance
    dist++;
    //An array for the current level
    int[] exploring = db.getFriends(user_id1);
    //A set of seen id's
    HashSet<Integer> seen = new HashSet<Integer>();
    seen.add(Integer.valueOf(user_id1));
    //While there are still users to explore
    while (exploring.length > 0){
      //Iterate through the current level to check if user2 is there
      for (int i = 0; i < exploring.length; i++){
        //If so return the distance
        if (exploring[i] == user_id2){
          return dist;
        }
      }
      //If not add all these people to seen
      for (int i = 0; i < exploring.length; i++){
        seen.add(Integer.valueOf(exploring[i]));
      }
      //An array list of the next level
      ArrayList<Integer> toexplore = new ArrayList<Integer>();
      //add all of the friends of the user
      for (int i = 0; i < exploring.length; i++){
        int curr = exploring[i];
        //Get all the friends of the current user
        int[] friends = db.getFriends(curr);
        for (int j = 0; j < friends.length; j++){
          if (!seen.contains(Integer.valueOf(friends[j])) && !toexplore.contains(Integer.valueOf(friends[j]))){
            toexplore.add(Integer.valueOf(friends[j]));
          }
        }
      }
      //Update the current level to the new level
      exploring = new int[toexplore.size()];
      for (int i = 0; i < toexplore.size(); i++){
        exploring[i] = (toexplore.get(i).intValue());
      }
      //Update the distance count
      dist++;
    }
    //If the person isn't found return -1
    return -1;
  }