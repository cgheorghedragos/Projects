import './App.css';
import CollectionsList from './CollectionsList';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import EditElement from './EditElement';

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/' element={<div className="displayCard">
          <CollectionsList />
        </div>} />

        <Route path='/edit/:id' element={
          <div>
            <EditElement/>
          </div>
        } />
      </Routes>
    </Router>

  );
}

export default App;
