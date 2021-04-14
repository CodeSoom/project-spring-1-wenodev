import React, {useState, useEffect} from 'react';
import * as API from '../services/api'
import { withRouter } from "react-router";


function ProblemDetail({match}){

    const[problem, setProblem] = useState(null);

    useEffect(() =>{
        API.fetchProblem(match.params.id)
        .then((response) => {
            setProblem(response);
        })
        .catch((e) => {
            console.log(e);
        })
    }, [])

    if(!problem || !problem.contents){
        return null;
    }

    return(
        <ul>
            <li>{problem.id}</li>
            <li>{problem.title}</li>
            {problem.contents.map((content) =>(
                <ul key={content.id}>
                    <li>{content.question}</li>
                </ul>
            ))}
        </ul>    
    )
}

export default withRouter(ProblemDetail);