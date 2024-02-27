package ru.springgb.sem12HW.service;

import ru.springgb.sem12HW.model.entity.Subscriber;

import java.util.List;

public interface SubscriberService {

    List<Subscriber> findAll();

    Subscriber findById(Long id);

    Subscriber save(Subscriber subscriber);

    Subscriber updateSubscriber(Long id, Subscriber subscriber);

    Subscriber apdateSubscriber(Subscriber subscriber);

    void deleteById(Long id);
}
