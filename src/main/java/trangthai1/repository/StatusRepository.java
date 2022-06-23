package trangthai1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import trangthai1.model.Entity.Status;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository< Status ,Long > {

    Boolean existsStatusByStatusname(String statusname);
    Boolean existsStatusByLevel(int level);

    @Query(value = "SELECT * FROM user_status.status where id = :statusId",nativeQuery = true)
     Status findStatus(Long statusId) ;

    @Query(value = "SELECT * FROM user_status.status\n" +
            "order by level asc",nativeQuery = true)
    List<Status> getAllStatusAndSort();
}
