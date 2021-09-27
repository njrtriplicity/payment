package com.cb.viooh.payment.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Table(name = "payment")
@Entity
public class Payment {
    @Id
    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "status")
    private String status;

    @Column(name = "amount")
    private BigDecimal amount;


    public Payment() {

    }

    public Payment(String paymentId, String orderId, String paymentType, String status, BigDecimal amount) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.paymentType = paymentType;
        this.status = status;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(paymentId, payment.paymentId) && Objects.equals(orderId, payment.orderId) && Objects.equals(paymentType, payment.paymentType) && Objects.equals(status, payment.status) && Objects.equals(amount, payment.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, orderId, paymentType, status, amount);
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
