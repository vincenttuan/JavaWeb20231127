package vending_machine.util;

public class Test {

	public static void main(String[] args) {
		/*
		1	john	CvYZAzr78PYhyTxDs2CJ91hhauo3+KUITuoK9ewWQ90=	dkBTXgJMWWdcPcOwC2/icg==	john@gmail.com
		2	jack	f4GwKKP/uMoYNkS3zO38rdqTF8psWW+ITIUcy+R/LaY=	pIDHnorSH7B/VcPdSVmZyQ==	jack@gmail.com
		3	rose	LKaCzacdQIni+M+99r2EgqiD7TLiPCvacrm2dGhMh7U=	injLTEco9KdKXgfD7QwncQ==	rose@gmail.com
		*/
		// 猜 john 的密碼
		System.out.println("密碼 1111: hash: " + SecurityUtils.getHashPassword("1111", "dkBTXgJMWWdcPcOwC2/icg==") );
		System.out.println("密碼 1234: hash: " + SecurityUtils.getHashPassword("1234", "dkBTXgJMWWdcPcOwC2/icg==") );
	}

}
