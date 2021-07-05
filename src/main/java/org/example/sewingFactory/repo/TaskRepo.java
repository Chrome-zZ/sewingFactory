package org.example.sewingFactory.repo;

import org.example.sewingFactory.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {
}
