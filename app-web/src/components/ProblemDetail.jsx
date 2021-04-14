import React, {useState, useEffect} from 'react';
import { withRouter } from 'react-router';
import * as API from '../services/api'


 function ProblemDetail({match}){

    const[problem, setProblem] = useState(null);
    const[answer, setAnswer] = useState([]);

    useEffect(() =>{
        API.fetchProblem(match.params.id)
        .then((response) => {
            setProblem(response);
        })
        .catch((e) => {
            console.log(e);
        })
   
    }, [])

    function onChange(event){
        const{id, value} = event.target;
        const data = {
            id : id,
            value : value
        }
        setAnswer(data)
    }

    function submitAnswer(){
        const data = {
            id : match.params.id,
            userAnswer : answer
        }
        console.log(data);
    }

    if(!problem || !problem.contents){
        return null;
    }

    return(
        <div>
            <h2>{problem.title}</h2>
            {problem.contents.map((content) =>(
                <div key={content.id}>
                    <div>질문 : {content.question}</div>
                    <label>답변 : </label> <input id={content.id} type="text" name="userAnswer" value={content.useAnswer} onChange={onChange} />
                </div>
            ))}
            <button onClick={submitAnswer}>제출하기</button>
        </div>    
    )
}

export default withRouter(ProblemDetail);