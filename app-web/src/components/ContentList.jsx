import React from "react";

export default function ContentList({contents, deleteContent}){
    return(
        <div>
        {contents?.map((content, index) => (
        <div key={index}>
            <input
            type="text"
            name="question"
            value={content.question}
            disabled
          />
          <input
            type="text"
            name="answer"
            value={content.answer}
            disabled
          />
          <button onClick={() => deleteContent(index)}>지우기</button>
        </div>
      ))}
        </div>
    )
}