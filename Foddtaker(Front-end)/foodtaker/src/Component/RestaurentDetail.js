import React, { useState, useEffect } from 'react';
import axios from 'axios';

const UserDetailPage = ({ registereddetail }) => {
    const [name, setName] = useState(registereddetail.name);
    const [ownerName, setownerName] = useState(registereddetail.ownerName);
    const [pincode, setpincode] = useState(registereddetail.pincode);
    const [location, setlocation] = useState(registereddetail.location);
    const [website, setwebsite] = useState(registereddetail.website);
    const [phoneNumber, setphoneNumber] = useState(registereddetail.phoneNumber);
    const [avgTransaction, setavgTransaction] = useState(registereddetail.avgTransaction);
    const [rating,setRating]=useState(registereddetail.rating);
    const [isEditing, setIsEditing] = useState(false);

  useEffect(() => {
    setName(registereddetail.name);
    setownerName(registereddetail.ownerName);
    setlocation(registereddetail.location);
    setpincode(registereddetail.pincode);
    setwebsite(registereddetail.website);
    setphoneNumber(registereddetail.phoneNumber);
    setavgTransaction(registereddetail.avgTransaction); 
    setRating(registereddetail.rating);
    
  }, [user]);

  const handleUpdate = async () => {
    const updatedUser = {
      id: registereddetail.id,
      name,
      ownerName,
      location,
      pincode,
      website,
      phoneNumber,
      avgTransaction,
      rating  
    };

    try {
      const response = await axios.put(`/merchant`, updatedUser);
      console.log('User updated:', response.data);
      setIsEditing(false);
    } catch (error) {
      console.error('Error updating user:', error);
    }
  };

  const handleDelete = async () => {
    try {
      await axios.delete(`/merchant/${registereddetail.id}`);
      console.log('User deleted');
      // Perform any necessary clean-up or redirection
    } catch (error) {
      console.error('Error deleting user:', error);
    }
  };

  return (
    <div>
      {isEditing ? (
        <>
          <h2>Edit User</h2>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <button onClick={handleUpdate}>Update</button>
          <button onClick={() => setIsEditing(false)}>Cancel</button>
        </>
      ) : (
        <>
          <h2>User Details</h2>
          <p>Restaurent Name: {registereddetail.name}</p>
          <p>Owner's Name: {registereddetail.ownerName}</p>
          <p>Address: {registereddetail.location}</p>
          <p>Pincode:{registereddetail.pincode}</p>
          <p>Website: {registereddetail.website}</p>
          <p>Phone No: {registereddetail.phoneNumber}</p>
          <p>Daily Avg Order Placed: {registereddetail.avgTransaction}</p>
          <p>Rating: {registereddetail.rating}</p>
          <button onClick={() => setIsEditing(true)}>Edit</button>
          <button onClick={handleDelete}>Delete</button>
        </>
      )}
    </div>
  );
};

export default UserDetailPage;
