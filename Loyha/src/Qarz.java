import java.time.LocalDateTime;
import java.time.LocalTime;

public class Qarz {
    private Long id;
    private Long userId;
    private Long maeketId;
    private double amount;
    private  java.time.LocalTime createdAt;

    public Qarz(Long id, Long userId, Long maeketId, double amount, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.maeketId = maeketId;
        this.amount = amount;
        this.createdAt = LocalTime.from(createdAt);
    }

    public Long getUserId() {
        return userId;
    }

    public Long getMaeketId() {
        return maeketId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalTime getCreatedAt() {
        return createdAt;
    }

    public String getMarketId() {
        return "";
    };
}
