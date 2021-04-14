import React from 'react';
import ProblemList from './components/ProblemList';
import ProblemDetail from './components/ProblemDetail';
import ProblemForm from './components/ProblemForm';

import { Route } from 'react-router-dom';

function App() {
  return (
    <div>
      <Route exact component={ProblemList} path="/" />
      <Route exact component={ProblemDetail} path="/problems/:id" />
      <Route exact component={ProblemForm} path="/problems/form" />
    </div>
  );
}

export default App;
