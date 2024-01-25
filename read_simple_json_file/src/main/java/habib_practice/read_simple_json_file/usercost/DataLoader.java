package habib_practice.read_simple_json_file.usercost;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DataLoader implements CommandLineRunner {

    private final ObjectMapper objectMapper;
    private final UserCostRepository userCostRepository;

    public DataLoader(ObjectMapper objectMapper, UserCostRepository userCostRepository) {
        this.objectMapper = objectMapper;
        this.userCostRepository = userCostRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<UserCost> userCosts = new ArrayList<>();
        JsonNode json;
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/UserCosts.json")) {
            json = objectMapper.readValue(inputStream, JsonNode.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file!", e);
        }

        for (JsonNode userCostNode : json) {
            userCosts.add(createUserCostFromNode(userCostNode));
        }

        userCostRepository.saveAll(userCosts);
    }

    private UserCost createUserCostFromNode(JsonNode userCostNode) {
        String id = UUID.randomUUID().toString();
        String category = userCostNode.get("category").asText();
        LocalDate date = LocalDate.parse(userCostNode.get("date").asText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        BigDecimal cost = BigDecimal.valueOf(userCostNode.get("cost").asDouble());

        return new UserCost(id, category, date, cost, null);
    }
}