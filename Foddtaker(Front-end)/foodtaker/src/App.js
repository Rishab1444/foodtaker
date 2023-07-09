import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route,Navigate} from 'react-router-dom';
import HomePage from './Component/HomePage';
import LoginPage from './Component/LoginPage.js';
import RegisterPage from './Component/RegistrationForm.js';
import RestaurantListPage from './Component/RestaurentList.js';
import RestaurantDetail from './Component/RestaurentDetail.js'

const App = () => {
    
    return (
      <Router>
        <Routes>
          <Route exact path="/" element={<HomePage/>} />
          <Route path="/signin" element = {
           <LoginPage/>}
          />
          <Route path="/register" element={<RegisterPage/>} />
          <Route path="/restaurants" element={<RestaurantListPage/>} />
          <Route path="/restaurantdetail" element={<RestaurantDetail/>} />
        </Routes>
      </Router>
    );
};

export default App;
