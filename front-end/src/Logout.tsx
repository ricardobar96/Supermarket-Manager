import React, { useEffect } from 'react';
import { useNavigate } from "react-router-dom";

const Logout: React.FC = () => {
    const navigate = useNavigate();

    useEffect(() => {
    localStorage.clear();
    navigate("/");
    window.location.reload();

    return () => {
        console.log('Logged out');
      };
    }, []);

    return (
        <>
        </>
    )
}

export default Logout;