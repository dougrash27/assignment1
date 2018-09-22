
/**
 * Write a description of class defList here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class defList
{
    private String definition;
    private String speech;
    
    defList(String definition, String speech)
    {
        this.definition = definition;
        this.speech = speech;
    }
    public String getDefinition()
    {
        return this.definition;
    }
    public String getSpeech()
    {
        return this.speech;
    }
}
