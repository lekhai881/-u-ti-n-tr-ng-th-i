package trangthai1.service.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import trangthai1.model.Dto.StatusDto;
import trangthai1.model.Response.ResponseData;
import trangthai1.model.Entity.Status;
import trangthai1.repository.StatusRepository;
import trangthai1.service.Mapper;

import java.util.List;
import java.util.Optional;

@Component
public class StatusImp implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public ResponseData save(Status status) {
        try {
            Status status1 = statusRepository.save(status);
            return new ResponseData("true", "Them moi thanh cong!", status1);
        } catch (Exception e) {
            if (statusRepository.existsStatusByStatusname(status.getStatusname())) {
                return new ResponseData("false", status.getStatusname() + " da duoc su dung! Nhap lai!");
            }
            if (statusRepository.existsStatusByLevel(status.getLevel())) {
                return new ResponseData("false", "Muc do uu tien " + status.getLevel() + " da duoc su dung! Nhap lai!");
            }
        }
        return null;
    }

    @Override
    public ResponseData updateStatus(Status status, Long id) {
        try{
            final Optional < Status > status1 = statusRepository.findById(id).map(status2 -> {
                status2.setStatusname(status.getStatusname());
                status2.setLevel(status.getLevel());
                return statusRepository.save(status2);
            });
            return new ResponseData("true","cap nhat trang thai thanh cong!",status1);
        }catch (Exception e){
            if (statusRepository.existsStatusByStatusname(status.getStatusname())) {
                return new ResponseData("false", status.getStatusname() + " da duoc su dung! Nhap lai!");
            }
            if (statusRepository.existsStatusByLevel(status.getLevel())) {
                return new ResponseData("false", "Muc do uu tien " + status.getLevel() + " da duoc su dung! Nhap lai!");
            }
        }
        return null;
    }

    @Override
    public ResponseData getAllStatusAndSort() {
        List < Status > allStatusAndSort = statusRepository.getAllStatusAndSort();
        return new ResponseData("true","Danh sach status da duoc sap xep: ",allStatusAndSort) ;
    }

    @Override
    public ResponseData deleteStatus(Long id) {
        Optional < Status > status = statusRepository.findById(id);
        try {
            statusRepository.deleteById(id);
            StatusDto statusDto = Mapper.changeStatusDto(status.get());
            return new ResponseData("true","Xoa thong tin thanh cong!",statusDto);
        }catch (Exception e){
                return new ResponseData("false","Không xóa được. "+status.get().getStatusname()+" đang liên kết với người dùng! Phải xóa liên kết người dùng trước");
        }
    }
}
