package com.cybrilla.serviceimpl;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.DigestUtils;

import com.cybrilla.entity.PaymentGatewayEntity;
import com.cybrilla.service.PaymentService;

public class PaymentServiceImpl implements PaymentService{
	private String bank_ifsc_code;
	private String bank_account_number;
	private String amount;
	private String merchant_transaction_reference;
	private String transaction_Date;
	private String payment_gateway_merchant_reference;
	private String payload_with_sha;
	private String payload_to_aes;
	private String payload_to_pg;
	
	
	String key = "Q9fbkBF8au24C9wshGRW9ut8ecYpyXye5vhFLtHFdGjRg3a4HxPYRfQaKutZx5N4";
	
	String message = null;
	
	
	@Override
	public String generateMessage(PaymentGatewayEntity paymentGatewayEntity) {
		bank_ifsc_code = paymentGatewayEntity.getBank_ifsc_code();
		bank_account_number = paymentGatewayEntity.getBank_account_number();
		amount = paymentGatewayEntity.getAmount();
		merchant_transaction_reference = paymentGatewayEntity.getMerchant_transaction_reference();
		transaction_Date = paymentGatewayEntity.getTransaction_Date();
		payment_gateway_merchant_reference = paymentGatewayEntity.getPayment_gateway_merchant_reference();
		
		message = "bank_ifsc_code=" + bank_ifsc_code + "|" + "bank_account_number=" + bank_account_number + "|"
				+ "amount=" + amount + "|" + "merchant_transaction_reference" + merchant_transaction_reference
				 + "|" + "transaction_Date=" + transaction_Date + "|" + "payment_gateway_merchant_reference="
				+ payment_gateway_merchant_reference;
		
		 System.out.println("Message = " + message);
		 payload_with_sha = createSHA(message);
		
		 System.out.println("Payload with SHA = " + payload_with_sha);
		 
		 try {
			payload_to_aes = createWithAES(payload_with_sha);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		payload_to_pg = encodeUsingBase(payload_to_aes);
		
		return payload_to_pg;
	}

	public String createSHA(String message){
		
		String newSHA = DigestUtils.sha512Hex(message);
		String payloadwithsha = message + "|" + "hash=" + newSHA ; 
		
		return payloadwithsha;
	}
	
	public String  createWithAES(String messagewithoutAES) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException{
		
		// Create key and cipher
	     Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
	     Cipher cipher = Cipher.getInstance("AES");

	     // encrypt the text
	     cipher.init(Cipher.ENCRYPT_MODE, aesKey);
	     byte[] encrypted = cipher.doFinal(messagewithoutAES.getBytes());
	     System.err.println("Encrypted: " + new String(encrypted));
		return new String(encrypted);
	}
	
	
	public String encodeUsingBase(String messageWithAES){
		byte[] encodedBytes = Base64.getEncoder().encode(messageWithAES.getBytes());
		System.out.println("encodedBytes " + new String(encodedBytes));
		return new String(encodedBytes);
	}
	

}
