package tools.qq.ppy;

import org.apache.commons.mail.HtmlEmail;

public class SendEmail {
	public static boolean send(String toEmailAddress, String captcha) {
		
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.qq.com");
			email.setCharset("UTF-8");
			email.addTo(toEmailAddress); // �ռ���ַ
	 
			email.setFrom("tom2001926@foxmail.com", "ƤƤ��"); // �����ַ  ��  �û���, �û�������������д
	 
			email.setAuthentication("tom2001926@foxmail.com", "mqxvdbsxhkjfecea"); // �����ַ  ��  �ͻ�����Ȩ��
	 
			email.setSubject("ƤƤ�����������֤��"); //�ʼ���
			email.setMsg("�𾴵��û�����,�����ε���֤����:" + captcha); //�ʼ�����
	 
			email.send();
		}
		catch(Exception e){
			return false;
		}
		
		return true;
	}
}
