package tools.qq.ppy;

import java.util.Calendar;

public class JudgeYearMonthDay {
	public static boolean judge(String year, String month, String day) {
		
		if(null == year || null == month || null == day) {
			return false;
		}
			
		int y = Integer.valueOf(year);
		int m = Integer.valueOf(month);
		int d = Integer.valueOf(day);
		
		Calendar c = Calendar.getInstance();
		int nowY = c.get(Calendar.YEAR);
		int nowM = c.get(Calendar.MONTH); nowM++;
		int nowD = c.get(Calendar.DATE);
		
		System.out.println(y + "-" + m + "-" + d + "\n" + nowY + "-" + nowM + "-" + nowD);
		
		if(y > nowY || (y == nowY && (m > nowM)) || (y == nowY && m == nowM && d > nowD)) {
			return false;
		}
		else {
			
			if(y < 1 || m < 1 || m > 12 || d < 1)
				return false;
			
			
			if((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) {
				if(y == 1 || y == 3 || y == 5 || y == 7 || y == 8 || y == 10 || y == 12) {
					if(d > 31) {
						return false;
					}
				}
				else if(y == 2) {
					if(d > 29) {
						return false;
					}
				}
				else {
					if(d > 30) {
						return false;
					}
				}
			} 
			else {
				if(y == 1 || y == 3 || y == 5 || y == 7 || y == 8 || y == 10 || y == 12) {
					if(d > 31) {
						return false;
					}
				}
				else if(y == 2) {
					if(d > 28) {
						return false;
					}
				}
				else {
					if(d > 30) {
						return false;
					}
				}
			}
			
			return true;
		}
		
	}
}
