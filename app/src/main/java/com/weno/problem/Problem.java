package com.weno.problem;

import com.weno.problem.dto.ProblemRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Builder
    public Problem(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public void updateProblem(ProblemRequestDto request) {
        this.title = request.getTitle();
    }
}
