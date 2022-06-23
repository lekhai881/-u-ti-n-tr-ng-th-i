package trangthai1.service.status;

import org.springframework.stereotype.Service;
import trangthai1.model.Response.ResponseData;
import trangthai1.model.Entity.Status;

@Service
public interface StatusService {
    ResponseData save(Status status);

    ResponseData updateStatus(Status status, Long id);

    ResponseData getAllStatusAndSort();

    ResponseData deleteStatus(Long id);
}
