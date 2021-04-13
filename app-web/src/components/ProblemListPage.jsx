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
                {problem.contents.map((content) =>(
                        <ul key={content.id}>
                            <li>{content.question}</li>
                        </ul>
                    ))}
                </div>
            ))}
        </ul>    
    );
}