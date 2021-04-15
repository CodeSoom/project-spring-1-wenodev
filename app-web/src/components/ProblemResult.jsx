import React, {useState, useEffect} from "react";
import * as API from "../services/api";

export default function ProblemResult(){

    const[problem, setProblem] = useState();

    useEffect(()=> {
        API.fetchProblems
        .then((response) => {
            setProblem(response);
        })
        .catch(e => {
            console.log(e);
        })
    }, [])

    if (!problem) {
        return null;
    }

    return(<div>
        <h2>확인 페이지</h2>
        <h3>{problem.title}</h3>
        {problem.contents.map(content => (
            <div>
            <div>질문 : {content.question}</div>
            <div>내가 제출한 답변 : {content.userAnswer}</div>
            <div>정답 : {content.answer}</div>
            </div>
        ))}
    </div>)
}