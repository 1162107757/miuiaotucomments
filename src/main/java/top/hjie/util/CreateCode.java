package top.hjie.util;

import java.util.Random;


public class CreateCode {

	public static void createUserCode(){
		String[] code = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0","1","2","3","4","5","6","7","8","9"};
		StringBuffer userCode = new StringBuffer("ak_");
		for (int i = 0; i < 6; i++) {
			int index = new Random().nextInt(code.length);
			userCode.append(code[index]);
		}
		
		System.out.println(userCode.toString());
	}
	
}
