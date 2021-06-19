package Exam.models;

public class FavoriteRecord {
    private String userId;
    private String itemId;

    public FavoriteRecord() {
    }

    public FavoriteRecord(String userId, String itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "FavoriteRecord{" +
                "userId='" + userId + '\'' +
                ", itemId='" + itemId + '\'' +
                '}';
    }
}
