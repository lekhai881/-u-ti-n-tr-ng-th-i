package trangthai1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import trangthai1.model.Entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository < User, Long > {

    @Query(value = "SELECT * FROM user_status.user\n" +
            "join status on status.id = user.status_id\n" +
            "order by level, time_update asc",
            countQuery = "SELECT count(*) FROM user_status.user\n" +
                    "join status on status.id = user.status_id\n" +
                    "order by level, time_update asc"
            ,nativeQuery = true)
    Page < User > findPagination(Pageable pageable); //Dùng Page<User> thì query phải thêm countQuery mới chạy được!ok men!

    @Query(value = "select user.status_id from user_status.user where id = :id", nativeQuery = true)
    Long existsStatusId(Long id);

    @Query(value = "select user.note from user_status.user where id = :id", nativeQuery = true)
    String existsNote(Long id);

    @Query(value = "SELECT user.id,   user.status_id ,user.username,status.status_name,user.note,user.time_update FROM user_status.user\n" +
            "join status on status.id = user.status_id\n" +
            "order by level, time_update asc ", nativeQuery = true)
    List < User > getUserAndSort();

    @Query(value = "SELECT user.id,   user.status_id ,user.username,status.status_name,user.note,user.time_update FROM user_status.user\n" +
            "join status on status.id = user.status_id\n" +
            "order by level, time_update asc \n" +
            "limit 1", nativeQuery = true)
    User getOneUser();

}
