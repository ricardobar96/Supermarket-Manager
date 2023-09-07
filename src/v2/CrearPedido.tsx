import React, { useRef } from 'react'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
export default function CreatePedido() {
    let navigate = useNavigate();
    const fechaP = useRef<HTMLInputElement>(null);
    const pagadoP = useRef<HTMLInputElement>(null);
    const enviadoP = useRef<HTMLInputElement>(null);
    const entregadoP = useRef<HTMLInputElement>(null);

    const agregarPedidoApi = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        let formulario: HTMLFormElement = event.currentTarget;
        let fecha = fechaP.current?.value;
        let pagado = pagadoP.current?.value;
        let enviado = enviadoP.current?.value;
        let entregado = entregadoP.current?.value;

        const newPedido = {
            "fecha": fecha,
            "pagado": pagado,
            "enviado": enviado,
            "entregado": entregado
        }
        let ruta = "http://localhost:8080/api/v2/pedidos";
        const axiospost = async (rutaDePedido: string) => {
            try {
                const { data } = await axios.post(rutaDePedido, newPedido)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiospost(ruta);
        navigate("/pedidos");
    }
    return (
        <>
            <form onSubmit={agregarPedidoApi}>
                Fecha: <input type="text" ref={fechaP} /><br />
                Pagado: <input type="number" ref={pagadoP} /> <br />
                Enviado: <input type="number" ref={enviadoP} /> <br />
                Entregado: <input type="number" ref={entregadoP} /> <br />
                <button type="submit">Crear </button>
            </form>
        </>
    )
}