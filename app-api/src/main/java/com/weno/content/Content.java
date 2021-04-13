package com.weno.content;

import com.weno.problem.Problem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String question;

    @Column
    private String answer;

    @Column
    private String userAnswer;

    @JoinColumn(name = "problem_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Problem problem;

    @Builder
    public Content(Long id, String question, String answer, String userAnswer, Problem problem) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.userAnswer = userAnswer;
        this.problem = problem;
    }

    public void updateContent(String question, String answer, String userAnswer) {
        this.question = question;
        this.answer = answer;
        this.userAnswer = userAnswer;
    }

    public void updateProblem(Problem problem) {
        this.problem = problem;
    }
}
