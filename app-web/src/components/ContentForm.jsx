import React from "react";

export default function ContentForm({question, answer, onChange, createContent}){
    return (
        <div>
          <input
            type="text"
            name="question"
            placeholder="질문"
            onChange={onChange}
            value={question}
          />
          <input
            type="text"
            name="answer"
            placeholder="답변"
            onChange={onChange}
            value={answer}
          />
          <button onClick={createContent}>등록</button>
        </div>
      );
}