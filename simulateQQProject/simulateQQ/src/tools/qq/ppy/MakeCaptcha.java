package tools.qq.ppy;

import java.util.Random;

public class MakeCaptcha {
	public static String makeCapcha() {
		
		String captcha = "";/* ÑéÖ¤Âë*/
		Random random = new Random();
		char[] s = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		for(int i = 0; i < 6; i++) {
			captcha += s[random.nextInt(9)];
		}
		System.out.println(captcha);
		return captcha;
	} 
}
