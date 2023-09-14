import React, { useEffect } from 'react';
import { useNavigate } from "react-router-dom";

const Logout: React.FC = () => {
    const navigate = useNavigate();

    useEffect(() => {
    localStorage.clear();
    navigate("/");

    return () => {
        console.log('Logged out');
      };
    }, []);

    return (
        <>
            <h2 style={{ textAlign: "center", }}>Log out</h2>
            <br />
            <p style={{ textAlign: "center", }}>You just logged out</p>
            <br />
        </>
    )
}

export default Logout;