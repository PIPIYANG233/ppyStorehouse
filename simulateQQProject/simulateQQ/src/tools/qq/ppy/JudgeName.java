package tools.qq.ppy;

public class JudgeName {
	public static boolean judgeName(String name) {
		
		int lenName = name.length();
		int blankNum = 0;
		for(int i = 0; i < lenName; i++) {
			if(name.charAt(i) == ' ')
				blankNum++;
		}
		if(blankNum == lenName) {
			return false;
		}
		
		return true;
	}
}
