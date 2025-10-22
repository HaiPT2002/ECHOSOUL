package com.example.anonymousletter.service;

import com.example.anonymousletter.model.Room;
import com.example.anonymousletter.model.User;
import com.example.anonymousletter.repository.RoomRepository;
import com.example.anonymousletter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class SaomateService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    /**
     * Tìm hoặc tạo room cho user.
     * @return roomId nếu tìm thấy cặp, null nếu vẫn đang chờ.
     */
    public Long findPartner(Long userId) {
        User currentUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Nếu người này đã chờ rồi, không tạo thêm
        if (Boolean.TRUE.equals(currentUser.isWaiting())) {
            return null;
        }

        // Tìm danh sách user KHÔNG ở trạng thái chờ (isWaiting = false)
        List<User> availableUsers = userRepository.findByWaiting(false);

        // Loại bỏ chính user hiện tại
        availableUsers.removeIf(u -> u.getUserId().equals(userId));

        if (availableUsers.isEmpty()) {
            // Nếu không có ai để ghép -> chuyển sang trạng thái chờ
            currentUser.setWaiting(true);
            userRepository.save(currentUser);
            return null;
        }

        // Chọn ngẫu nhiên 1 người
        Random random = new Random();
        User partner = availableUsers.get(random.nextInt(availableUsers.size()));

        // Tạo room mới
        Room room = new Room();
        room.setUser1(currentUser);
        room.setUser2(partner);
        Room savedRoom = roomRepository.save(room);

        // Cập nhật trạng thái của 2 người
        currentUser.setWaiting(false);
        partner.setWaiting(false);
        userRepository.save(currentUser);
        userRepository.save(partner);

        return savedRoom.getRoomId();
    }

    public void endChat(Long roomId) {
        roomRepository.deleteById(roomId);
    }
}
