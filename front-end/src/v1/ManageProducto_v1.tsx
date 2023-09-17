import React, { useState, useEffect, useRef } from 'react';
import { Link, Route, BrowserRouter, Routes, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import Productos_v1 from './Producto_v1';

interface IState { producto?: Supermercado.Producto }

declare module Supermercado {

    export interface Producto {
        idproducto: number;
        nombre: string;
        preciounidad: number;
        stock: number;
    }

}

export default function ManageProducto_v1() {
    let idP: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);
    let nombreP: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);
    let precioP: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);
    let stockP: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);

    let navigate = useNavigate();
    const [stProducto, setStProducto] = useState<IState>({});
    const { id } = useParams();

    useEffect(() => {
        const getProducto = async (idproducto: string | undefined) => {
            let rutaDeProducto = "http://localhost:8080/api/v1/productos/";
            let { data } = await axios.get(rutaDeProducto + idproducto);
            let producto: Supermercado.Producto = data;
            console.log(producto);
            setStProducto({ producto });
        }
        getProducto(id);
    },
        [id]
    )
    return (
        <section style={{background: 'blue'}}>
            <div className="container py-5">
                <div className="row">
                    <div className="col-md-12 col-lg-4 mb-4 mb-lg-0">
                        <div className="card">
                            <div className="d-flex justify-content-between p-3">
                                <p className="lead mb-0">Today's Combo Offer</p>
                                    <div className="bg-info rounded-circle d-flex align-items-center justify-content-center shadow-1-strong"
                                    style={{width: "35px", height: "35px"}}>
                                        <p className="text-white mb-0 small">x4</p>
                                    </div>
                                </div>
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/Horizontal/E-commerce/Products/4.webp"
                            className="card-img-top" alt="Laptop" />
                            <div className="card-body">
                                <div className="d-flex justify-content-between">
                                    <p className="small"><a href="#!" className="text-muted">Laptops</a></p>
                                    <p className="small text-danger"><s>$1099</s></p>
                                </div>

                            <div className="d-flex justify-content-between mb-3">
                                <h5 className="mb-0">HP Notebook</h5>
                                <h5 className="text-dark mb-0">$999</h5>
                            </div>

                            <div className="d-flex justify-content-between mb-2">
                                <p className="text-muted mb-0">Available: <span className="fw-bold">6</span></p>
                                <div className="ms-auto text-warning">
                                    <i className="fa fa-star"></i>
                                    <i className="fa fa-star"></i>
                                    <i className="fa fa-star"></i>
                                    <i className="fa fa-star"></i>
                                    <i className="fa fa-star"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
            <div>
                <h3>Product details: </h3>
                <h4>Id: {stProducto.producto?.idproducto} || Name: {stProducto.producto?.nombre} || Unit price: {stProducto.producto?.preciounidad} || Stock: {stProducto.producto?.stock}</h4>
            </div>
        </section>
    );
}