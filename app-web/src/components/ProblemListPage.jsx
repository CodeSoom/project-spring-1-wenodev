import React, { useState, useEffect } from 'react';

export default function ProblemListPage() {

    const [problems, setProblems] = useState(
        [
            {id : 1, title : "t1"},
            {id : 2, title : "t2"}
        ]
    );


    useEffect(() =>{

    })

    return(
        <ul>
            dd
            {problems.map((problem) => (
                <li key={problem.id}>
                    {problem.name}
                </li>
            ))}
        </ul>    
    );
}