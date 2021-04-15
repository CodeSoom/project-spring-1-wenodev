import React, {useState, useEffect} from 'react';
import { withRouter } from 'react-router';
import * as API from '../services/api'

 function ProblemDetail({match}, props){

    const[problem, setProblem] = useState(null);
    const[answer, setAnswer] = useState([]);
 
    useEffect(() =>{
        API.fetchProblem(match.params.id)
        .then((response) => {
            setProblem(response);
            setAnswer(response.contents)
        })
        .catch((e) => {
            console.log(e);
        })
   
    }, [])

    function onChange(event, idx ){
        const data = answer;
        data.forEach( (t) => {
            if (t.id === idx) {
                t.userAnswer = event.target.value
            }
        })
        setAnswer(data);
    }

    function submitAnswer(){
        const data = {
            id : match.params.id,
            contents : answer
        }

        API.putProblem(data)
        .then((response) => {
            console.log(response);
            props.history.push('/result');
        })
        .catch(e => {
            console.log(e);
        })
        
    }

    if(!problem || !problem.contents){
        return null;
    }

    return(
        <div>
            <h2>{problem.title}</h2>
            {problem.contents.map((content,index) =>(
                <div key={content.id}>
                    <div>질문 : {content.question}</div>
                    <label>답변 : </label> <input id={content.id} type="text" name="userAnswer" onChange={(e) =>onChange(e, content.id,index)} />
                </div>
            ))}
            <button onClick={submitAnswer}>제출하기</button>
        </div>    
    )
}

export default withRouter(ProblemDetail);