package mj.childGrowth;

import mj.childGrowth.domain.HeightAggregationRepository;
import mj.childGrowth.domain.HeightRequestAggregation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class ViewAggMqListener {

    public final HeightAggregationRepository repository;
    private final MessageExtractor extractor = new MessageExtractor();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final int TOTAL_COUNT = 0;
    private final int MALE_COUNT = 1;
    private final int FEMALE_COUNT = 2;
    private final int MONTH_AVERAGE = 3;
    private final int HEIGHT_AVERAGE = 4;

    public ViewAggMqListener(HeightAggregationRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "cg_agg_q")
    public void receiveMessage(final Message message) {
        List<String> bodyAsList = extractor.extractAsList(message);

        try {
            int totalRequestCount = Integer.parseInt(bodyAsList.get(TOTAL_COUNT));
            int maleCount = Integer.parseInt(bodyAsList.get(MALE_COUNT));
            int femaleCount = Integer.parseInt(bodyAsList.get(FEMALE_COUNT));
            float monthAverage = Float.parseFloat(bodyAsList.get(MONTH_AVERAGE));
            float heightAverage = Float.parseFloat(bodyAsList.get(HEIGHT_AVERAGE));
            Optional<HeightRequestAggregation> aggregation = repository.findFirstByCreatedAtBefore(LocalDateTime.now());

            if (aggregation.isEmpty()) {
                repository.save(new HeightRequestAggregation(totalRequestCount, maleCount, femaleCount,
                        monthAverage, heightAverage));
                return;
            }

            HeightRequestAggregation aggregationPresent = aggregation.get();
            aggregationPresent.updateAll(totalRequestCount, maleCount, femaleCount,
                    monthAverage, heightAverage);

            repository.save(aggregationPresent);

        } catch (NumberFormatException e) {
            logger.info("NumberFormatException Caused by {}", e.getMessage());
        }
    }
}
