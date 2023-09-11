import React, { useRef } from 'react';

export default function Logout() {
    localStorage.clear();
    //window.location.href = '/';

    return (
        <>
            <h2 style={{ textAlign: "center", }}>Log out</h2>
            <br />
            <p style={{ textAlign: "center", }}>You just logged out</p>
            <br />
        </>
    )
}
