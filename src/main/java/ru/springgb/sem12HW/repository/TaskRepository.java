package ru.springgb.sem12HW.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.springgb.sem12HW.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query(value = "select * from tasks s where s.status like %:keyword% "
//           +  " OR s.description LIKE %:keyword% "
//           + " OR s.completionTime LIKE %:keyword% "
            , nativeQuery = true)
    public List<Task> findAll(String keyword);
}
