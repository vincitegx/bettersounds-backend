package com.bettersounds.services.impl;

import com.bettersounds.services.PaypalService;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author TEGA
 */
@Service
@AllArgsConstructor
@Slf4j
public class PaypalServiceImpl implements PaypalService{
    
//    private final APIContext context;
//
//    @Override
//    public Payment createPayment(Double total, String currency, String method, String intent, String description, String cancelUrl, String successUrl) throws  PayPalRESTException{
//        Amount amount = new Amount();
//        amount.setCurrency(currency);
//        total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
//        amount.setTotal(String.format("%.2f", total));
//        
//        Transaction transaction = new Transaction();
//        transaction.setDescription(description);
//        transaction.setAmount(amount);
//        
//        List<Transaction> transactions = new ArrayList<>();
//        transactions.add(transaction);
//        
//        Payer payer = new Payer();
//        payer.setPaymentMethod(method);
//        
////        PayerInfo payerInfo = new PayerInfo();
////        payerInfo.setPayerId();
//        
//        Payment payment = new Payment();
//        payment.setIntent(intent);
//        payment.setPayer(payer);
//        payment.setTransactions(transactions);
//        RedirectUrls redirectUrls = new RedirectUrls();
//        redirectUrls.setCancelUrl(cancelUrl);
//        redirectUrls.setReturnUrl(successUrl);
//        return payment.create(context);
//    }
//
//    @Override
//    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
//        Payment payment = new Payment();
//        payment.setId(paymentId);
//        PaymentExecution paymentExecution = new PaymentExecution();
//        paymentExecution.setPayerId(payerId);
//        return payment.execute(context, paymentExecution);
//    }
//    
    
}
