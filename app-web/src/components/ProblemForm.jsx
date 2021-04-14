import React, {useState} from 'react';
import ContentForm from './ContentForm';
import ContentList from './ContentList';

export default function ProblemForm() {

  const [title, setTitle] = useState()

  const [inputs, setInputs] = useState({
    question: '',
    answer: '',
  });

  const[contents, setContents] = useState([]);
  const {question, answer} = inputs;

  function onChangeTitle(event){
    setTitle(event.target.value);
  }

  function onChange(event){
    const{name, value} = event.target;
    setInputs({
      ...inputs,
      [name]: value
    })
  }

  function createContent(){
    const content = {
      question,
      answer
    };
    setContents(contents.concat(content));
    setInputs({
      question: '',
      answer: '',
    })
  }

  function deleteContent(index){
    contents.splice(index, 1);
    setContents(contents);
    setInputs({
      question: '',
      answer: '',
    })
  }

  function submitProblem(){
    const problems = {
      title : title,
      contents : contents
    }
    console.log(problems);
  }

  return(
    <div>
      <h2>문제를 만들어 주세요</h2>
      <label>제목</label>
      <input type="text" onChange={onChangeTitle}></input>
      <ContentForm
        question={question}
        answer={answer}
        onChange={onChange}
        createContent={createContent}
      />
      <ContentList 
      contents={contents} 
      deleteContent={deleteContent}
      />
      <button onClick={submitProblem}>이대로 만들기</button>
    </div>
  )

}
