import React from 'react';
import { Link } from 'react-router-dom';
import "../style/Home.css";
import styled from "styled-components";
import { Button, Checkbox, Form, Input } from "antd";

const Navbar = styled.div`
height: 100px;
width: 100%;
background-color: lightblue;
display: flex;
justify-content: space-between;
align-items: center;
padding: 0 0px;
`;
const HeadingText = styled.div`
  font-size: 34px;
  color: white;
  font-style :Arial;
`;

const NavIcons = styled.div`
  display: flex;
  justify-content: space-evenly;
  gap: 8px;
`;
const NavItem = styled(Link)`

`
const Bodyheader = styled.main`
    padding: 5 6px;
    align-items:centre`;

const HomePage1 = () => {
  return (
    <div>
      <Navbar>
      <HeadingText>
        FoodTaker
      </HeadingText>
      <NavIcons>
        <NavItem to={"/signin"} >
          Sign In
        </NavItem>
        <NavItem to={"/register"}>
            Register
        </NavItem>
        </NavIcons>
      </Navbar>
      <Bodyheader>
        <h1>Welcome to the FoodTaker !!</h1>
        <p>This is a self Onboarding Portal for all Restaurent.<br/>
            You can add your ssrestaurent to our delivering app by clicking on Add Your Restaurent.<br/>
            If already a user,Log in to update your information or to view your rating. <br/></p>
      </Bodyheader>
    </div>
  );
};

export default HomePage1;
