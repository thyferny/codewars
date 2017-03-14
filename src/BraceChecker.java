public class BraceChecker {

	public boolean isValid(String braces) {
		while (!braces.equals(braces.replaceAll("\\(\\)|\\{\\}|\\[\\]", "")))
			braces = braces.replaceAll("\\(\\)|\\{\\}|\\[\\]", "");
		if (braces.equals(""))
			return true;
		return false;
	}
}