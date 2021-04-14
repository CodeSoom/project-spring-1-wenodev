import React from 'react';
import ProblemList from './components/ProblemList';
import ProblemDetail from './components/ProblemDetail';
import { Route } from 'react-router-dom';

function App() {
  return (
    <div>
      <Route exact component={ProblemList} path="/" />
      <Route exact component={ProblemDetail} path="/problems/:id" />
    </div>
  );
}

export default App;
