package habib_practice.read_simple_json_file.usercost;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
public record UserCost(
        @Id String id,
        String category,
        LocalDate date,
        BigDecimal cost,
        @Version
        Integer version
) {
}
