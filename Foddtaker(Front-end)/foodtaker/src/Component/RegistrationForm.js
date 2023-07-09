import React, { useState } from 'react';
import axios from 'axios';
import styled from "styled-components";


const RegistrationForm = () => {
  const [name, setName] = useState('');
  const [ownerName, setownerName] = useState('');
  const[email,setemail]=useState('');
  const[password,setpassword] = useState('');
  const [pincode, setpincode] = useState('');
  const [location, setlocation] = useState('');
  const [website, setwebsite] = useState('');
  const [phoneNumber, setphoneNumber] = useState('');
  const [avgTransaction, setavgTransaction] = useState('');

  const handleSubmit = async (e) => {
    // e.preventDefault();

    const newUser = {
      name,
      ownerName,
      email,
      password,
      location,
      pincode,
      website,
      phoneNumber,
      avgTransaction
    };

    try {
      const response = await axios.post('http://localhost:8080/merchant', newUser, { withCredentials: true });
      console.log('User registered:', response.data);
      // Reset form values
      setName('');
      setownerName('');
      setemail('');
      setpassword('');
      setlocation('');
      setpincode('');
      setwebsite('');
      setphoneNumber('');
      setavgTransaction('');
      
      alert(`Thank you for registration}!`);
    } catch (error) {
      console.error('Error registering user:', error);
    }
  };
  const Navbar = styled.div`
height: 100px;
width: 100%;
background-color: lightblue;
display: flex;
justify-content:center;
align-items: center;
padding: 0 0px;
`;
const HeadingText = styled.div`
  font-size: 34px;
  color: white;
  font-style :Arial;
`;

  return (

    
    <div>
    <Navbar>
    <HeadingText>
        FoodTaker
      </HeadingText>
      </Navbar>
      <h2>Registration Form</h2>
      <form onSubmit={handleSubmit}>
       <br/> Restaurent Name :  <input
          type="text"
          placeholder="Restaurent Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        /><br/>
        Owner's Name  <input type="text"
          placeholder="Owner's Name"
          value={ownerName}
          onChange={(e) => setownerName(e.target.value)}
        />
        <br/>
        E-mail
        <input
          type="text"
          placeholder="Daily Avg Order Placed"
          value={email}
          onChange={(e) => setemail(e.target.value)}
        /> <br/> Password
        <input
          type="text"
          placeholder="Password"
          value={password}
          onChange={(e) => setpassword(e.target.value)}
        />
        Address
        <input
          type="text"
          placeholder="Address"
          value={location}
          onChange={(e) => setlocation(e.target.value)}
        />
         Pincode:
        <input
          type="text"
          placeholder="Pincode"
          value={setpincode}
          onChange={(e) => setpincode(e.target.value)}
        />
        <br/>
        website
        <input
          type="text"
          placeholder="website"
          value={website}
          onChange={(e) => setwebsite(e.target.value)}
        />
        <br/>
        Phone No.
        <input
          type="text"
          placeholder="Phone No."
          value={phoneNumber}
          onChange={(e) => setphoneNumber(e.target.value)}
        />
        <br/>
        Daily Avg Order Placed
        <input
          type="text"
          placeholder="Daily Avg Order Placed"
          value={avgTransaction}
          onChange={(e) => setavgTransaction(e.target.value)}
        />
        <br/>
        <button type="submit" color='green'>Register</button>
      </form>
    </div>
  );
};

export default RegistrationForm;
