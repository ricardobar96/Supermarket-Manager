import React, { useState, useEffect, useRef } from 'react';
import { Link, Route, BrowserRouter, Routes, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import Pedido_v2 from './Pedido_v2';

interface IState { detalle?: Supermercado.Detallepedido }

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

export default function ManageDetalle_v2() {
    let navigate = useNavigate();
    const [stDetalle, setStDetalle] = useState<IState>({});
    const { id } = useParams();

    useEffect(() => {
        const getDetalle = async (iddetalle: string | undefined) => {
            let rutaDeDetalle = "http://localhost:8080/api/v2/detallepedidos/";
            let { data } = await axios.get(rutaDeDetalle + iddetalle);
            let detalle: Supermercado.Detallepedido = data;
            console.log(detalle);
            setStDetalle({ detalle });
        }
        getDetalle(id);
    },
        [id]
    )
    return (
        <>
            <div>
                <h3>Datos del Detlalepedido: </h3>
                <h4>Id: {stDetalle.detalle?.iddetallepedido} || Cantidad: {stDetalle.detalle?.cantidad} || Precio por unidad: {stDetalle.detalle?.preciounidad}</h4>
                <h5>Producto:</h5>
                {stDetalle.detalle?.producto.idproducto}
                <h4>Id: {stDetalle.detalle?.producto.idproducto} || Nombre: {stDetalle.detalle?.producto.nombre}</h4>
            </div>
        </>
    );
}