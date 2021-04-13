package com.weno.content;

import com.weno.problem.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findAllByProblem(Problem problem);
}
