package trangthai1.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import trangthai1.model.Response.ResponseData;
import trangthai1.model.Entity.Status;
import trangthai1.model.Entity.User;
import trangthai1.model.Dto.UserDto;
import trangthai1.model.Response.ResponsePage;
import trangthai1.repository.StatusRepository;
import trangthai1.repository.UserRepository;
import trangthai1.service.Mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class UserImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public ResponsePage save(User user, Long statusId, int limit) {
        Status status = statusRepository.findStatus(statusId);
        user.setStatus(status);
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String formatted = current.format(formatter);

        user.setTimeUpdate(formatted);
        User save = userRepository.save(user);
        List < User > all = userRepository.getUserAndSort();
        int indexUser = all.indexOf(save) +1;
        int activePage = (int) Math.ceil((double)indexUser/limit);

        Pageable pageable = PageRequest.of(activePage-1, limit);
        Page < User > userPage = userRepository.findPagination(pageable);
        long totalPage = userPage.getTotalPages();
        List < User > list = userPage.stream().collect(Collectors.toList());
        List<UserDto> userDto = Mapper.changeListUserDto(list);
        return new ResponsePage("true","Truy van du lieu thanh cong!",activePage,limit,totalPage,userDto);
    }

    @Override
    public ResponseData getUser() {
        List < User > users = userRepository.getUserAndSort();
        List<UserDto> userDtoList = Mapper.changeListUserDto(users);
        return new ResponseData("true", "Danh sach user sap xep uu tien", userDtoList);
    }

    @Override
    public ResponseData deleteUser(Long id) {
        Optional < User > user = userRepository.findById(id);
        UserDto userDto = Mapper.changeUserDto(user.get());
        userRepository.deleteById(id);
        return new ResponseData("true", "Xoa thong tin thanh cong!", userDto);
    }

    @Override
    public ResponseData updateUser(User user, Long id, Long statusId) {

        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String formatted = current.format(formatter);

        String existedNote = userRepository.existsNote(id);
        Long existedStatusId = userRepository.existsStatusId(id);
        System.out.println(existedNote);
        System.out.println(existedStatusId);

        Optional < User > user2 = userRepository.findById(id).map(user1 -> {
            user1.setUsername(user.getUsername());

            if (!user.getNote().equalsIgnoreCase(existedNote) || statusId != (existedStatusId)) {
                user1.setTimeUpdate(formatted);
                user1.setNote(user.getNote());
                user1.setStatus(statusRepository.findStatus(statusId));
            }

            return userRepository.save(user1);
        });
        return new ResponseData("true", "Update thong tin thanh cong!", user2);
    }

    @Override
    public ResponseData getOneUser() {
        User oneUser = userRepository.getOneUser();
        UserDto userDto = Mapper.changeUserDto(oneUser);
        return new ResponseData("true", "Truy van du lieu thanh cong!", userDto);
    }

    @Override
    public ResponsePage getPagination(Integer activePage, Integer limit) {
        Pageable pageable = PageRequest.of(activePage - 1, limit);

        Page < User > userPage = userRepository.findPagination(pageable);
        long totalPage = userPage.getTotalPages();
        List < User > list = userPage.stream().collect(Collectors.toList());
        return new ResponsePage("true","Truy van du lieu thanh cong!",activePage,limit,totalPage,list);

    }
}
