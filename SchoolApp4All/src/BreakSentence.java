import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BreakSentence {

	public static void main(String[] args) {
		int a = 40;
		String original = "This is a sentence. Rajesh want to test the applications for the word split handling.";
		List matchList = new ArrayList();
		Pattern regex = Pattern.compile("(.{1,"+a+"}(?:\\s|$))|(.{0,"+a+"})", Pattern.DOTALL);
		Matcher regexMatcher = regex.matcher(original);
		while (regexMatcher.find()) {
		  matchList.add(regexMatcher.group());
		}
		System.out.println("Match List "+matchList);
		System.out.println(matchList.size());
		System.out.println(matchList.get(0));
		System.out.println(matchList.get(1));
		System.out.println(matchList.get(2));
		System.out.println(matchList.get(3));
	}

}
