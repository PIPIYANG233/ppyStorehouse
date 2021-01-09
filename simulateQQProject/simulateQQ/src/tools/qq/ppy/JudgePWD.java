package tools.qq.ppy;

public class JudgePWD {
	public static boolean judgePWD(String pwd) {
		
		int lenPWD = pwd.length();
		if(lenPWD < 8 || lenPWD > 26) {
			return false;
		}
		
		for(int i = 0; i < lenPWD; i++) {
			char c = pwd.charAt(i);
			System.out.println(c);
			if(!(('0' <= c && c <= '9') || ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || c == '.')) {
				return false;
			}
		}
		
		return true;
	}
}
