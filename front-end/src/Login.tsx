import React, { useRef } from 'react';
import axios from 'axios';
export default function Inicio() {

    const nombreUser = useRef<HTMLInputElement>(null);
    const passwordUser = useRef<HTMLInputElement>(null);

    const login = (event: React.FormEvent<HTMLFormElement>) => {

        event.preventDefault();
        let formulario: HTMLFormElement = event.currentTarget;

        let nombreI = nombreUser.current?.value;
        let passwordI = passwordUser.current?.value;

        let login = {
            name: nombreI,
            password: passwordI
        }
        const axiospost = async (rutaDeLogin: string) => {
            try {
                const { data } = await axios.post(rutaDeLogin, login)
                localStorage.clear();
                localStorage.setItem("token", data);
            } catch (error) {
                console.log(error);
            }
        }
        axiospost("http://localhost:8080/api/login");
    }


    return (
        <>
            <h2 style={{ textAlign: "center", }}>Aplicación Supermercado</h2>
            <br />
            <p style={{ textAlign: "center", }}>Por favor, introduzca sus datos para iniciar sesión:</p>
            <br />
            <form onSubmit={login} style={{ textAlign: "center", }}>
                Nombre: <input type="text" ref={nombreUser} /><br />
                <br />
                Password: <input type="password" ref={passwordUser} /><br />
                <br />
                <button type="submit">Login </button>
            </form>
        </>
    )
}