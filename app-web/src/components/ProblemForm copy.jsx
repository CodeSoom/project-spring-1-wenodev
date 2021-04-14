// import React, { useState, useEffect } from 'react';

// export default function ProblemForm() {
//   const [inputs, setInputs] = useState({
//     question: '',
//     answer: '',
//   });


//   const [problem, setProblem] = useState({
//     title: '',
//     contents: [
//       {
//         question: '',
//         answer: '',
//       },
//     ],
//   });

//   useEffect(() => {
//     console.log(problem);
//   });

//   function handleChange(event) {
//     const { name, value } = event.target;
//     setProblem({
//       ...problem,
//       [name]: value,
//     });
//   }

//   function addContent() {
//     const content = {
//       question: '',
//       answer: '',
//     };
//     //   problem.contents.push(content);
//     setProblem({
//       ...problem,
//       contents: [...problem.contents, content],
//     });
//   }

//   function deleteContent(index) {
//     problem.contents.filter((content, contentIndex) => contentIndex !== index);
//     console.log(problem);
//   }

//   return (
//     <div>
//       <h2>문제</h2>
//       <label id="title">제목</label>
//       <input type="text" name="title" onChange={handleChange} />
//       <button onClick={addContent}>추가</button>
//       {problem.contents?.map((content, index) => (
//         <div key={index}>
//           <label id="question">질문</label>
//           <input
//             type="text"
//             name="question"
//             value={content.question}
//             onChange={handleChange}
//           />
//           <label id="answer">답변</label>
//           <input
//             type="text"
//             name="answer"
//             value={content.question}
//             onChange={handleChange}
//           />
//           <button onClick={() => deleteContent(index)}>제거</button>
//         </div>
//       ))}
//       <button>등록</button>
//     </div>
//   );
// }
