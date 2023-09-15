import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
interface IProps { }
interface IState { pedidos?: Array<Supermercado.Pedido>; }

declare module Supermercado {

    export interface Pedido {
        idpedido: number;
        fecha: string;
        pagado: number;
        enviado: number;
        entregado: number;
        direccion_entrega: string;
    }

    export interface Cliente {
        nombre: string;
        password: string;
    }

}

export const Pedidos_v2 = () => {
    const [pedidos, setPedido] = useState<IState>();
    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;
    const rutaPedidos: string = rutaBase + "/api/v2/pedidos";

    useEffect(() => {
        const getPedido = async () => {
            let token: string = localStorage.getItem("token") as string;
            let ruta = rutaPedidos;
            console.log(ruta);
            const headers = {
                headers: { Authorization: token }
            };
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setPedido({ pedidos: respuesta.data });
        }
        getPedido();
    }, []);

    return (
        <>
            <h3>Pedidos:</h3>
            <ul>
                {
                    pedidos?.pedidos?.map((a: Supermercado.Pedido) => {
                        return (
                            <Link to={{ pathname: "/api/v2/pedido/" + a.idpedido }}>
                                <li>Id: {a.idpedido} || Date: {a.fecha} || Paid: {a.pagado} || Sent: {a.enviado} || Delivered: {a.entregado}</li>
                            </Link>
                        );
                    })
                }
            </ul>
            <br/>
            <Link to={{pathname:"/api/v2/crearPedido"}}> Create order </Link> &nbsp;
        </>
    );
}
export default Pedidos_v2;