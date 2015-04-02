public class JsonLiteParser {
    // Json lite has key:value pairs key is string, value can either be string or another json lite
   "{
      'key':'val',
      'key2':{
        'key3':'val3',
        'key4':{
          'key5':'val5',
          'key6':'val6'
        },
      },
      'key7':'val7'
   }"


  //Returns a nested hash map structure that creates a new hashmap to store nested json_lite
  //assumes validity of input string structure
  public static HashMap<String,Object> parseJsonLite(String in){
    HashMap<String,Object> map = new HashMap<String,Object>();
    String bindings = in.substring(1, in.length);
    String[] pairings = bindings.split(",");
    for (int i = 0; i < pairings.length; i++){
      String pairing = pairings[i].split(":");
      if (pairing[1].charAt(0) == "{"){
        String val = "{" + pairings[i].split("{")[1];
        map.put(pairing[0], parseJsonLite(val));
      }
      else {
        map.put(pairing[0], pairing[1]);
      }
    }

    return map;
}