package trangthai1.service.user;

import org.springframework.stereotype.Service;
import trangthai1.model.Response.ResponseData;
import trangthai1.model.Entity.User;
import trangthai1.model.Response.ResponsePage;

@Service
public interface UserService {
    ResponsePage save(User user, Long statusId, int limit);

    ResponseData getUser();
    
    ResponseData deleteUser(Long id);

    ResponseData updateUser(User user, Long id, Long statusId);

    ResponseData getOneUser();

    ResponsePage getPagination(Integer activePage, Integer limit);
}
