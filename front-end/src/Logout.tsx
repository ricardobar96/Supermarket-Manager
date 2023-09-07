import React, { useRef } from 'react';

export default function Logout() {
    localStorage.clear();
    //window.location.href = '/';

    return (
        <>
            <h2 style={{ textAlign: "center", }}>Cierre de sesión</h2>
            <br />
            <p style={{ textAlign: "center", }}>Usted acaba de finalizar la sesión</p>
            <br />
        </>
    )
}
