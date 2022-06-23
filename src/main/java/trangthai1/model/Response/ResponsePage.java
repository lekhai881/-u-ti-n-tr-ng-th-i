package trangthai1.model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePage {
    private String status;
    private String message;
    private long activePage;
    private long limit;
    private long totalPage;
    private Object data;

    public ResponsePage(Object data) {
        this.data = data;
    }
}
