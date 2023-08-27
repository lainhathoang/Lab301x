package util;

import java.util.Random;

public class GetOTPUtil {
	
	public GetOTPUtil() {}
	
	public int createOTP() {
		// Generate a 6-digit random OTP
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        
        return otp;
	}
}
