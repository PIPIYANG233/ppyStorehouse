package tools.qq.ppy;

import org.apache.commons.mail.HtmlEmail;

public class SendEmail {
	public static boolean send(String toEmailAddress, String captcha) {
		
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.qq.com");
			email.setCharset("UTF-8");
			email.addTo(toEmailAddress); // 收件地址
	 
			email.setFrom("tom2001926@foxmail.com", "皮皮杨"); // 邮箱地址  和  用户名, 用户名可以任意填写
	 
			email.setAuthentication("tom2001926@foxmail.com", "mqxvdbsxhkjfecea"); // 邮箱地址  和  客户端授权码
	 
			email.setSubject("皮皮杨给您发的验证码"); //邮件名
			email.setMsg("尊敬的用户您好,您本次的验证码是:" + captcha); //邮件内容
	 
			email.send();
		}
		catch(Exception e){
			return false;
		}
		
		return true;
	}
}
