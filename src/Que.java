import java.util.ArrayList;
import java.util.List;

public class Que {
	public static List<Orange> Oranges;

    Que() {
        Oranges = new ArrayList<Orange>();
    }

    public void addOrange(Orange o) {
        Oranges.add(o);
    }

    public Orange getOrange() {
        return Oranges.get(0);
    }

    public static int countOranges() {
        return Oranges.size();
    }
}