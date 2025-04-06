package org.app.service;

import org.app.dao.SubscriptionDAO;
import org.app.dto.SubscriptionDTO;
import org.app.mapper.SubscriptionMapper;
import org.app.model.Subscription;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class SubscriptionService {
    private static final Logger logger = LogManager.getLogger(SubscriptionService.class);
    private final SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
    private final SubscriptionMapper mapper = SubscriptionMapper.INSTANCE;

    public void createSubscription(Long userId, SubscriptionDTO dto) {
        if (dto.getMonths() <= 0) {
            throw new IllegalArgumentException("Subscription months must be greater than 0");
        }

        LocalDate start = LocalDate.now();
        LocalDate end = start.plusMonths(dto.getMonths());

        Subscription subscription = Subscription.builder()
                .userId(userId)
                .publicationId(dto.getPublicationId())
                .months(dto.getMonths())
                .startDate(start)
                .endDate(end)
                .build();

        subscriptionDAO.save(subscription);
        logger.info("Created subscription: {}", subscription);
    }

    public List<SubscriptionDTO> getUserSubscriptions(Long userId) {
        List<SubscriptionDTO> subscriptions = mapper.toDTOList(subscriptionDAO.findByUserId(userId));
        logger.info("Fetched {} subscriptions for user {}", subscriptions.size(), userId);
        return subscriptions;
    }
}
