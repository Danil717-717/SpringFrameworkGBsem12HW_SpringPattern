package ru.springgb.sem12HW.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Task implements ITask{




    public enum Status{
        NORMAL_EXECUTION,
        URGENT_IMPLEMENTATION,
        NOT_STARTED,
        IN_PROGRESS,
        COMPLETED;

    }

    public enum TaskType{
        NORMAL_EXECUTION,
        URGENT_IMPLEMENTATION
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String description;


    @Enumerated(EnumType.STRING)
    private Status status;


    @Column
    @CreationTimestamp(source = SourceType.DB)
    private Timestamp createdAt;

    @Column
    @UpdateTimestamp(source = SourceType.DB)
    private Timestamp updatedAt;


    @ManyToMany
    @JoinTable(
            name = "execute_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "executor_id"))
    private List<Executor> executors = new ArrayList<>();


    @OneToMany
    private List<Subscriber> subs = new ArrayList<>();

    public Task() {
        // Nothing to do
    }

    public Task(String description) {
        this.description = description;
    }

    @Override
    public Long getTaskId() {
        return getId();
    }

    public void addExecutor(Executor executor) {
        this.executors.add(executor);

    }

    public void removeExecutor(long executorId) {
        Executor executor = this.executors.stream().filter(t -> t.getId() == executorId)
                .findFirst().orElse(null);
        if (executor != null) {
            this.executors.remove(executor);
            executor.getTasks().remove(this);
        }
    }


    public void addSubscribe(Subscriber sub){
        subs.add(sub);
    }


    public void delSubscribe(long subscriberId){
        Subscriber subscriber = this.subs.stream().filter(t -> t.getId() == subscriberId)
                .findFirst().orElse(null);
        if (subscriber != null) {
            this.subs.remove(subscriber);
            subscriber.getTasks().remove(this);
        }
    }

    public void notifySubscribers(){
        for (Subscriber sub:subs){
            sub.update();
        }
    }


}
