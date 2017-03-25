package com.cybrilla.entity;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class PaymentGatewayEntity {
	
	
	private String bank_ifsc_code;
	private String bank_account_number;
	private String amount;
	private String merchant_transaction_reference;
	private String transaction_Date;
	private String payment_gateway_merchant_reference;
	public String getBank_ifsc_code() {
		return bank_ifsc_code;
	}
	public void setBank_ifsc_code(String bank_ifsc_code) {
		this.bank_ifsc_code = bank_ifsc_code;
	}
	public String getBank_account_number() {
		return bank_account_number;
	}
	public void setBank_account_number(String bank_account_number) {
		this.bank_account_number = bank_account_number;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getMerchant_transaction_reference() {
		return merchant_transaction_reference;
	}
	public void setMerchant_transaction_reference(String merchant_transaction_reference) {
		this.merchant_transaction_reference = merchant_transaction_reference;
	}
	public String getTransaction_Date() {
		return transaction_Date;
	}
	public void setTransaction_Date(String transaction_Date) {
		this.transaction_Date = transaction_Date;
	}
	public String getPayment_gateway_merchant_reference() {
		return payment_gateway_merchant_reference;
	}
	public void setPayment_gateway_merchant_reference(String payment_gateway_merchant_reference) {
		this.payment_gateway_merchant_reference = payment_gateway_merchant_reference;
	}
	public PaymentGatewayEntity(String bank_ifsc_code, String bank_account_number, String amount,
			String merchant_transaction_reference, String transaction_Date, String payment_gateway_merchant_reference) {
		super();
		this.bank_ifsc_code = bank_ifsc_code;
		this.bank_account_number = bank_account_number;
		this.amount = amount;
		this.merchant_transaction_reference = merchant_transaction_reference;
		this.transaction_Date = transaction_Date;
		this.payment_gateway_merchant_reference = payment_gateway_merchant_reference;
	}
	@Override
	public String toString() {
		return "PaymentGatewayEntity [bank_ifsc_code=" + bank_ifsc_code + ", bank_account_number=" + bank_account_number
				+ ", amount=" + amount + ", merchant_transaction_reference=" + merchant_transaction_reference
				+ ", transaction_Date=" + transaction_Date + ", payment_gateway_merchant_reference="
				+ payment_gateway_merchant_reference + "]";
	}
	
	
	
	

}
