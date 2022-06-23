package trangthai1.service;

import trangthai1.model.Dto.StatusDto;
import trangthai1.model.Entity.Status;
import trangthai1.model.Entity.User;
import trangthai1.model.Dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    public static UserDto changeUserDto(User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setNote(user.getNote());
        userDto.setStatusname(user.getStatus().getStatusname());
        userDto.setLevel(user.getStatus().getLevel());
        userDto.setDatetime(user.getTimeUpdate());
        return userDto;
    }

    public static StatusDto changeStatusDto(Status status){
        StatusDto statusDto = new StatusDto();

        statusDto.setId(status.getId());
        statusDto.setStatusname(status.getStatusname());
        statusDto.setLevel(status.getLevel());
        return statusDto;
    }
    
    public static List<UserDto> changeListUserDto(List<User> userList){
        List<UserDto> userDtoList = userList.stream().map(user -> {
            UserDto userDto = new UserDto();

            userDto.setId(user.getId());
            userDto.setUsername(user.getUsername());
            userDto.setNote(user.getNote());
            userDto.setDatetime(user.getTimeUpdate());
            userDto.setStatusname(user.getStatus().getStatusname());
            userDto.setLevel(user.getStatus().getLevel());

            return userDto;
        }).collect(Collectors.toList());
        return  userDtoList;
    }
}
