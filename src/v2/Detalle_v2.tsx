import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
interface IProps { }
interface IState { detalles?: Array<Supermercado.Detallepedido>; }

declare module Supermercado {

    export interface Pedido {
        idpedido: number;
        fecha: string;
        pagado: number;
        enviado: number;
        entregado: number;
        direccion_entrega: string;
    }

    export interface Detallepedido {
        iddetallepedido: number;
        cantidad: number;
        preciounidad: number;
        producto: Producto;
    }

    export interface Producto {
        idproducto: number;
        nombre: string;
        preciounidad: number;
        stock: number;
    }

}

export const Detalles_v2 = () => {
    const [detalles, setDetalle] = useState<IState>();
    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;
    const rutaDetalles: string = rutaBase + "/api/v2/detallepedidos";

    useEffect(() => {
        const getDetalle = async () => {
            let token: string = localStorage.getItem("token") as string;
            let ruta = rutaDetalles;
            console.log(ruta);
            const headers = {
                headers: { Authorization: token }
            };
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setDetalle({ detalles: respuesta.data });
        }
        getDetalle();
    }, []);

    return (
        <>
            <h3>Detalles de los pedidos:</h3>
            <ul>
                {
                    detalles?.detalles?.map((a: Supermercado.Detallepedido) => {
                        return (
                            <Link to={{ pathname: "/api/v2/detallepedido/" + a.iddetallepedido }}>
                                <li>Id: {a.iddetallepedido} || Cantidad: {a.cantidad} || Precio por unidad: {a.preciounidad}</li>
                            </Link>
                        );
                    })
                }
            </ul>
            <br/>
            <Link to={{pathname:"/api/v2/crearDetalle"}}> Hacer Detallepedido </Link> &nbsp;
        </>
    );
}
export default Detalles_v2;