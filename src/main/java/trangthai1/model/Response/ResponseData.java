package trangthai1.model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
    private String status;
    private String message;
    private Object data;

    public ResponseData(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
