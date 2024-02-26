package ru.springgb.sem12HW.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.springgb.sem12HW.model.entity.Executor;

public interface ExecutorRepository extends JpaRepository<Executor, Long> {

}
