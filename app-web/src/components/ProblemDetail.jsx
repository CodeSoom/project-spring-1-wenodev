import React, {useState, useEffect} from 'react';
import { withRouter } from 'react-router';
import * as API from '../services/api'


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
                <div key={content.id}>
                    <div>질문 : {content.question}</div>
                    <label>답변 : </label> <input type="text" />
                </div>
            ))}
        </ul>    
    )
}

export default withRouter(ProblemDetail);