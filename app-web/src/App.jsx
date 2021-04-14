import React from 'react';
import ProblemList from './components/ProblemList';
import ProblemDetail from './components/ProblemDetail';
import ProblemForm from './components/ProblemForm';

import { Route } from 'react-router-dom';

function App() {
  return (
    <div>
      <Route exact path="/" component={ProblemList} />
      <Route exact path="/problems" component={ProblemList} />
      <Route exact path="/problems/:id" component={ProblemDetail} />
      <Route exact path="/form" component={ProblemForm} />
    </div>
  );
}

export default App;
