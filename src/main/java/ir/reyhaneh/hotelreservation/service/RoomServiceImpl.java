package ir.reyhaneh.hotelreservation.service;

import ir.reyhaneh.hotelreservation.model.Rooms;
import ir.reyhaneh.hotelreservation.repository.RoomRepository;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public List<Rooms> getAllRooms() {
        return roomRepository.getAllRoom();
    }


    @Override
    public Rooms getRoomById(Long id) {
        return roomRepository.getRoom(id);
    }

    @Override
    public void saveRoom(String type, String status, Long price) {
        if (type.isEmpty()) {
            throw new ValidationException("Type is empty");
        }

        roomRepository.addRoom(type, status, price);
    }

    @Override
    public void updateRoom(Long id, String type, String status, Long price) {
        if (id == null) {
            throw new ValidationException("id is null");
        }
        if (getRoomById(id) == null) {
            throw new ValidationException("Room is not found");
        }

        roomRepository.editRoom(id, type, status, price);
    }

    @Override
    public void deleteRoom(Long id) {
        if (id == null) {
            throw new ValidationException("id is null");
        }
        if (getRoomById(id) == null) {
            throw new ValidationException("Room is not found");
        }

        roomRepository.deleteRoom(id);
    }
}

