import ProblemList from './components/ProblemList';
import ProblemDetail from './components/ProblemDetail'
import Route from 'react-router-dom';

function App() {
  return (
    <div>
      <Route expact component={ProblemList} path="/" />
      <Route expact component={ProblemDetail} path="/problem/:id" />
    </div>
  );
}

export default App;
