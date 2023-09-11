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
        <main>
            <section id="form" className="text-center">
                <h1 className="lead display-3 text-danger pt-5 fw-bold">Login</h1>
                <p className="mt-4 fw-medium text-muted">Enter your username and password</p>
                <br />
                <form onSubmit={login} style={{ textAlign: "center", }}>
                    Nombre: <input type="text" ref={nombreUser} /><br />
                    <br />
                    Password: <input type="password" ref={passwordUser} /><br />
                    <br />
                    <button type="submit">Login </button>
                </form>
            </section>
        </main>
    )
}