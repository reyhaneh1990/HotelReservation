package ir.reyhaneh.hotelreservation.service;

import ir.reyhaneh.hotelreservation.model.Payments;

import java.util.List;

public interface PaymentsService {

    List<Payments> getAllPayments();

    Payments getPaymentsById(Long id);

}
