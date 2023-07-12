package com.bidbay.externalsAPI;
import com.mercadopago.*;

import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import java.math.BigDecimal;
public class MercadoPago {

	 public static void main(String[] args) {
		    MercadoPagoConfig.setAccessToken("TEST-4671719881836171-051921-65b774b2d7ed0a7be6596faa7729fda9-83474790");

		    PaymentClient client = new PaymentClient();

		    PaymentCreateRequest createRequest =
		        PaymentCreateRequest.builder()
		            .transactionAmount(new BigDecimal(1000))
		            .token("9b2d63e00d66a8c721607214ceda233a")
		            .description("description")
		            .installments(1)
		            .paymentMethodId("visa")
		            .payer(PaymentPayerRequest.builder().email("dummy_email").build())
		            .build();

		    try {
		      Payment payment = client.create(createRequest);
		      System.out.println(payment);
		    } catch (MPApiException ex) {
		      System.out.printf(
		          "MercadoPago Error. Status: %s, Content: %s%n",
		          ex.getApiResponse().getStatusCode(), ex.getApiResponse().getContent());
		    } catch (MPException ex) {
		      ex.printStackTrace();
		    }
		  }
}
