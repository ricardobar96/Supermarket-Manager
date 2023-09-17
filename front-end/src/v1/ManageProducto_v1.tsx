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
        <section>
            <div className="container py-2">
                <div className="row justify-content-center">
                    <div className="col-md-12 col-lg-4 mb-4 mb-lg-0">
                        <div className="card " style={{background: "#d6eaf8"}}>
                            <div className="d-flex justify-content-between p-3">
                                <p className="lead mb-0">Product details</p>
                                    <div className="bg-info rounded-circle d-flex align-items-center justify-content-center shadow-1-strong"
                                    style={{width: "35px", height: "35px"}}>
                                        <p className="text-white mb-0 small">x{stProducto.producto?.stock}</p>
                                    </div>
                                </div>
                            <img src="https://www.kesargrocery.com/images/P/Wonderful%20Pistachios%2C%20No%20Shell%20Nuts%2C%20Variety%20Pack%20%28Pack%20of%209%29.webp"
                            className="card-img-top" style={{height: "300px"}} alt="Laptop" />
                            <div className="card-body">
                                <div className="d-flex justify-content-between">
                                    <p className="small"><a href="/api/v1/productos" className="text-danger text-decoration-none">Return to catalog</a></p>
                                    <p className="small text-danger"><s>{stProducto.producto?.preciounidad} €</s></p>
                                </div>

                            <div className="d-flex justify-content-between mb-3">
                                <h5 className="mb-0">{stProducto.producto?.nombre}</h5>
                                <h5 className="text-dark mb-0">{stProducto.producto?.preciounidad} €</h5>
                            </div>

                            <div className="d-flex justify-content-between mb-2">
                                <p className="text-muted mb-0">Available: <span className="fw-bold">{stProducto.producto?.stock}</span></p>
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
    </section>
    );
}