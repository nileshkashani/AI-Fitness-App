// App.jsx
import React from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Starter from './components/Starter';
import ActivityForm from './components/ActivityForm';
import Activities from './components/Activities';
import ActivityDetails from './components/ActivityDetails';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Starter />} />
        <Route path='/activityForm' element={<ActivityForm/>}></Route>
        <Route path='/allActivities' element={<Activities/>}></Route>
        <Route path='/activityDetail' element={<ActivityDetails/>}></Route>
      </Routes>
    </Router>
  );
}

export default App;
