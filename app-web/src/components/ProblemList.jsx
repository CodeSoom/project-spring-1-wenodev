import React, { useState, useEffect } from 'react';
import * as API from "../services/api";

export default function ProblemListPage() {

    const [problems, setProblems] = useState(null);

    useEffect(() => {
        API.fetchProblems()
        .then((response) => {
            console.log(response);
            setProblems(response);
        })
        .catch((e) => {
            console.log(e);
        })
    }, []);

    if(!problems){
        return null;
    }

    return(
        <ul>
            {problems.map((problem) =>(
                <div key={problem.id}>
                <li>{problem.id}</li>
                <li>{problem.title}</li>
                <Link to={`/product/${problem.id}`}>
                    <button>문제 풀기</button>
                </Link>
                </div>
            ))}
        </ul>    
    );
}