import java.util.*;
/**
 * Enumeration class DictionList - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum DictionList
{
    CSC210("CSC210", "Comfortable with Objects and Classes. ? Ready for CSC220. ? Intro to Java. ? To learn Java", "adjective ? adjective ? noun ? verb"),
    CSC220("CSC220", "Ready to create complete data structures. ? Data Structures. ? To create data Structures.", "adjective ? noun ? verb"),
    CSC340("CSC340", " = C++ Version of CSC210 + CSC220 + more ? A CS upper division course. ? Many hours outside of class. ? Programming Methodology.","adjective ? noun ? noun ? noun ?"),
    PLACEHOLDER("Placeholder", "To be Updated. . . ? To be Updated. . . ? To be Updated. . . ? To be Updated. . . ? To be Updated. . . ? To be Updated. . . ? To be Updated. . . ? To be Updated. . . ? To be Updated. . . ? To be Updated. . . ? To be Updated. . .", "adjective ? adjective ? adverb ? conjunction ? interjection ? noun ? noun ? noun? preposition ? pronoun ? verb"),
    ADJECTIVE("Adjective", "Adjective is a word that describes a person or thing, for example big, red and clever in a big house, red wine and a clever idea.","noun"),
    VERB("Verb", "Verb is a word or group of words that express an actiong (such as eat), an event (such as happen or state (such as exist).", "noun"),
    BOOK("Book", "A set of pages. ? A written work published in printed or electronic form. ? To arrange for someone to have a seat on a plane ? To arrange something on a particular date", "noun ? noun ? verb ? verb"),
    BOOKABLE("Bookable", "Can be ordered in advance.", "adjective"),
    CONJUNCTION("Conjunction", "Conjunction is a word that joins words, phrases or sentences, for example 'and', 'but', 'or'.","noun"),
    INTERJECTION("Interjection", "Interjection is a short sound, word or phrase spoke sudenly to express an emotion. Oh!, Look out! and Ow! are interjections.", "noun");
    // enums stored with the word itself, the definitions of that word, and its part of speech in separate variables. 
    
    private String cWord;
    private String cDef;
    private String cSpeech;
    
    private DictionList(String cWord, String cDef, String cSpeech) //constructor with each variable from the enum
    {
        this.cWord = cWord;
        this.cDef = cDef;
        this.cSpeech = cSpeech;
    }
    // getters for each enum variable 
    public String getcWord()
    {
        return cWord;
    }
    public String getcDef()
    {
        return cDef;
    }
    public String getcSpeech()
    {
        return cSpeech;
    }
    public static boolean validSpeech(String speech)
    {
        switch(speech.toLowerCase())
        {
            case "adjective":
            case "noun":
            case "verb":
            case "adverb":
            case "conjunction":
            case "interjection":
            case "preposition":
            case "pronoun":
                return true;
            default: 
                return false;
        }
    }
    public static void printValue(List<defList> enter, String speech, String word) // the method that will output the word, it's definition and its type of speech. 
    {
      String speechString = "";
      for(defList element : enter) 
      {                            
          if((element.getSpeech()).equals(speech))                                            
          {
              speechString = element.getDefinition();
              word = word.toLowerCase();                                                      // these two lines correct the capital lettering as they get swithed around in the main 
              word = word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
              System.out.println("\t" + word + " [" + speech + "] : " + speechString);        
          }
      }
      if(speechString == "")
      {
          return;
      }
    }    
    
    public static void main(String[] args)
    {

        Map<String, List<defList>> holder = new HashMap<String, List<defList>>();  // creating hash map called holder
        Scanner uInput = new Scanner(System.in);
        System.out.println("! Loading data. . .");
        for(DictionList entry : DictionList.values())
        {
            String[] splitDef = (entry.getcDef()).split("[?]");             //splitting the definitions and the parts of speech by the symbol : ?
            String[] splitSpeech = (entry.getcSpeech()).split("[?]");  
            
            List<defList> cList = new ArrayList<defList>();                 //this array will hold the the definitions and the part of speech
            for(int i = 0; i < splitDef.length; i++)                        //loops to link the part of speech with the definition and adds it into the array
            {

                cList.add(new defList(splitDef[i].trim(), splitSpeech[i].trim()));
                //created defList class to keep the definitions so defList objects can be added to array.

            }
            holder.put((entry.getcWord()).toUpperCase(), cList);      //puts key (the word) and its value(cList array) into the hashmap
        }
        System.out.println("! Loading completed . . .\n");
        System.out.println("-----DICTIONARY 340 JAVA-----\n");
        
        do{                                                           // do while loop so the search question in the UI continually asks until user stops
            
            System.out.println("Search: ");
            String[] userInput = (uInput.nextLine()).split("[ ]");    //splitting the users input which will be the word they want and part of speech, by the white spaces
            System.out.println("|\n");
            String uWord = "";
            String uSpeech = "";
            boolean validSpeech = false; 
            for(int i = 0; i < userInput.length; i++)
            {
                if(i == 0)
                {
                uWord = userInput[i].toUpperCase();                   //creates a string of the users input that indicates that the first word is the word they are searching for
                }
                else 
                {
                    uSpeech = userInput[i].toUpperCase();            //any words after the first word indicates that it is a part of speech
                    validSpeech = DictionList.validSpeech(uSpeech.trim());
                    break;
                }
            }
            
            if(uWord.equals("!Q"))                                   // first if breaks code if user wants to exit program
            {
                System.out.println("-----THANK YOU-----");
                break;
            }
            else if(holder.get(uWord) == null)
            {
                System.out.println("<Not Found>");                   // if user searches word not in library, they are notified
            }
            else if (uSpeech != "")
            {
                if(validSpeech)
                {
                 List<defList> stuff = holder.get(uWord);             // this is where if user wanted a specific part of speech definition that it would call the output method to display *does not work*
                 printValue(stuff, uSpeech, uWord);
                }
                else
                {
                    System.out.println("<2nd argument must be a part of speech or distinct>");
                }
            }
            else
            {
                List<defList> stuff = holder.get(uWord);             //last statement lists all the parts of speech and its definition using the output method
                String[] holdSpeech = {"adjective", "noun", "verb","adverb", "conjunction", "interjection", "preposition", "pronoun"};
                for(int i = 0; i<8; i++)
                {
                    printValue(stuff, holdSpeech[i], uWord);
                }
                System.out.println("|");
            }
        }while(true);
    }
}
