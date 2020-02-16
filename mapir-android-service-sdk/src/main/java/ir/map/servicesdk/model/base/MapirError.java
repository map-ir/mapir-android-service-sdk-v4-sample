package ir.map.servicesdk.model.base;

public class MapirError extends BaseModel {

    private String errorMessage;
    private int errorCode;

    public MapirError(String errorMessage, int errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
