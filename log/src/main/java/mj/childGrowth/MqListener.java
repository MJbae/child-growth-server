package mj.childGrowth;

import mj.childGrowth.config.TypeConverter;
import mj.childGrowth.domain.HeightRangeRequestLogRepository;
import mj.childGrowth.domain.HeightRequestLog;
import mj.childGrowth.domain.Sex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MqListener {
    public final HeightRangeRequestLogRepository repository;
    private final TypeConverter converter = new TypeConverter();
    private final MessageExtractor extractor = new MessageExtractor();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final int HEIGHT = 0;
    private final int MONTH = 1;
    private final int SEX = 2;

    public MqListener(HeightRangeRequestLogRepository repository){
        this.repository = repository;
    }

    @RabbitListener(queues = "cg_q")
    public void receiveMessage(final Message message) {
        List<String> bodyAsList = extractor.extractAsList(message);

        try {
            Float height = Float.parseFloat(bodyAsList.get(HEIGHT));
            Integer monthAfterBirth = Integer.parseInt(bodyAsList.get(MONTH));
            Sex sex = converter.convert(bodyAsList.get(SEX));
            logger.info("Logging Request Parameters in Interceptor: height={}, monthAfterBirth={}, sex={}",
                    height, monthAfterBirth, sex);

            repository.save(new HeightRequestLog(height, monthAfterBirth, sex));
        } catch (NumberFormatException e) {
            logger.info("NumberFormatException Caused by {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.info("Invalid Request Param in Sex Caused by {}", e.getMessage());
        }
    }
}
