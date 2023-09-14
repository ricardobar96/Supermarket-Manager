import React, { useRef } from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";

export default function Inicio() {

    const nombreUser = useRef<HTMLInputElement>(null);
    const passwordUser = useRef<HTMLInputElement>(null);
    const navigate = useNavigate();

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
                navigate("/api/v1/productos");
                window.location.reload();
            } catch (error) {
                console.log(error);
            }
        }
        axiospost("http://localhost:8080/api/login");
    }


    return (
        <main className="w-50 mx-auto rounded" style={{background: "#d6eaf8"}}>
            <section id="form" className="text-center">
                <h1 className="lead display-3 text-primary pt-5 fw-bold">SManager</h1>
                <p className="mt-4 pb-2 fw-medium text-muted">Enter your username and password</p>
                <form onSubmit={login}>
                    <input type="text" ref={nombreUser} className="text-center border border-primary rounded mb-3"
                     placeholder="Username"/>
                    <br />
                    <input type="password" ref={passwordUser} className="text-center border border-primary rounded mb-3" 
                    placeholder="Password"/>
                    <br />
                    <button type="submit" className="btn btn-outline-light btn-primary btn-modified w-25 mt-3 mb-5">
                        <span className="fw-bold">Login</span> 
                    </button>
                </form>
            </section>
        </main>
    )
}