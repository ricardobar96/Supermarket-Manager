import React, { useState, useEffect, useRef } from 'react';
import { Link, Route, BrowserRouter, Routes, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import Pedido_v2 from './Pedido_v2';

interface IState { pedido?: Supermercado.Pedido }

declare module Supermercado {

    export interface Pedido {
        idpedido: number;
        fecha: string;
        pagado: number;
        enviado: number;
        entregado: number;
        direccion_entrega: string;
    }

}

export default function ManagePedido_v2() {
    let idP: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);
    let fechaP: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);
    let pagadoP: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);
    let enviadoP: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);
    let entregadoP: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);

    let navigate = useNavigate();
    const [stPedido, setStPedido] = useState<IState>({});
    const { id } = useParams();

    useEffect(() => {
        const getPedido = async (idpedido: string | undefined) => {
            let rutaDePedido = "http://localhost:8080/api/v2/pedidos/";
            let { data } = await axios.get(rutaDePedido + idpedido);
            let pedido: Supermercado.Pedido = data;
            console.log(pedido);
            setStPedido({ pedido });
        }
        getPedido(id);
    },
        [id]
    )
    return (
        <>
            <div>
                <h3>Datos del Pedido: </h3>
                <h4>Id: {stPedido.pedido?.idpedido} || Fecha: {stPedido.pedido?.fecha} || Pagado: {stPedido.pedido?.pagado} || Enviado: {stPedido.pedido?.enviado} || Entregado: {stPedido.pedido?.entregado}</h4>
            </div>
        </>
    );
}