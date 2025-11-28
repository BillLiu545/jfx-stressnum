import java.util.*;

public class Stress extends Stack<String>
{
    private int stressLevel;
    public void pushStress()
    {
        if (stressLevel < 10) {
            push("stress");
            stressLevel++;
        }
    }
    
    public void popStress()
    {
        if (stressLevel > 0) {
            pop();
            stressLevel--;
        }
    }
    
    public Integer getStressLevel()
    {
        return stressLevel;
    }
    
    public void clearStress()
    {
        clear();
        stressLevel  = 0;
    }
}
