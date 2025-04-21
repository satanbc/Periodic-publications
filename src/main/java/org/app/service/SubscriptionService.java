package org.app.service;

import org.app.dao.SubscriptionDAO;
import org.app.model.Subscription;

import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionService {

    private final SubscriptionDAO subscriptionDAO = new SubscriptionDAO();

    public List<Subscription> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionDAO.findAll();
        return subscriptions.stream()
                .map(subscription -> new Subscription(
                        subscription.getId(),
                        subscription.getEmail(),
                        subscription.getPublicationId(),
                        subscription.getMonths(),
                        subscription.getStartDate(),
                        subscription.getEndDate(),
                        subscription.isActive(),
                        subscription.getTotalPrice()
                ))
                .collect(Collectors.toList());
    }

    public List<Subscription> getSubscriptionsByEmail(String email) {
        List<Subscription> subscriptions = subscriptionDAO.findByEmail(email);
        return subscriptions.stream()
                .map(subscription -> new Subscription(
                        subscription.getId(),
                        subscription.getEmail(),
                        subscription.getPublicationId(),
                        subscription.getMonths(),
                        subscription.getStartDate(),
                        subscription.getEndDate(),
                        subscription.isActive(),
                        subscription.getTotalPrice()
                ))
                .collect(Collectors.toList());
    }

    public Long addSubscription(Subscription subscription) {

        return subscriptionDAO.addSubscription(subscription);
    }


    public Subscription getSubscriptionById(Long id) {
        return subscriptionDAO.getSubscriptionById(id);
    }
}
