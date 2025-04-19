package org.app.service;

import org.app.dao.SubscriptionDAO;
import org.app.dto.SubscriptionDTO;
import org.app.model.Subscription;

import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionService {

    private final SubscriptionDAO subscriptionDAO = new SubscriptionDAO();

    public List<SubscriptionDTO> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionDAO.findAll();
        return subscriptions.stream()
                .map(subscription -> new SubscriptionDTO(
                        subscription.getId(),
                        subscription.getUserId(),
                        subscription.getPublicationId(),
                        subscription.getMonths(),
                        subscription.getStartDate(),
                        subscription.getEndDate(),
                        subscription.isActive()
                ))
                .collect(Collectors.toList());
    }

    public Long addSubscription(Long userId, SubscriptionDTO dto) {
        Subscription subscription = new Subscription(
                null,
                userId,
                dto.getPublicationId(),
                dto.getMonths(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.isActive()
        );
        return subscriptionDAO.addSubscription(subscription);
    }

    public SubscriptionDTO getSubscriptionById(Long id) {
        return subscriptionDAO.getSubscriptionById(id);
    }
}
