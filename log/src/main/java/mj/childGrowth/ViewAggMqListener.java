package mj.childGrowth;

import mj.childGrowth.config.TypeConverter;
import mj.childGrowth.domain.HeightRangeRequestLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class ViewAggMqListener {

    public final HeightRangeRequestLogRepository repository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ViewAggMqListener(HeightRangeRequestLogRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "cg_agg_q")
    public void receiveMessage(final Message message) {
        logger.info("Testing cg agg q");
    }
}
