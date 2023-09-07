import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
interface IProps { }
interface IState { productos?: Array<Supermercado.Producto>; }

declare module Supermercado {

    export interface Producto {
        idproducto: number;
        nombre: string;
        preciounidad: number;
        stock: number;
    }

}

export const Productos_v2 = () => {
    const [productos, setProducto] = useState<IState>();
    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;
    const rutaProductos: string = rutaBase + "/api/v2/productos";

    useEffect(() => {
        const getProducto = async () => {
            let token: string = localStorage.getItem("token") as string;
            let ruta = rutaProductos;
            console.log(ruta);
            const headers = {
                headers: { Authorization: token }
            };
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setProducto({ productos: respuesta.data });
        }
        getProducto();
    }, []);

    return (
        <>
            <h3>Productos:</h3>
            <ul>
                {
                    productos?.productos?.map((a: Supermercado.Producto) => {
                        return (
                            <Link to={{ pathname: "/api/v2/producto/" + a.idproducto }}>
                                <li>Id: {a.idproducto} || Nombre: {a.nombre} || Precio por unidad: {a.preciounidad} || Stock: {a.stock}</li>
                            </Link>
                        );
                    })
                }
            </ul>
            <br/>
        </>
    );
}
export default Productos_v2;