package org.app.service;

import org.app.dao.SubscriptionDAO;
import org.app.dto.SubscriptionDTO;
import org.app.mapper.SubscriptionMapper;
import org.app.model.Subscription;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionService {
    private final SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
    private final SubscriptionMapper mapper = SubscriptionMapper.INSTANCE;

    public void createSubscription(Long userId, SubscriptionDTO dto) {
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
    }

    public List<SubscriptionDTO> getUserSubscriptions(Long userId) {
        return subscriptionDAO.findByUserId(userId)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
