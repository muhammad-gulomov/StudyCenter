package uz.pdp.studycenter.model.req;

public record PaymentRequestDto(String firstName, String lastName, String phoneNumber, Integer amount, String date) {
}
