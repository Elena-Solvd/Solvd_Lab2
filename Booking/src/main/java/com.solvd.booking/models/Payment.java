public class Payment {

    private Long id;
    private PaymentStatus paymentStatus;
    private Float amount;
    private LocalDateTime paidAt;
    private PaymentMethod paymentMethod;
    private Card card;
    private Booking booking;

    public Payment() {
    }

    public Payment(Long id, PaymentStatus paymentStatus, Float amount, LocalDateTime paidAt, PaymentMethod paymentMethod, Card card, Booking booking) {
        this.id = id;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.paidAt = paidAt;
        this.paymentMethod = paymentMethod;
        this.card = card;
        this.booking = booking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
