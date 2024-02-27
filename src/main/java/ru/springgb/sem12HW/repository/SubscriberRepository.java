package ru.springgb.sem12HW.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.springgb.sem12HW.model.entity.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

}
