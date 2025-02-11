package ir.reyhaneh.hotelreservation.service;

import ir.reyhaneh.hotelreservation.model.Payments;
import ir.reyhaneh.hotelreservation.repository.PaymentsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentsServiceImpl implements PaymentsService {

    private final PaymentsRepository paymentsRepository;

    @Override
    public List<Payments> getAllPayments() {
        return paymentsRepository.getAllPayments();
    }


    @Override
    public Payments getPaymentsById(Long id) {
        return paymentsRepository.getPayments(id);
    }

}

